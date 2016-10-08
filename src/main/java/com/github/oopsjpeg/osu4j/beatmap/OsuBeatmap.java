package com.github.oopsjpeg.osu4j.beatmap;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.ApprovalMode;
import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.OsuUser;
import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.util.Utility;

public class OsuBeatmap {
    protected ApprovalMode approved;
    protected ZonedDateTime approvedDate;
    protected ZonedDateTime lastUpdate;
    protected String artist;
    protected int beatmapID;
    protected int beatmapSetID;
    protected double bpm;
    protected String creatorName;
    protected LazilyLoaded<OsuUser> creator;
    protected double difficultyRating;
    protected double diffSize;
    protected double diffOverall;
    protected double diffApproach;
    protected double diffDrain;
    protected double hitLength;
    protected String source;
    protected Genre genreID;
    protected Language languageID;
    protected String title;
    protected double totalLength;
    protected String version;
    protected String fileMd5;
    protected OsuMode mode;
    protected String[] tags;
    protected long favouriteCount;
    protected long playCount;
    protected long passCount;
    protected long maxCombo;

    public OsuBeatmap(Osu osu, JSONObject json) {
        approved = ApprovalMode.getById(Integer.parseInt(json.getString("approved")));
        approvedDate = Utility.parseDate(json.getString("approved_date"));
        lastUpdate = Utility.parseDate(json.getString("last_update"));
        artist = json.getString("artist");
        beatmapID = Integer.parseInt(json.getString("beatmap_id"));
        beatmapSetID = Integer.parseInt(json.getString("beatmapset_id"));
        bpm = Double.parseDouble(json.getString("bpm"));
        creatorName = json.getString("creator");
        creator = osu.users.getAsQuery(new EndpointUsers.ArgumentsBuilder(creatorName).build()).asLazilyLoaded();
        difficultyRating = Double.parseDouble(json.getString("difficultyrating"));
        diffSize = Double.parseDouble(json.getString("diff_size"));
        diffOverall = Double.parseDouble(json.getString("diff_overall"));
        diffApproach = Double.parseDouble(json.getString("diff_approach"));
        diffDrain = Double.parseDouble(json.getString("diff_drain"));
        hitLength = Double.parseDouble(json.getString("hit_length"));
        source = json.getString("source");
        genreID = Genre.getById(Integer.parseInt(json.getString("genre_id")));
        languageID = Language.getById(Integer.parseInt(json.getString("language_id")));
        title = json.getString("title");
        totalLength = Double.parseDouble(json.getString("total_length"));
        version = json.getString("version");
        fileMd5 = json.getString("file_md5");
        mode = OsuMode.getByID(Integer.parseInt(json.getString("mode")));
        tags = json.getString("tags").split(" ");
        favouriteCount = Long.parseLong(json.getString("favourite_count"));
        playCount = Long.parseLong(json.getString("playcount"));
        passCount = Long.parseLong(json.getString("passcount"));
        maxCombo = Long.parseLong(json.getString("max_combo"));
    }

    public ApprovalMode getApprovalMode() {
        return approved;
    }

    public ZonedDateTime getApprovedDate() {
        return approvedDate;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getArtist() {
        return artist;
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public int getBeatmapSetID() {
        return beatmapSetID;
    }

    public long getBPM() {
        return Math.round(bpm);
    }

    public double getBPMRaw() {
        return bpm;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public LazilyLoaded<OsuUser> getCreator() {
        return creator;
    }

    public double getDifficultyRating() {
        return difficultyRating;
    }

    public double getCS() {
        return diffSize;
    }

    public double getOD() {
        return diffOverall;
    }

    public double getAR() {
        return diffApproach;
    }

    public double getHP() {
        return diffDrain;
    }

    public double getHitLength() {
        return hitLength;
    }

    public String getSource() {
        return source;
    }

    public Genre getGenreID() {
        return genreID;
    }

    public Language getLanguageID() {
        return languageID;
    }

    public String getTitle() {
        return title;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public String getVersion() {
        return version;
    }

    public String getFileMD5() {
        return fileMd5;
    }

    public OsuMode getMode() {
        return mode;
    }

    public String[] getTags() {
        return tags;
    }

    public long getFavouriteCount() {
        return favouriteCount;
    }

    public long getPlayCount() {
        return playCount;
    }

    public long getPassCount() {
        return passCount;
    }

    public long getMaxCombo() {
        return maxCombo;
    }

    public URL getURL() throws MalformedURLException {
        return new URL("https://osu.ppy.sh/b/" + beatmapSetID);
    }
}
