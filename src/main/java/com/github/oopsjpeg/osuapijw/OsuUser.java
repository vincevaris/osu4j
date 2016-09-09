package com.github.oopsjpeg.osuapijw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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
		this.mode = mode;
	}
	
	public OsuUser withTopScores(int limit) throws IOException {
		topScores = osu.getTopScores(userId, limit, mode);
		return this;
	}
	
	public OsuUser withRecentScores(int limit) throws IOException {
		recentScores = osu.getRecentScores(userId, limit, mode);
		return this;
	}
	
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
	public OsuScore getTopScore(int index){ return topScores.get(index); }
	public List<OsuScore> getTopScores(){ return topScores; }
	public OsuScore getRecentScore(int index){ return recentScores.get(index); }
	public List<OsuScore> getRecentScores(){ return recentScores; }
	public String getURL(){ return "https://osu.ppy.sh/u/" + userId; }
	
}
