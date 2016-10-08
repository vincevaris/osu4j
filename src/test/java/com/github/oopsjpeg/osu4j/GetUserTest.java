package com.github.oopsjpeg.osu4j;

import java.io.IOException;
import java.util.List;

import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

public class GetUserTest {

    private static final String USER = "oopsjpeg";
    private static final OsuMode MODE = OsuMode.STANDARD;
    private static final int TOP_SCORE_LIMIT = 3;

    public static void main(String[] args) throws OsuAPIException, IOException {
        // Create a new Osu object with an API key
        String KEY = args[0];
        Osu osu = Osu.getAPI(KEY);

        // Get the user
        OsuUser user = osu.users.query(new EndpointUsers.ArgumentsBuilder(USER).setMode(MODE).build());

        // Print basic information
        System.out.println(MODE.getName() + " Information for " + user.getUsername());
        System.out.println(user.getURL());
        System.out.println("Rank: #" + user.getRank());
        System.out.println("Performance Points: " + user.getPP() + "pp");
        System.out.println("Total Score: " + user.getTotalScore());

        LazilyLoaded<List<OsuScore>> userScores = user.getTopScores(TOP_SCORE_LIMIT);
        List<OsuScore> topScores = userScores.get();
        // Print top scores
        int i = 0;
        for (OsuScore score : topScores) {
            OsuBeatmap beatmap = score.getBeatmap().get();
            System.out.println("Top score " + (i + 1) + ": " + beatmap.getArtist() + " - " + beatmap.getTitle());
            ++i;
        }
    }
}
