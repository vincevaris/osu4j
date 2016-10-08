package com.github.oopsjpeg.osu4j;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.backend.Osu;

public class OsuReplay {

    private String content;
    private String encoding;

    public OsuReplay(Osu osu, JSONObject json) {
        content = json.getString("content");
        encoding = json.getString("encoding");
    }

    public String getContent() {
        return content;
    }

    public String getEncoding() {
        return encoding;
    }

}
