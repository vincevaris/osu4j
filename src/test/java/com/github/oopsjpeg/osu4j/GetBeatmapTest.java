package com.github.oopsjpeg.osu4j;

import java.io.IOException;

import com.github.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

public class GetBeatmapTest {

    private static final int BEATMAP_ID = 131891;
    private static final OsuMode MODE = OsuMode.STANDARD;

    public static void main(String[] args) throws OsuAPIException, IOException {
        // Create a new Osu object with an API key
        String KEY = args[0];
        Osu osu = Osu.getAPI(KEY);

        // Get the beatmap
        OsuBeatmap beatmap = osu.beatmaps
                .getAsQuery(new EndpointBeatmaps.ArgumentsBuilder().setBeatmapID(BEATMAP_ID).setMode(MODE).build())
                .resolve().get(0);

        // Print information
        System.out.println(MODE.getName() + " Information for " + beatmap.getArtist() + " - " + beatmap.getTitle());
        System.out.println(beatmap.getURL());
        System.out.println("Creator: " + beatmap.getCreatorName());
        System.out.println("BPM: " + beatmap.getBPM());
        System.out.println("Difficulty Rating: " + beatmap.getDifficultyRating());

    }
}
