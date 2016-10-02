package com.github.oopsjpeg.osu4j;

import java.io.IOException;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class OsuScore {
	private Osu osu;
	private int beatmapID;
	private int score;
	private int maxCombo;
	private int count300;
	private int count100;
	private int count50;
	private int countMiss;
	private int countKatu;
	private int countGeki;
	private int perfect;
	private int enabledMods;
	private int userID;
	private String date;
	private String rank;
	public double pp;
	
	public OsuScore(Osu osu, JSONObject json){
		this.osu = osu;
		if(!json.isNull("beatmap_id")) beatmapID = Integer.parseInt(json.getString("beatmap_id"));
		if(!json.isNull("score")) score = Integer.parseInt(json.getString("score"));
		if(!json.isNull("maxcombo")) maxCombo = Integer.parseInt(json.getString("maxcombo"));
		if(!json.isNull("count300")) count300 = Integer.parseInt(json.getString("count300"));
		if(!json.isNull("count100")) count100 = Integer.parseInt(json.getString("count100"));
		if(!json.isNull("count50")) count50 = Integer.parseInt(json.getString("count50"));
		if(!json.isNull("countmiss")) countMiss = Integer.parseInt(json.getString("countmiss"));
		if(!json.isNull("countkatu")) countKatu = Integer.parseInt(json.getString("countkatu"));
		if(!json.isNull("countgeki")) countGeki = Integer.parseInt(json.getString("countgeki"));
		if(!json.isNull("perfect")) perfect = Integer.parseInt(json.getString("perfect"));
		if(!json.isNull("enabled_mods")) enabledMods = Integer.parseInt(json.getString("enabled_mods"));
		if(!json.isNull("user_id")) userID = Integer.parseInt(json.getString("user_id"));
		if(!json.isNull("date")) date = json.getString("date");
		if(!json.isNull("rank")) rank = json.getString("rank");
		if(!json.isNull("pp")) pp = Double.parseDouble(json.getString("pp"));
	}
	
	public Osu getParent(){ return osu; }
	public int getBeatmapID(){ return beatmapID; }
	public int getScore(){ return score; }
	public int getMaxCombo(){ return maxCombo; }
	public int getCount300(){ return count300; }
	public int getCount100(){ return count100; }
	public int getCount50(){ return count50; }
	public int getCountMiss(){ return countMiss; }
	public int getCountKatu(){ return countKatu; }
	public int getCountGeki(){ return countGeki; }
	public int getCountTotal(){ return countMiss + count50 + count100 + count300; }
	public boolean isPerfect(){ return (perfect==1) ? true : false; }
	public int getEnabledModIDs(){ return enabledMods; }
	public int getUserID(){ return userID; }
	public String getDate(){ return date; }
	public String getRank(){ return rank; }
	public long getPP(){ return Math.round(pp); }
	public double getPPRaw(){ return pp; }
	
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException { return osu.getBeatmap(beatmapID); }
	
}
