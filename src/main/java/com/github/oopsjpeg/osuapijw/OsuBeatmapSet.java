package com.github.oopsjpeg.osuapijw;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class OsuBeatmapSet extends OsuBeatmap {
	
	private List<OsuBeatmap> beatmaps = new ArrayList<>();
	
	public OsuBeatmapSet(Osu osu, JSONObject json, List<OsuBeatmap> beatmaps){
		super(osu, json);
		this.beatmaps = beatmaps;
	}
	
	public int getSize(){ return beatmaps.size(); }
	public OsuBeatmap getBeatmap(int index){ return beatmaps.get(index); }
	public List<OsuBeatmap> getBeatmaps(){ return beatmaps; }
	
	@Override
	public String getURL(){ return "https://osu.ppy.sh/b/" + beatmapSetId; }
}
