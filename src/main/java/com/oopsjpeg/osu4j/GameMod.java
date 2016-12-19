package com.oopsjpeg.osu4j;

import java.util.EnumSet;

public enum GameMod {
	None(0, "None"),
	NoFail(1, "No-fail"),
	Easy(2, "Easy"),
	NoVideo(4, "No Video"),
	Hidden(8, "Hidden"),
	HardRock(16, "Hard Rock"),
	SuddenDeath(32, "Sudden Death"),
	DoubleTime(64, "Double Time"),
	Relax(128, "Relax"),
	HalfTime(256, "Halftime"),
	Nightcore(512, "Nightcore"),
	Flashlight(1024, "Flashlight"),
	Autoplay(2048, "Auto-play"),
	SpunOut(4096, "Spun Out"),
	Autopilot(8192, "Autopilot"),
	Perfect(16384, "Perfect"),
	Key4(32768, "4K"),
	Key5(65536, "5K"),
	Key6(131072, "6K"),
	Key7(262144, "7K"),
	Key8(524288, "8K"),
	FadeIn(1048576, "Fade-in"),
	Random(2097152, "Random"),
	LastMod(4194304, "Last GameMod"),
	Key9(16777216, "9K"),
	Key10(33554432, "10K"),
	Key1(67108864, "1K"),
	Key3(134217728, "3K"),
	Key2(268435456, "2K");

	private final long flag;
	private final String name;

	GameMod(int flag, String name) {
		this.flag = flag;
		this.name = name;
	}

	public static GameMod fromFlag(long flag) {
		for (GameMod m : values())
			if (m.flag == flag) return m;
		return null;
	}

	public static EnumSet<GameMod> fromFlags(long flags) {
		EnumSet<GameMod> set = EnumSet.noneOf(GameMod.class);
		for (GameMod m : values()) if ((flags & m.flag) == m.flag) set.add(m);
		return set;
	}

	public long getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
