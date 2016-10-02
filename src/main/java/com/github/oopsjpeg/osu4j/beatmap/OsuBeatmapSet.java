package com.github.oopsjpeg.osu4j.beatmap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import com.github.oopsjpeg.osu4j.Osu;

public class OsuBeatmapSet extends OsuBeatmap {
	
	private List<OsuBeatmap> beatmaps = new ArrayList<>();
	
	public OsuBeatmapSet(Osu osu, JSONArray json){
		super(osu, json.getJSONObject(0));
		for(int i = 0; i < json.length(); i++){
			beatmaps.add(new OsuBeatmap(osu, json.getJSONObject(i)));
		}
	}
	
	public Osu getParent(){ return osu; }
	public int getSize(){ return beatmaps.size(); }
	public OsuBeatmap getBeatmap(int index){ return beatmaps.get(index); }
	public List<OsuBeatmap> getBeatmaps(){ return beatmaps; }
	@Override
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/b/" + beatmapSetID); }
}
