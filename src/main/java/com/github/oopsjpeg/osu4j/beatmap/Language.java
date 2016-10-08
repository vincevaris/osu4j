package com.github.oopsjpeg.osu4j.beatmap;

public enum Language {
    ANY(0),
    OTHER(1),
    ENGLISH(2),
    JAPANESE(3),
    CHINESE(4),
    INSTRUMENTAL(5),
    KOREAN(6),
    FRENCH(7),
    GERMAN(8),
    SWEDISH(9),
    SPANISH(10),
    ITALIAN(11);

    private int id;

    private Language(int id) {
        this.id = id;
    }

    private static Language[] ALL_VALUES = Language.values();

    public static Language getById(int id) {
        for (Language mode : ALL_VALUES) {
            if (mode.id == id) {
                return mode;
            }
        }
        throw new IllegalArgumentException("No approval mode with id: " + id);
    }
}
