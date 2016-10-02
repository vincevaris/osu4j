package com.github.oopsjpeg.osu4j.beatmap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.Osu;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class OsuBeatmap {
	protected Osu osu;
	protected int approved;
	protected String approvedDate;
	protected String lastUpdate;
	protected String artist;
	protected int beatmapID;
	protected int beatmapSetID;
	protected double bpm;
	protected String creator;
	protected double difficultyRating;
	protected double diffSize;
	protected double diffOverall;
	protected double diffApproach;
	protected double diffDrain;
	protected double hitLength;
	protected String source;
	protected int genreID;
	protected int languageID;
	protected String title;
	protected double totalLength;
	protected String version;
	protected String fileMd5;
	protected int mode;
	protected String tags;
	protected long favouriteCount;
	protected long playCount;
	protected long passCount;
	protected long maxCombo;
	
	public OsuBeatmap(Osu osu, JSONObject json){
		this.osu = osu;
		approved = Integer.parseInt(json.getString("approved"));
		if(!json.isNull("approved_date")) approvedDate = json.getString("approved_date");
		if(!json.isNull("last_update")) lastUpdate = json.getString("last_update");
		if(!json.isNull("artist")) artist = json.getString("artist");
		if(!json.isNull("beatmap_id")) beatmapID = Integer.parseInt(json.getString("beatmap_id"));
		if(!json.isNull("beatmapset_id")) beatmapSetID = Integer.parseInt(json.getString("beatmapset_id"));
		if(!json.isNull("bpm")) bpm = Double.parseDouble(json.getString("bpm"));
		if(!json.isNull("creator")) creator = json.getString("creator");
		if(!json.isNull("difficultyrating")) difficultyRating = Double.parseDouble(json.getString("difficultyrating"));
		if(!json.isNull("diff_size")) diffSize = Double.parseDouble(json.getString("diff_size"));
		if(!json.isNull("diff_overall")) diffOverall = Double.parseDouble(json.getString("diff_overall"));
		if(!json.isNull("diff_approach")) diffApproach = Double.parseDouble(json.getString("diff_approach"));
		if(!json.isNull("diff_drain")) diffDrain = Double.parseDouble(json.getString("diff_drain"));
		if(!json.isNull("hit_length")) hitLength = Double.parseDouble(json.getString("hit_length"));
		if(!json.isNull("source")) source = json.getString("source");
		if(!json.isNull("genre_id")) genreID = Integer.parseInt(json.getString("genre_id"));
		if(!json.isNull("language_id")) languageID = Integer.parseInt(json.getString("language_id"));
		if(!json.isNull("title")) title = json.getString("title");
		if(!json.isNull("total_length")) totalLength = Double.parseDouble(json.getString("total_length"));
		if(!json.isNull("Version")) version = json.getString("version");
		if(!json.isNull("file_md5")) fileMd5 = json.getString("file_md5");
		if(!json.isNull("mode")) mode = Integer.parseInt(json.getString("mode"));
		if(!json.isNull("tags")) tags = json.getString("tags");
		if(!json.isNull("favourite_count")) favouriteCount = Long.parseLong(json.getString("favourite_count"));
		if(!json.isNull("playcount")) playCount = Long.parseLong(json.getString("playcount"));
		if(!json.isNull("passcount")) passCount = Long.parseLong(json.getString("passcount"));
		if(!json.isNull("max_combo")) maxCombo = Long.parseLong(json.getString("max_combo"));
	}
	
	public Osu getParent(){ return osu; }
	public boolean getApproved(){ return (approved==1) ? true : false; }
	public String getApprovedDate(){ return approvedDate; }
	public String getLastUpdate(){ return lastUpdate; }
	public String getArtist(){ return artist; }
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException { return osu.getBeatmap(beatmapID); }
	public int getBeatmapID(){ return beatmapID; }
	public int getBeatmapSetID(){ return beatmapSetID; }
	public long getBPM(){ return Math.round(bpm); }
	public double getBPMRaw(){ return bpm; }
	public String getCreator(){ return creator; }
	public double getDifficultyRating(){ return difficultyRating; }
	public double getCS(){ return diffSize; }
	public double getOD(){ return diffOverall; }
	public double getAR(){ return diffApproach; }
	public double getHP(){ return diffDrain; }
	public double getHitLength(){ return hitLength; }
	public String getSource(){ return source; }
	public int getGenreID(){ return genreID; }
	public int getLanguageID(){ return languageID; }
	public String getTitle(){ return title; }
	public double getTotalLength(){ return totalLength; }
	public String getVersion(){ return version; }
	public String getFileMD5(){ return fileMd5; }
	public OsuMode getMode(){ return OsuMode.getByID(mode); }
	public int getModeID(){ return mode; }
	public String[] getTags(){ return tags.split(" "); }
	public long getFavouriteCount(){ return favouriteCount; }
	public long getPlayCount(){ return playCount; }
	public long getPassCount(){ return passCount; }
	public long getMaxCombo(){ return maxCombo; }
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/b/" + beatmapSetID); }
}
