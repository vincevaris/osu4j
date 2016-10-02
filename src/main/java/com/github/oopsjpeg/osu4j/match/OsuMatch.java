package com.github.oopsjpeg.osu4j.match;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.Osu;

public class OsuMatch {
	
	private Osu osu;
	private int matchID;
	private String name;
	private String startTime;
	private String endTime; // As of now, always null!
	
	public OsuMatch(Osu osu, JSONObject json){
		this.osu = osu;
		JSONObject match = json.getJSONObject("match");
		if(!match.isNull("match_id")) matchID = Integer.parseInt(match.getString("match_id"));
		if(!match.isNull("name")) name = match.getString("name");
		if(!match.isNull("start_time")) startTime = match.getString("start_time");
		if(!match.isNull("end_time")) endTime = match.getString("end_time");
	}
	
	public Osu getParent(){ return osu; }
	public int getMatchID(){ return matchID; }
	public String getName(){ return name; }
	public String getStartTime(){ return startTime; }
	public String getEndTime(){ return endTime; }
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/mp/" + matchID); }
	
}
