package com.github.oopsjpeg.osuapijw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class Osu {

	private String token;
	private boolean debug = false;
	
	public Osu(String token){
		this.token = token;
	}
	
	public Osu withDebug(){
		debug = true;
		return this;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getToken(){
		return token;
	}
	
	public void setDebug(boolean debug){
		this.debug = debug;
	}
	
	public boolean isDebug(){
		return debug;
	}

	public OsuBeatmap getBeatmap(int beatmapId) throws IOException {
		return getBeatmap(String.valueOf(beatmapId), 0);
	}
	
	public OsuBeatmap getBeatmap(int beatmapId, int modeID) throws IOException {
		return getBeatmap(String.valueOf(beatmapId), modeID);
	}
	
	public OsuBeatmap getBeatmap(int beatmapId, Gamemode mode) throws IOException {
		return getBeatmap(String.valueOf(beatmapId), mode.getID());
	}
	
	public OsuBeatmap getBeatmap(String beatmapId) throws IOException {
		return getBeatmap(beatmapId, 0);
	}
	
	public OsuBeatmap getBeatmap(String beatmapId, Gamemode mode) throws IOException {
		return getBeatmap(beatmapId, mode.getID());
	}
	
	public OsuBeatmap getBeatmap(String beatmapId, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&b=" + beatmapId + "&m=" + modeID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuBeatmap(this, json.getJSONObject(0));
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetId) throws IOException {
		return getBeatmapSet(String.valueOf(beatmapSetId), 0);
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetId, int modeID) throws IOException {
		return getBeatmapSet(String.valueOf(beatmapSetId), modeID);
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetId, Gamemode mode) throws IOException {
		return getBeatmapSet(String.valueOf(beatmapSetId), mode.getID());
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetId) throws IOException {
		return getBeatmapSet(beatmapSetId, 0);
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetId, Gamemode mode) throws IOException {
		return getBeatmapSet(beatmapSetId, mode.getID());
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetId, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&s=" + beatmapSetId + "&m=" + modeID);
		JSONArray json = new JSONArray(jsontext);
		List<OsuBeatmap> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){
			build.add(new OsuBeatmap(this, json.getJSONObject(i)));
		}
		return new OsuBeatmapSet(this, json.getJSONObject(0), build);
	}
	
	public OsuUser getUser(int userId) throws IOException {
		return getUser(userId, 0);
	}
	
	public OsuUser getUser(String username) throws IOException {
		return getUser(username, 0);
	}
	
	public OsuUser getUser(int userId, Gamemode mode) throws IOException {
		return getUser(userId, mode.getID());
	}
	
	public OsuUser getUser(String username, Gamemode mode) throws IOException {
		return getUser(username, mode.getID());
	}
	
	public OsuUser getUser(int userId, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user?k=" + token + "&u=" + userId + "&m=" + modeID + "&type=id");
		JSONArray json = new JSONArray(jsontext);
		return new OsuUser(this, json.getJSONObject(0), modeID);
	}
	
	public OsuUser getUser(String username, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string");
		JSONArray json = new JSONArray(jsontext);
		return new OsuUser(this, json.getJSONObject(0), modeID);
	}
	
	public List<OsuScore> getTopScores(int userId, int limit) throws IOException {
		return getTopScores(userId, limit, 0);
	}
	
	public List<OsuScore> getTopScores(String username, int limit) throws IOException {
		return getTopScores(username, limit, 0);
	}
	
	public List<OsuScore> getTopScores(int userId, int limit, Gamemode mode) throws IOException {
		return getTopScores(userId, limit, mode.getID());
	}
	
	public List<OsuScore> getTopScores(String username, int limit, Gamemode mode) throws IOException {
		return getTopScores(username, limit, mode.getID());
	}
	
	public List<OsuScore> getTopScores(int userId, int limit, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_best?k=" + token + "&u=" + userId + "&m=" + modeID + "&type=id&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public List<OsuScore> getTopScores(String username, int limit, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_best?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public List<OsuScore> getRecentScores(int userId, int limit) throws IOException {
		return getTopScores(userId, limit, 0);
	}
	
	public List<OsuScore> getRecentScores(String username, int limit) throws IOException {
		return getTopScores(username, limit, 0);
	}
	
	public List<OsuScore> getRecentScores(int userId, int limit, Gamemode mode) throws IOException {
		return getTopScores(userId, limit, mode.getID());
	}
	
	public List<OsuScore> getRecentScores(String username, int limit, Gamemode mode) throws IOException {
		return getTopScores(username, limit, mode.getID());
	}
	
	public List<OsuScore> getRecentScores(int userId, int limit, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_recent?k=" + token + "&u=" + userId + "&m=" + modeID + "&type=id&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public List<OsuScore> getRecentScores(String username, int limit, int modeID) throws IOException {
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_recent?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	private static String readUrl(String urlString) throws IOException {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder builder = new StringBuilder();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				builder.append(chars, 0, read);

			return builder.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
}
