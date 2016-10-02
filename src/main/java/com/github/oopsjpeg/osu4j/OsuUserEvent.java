package com.github.oopsjpeg.osu4j;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmapSet;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class OsuUserEvent {
	
	private Osu osu;
	private String displayHtml;
	private int beatmapID;
	private int beatmapSetID;
	private String date;
	private int epicFactor;
	
	public OsuUserEvent(Osu osu, JSONObject json) throws JSONException, IOException {
		this.osu = osu;
		if(!json.isNull("display_html")) displayHtml = json.getString("display_html");
		if(!json.isNull("beatmap_id")) beatmapID = Integer.parseInt(json.getString("beatmap_id"));
		if(!json.isNull("beatmapset_id")) beatmapSetID = Integer.parseInt(json.getString("beatmapset_id"));
		if(!json.isNull("date")) date = json.getString("date");
		if(!json.isNull("epicfactor")) epicFactor = Integer.parseInt(json.getString("epicfactor"));
	}
	
	public Osu getParent(){ return osu; }
	public String getDisplayHTML(){ return displayHtml; }
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException{ return osu.getBeatmap(beatmapID); }
	public int getBeatmapID() { return beatmapID; }
	public OsuBeatmapSet getBeatmapSet() throws IOException, OsuRateLimitException { return osu.getBeatmapSet(beatmapSetID); }
	public int getBeatmapSetID(){ return beatmapSetID; }
	public String getDate(){ return date; }
	public int getEpicFactor(){ return epicFactor; }
	
}
