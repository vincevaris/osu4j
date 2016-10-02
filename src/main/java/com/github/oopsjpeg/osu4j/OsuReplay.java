package com.github.oopsjpeg.osu4j;

import org.json.JSONObject;

public class OsuReplay {

	private Osu osu;
	private String content;
	private String encoding;
	
	public OsuReplay(Osu osu, JSONObject json){
		this.osu = osu;
		if(!json.isNull("content")) content = json.getString("content");
		if(!json.isNull("encoding")) encoding = json.getString("encoding");
	}
	
	public Osu getParent(){ return osu; }
	public String getContent(){ return content; }
	public String getEncoding(){ return encoding; }
	
}
