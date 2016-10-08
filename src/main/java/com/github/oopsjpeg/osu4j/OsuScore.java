package com.github.oopsjpeg.osu4j;

import java.time.ZonedDateTime;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.util.Utility;

public class OsuScore {
    private int beatmapID;
    private LazilyLoaded<OsuBeatmap> beatmap;
    private int score;
    private int maxCombo;
    private int count300;
    private int count100;
    private int count50;
    private int countMiss;
    private int countKatu;
    private int countGeki;
    private boolean perfect;
    private GameMods enabledMods;
    private int userID;
    private LazilyLoaded<OsuUser> user;
    private ZonedDateTime date;
    private String rank;
    public double pp;

    public OsuScore(Osu osu, JSONObject json) {
        beatmapID = Integer.parseInt(json.getString("beatmap_id"));
        beatmap = osu.beatmaps.getAsQuery(new EndpointBeatmaps.ArgumentsBuilder().setBeatmapID(beatmapID).build())
                .asLazilyLoaded().map(list -> list.get(0));
        score = Integer.parseInt(json.getString("score"));
        maxCombo = Integer.parseInt(json.getString("maxcombo"));
        count300 = Integer.parseInt(json.getString("count300"));
        count100 = Integer.parseInt(json.getString("count100"));
        count50 = Integer.parseInt(json.getString("count50"));
        countMiss = Integer.parseInt(json.getString("countmiss"));
        countKatu = Integer.parseInt(json.getString("countkatu"));
        countGeki = Integer.parseInt(json.getString("countgeki"));
        perfect = Integer.parseInt(json.getString("perfect")) == 1;
        enabledMods = GameMods.parse(Integer.parseInt(json.getString("enabled_mods")));
        userID = Integer.parseInt(json.getString("user_id"));
        user = osu.users.getAsQuery(new EndpointUsers.ArgumentsBuilder(userID).build()).asLazilyLoaded();
        date = Utility.parseDate(json.getString("date"));
        rank = json.getString("rank");
        pp = Double.parseDouble(json.getString("pp"));
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public LazilyLoaded<OsuBeatmap> getBeatmap() {
        return beatmap;
    }

    public int getScore() {
        return score;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public int getCount300() {
        return count300;
    }

    public int getCount100() {
        return count100;
    }

    public int getCount50() {
        return count50;
    }

    public int getCountMiss() {
        return countMiss;
    }

    public int getCountKatu() {
        return countKatu;
    }

    public int getCountGeki() {
        return countGeki;
    }

    public int getCountTotal() {
        return countMiss + count50 + count100 + count300;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public GameMods getEnabledModIDs() {
        return enabledMods;
    }

    public int getUserID() {
        return userID;
    }

    public LazilyLoaded<OsuUser> getUser() {
        return user;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getRank() {
        return rank;
    }

    public long getPP() {
        return Math.round(pp);
    }

    public double getPPRaw() {
        return pp;
    }

}
