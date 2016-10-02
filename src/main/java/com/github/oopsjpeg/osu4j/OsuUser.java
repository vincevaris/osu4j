package com.github.oopsjpeg.osu4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

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
	private int mode;
	private JSONArray eventsArr;
	private List<OsuUserEvent> events = new ArrayList<>();
	private List<OsuScore> topScores = new ArrayList<>();
	private List<OsuScore> recentScores = new ArrayList<>();
	
	public OsuUser(Osu osu, JSONObject json, int mode) throws JSONException, IOException {
		this.osu = osu;
		this.mode = mode;
		if(!json.isNull("user_id")) userID = Integer.parseInt(json.getString("user_id"));
		if(!json.isNull("username")) username = json.getString("username");
		if(!json.isNull("count300")) count300 = Long.parseLong(json.getString("count300"));
		if(!json.isNull("count100")) count100 = Long.parseLong(json.getString("count100"));
		if(!json.isNull("count50")) count50 = Long.parseLong(json.getString("count50"));
		if(!json.isNull("playcount")) playCount = Long.parseLong(json.getString("playcount"));
		if(!json.isNull("ranked_score")) rankedScore = Long.parseLong(json.getString("ranked_score"));
		if(!json.isNull("total_score")) totalScore = Long.parseLong(json.getString("total_score"));
		if(!json.isNull("pp_raw")) pp = Double.parseDouble(json.getString("pp_raw"));
		if(!json.isNull("pp_rank")) rank = Long.parseLong(json.getString("pp_rank"));
		if(!json.isNull("accuracy")) accuracy = Double.parseDouble(json.getString("accuracy"));
		if(!json.isNull("count_rank_ss")) countRankSS = Long.parseLong(json.getString("count_rank_ss"));
		if(!json.isNull("count_rank_s")) countRankS = Long.parseLong(json.getString("count_rank_s"));
		if(!json.isNull("count_rank_a")) countRankA = Long.parseLong(json.getString("count_rank_a"));
		if(!json.isNull("country")) country = json.getString("country");
		if(!json.isNull("pp_country_rank")) countryRank = Long.parseLong(json.getString("pp_country_rank"));
		if(!json.isNull("events")) eventsArr = json.getJSONArray("events");
	}
	
	public OsuUser withTopScores(int limit) throws IOException, OsuRateLimitException {
		topScores = osu.getTopScores(userID, limit, mode);
		return this;
	}
	
	public OsuUser withRecentScores(int limit) throws IOException, OsuRateLimitException {
		recentScores = osu.getRecentScores(userID, limit, mode);
		return this;
	}
	
	public OsuUser withEvents() throws JSONException, IOException {
		events.clear();
		for(int i = 0; i < eventsArr.length(); i++){
			events.add(new OsuUserEvent(osu, eventsArr.getJSONObject(i)));
		}
		return this;
	}
	
	public Osu getParent(){ return osu; }
	public int getUserID(){ return userID; }
	public String getUsername(){ return username; }
	public long getCount300(){ return count300; }
	public long getCount100(){ return count100; }
	public long getCount50(){ return count50; }
	public long getCountTotal(){ return count300 + count100 + count50; }
	public long getPlayCount(){ return playCount; }
	public long getRankedScore(){ return rankedScore; }
	public long getTotalScore(){ return totalScore; }
	public double getPPRaw(){ return pp; }
	public long getPP(){ return Math.round(pp); }
	public long getRank(){ return rank; }
	public double getAccuracy(){ return accuracy; }
	public long getCountRankSS(){ return countRankSS; }
	public long getCountRankS(){ return countRankS; }
	public long getCountRankA(){ return countRankA; }
	public String getCountry(){ return country; }
	public long getCountryRank(){ return countryRank; }
	public OsuGamemode getMode(){ return OsuGamemode.getByID(mode); }
	public int getModeID(){ return mode; }
	public OsuUserEvent getEvent(int index){ return events.get(index); }
	public List<OsuUserEvent> getEvents(){ return events; }
	public OsuScore getTopScore(int index){ return topScores.get(index); }
	public List<OsuScore> getTopScores(){ return topScores; }
	public OsuScore getRecentScore(int index){ return recentScores.get(index); }
	public List<OsuScore> getRecentScores(){ return recentScores; }
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/u/" + userID); }
	
}
