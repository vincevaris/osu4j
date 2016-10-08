package com.github.oopsjpeg.osu4j.backend;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.OsuUser;
import com.github.oopsjpeg.osu4j.abstractbackend.ArgumentBuilder;
import com.github.oopsjpeg.osu4j.abstractbackend.Endpoint;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers.Arguments;
import com.github.oopsjpeg.osu4j.backend.Osu.APIAccess;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

public class EndpointUsers implements Endpoint<Arguments, OsuUser> {
    public static class Arguments {
        private Map<String, String> arguments;
        private OsuMode mode;

        public Arguments(ArgumentsBuilder builder) {
            Objects.requireNonNull(builder);
            Map<String, String> arguments = new HashMap<>();
            arguments.put("u", builder.user.getUserID());
            arguments.put("type", builder.user.isTextualID() ? "string" : "id");
            builder.mode.ifPresent(mode -> arguments.put("m", Integer.toString(mode.getID())));
            builder.eventDays.ifPresent(l -> arguments.put("event_days", Integer.toString(l)));
            this.arguments = Collections.unmodifiableMap(arguments);
            this.mode = builder.mode.orElse(OsuMode.STANDARD);
        }

        private OsuMode getMode() {
            return mode;
        }

        private Map<String, String> asURLArguments() {
            return arguments;
        }
    }

    public static class ArgumentsBuilder implements ArgumentBuilder<Arguments> {
        private UserInfo user;
        private Optional<OsuMode> mode = Optional.empty();
        private OptionalInt eventDays = OptionalInt.empty();

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

        public ArgumentsBuilder setEventDays(int limit) {
            this.eventDays = OptionalInt.of(limit);
            return this;
        }

        public ArgumentsBuilder unsetEventDays() {
            this.eventDays = OptionalInt.empty();
            return this;
        }

        @Override
        public Arguments build() {
            return new Arguments(this);
        }
    }

    private static String API_ENDPOINT = "/api/get_user";

    private final APIAccess api;

    public EndpointUsers(APIAccess api) {
        this.api = api;
    }

    @Override
    public OsuUser query(Arguments arguments) throws OsuAPIException {
        JSONArray beatmapUser = api.getRESTfulArray(API_ENDPOINT, arguments.asURLArguments());
        JSONObject userObject = beatmapUser.getJSONObject(0);
        return new OsuUser(api.getAPI(), userObject, arguments.getMode());
    }
}
