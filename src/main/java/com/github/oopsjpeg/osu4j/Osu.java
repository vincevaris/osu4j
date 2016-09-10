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

	public OsuBeatmap getBeatmap(int beatmapId) throws IOException, OsuRateLimitException {
		return getBeatmap(String.valueOf(beatmapId), 0);
	}
	
	public OsuBeatmap getBeatmap(int beatmapId, int modeID) throws IOException, OsuRateLimitException {
		return getBeatmap(String.valueOf(beatmapId), modeID);
	}
	
	public OsuBeatmap getBeatmap(int beatmapId, Gamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmap(String.valueOf(beatmapId), mode.getID());
	}
	
	public OsuBeatmap getBeatmap(String beatmapId) throws IOException, OsuRateLimitException {
		return getBeatmap(beatmapId, 0);
	}
	
	public OsuBeatmap getBeatmap(String beatmapId, Gamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmap(beatmapId, mode.getID());
	}
	
	public OsuBeatmap getBeatmap(String beatmapId, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&b=" + beatmapId + "&m=" + modeID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuBeatmap(this, json.getJSONObject(0));
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetId) throws IOException, OsuRateLimitException {
		return getBeatmapSet(String.valueOf(beatmapSetId), 0);
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetId, int modeID) throws IOException, OsuRateLimitException {
		return getBeatmapSet(String.valueOf(beatmapSetId), modeID);
	}
	
	public OsuBeatmapSet getBeatmapSet(int beatmapSetId, Gamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmapSet(String.valueOf(beatmapSetId), mode.getID());
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetId) throws IOException, OsuRateLimitException {
		return getBeatmapSet(beatmapSetId, 0);
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetId, Gamemode mode) throws IOException, OsuRateLimitException {
		return getBeatmapSet(beatmapSetId, mode.getID());
	}
	
	public OsuBeatmapSet getBeatmapSet(String beatmapSetId, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_beatmaps?k=" + token + "&s=" + beatmapSetId + "&m=" + modeID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuBeatmapSet(this, json);
	}
	
	public OsuUser getUser(int userId) throws IOException, OsuRateLimitException {
		return getUser(userId, 0);
	}
	
	public OsuUser getUser(String username) throws IOException, OsuRateLimitException {
		return getUser(username, 0);
	}
	
	public OsuUser getUser(int userId, Gamemode mode) throws IOException, OsuRateLimitException {
		return getUser(userId, mode.getID());
	}
	
	public OsuUser getUser(String username, Gamemode mode) throws IOException, OsuRateLimitException {
		return getUser(username, mode.getID());
	}
	
	public OsuUser getUser(int userId, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user?k=" + token + "&u=" + userId + "&m=" + modeID + "&type=id");
		JSONArray json = new JSONArray(jsontext);
		return new OsuUser(this, json.getJSONObject(0), modeID);
	}
	
	public OsuUser getUser(String username, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user?k=" + token + "&u=" + username + "&m=" + modeID + "&type=string");
		JSONArray json = new JSONArray(jsontext);
		return new OsuUser(this, json.getJSONObject(0), modeID);
	}
	
	public List<OsuScore> getTopScores(int userId, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(userId, limit, 0);
	}
	
	public List<OsuScore> getTopScores(String username, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, 0);
	}
	
	public List<OsuScore> getTopScores(int userId, int limit, Gamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(userId, limit, mode.getID());
	}
	
	public List<OsuScore> getTopScores(String username, int limit, Gamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, mode.getID());
	}
	
	public List<OsuScore> getTopScores(int userId, int limit, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_best?k=" + token + "&u=" + userId + "&m=" + modeID + "&type=id&limit=" + limit);
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
	
	public List<OsuScore> getRecentScores(int userId, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(userId, limit, 0);
	}
	
	public List<OsuScore> getRecentScores(String username, int limit) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, 0);
	}
	
	public List<OsuScore> getRecentScores(int userId, int limit, Gamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(userId, limit, mode.getID());
	}
	
	public List<OsuScore> getRecentScores(String username, int limit, Gamemode mode) throws IOException, OsuRateLimitException {
		return getTopScores(username, limit, mode.getID());
	}
	
	public List<OsuScore> getRecentScores(int userId, int limit, int modeID) throws IOException, OsuRateLimitException {
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_user_recent?k=" + token + "&u=" + userId + "&m=" + modeID + "&type=id&limit=" + limit);
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
	
	public OsuReplay getReplay(Gamemode mode, OsuBeatmap beatmap, OsuUser user) throws IOException, OsuRateLimitException {
		return getReplay(mode.getID(), beatmap.getBeatmapID(), user.getUserId());
	}
	
	public OsuReplay getReplay(int modeID, int beatmapID, int userID) throws IOException, OsuRateLimitException {
		if(currentRequests > 10) throw new OsuRateLimitException(10);
		if(currentRequests >= requestsPerMinute) throw new OsuRateLimitException(requestsPerMinute);
		String jsontext = readUrl("http://osu.ppy.sh/api/get_replay?k=" + token + "&m=" + modeID + "&b=" + beatmapID + "&u=" + userID);
		JSONArray json = new JSONArray(jsontext);
		return new OsuReplay(this, json.getJSONObject(0));
	}
	
}
