package com.oopsjpeg.osu4j;

import com.google.gson.JsonObject;
import com.oopsjpeg.osu4j.abstractbackend.Lazy;
import com.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.oopsjpeg.osu4j.backend.EndpointUsers;
import com.oopsjpeg.osu4j.backend.Osu;
import com.oopsjpeg.osu4j.util.Utility;

import java.time.ZonedDateTime;

public class OsuScore extends OsuElement {
    private int beatmapID = -1;
    private Lazy<OsuBeatmap> beatmap;
    private int score;
    private int maxcombo;
    private int count300;
    private int count100;
    private int count50;
    private int countmiss;
    private int countkatu;
    private int countgeki;
    private boolean perfect;
    private GameMod[] enabledMods;
    private int userID = -1;
    private Lazy<OsuUser> user;
    private ZonedDateTime date;
    private String rank;
    private float pp;

    public OsuScore(Osu api, JsonObject obj) {
        super(api);
        if (obj.has("beatmap_id")) beatmapID = obj.get("beatmap_id").getAsInt();
        if (obj.has("score")) score = obj.get("score").getAsInt();
        if (obj.has("maxcombo")) maxcombo = obj.get("maxcombo").getAsInt();
        if (obj.has("count300")) count300 = obj.get("count300").getAsInt();
        if (obj.has("count100")) count100 = obj.get("count100").getAsInt();
        if (obj.has("count50")) count50 = obj.get("count50").getAsInt();
        if (obj.has("countmiss")) countmiss = obj.get("countmiss").getAsInt();
        if (obj.has("countkatu")) countkatu = obj.get("countkatu").getAsInt();
        if (obj.has("countgeki")) countgeki = obj.get("countgeki").getAsInt();
        if (obj.has("perfect")) perfect = obj.get("perfect").getAsInt() == 1;
        if (obj.has("enabled_mods")) enabledMods = GameMod.get(obj.get("enabled_mods").getAsLong());
        if (obj.has("user_id")) userID = obj.get("user_id").getAsInt();
        if (obj.has("date")) date = Utility.parseDate(obj.get("date").getAsString());
        if (obj.has("rank")) rank = obj.get("rank").getAsString();
        if (obj.has("pp")) pp = obj.get("pp").getAsFloat();
        if (beatmapID != -1) beatmap = getAPI().beatmaps.getAsQuery(new EndpointBeatmaps.ArgumentsBuilder()
                .setBeatmapID(beatmapID).build())
                .asLazilyLoaded().map(list -> list.get(0));
        if (userID != -1) user = getAPI().users.getAsQuery(new EndpointUsers.ArgumentsBuilder(userID).build())
                .asLazilyLoaded();
    }

    public OsuScore(OsuScore other) {
        super(other);
        this.beatmapID = other.beatmapID;
        this.beatmap = other.beatmap;
        this.score = other.score;
        this.maxcombo = other.maxcombo;
        this.count300 = other.count300;
        this.count100 = other.count100;
        this.count50 = other.count50;
        this.countmiss = other.countmiss;
        this.countkatu = other.countkatu;
        this.countgeki = other.countgeki;
        this.perfect = other.perfect;
        this.enabledMods = other.enabledMods;
        this.userID = other.userID;
        this.user = other.user;
        this.date = other.date;
        this.rank = other.rank;
        this.pp = other.pp;
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public Lazy<OsuBeatmap> getBeatmap() {
        return beatmap;
    }

    public int getScore() {
        return score;
    }

    public int getMaxCombo() {
        return maxcombo;
    }

    public int getHit300() {
        return count300;
    }

    public int getHit100() {
        return count100;
    }

    public int getHit50() {
        return count50;
    }

    public int getTotalHits() {
        return count300 + count100 + count50;
    }

    public int getMisses() {
        return countmiss;
    }

    public int getKatus() {
        return countkatu;
    }

    public int getGekis() {
        return countgeki;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public GameMod[] getEnabledMods() {
        return enabledMods;
    }

    public int getUserID() {
        return userID;
    }

    public Lazy<OsuUser> getUser() {
        return user;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getRank() {
        return rank;
    }

    public float getPp() {
        return pp;
    }

}
