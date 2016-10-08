package com.github.oopsjpeg.osu4j.match;

import java.io.IOException;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.GameMods;
import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.exception.OsuRateLimitException;

public class OsuMatchGame {
    private int gameID;
    private String startTime;
    private String endTime;
    private int beatmapID;
    private LazilyLoaded<OsuBeatmap> beatmap;
    private OsuMode playMode;
    private int matchType;
    private ScoringType scoringType;
    private TeamType teamType;
    private GameMods mods;
    // TODO: add scores

    public OsuMatchGame(Osu osu, JSONObject json) {
        gameID = Integer.parseInt(json.getString("game_id"));
        startTime = json.getString("start_time");
        endTime = json.getString("end_time");
        beatmapID = Integer.parseInt(json.getString("beatmap_id"));
        beatmap = osu.beatmaps.getAsQuery(new EndpointBeatmaps.ArgumentsBuilder().setBeatmapID(beatmapID).build())
                .asLazilyLoaded().map(list -> list.get(0));
        playMode = OsuMode.getByID(Integer.parseInt(json.getString("play_mode")));
        matchType = Integer.parseInt(json.getString("match_type"));
        scoringType = ScoringType.getById(Integer.parseInt(json.getString("scoring_type")));
        teamType = TeamType.getById(Integer.parseInt(json.getString("team_type")));
        mods = GameMods.parse(Integer.parseInt(json.getString("mods")));
    }

    public int getGameID() {
        return gameID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public LazilyLoaded<OsuBeatmap> getBeatmap() throws IOException, OsuRateLimitException {
        return beatmap;
    }

    public OsuMode getMode() {
        return playMode;
    }

    public int getMatchTypeID() {
        return matchType;
    }

    public ScoringType getScoringType() {
        return scoringType;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public GameMods getMods() {
        return mods;
    }
}
