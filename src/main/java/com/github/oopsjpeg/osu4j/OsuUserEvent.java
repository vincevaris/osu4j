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
	private int beatmapId;
	private int beatmapSetId;
	private String date;
	private int epicFactor;
	
	public OsuUserEvent(Osu osu, JSONObject obj) throws JSONException, IOException {
		this.osu = osu;
		displayHtml = obj.getString("display_html");
		beatmapId = Integer.parseInt(obj.getString("beatmap_id"));
		beatmapSetId = Integer.parseInt(obj.getString("beatmapset_id"));
		date = obj.getString("date");
		epicFactor = Integer.parseInt(obj.getString("epicfactor"));
	}
	
	public Osu getParent(){ return osu; }
	public String getDisplayHTML(){ return displayHtml; }
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException{ return osu.getBeatmap(beatmapId); }
	public int getBeatmapID() { return beatmapId; }
	public OsuBeatmapSet getBeatmapSet() throws IOException, OsuRateLimitException { return osu.getBeatmapSet(beatmapSetId); }
	public int getBeatmapSetID(){ return beatmapSetId; }
	public String getDate(){ return date; }
	public int getEpicFactor(){ return epicFactor; }
	
}
