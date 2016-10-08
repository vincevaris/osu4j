package com.github.oopsjpeg.osu4j.backend;

import java.util.List;

import com.github.oopsjpeg.osu4j.abstractbackend.MappingEndpoint;
import com.github.oopsjpeg.osu4j.backend.EndpointBeatmapSet.Arguments;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmapSet;

public class EndpointBeatmapSet extends MappingEndpoint<Arguments, OsuBeatmapSet> {
    public static class Arguments {
        private EndpointBeatmaps.Arguments actualArguments;

        public Arguments(int setID) {
            this.actualArguments = new EndpointBeatmaps.ArgumentsBuilder().setBeatmapID(setID).build();
        }
    }

    private static EndpointBeatmaps.Arguments transformArguments(Arguments arguments) {
        return arguments.actualArguments;
    }

    private static OsuBeatmapSet transformResult(List<OsuBeatmap> beatmaps) {
        return new OsuBeatmapSet(beatmaps);
    }

    public EndpointBeatmapSet(EndpointBeatmaps beatmaps) {
        super(beatmaps, EndpointBeatmapSet::transformArguments, EndpointBeatmapSet::transformResult);
    }

}
