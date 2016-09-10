package com.github.oopsjpeg.osu4j;

import java.io.IOException;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class OsuScore {
	private Osu osu;
	private int beatmapId;
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
	private int userId;
	private String date;
	private String rank;
	public double pp;
	
	public OsuScore(Osu osu, JSONObject obj){
		this.osu = osu;
		beatmapId = Integer.parseInt(obj.getString("beatmap_id"));
		score = Integer.parseInt(obj.getString("score"));
		maxCombo = Integer.parseInt(obj.getString("maxcombo"));
		count300 = Integer.parseInt(obj.getString("count300"));
		count100 = Integer.parseInt(obj.getString("count100"));
		count50 = Integer.parseInt(obj.getString("count50"));
		countMiss = Integer.parseInt(obj.getString("countmiss"));
		countKatu = Integer.parseInt(obj.getString("countkatu"));
		countGeki = Integer.parseInt(obj.getString("countgeki"));
		perfect = Integer.parseInt(obj.getString("perfect"));
		enabledMods = Integer.parseInt(obj.getString("enabled_mods"));
		userId = Integer.parseInt(obj.getString("user_id"));
		date = obj.getString("date");
		rank = obj.getString("rank");
		pp = Double.parseDouble(obj.getString("pp"));
	}
	
	public Osu getParent(){ return osu; }
	public int getBeatmapId(){ return beatmapId; }
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
	public int getEnabledMods(){ return enabledMods; }
	public int getUserId(){ return userId; }
	public String getDate(){ return date; }
	public String getRank(){ return rank; }
	public long getPP(){ return Math.round(pp); }
	public double getPPRaw(){ return pp; }
	
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException { return osu.getBeatmap(beatmapId); }
	
}
