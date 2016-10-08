package com.github.oopsjpeg.osu4j.backend;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.abstractbackend.ArgumentBuilder;
import com.github.oopsjpeg.osu4j.abstractbackend.Endpoint;
import com.github.oopsjpeg.osu4j.backend.EndpointMatches.Arguments;
import com.github.oopsjpeg.osu4j.backend.Osu.APIAccess;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;
import com.github.oopsjpeg.osu4j.match.OsuMatch;

public class EndpointMatches implements Endpoint<Arguments, OsuMatch> {
    public static class Arguments {
        private Map<String, String> arguments;

        public Arguments(ArgumentsBuilder builder) {
            Objects.requireNonNull(builder);
            Map<String, String> arguments = new HashMap<>();
            arguments.put("mp", Integer.toString(builder.matchID));
            this.arguments = Collections.unmodifiableMap(arguments);
        }

        private Map<String, String> asURLArguments() {
            return arguments;
        }
    }

    public static class ArgumentsBuilder implements ArgumentBuilder<Arguments> {
        private int matchID;

        public ArgumentsBuilder(int matchID) {
            this.matchID = matchID;
        }

        public ArgumentsBuilder setMatch(int matchID) {
            this.matchID = matchID;
            return this;
        }

        @Override
        public Arguments build() {
            return new Arguments(this);
        }
    }

    private static String API_ENDPOINT = "/api/get_match";

    private final APIAccess api;

    public EndpointMatches(APIAccess api) {
        this.api = api;
    }

    @Override
    public OsuMatch query(Arguments arguments) throws OsuAPIException {
        JSONArray beatmapUser = api.getRESTfulArray(API_ENDPOINT, arguments.asURLArguments());
        JSONObject userObject = beatmapUser.getJSONObject(0);
        return new OsuMatch(api.getAPI(), userObject);
    }
}
