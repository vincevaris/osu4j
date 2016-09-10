package com.github.oopsjpeg.osu4j;

import java.io.IOException;

import com.github.oopsjpeg.osu4j.Gamemode;
import com.github.oopsjpeg.osu4j.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class GetBeatmapTest {
	
	private static final int BEATMAP_ID = 131891;
	private static final String KEY = "Your osu! API key"; // Replace this with your API key to test.
	private static final Gamemode MODE = Gamemode.STANDARD;
	
	public static void main(String[] args) throws OsuRateLimitException, IOException {
		// Create a new Osu object with an API key
		Osu osu = new Osu(KEY);
		
		// Get the beatmap
		OsuBeatmap beatmap = osu.getBeatmap(BEATMAP_ID, MODE);
		
		// Print information
		System.out.println(MODE.getName() + " Information for " + beatmap.getArtist() + " - " + beatmap.getTitle());
		System.out.println(beatmap.getURL());
		System.out.println("Creator: " + beatmap.getCreator());
		System.out.println("BPM: " + beatmap.getBPM());
		System.out.println("Difficulty Rating: " + beatmap.getDifficultyRating());
		
	}
}

