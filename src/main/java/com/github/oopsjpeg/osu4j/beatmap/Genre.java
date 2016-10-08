package com.github.oopsjpeg.osu4j.beatmap;

public enum Genre {
    ANY(0),
    UNSPECIFIED(1),
    VIDEO_GAME(2),
    ANIME(3),
    ROCK(4),
    POP(5),
    OTHER(6),
    NOVELTY(7),
    HIP_HOP(9),
    ELECTRONICS(10);

    private int id;

    private Genre(int id) {
        this.id = id;
    }

    private static Genre[] ALL_VALUES = Genre.values();

    public static Genre getById(int id) {
        for (Genre mode : ALL_VALUES) {
            if (mode.id == id) {
                return mode;
            }
        }
        throw new IllegalArgumentException("No approval mode with id: " + id);
    }
}
