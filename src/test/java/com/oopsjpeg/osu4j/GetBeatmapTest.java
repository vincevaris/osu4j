package com.oopsjpeg.osu4j;

import com.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.oopsjpeg.osu4j.backend.Osu;
import com.oopsjpeg.osu4j.exception.OsuAPIException;

import java.net.MalformedURLException;

public class GetBeatmapTest {
	private static final int BEATMAP_ID = 131891;

	public static void main(String[] args) throws OsuAPIException, MalformedURLException {
		// Create a new Osu object with an API key
		String KEY = args[0];
		Osu osu = Osu.getAPI(KEY);

		// Get the beatmap
		OsuBeatmap beatmap = osu.beatmaps.getAsQuery(new EndpointBeatmaps.ArgumentsBuilder()
				.setBeatmapID(BEATMAP_ID).build())
				.resolve().get(0);

		// Print information
		System.out.println(beatmap.toString());
		System.out.println(beatmap.getURL());
		System.out.println("Creator: " + beatmap.getCreatorName());
		System.out.println("BPM: " + beatmap.getBPM());
		System.out.println("Difficulty: " + beatmap.getDifficulty());
	}
}
