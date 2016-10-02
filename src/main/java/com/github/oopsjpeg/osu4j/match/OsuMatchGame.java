package com.github.oopsjpeg.osu4j.match;

import java.io.IOException;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.Osu;
import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class OsuMatchGame {
	private Osu osu;
	private int gameID;
	private String startTime;
	private String endTime;
	private int beatmapID;
	private int mode;
	private int matchType;
	private int scoringType;
	private int teamType;
	private int mods;
	
	public OsuMatchGame(Osu osu, JSONObject json) {
		this.osu = osu;
		if(json.isNull("game_id")) gameID = Integer.parseInt(json.getString("game_id"));
		if(json.isNull("start_time")) startTime = json.getString("start_time");
		if(json.isNull("end_time")) endTime = json.getString("end_time");
		if(json.isNull("beatmap_id")) beatmapID = Integer.parseInt(json.getString("beatmap_id"));
		if(json.isNull("play_mode")) mode = Integer.parseInt(json.getString("play_mode"));
		if(json.isNull("match_type")) matchType = Integer.parseInt(json.getString("match_type"));
		if(json.isNull("scoring_type")) scoringType = Integer.parseInt(json.getString("scoring_type"));
		if(json.isNull("team_type")) teamType = Integer.parseInt(json.getString("team_type"));
		if(json.isNull("mods")) mods = Integer.parseInt(json.getString("mods"));
	}
	
	public Osu getParent(){ return osu; }
	public int getGameID() { return gameID; }
	public String getStartTime() { return startTime; }
	public String getEndTime() { return endTime; }
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException { return osu.getBeatmap(beatmapID); }
	public int getBeatmapID() { return beatmapID; }
	public OsuMode getMode() { return OsuMode.getByID(mode); }
	public int getModeID() { return mode; }
	public int getMatchTypeID() { return matchType; }
	public int getScoringTypeID() { return scoringType; }
	public int getTeamTypeID() { return teamType; }
	public int getModIDs() { return mods; }
}
