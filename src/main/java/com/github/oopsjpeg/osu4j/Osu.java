package com.github.oopsjpeg.osu4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;

import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmapSet;
import com.github.oopsjpeg.osu4j.match.OsuMatch;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

import static com.github.oopsjpeg.osu4j.util.Utility.*;

public class Osu {
	
	private String token;
	private long requestsPerMinute = 1000;
	private long currentRequests = 0;
	private boolean debug = false;
	
	public Osu(String token){
		this.token = token;
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(new Runnable(){
			public void run(){
				currentRequests = 0;
			}
		}, 1, 1, TimeUnit.MINUTES);
	}
	
	public Osu withDebug(boolean debug){
		this.debug = debug;
		return this;
	}
	
	public Osu withRequestsPerMinute(long requestsPerMinute){
		if(requestsPerMinute > 1200) requestsPerMinute = 1200;
		this.requestsPerMinute = requestsPerMinute;
		return this;
	}
	
	public void setToken(String token){ this.token = token; }
	public String getToken(){ return token; }
	
	public void setDebug(boolean debug){ this.debug = debug; }
	public boolean isDebug(){ return debug; }
	
	public void setRequestsPerMinute(long requestsPerMinute){ this.requestsPerMinute = requestsPerMinute; }
	public long getRequestsPerMinute(){ return requestsPerMinute; }

	public List<OsuBeatmap> getBeatmaps() throws IOException, OsuRateLimitException {
		return getBeatmaps(500);
	}
	
	public List<OsuBeatmap> getBeatmaps(int limit) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuBeatmap> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuBeatmap(this, json.getJSONObject(i))); }
		return build;
	}
	
	public OsuBeatmap getBeatmap(int beatmapID) throws IOException, OsuRateLimitException {
		return getBeatmap(String.valueOf(beatmapID), 0);
	}
	
	public OsuBeatmap getBeatmap(int beatmapID, int modeID) throws IOException, OsuRateLimitException {
		return getBeatmap(String.valueOf(beatmapID), modeID);
	}
	
	public OsuBeatmap getBeatmap(int beatmapID, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmap(String.valueOf(beatmapID), mode.getID());
	}
	
	public OsuBeatmap getBeatmap(String beatmapID) throws IOException, OsuRateLimitException {
		return getBeatmap(beatmapID, 0);
	}
	
	public OsuBeatmap getBeatmap(String beatmapID, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmap(beatmapID, mode.getID());
	}
	
	public OsuBeatmap getBeatmap(String beatmapID, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&b=" + beatmapID + "&m=" + modeID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuBeatmap(this, json.getJSONObject(0));
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetID) throws IOException, OsuRateLimitException {
		return getBeatmapSet(String.valueOf(beatmapSetID), 0);
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetID, int modeID) throws IOException, OsuRateLimitException {
		return getBeatmapSet(String.valueOf(beatmapSetID), modeID);
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetID, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmapSet(String.valueOf(beatmapSetID), mode.getID());
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetID) throws IOException, OsuRateLimitException {
		return getBeatmapSet(beatmapSetID, 0);
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetID, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmapSet(beatmapSetID, mode.getID());
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetID, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&s=" + beatmapSetID + "&m=" + modeID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuBeatmapSet(this, json);
	}
	
	public OsuUser getUser(int userID) throws IOException, OsuRateLimitException {
		return getUser(userID, 0);
	}
	
	public OsuUser getUser(String username) throws IOException, OsuRateLimitException {
		return getUser(username, 0);
	}
	
	public OsuUser getUser(int userID, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getUser(userID, mode.getID());
	}
	
	public OsuUser getUser(String username, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getUser(username, mode.getID());
	}
	
	public OsuUser getUser(int userID, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user?k=" + token + "&u=" + userID + "&m=" + modeID + "&type=id");
		JSONArray json = new JSONArray(jsontext);
		return new OsuUser(this, json.getJSONObject(0), modeID);
	}
	
	public OsuUser getUser(String username, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string");
		JSONArray json = new JSONArray(jsontext);
		return new OsuUser(this, json.getJSONObject(0), modeID);
	}
	
	public List<OsuScore> getTopScores(int userID, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(userID, limit, 0);
	}
	
	public List<OsuScore> getTopScores(String username, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, 0);
	}
	
	public List<OsuScore> getTopScores(int userID, int limit, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(userID, limit, mode.getID());
	}
	
	public List<OsuScore> getTopScores(String username, int limit, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, mode.getID());
	}
	
	public List<OsuScore> getTopScores(int userID, int limit, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_best?k=" + token + "&u=" + userID + "&m=" + modeID + "&type=id&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public List<OsuScore> getTopScores(String username, int limit, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_best?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public List<OsuScore> getRecentScores(int userID, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(userID, limit, 0);
	}
	
	public List<OsuScore> getRecentScores(String username, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, 0);
	}
	
	public List<OsuScore> getRecentScores(int userID, int limit, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(userID, limit, mode.getID());
	}
	
	public List<OsuScore> getRecentScores(String username, int limit, OsuGamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, mode.getID());
	}
	
	public List<OsuScore> getRecentScores(int userID, int limit, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_recent?k=" + token + "&u=" + userID + "&m=" + modeID + "&type=id&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public List<OsuScore> getRecentScores(String username, int limit, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_recent?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string&limit=" + limit);
		JSONArray json = new JSONArray(jsontext);
		List<OsuScore> build = new ArrayList<>();
		for(int i = 0; i < json.length(); i++){ build.add(new OsuScore(this, json.getJSONObject(i))); }
		return build;
	}
	
	public OsuMatch getMatch(int matchID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_match?k=" + token + "&mp=" + matchID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuMatch(this, json.getJSONObject(0));
	}
	
	public OsuReplay getReplay(OsuGamemode mode, OsuBeatmap beatmap, OsuUser user) throws IOException, OsuRateLimitException {
		return getReplay(mode.getID(), beatmap.getBeatmapID(), user.getUserID());
	}
	
	public OsuReplay getReplay(int modeID, int beatmapID, int userID) throws IOException, OsuRateLimitException {
		if(currentRequests > 10) throw new OsuRateLimitException(10);
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_replay?k=" + token + "&m=" + modeID + "&b=" + beatmapID + "&u=" + userID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuReplay(this, json.getJSONObject(0));
	}
	
}
