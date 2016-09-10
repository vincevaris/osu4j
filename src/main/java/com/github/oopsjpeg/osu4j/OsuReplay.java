package com.github.oopsjpeg.osu4j;

import org.json.JSONObject;

public class OsuReplay {

	private Osu osu;
	private String content;
	private String encoding;
	
	public OsuReplay(Osu osu, JSONObject json){
		this.osu = osu;
		content = json.getString("content");
		encoding = json.getString("encoding");
	}
	
	public Osu getParent(){ return osu; }
	public String getContent(){ return content; }
	public String getEncoding(){ return encoding; }
	
}
