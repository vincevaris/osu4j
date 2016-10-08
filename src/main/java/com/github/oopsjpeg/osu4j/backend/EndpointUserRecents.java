package com.github.oopsjpeg.osu4j.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.OsuScore;
import com.github.oopsjpeg.osu4j.abstractbackend.ArgumentBuilder;
import com.github.oopsjpeg.osu4j.abstractbackend.Endpoint;
import com.github.oopsjpeg.osu4j.backend.EndpointUserRecents.Arguments;
import com.github.oopsjpeg.osu4j.backend.Osu.APIAccess;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

public class EndpointUserRecents implements Endpoint<Arguments, List<OsuScore>> {
    public static class Arguments {
        private Map<String, String> arguments;

        public Arguments(ArgumentsBuilder builder) {
            Objects.requireNonNull(builder);
            Map<String, String> arguments = new HashMap<>();
            arguments.put("u", builder.user.getUserID());
            arguments.put("type", builder.user.isTextualID() ? "string" : "id");
            builder.mode.ifPresent(mode -> arguments.put("m", Integer.toString(mode.getID())));
            builder.limit.ifPresent(l -> arguments.put("limit", Integer.toString(l)));
            this.arguments = Collections.unmodifiableMap(arguments);
        }

        private Map<String, String> asURLArguments() {
            return arguments;
        }
    }

    public static class ArgumentsBuilder implements ArgumentBuilder<Arguments> {
        private UserInfo user;
        private Optional<OsuMode> mode = Optional.empty();
        private OptionalInt limit = OptionalInt.empty();

        public ArgumentsBuilder(String userName) {
            setUserName(userName);
        }

        public ArgumentsBuilder(int userID) {
            setUserID(userID);
        }

        public ArgumentsBuilder setUserName(String userName) {
            this.user = UserInfo.create(userName);
            return this;
        }

        public ArgumentsBuilder setUserID(int userID) {
            this.user = UserInfo.create(userID);
            return this;
        }

        public ArgumentsBuilder setMode(OsuMode mode) {
            this.mode = Optional.of(mode);
            return this;
        }

        public ArgumentsBuilder unsetMode() {
            this.mode = Optional.empty();
            return this;
        }

        public ArgumentsBuilder setLimit(int limit) {
            this.limit = OptionalInt.of(limit);
            return this;
        }

        public ArgumentsBuilder unsetLimit() {
            this.limit = OptionalInt.empty();
            return this;
        }

        @Override
        public Arguments build() {
            return new Arguments(this);
        }
    }

    private static String API_ENDPOINT = "/api/get_user_recent";

    private final APIAccess api;

    public EndpointUserRecents(APIAccess api) {
        this.api = api;
    }

    @Override
    public List<OsuScore> query(Arguments arguments) throws OsuAPIException {
        JSONArray scoresJson = api.getRESTfulArray(API_ENDPOINT, arguments.asURLArguments());
        List<OsuScore> resultSet = new ArrayList<>();
        for (int i = 0; i < scoresJson.length(); ++i) {
            JSONObject scoreJson = scoresJson.getJSONObject(i);
            resultSet.add(new OsuScore(api.getAPI(), scoreJson));
        }
        return resultSet;
    }
}
