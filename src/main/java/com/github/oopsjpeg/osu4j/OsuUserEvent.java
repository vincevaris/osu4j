package com.github.oopsjpeg.osu4j;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointBeatmapSet;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmapSet;

public class OsuUserEvent {

    private String displayHtml;
    private int beatmapID;
    private int beatmapSetID;
    private LazilyLoaded<OsuBeatmapSet> beatmap;
    private String date;
    private int epicFactor;

    public OsuUserEvent(Osu osu, JSONObject json) {
        displayHtml = json.getString("display_html");
        beatmapID = Integer.parseInt(json.getString("beatmap_id"));
        beatmapSetID = Integer.parseInt(json.getString("beatmapset_id"));
        beatmap = osu.beatmapSets.getAsQuery(new EndpointBeatmapSet.Arguments(beatmapSetID)).asLazilyLoaded();
        date = json.getString("date");
        epicFactor = Integer.parseInt(json.getString("epicfactor"));
    }

    public String getDisplayHTML() {
        return displayHtml;
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public int getBeatmapSetID() {
        return beatmapSetID;
    }

    public LazilyLoaded<OsuBeatmapSet> getBeatmapSet() {
        return beatmap;
    }

    public String getDate() {
        return date;
    }

    public int getEpicFactor() {
        return epicFactor;
    }

}
