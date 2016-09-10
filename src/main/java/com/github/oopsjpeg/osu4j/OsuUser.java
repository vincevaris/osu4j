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
	private int userId;
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
	private List<OsuUserEvent> events = new ArrayList<>();
	private List<OsuScore> topScores = new ArrayList<>();
	private List<OsuScore> recentScores = new ArrayList<>();
	
	public OsuUser(Osu osu, JSONObject obj, int mode) throws JSONException, IOException {
		this.osu = osu;
		userId = Integer.parseInt(obj.getString("user_id"));
		username = obj.getString("username");
		count300 = Long.parseLong(obj.getString("count300"));
		count100 = Long.parseLong(obj.getString("count100"));
		count50 = Long.parseLong(obj.getString("count50"));
		playCount = Long.parseLong(obj.getString("playcount"));
		rankedScore = Long.parseLong(obj.getString("ranked_score"));
		totalScore = Long.parseLong(obj.getString("total_score"));
		pp = Double.parseDouble(obj.getString("pp_raw"));
		rank = Long.parseLong(obj.getString("pp_rank"));
		accuracy = Double.parseDouble(obj.getString("accuracy"));
		countRankSS = Long.parseLong(obj.getString("count_rank_ss"));
		countRankS = Long.parseLong(obj.getString("count_rank_s"));
		countRankA = Long.parseLong(obj.getString("count_rank_a"));
		country = obj.getString("country");
		countryRank = Long.parseLong(obj.getString("pp_country_rank"));
		JSONArray eventsArr = obj.getJSONArray("events");
		for(int i = 0; i < eventsArr.length(); i++){
			events.add(new OsuUserEvent(osu, eventsArr.getJSONObject(i)));
		}
		this.mode = mode;
	}
	
	public OsuUser withTopScores(int limit) throws IOException, OsuRateLimitException {
		topScores = osu.getTopScores(userId, limit, mode);
		return this;
	}
	
	public OsuUser withRecentScores(int limit) throws IOException, OsuRateLimitException {
		recentScores = osu.getRecentScores(userId, limit, mode);
		return this;
	}
	
	public Osu getParent(){ return osu; }
	public int getUserId(){ return userId; }
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
	public Gamemode getMode(){ return Gamemode.getByID(mode); }
	public int getModeID(){ return mode; }
	public OsuUserEvent getEvent(int index){ return events.get(index); }
	public List<OsuUserEvent> getEvents(){ return events; }
	public OsuScore getTopScore(int index){ return topScores.get(index); }
	public List<OsuScore> getTopScores(){ return topScores; }
	public OsuScore getRecentScore(int index){ return recentScores.get(index); }
	public List<OsuScore> getRecentScores(){ return recentScores; }
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/u/" + userId); }
	
}
