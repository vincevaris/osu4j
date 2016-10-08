package com.github.oopsjpeg.osu4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointUserBests;
import com.github.oopsjpeg.osu4j.backend.EndpointUserRecents;
import com.github.oopsjpeg.osu4j.backend.Osu;

public class OsuUser {
    private Osu osu;
    private int userID;
    private String username;
    private long count300;
    private long count100;
    private long count50;
    private long playCount;
    private long rankedScore;
    private long totalScore;
    private double pp;
    private long rank;
    private double accuracy;
    private long countRankSS;
    private long countRankS;
    private long countRankA;
    private String country;
    private long countryRank;
    private OsuMode mode;
    private List<OsuUserEvent> events = new ArrayList<>();
    private int topScoresSize = -1;
    private LazilyLoaded<List<OsuScore>> topScores;
    private int recentScoresSize = -1;
    private LazilyLoaded<List<OsuScore>> recentScores;

    public OsuUser(Osu osu, JSONObject json, OsuMode mode) {
        this.osu = osu;
        this.mode = mode;
        userID = Integer.parseInt(json.getString("user_id"));
        username = json.getString("username");
        count300 = Long.parseLong(json.getString("count300"));
        count100 = Long.parseLong(json.getString("count100"));
        count50 = Long.parseLong(json.getString("count50"));
        playCount = Long.parseLong(json.getString("playcount"));
        rankedScore = Long.parseLong(json.getString("ranked_score"));
        totalScore = Long.parseLong(json.getString("total_score"));
        pp = Double.parseDouble(json.getString("pp_raw"));
        rank = Long.parseLong(json.getString("pp_rank"));
        accuracy = Double.parseDouble(json.getString("accuracy"));
        countRankSS = Long.parseLong(json.getString("count_rank_ss"));
        countRankS = Long.parseLong(json.getString("count_rank_s"));
        countRankA = Long.parseLong(json.getString("count_rank_a"));
        country = json.getString("country");
        countryRank = Long.parseLong(json.getString("pp_country_rank"));
        JSONArray eventsArr = json.getJSONArray("events");
        for (int i = 0; i < eventsArr.length(); i++) {
            events.add(new OsuUserEvent(osu, eventsArr.getJSONObject(i)));
        }
    }

    public LazilyLoaded<List<OsuScore>> getTopScores(int limit) {
        if (limit <= topScoresSize) {
            return topScores.map(list -> list.subList(0, limit));
        }
        topScoresSize = limit;
        return topScores = osu.userBests
                .getAsQuery(new EndpointUserBests.ArgumentsBuilder(userID).setLimit(limit).build()).asLazilyLoaded();
    }

    public LazilyLoaded<List<OsuScore>> withRecentScores(int limit) {
        if (limit <= recentScoresSize) {
            return recentScores.map(list -> list.subList(0, limit));
        }
        recentScoresSize = limit;
        return recentScores = osu.userRecents
                .getAsQuery(new EndpointUserRecents.ArgumentsBuilder(userID).setLimit(limit).build()).asLazilyLoaded();
    }

    public OsuUser withEvents() throws JSONException, IOException {
        events.clear();
        return this;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public long getCount300() {
        return count300;
    }

    public long getCount100() {
        return count100;
    }

    public long getCount50() {
        return count50;
    }

    public long getCountTotal() {
        return count300 + count100 + count50;
    }

    public long getPlayCount() {
        return playCount;
    }

    public long getRankedScore() {
        return rankedScore;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public double getPPRaw() {
        return pp;
    }

    public long getPP() {
        return Math.round(pp);
    }

    public long getRank() {
        return rank;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public long getCountRankSS() {
        return countRankSS;
    }

    public long getCountRankS() {
        return countRankS;
    }

    public long getCountRankA() {
        return countRankA;
    }

    public String getCountry() {
        return country;
    }

    public long getCountryRank() {
        return countryRank;
    }

    public OsuMode getMode() {
        return mode;
    }

    public int getModeID() {
        return mode.getID();
    }

    public OsuUserEvent getEvent(int index) {
        return events.get(index);
    }

    public List<OsuUserEvent> getEvents() {
        return events;
    }

    public URL getURL() throws MalformedURLException {
        return new URL("https://osu.ppy.sh/u/" + userID);
    }

}
