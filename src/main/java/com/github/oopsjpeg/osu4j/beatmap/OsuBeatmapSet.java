package com.github.oopsjpeg.osu4j.beatmap;

import java.util.Collections;
import java.util.List;

public class OsuBeatmapSet {
    private List<OsuBeatmap> beatmaps;

    public OsuBeatmapSet(List<OsuBeatmap> beatmaps) {
        this.beatmaps = Collections.unmodifiableList(beatmaps);
    }

    public List<OsuBeatmap> getBeatmaps() {
        return beatmaps;
    }
}
