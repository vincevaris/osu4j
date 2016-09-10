package com.github.oopsjpeg.osu4j.beatmap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.Gamemode;
import com.github.oopsjpeg.osu4j.Osu;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class OsuBeatmap {
	protected Osu osu;
	protected int approved;
	protected String approvedDate;
	protected String lastUpdate;
	protected String artist;
	protected int beatmapId;
	protected int beatmapSetId;
	protected double bpm;
	protected String creator;
	protected double difficultyRating;
	protected double diffSize;
	protected double diffOverall;
	protected double diffApproach;
	protected double diffDrain;
	protected double hitLength;
	protected String source;
	protected int genreId;
	protected int languageId;
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
	
	public OsuBeatmap(Osu osu, JSONObject obj){
		this.osu = osu;
		approved = Integer.parseInt(obj.getString("approved"));
		approvedDate = obj.getString("approved_date");
		lastUpdate = obj.getString("last_update");
		artist = obj.getString("artist");
		beatmapId = Integer.parseInt(obj.getString("beatmap_id"));
		beatmapSetId = Integer.parseInt(obj.getString("beatmapset_id"));
		bpm = Double.parseDouble(obj.getString("bpm"));
		creator = obj.getString("creator");
		difficultyRating = Double.parseDouble(obj.getString("difficultyrating"));
		diffSize = Double.parseDouble(obj.getString("diff_size"));
		diffOverall = Double.parseDouble(obj.getString("diff_overall"));
		diffApproach = Double.parseDouble(obj.getString("diff_approach"));
		diffDrain = Double.parseDouble(obj.getString("diff_drain"));
		hitLength = Double.parseDouble(obj.getString("hit_length"));
		source = obj.getString("source");
		genreId = Integer.parseInt(obj.getString("genre_id"));
		languageId = Integer.parseInt(obj.getString("language_id"));
		title = obj.getString("title");
		totalLength = Double.parseDouble(obj.getString("total_length"));
		version = obj.getString("version");
		fileMd5 = obj.getString("file_md5");
		mode = Integer.parseInt(obj.getString("mode"));
		tags = obj.getString("tags");
		favouriteCount = Long.parseLong(obj.getString("favourite_count"));
		playCount = Long.parseLong(obj.getString("playcount"));
		passCount = Long.parseLong(obj.getString("passcount"));
		maxCombo = Long.parseLong(obj.getString("max_combo"));
	}
	
	public Osu getParent(){ return osu; }
	public boolean getApproved(){ return (approved==1) ? true : false; }
	public String getApprovedDate(){ return approvedDate; }
	public String getLastUpdate(){ return lastUpdate; }
	public String getArtist(){ return artist; }
	public OsuBeatmap getBeatmap() throws IOException, OsuRateLimitException { return osu.getBeatmap(beatmapId); }
	public int getBeatmapID(){ return beatmapId; }
	public int getBeatmapSetID(){ return beatmapSetId; }
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
	public int getGenreID(){ return genreId; }
	public int getLanguageID(){ return languageId; }
	public String getTitle(){ return title; }
	public double getTotalLength(){ return totalLength; }
	public String getVersion(){ return version; }
	public String getFileMD5(){ return fileMd5; }
	public Gamemode getMode(){ return Gamemode.getByID(mode); }
	public int getModeID(){ return mode; }
	public String[] getTags(){ return tags.split(" "); }
	public long getFavouriteCount(){ return favouriteCount; }
	public long getPlayCount(){ return playCount; }
	public long getPassCount(){ return passCount; }
	public long getMaxCombo(){ return maxCombo; }
	public URL getURL() throws MalformedURLException { return new URL("https://osu.ppy.sh/b/" + beatmapSetId); }
}
