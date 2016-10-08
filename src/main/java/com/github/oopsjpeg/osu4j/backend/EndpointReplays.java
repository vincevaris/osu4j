package com.github.oopsjpeg.osu4j.backend;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.OsuReplay;
import com.github.oopsjpeg.osu4j.abstractbackend.ArgumentBuilder;
import com.github.oopsjpeg.osu4j.abstractbackend.Endpoint;
import com.github.oopsjpeg.osu4j.backend.EndpointReplays.Arguments;
import com.github.oopsjpeg.osu4j.backend.Osu.APIAccess;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

public class EndpointReplays implements Endpoint<Arguments, OsuReplay> {
    public static class Arguments {
        private Map<String, String> arguments;

        public Arguments(ArgumentsBuilder builder) {
            Objects.requireNonNull(builder);
            Map<String, String> arguments = new HashMap<>();
            arguments.put("m", Integer.toString(builder.mode.getID()));
            arguments.put("b", Integer.toString(builder.beatmapID));
            arguments.put("u", Integer.toString(builder.userID));
            this.arguments = Collections.unmodifiableMap(arguments);
        }

        private Map<String, String> asURLArguments() {
            return arguments;
        }
    }

    public static class ArgumentsBuilder implements ArgumentBuilder<Arguments> {
        private OsuMode mode;
        private int beatmapID;
        private int userID;

        public ArgumentsBuilder(OsuMode mode, int beatmapID, int userID) {
            setMode(mode).setBeatmapID(beatmapID).setUserID(userID);
        }

        public ArgumentsBuilder setMode(OsuMode mode) {
            this.mode = Objects.requireNonNull(mode);
            return this;
        }

        public ArgumentsBuilder setBeatmapID(int mapID) {
            this.beatmapID = mapID;
            return this;
        }

        public ArgumentsBuilder setUserID(int userID) {
            this.userID = userID;
            return this;
        }

        @Override
        public Arguments build() {
            return new Arguments(this);
        }
    }

    private static String API_ENDPOINT = "/api/get_replay";

    private final APIAccess api;

    public EndpointReplays(APIAccess api) {
        this.api = api;
    }

    @Override
    public OsuReplay query(Arguments arguments) throws OsuAPIException {
        JSONObject replayJson = api.getRESTfulObject(API_ENDPOINT, arguments.asURLArguments());
        return new OsuReplay(api.getAPI(), replayJson);
    }
}
