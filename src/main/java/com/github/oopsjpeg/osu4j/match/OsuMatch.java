package com.github.oopsjpeg.osu4j.match;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.Osu;
import com.github.oopsjpeg.osu4j.OsuUserEvent;

public class OsuMatch {
	
	private Osu osu;
	private int matchID;
	private String name;
	private String startTime;
	private String endTime; // Not supported as of API v1.0, always null
	private JSONArray gamesArr;
	private List<OsuMatchGame> games = new ArrayList<>();
	
	public OsuMatch(Osu osu, JSONObject json) {
		this.osu = osu;
		JSONObject match = json.getJSONObject("match");
		if(!match.isNull("match_id")) matchID = Integer.parseInt(match.getString("match_id"));
		if(!match.isNull("name")) name = match.getString("name");
		if(!match.isNull("start_time")) startTime = match.getString("start_time");
		if(!match.isNull("end_time")) endTime = match.getString("end_time");
		gamesArr = json.getJSONArray("games");
	}
	
	public OsuMatch withGames() {
		games.clear();
		for(int i = 0; i < gamesArr.length(); i++){
			games.add(new OsuMatchGame(osu, gamesArr.getJSONObject(i)));
		}
		return this;
	}
	
	public Osu getParent(){ return osu; }
	public int getMatchID(){ return matchID; }
	public String getName(){ return name; }
	public String getStartTime(){ return startTime; }
	public String getEndTime(){ return endTime; }
	public List<OsuMatchGame> getGames() { return games; }
	public OsuMatchGame getGame(int index) { return games.get(index); }
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/mp/" + matchID); }
	
}
