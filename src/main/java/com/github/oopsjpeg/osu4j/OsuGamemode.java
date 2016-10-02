package com.github.oopsjpeg.osu4j;

public enum OsuGamemode {
	STANDARD(0, "osu!"),
	TAIKO(1, "osu!taiko"),
	CATCH_THE_BEAT(2, "osu!catch"),
	MANIA(3, "osu!mania");
	
	private int id;
	private String name;
	
	OsuGamemode(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public static OsuGamemode getByID(int id){
		switch(id){
			case 0:
				return STANDARD;
			case 1:
				return TAIKO;
			case 2:
				return CATCH_THE_BEAT;
			case 3:
				return MANIA;
			default:
				return null;
		}
	}
	
}
