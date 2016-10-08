package com.github.oopsjpeg.osu4j.match;

public enum ScoringType {
    BY_SCORE(0),
    BY_ACCURACY(1),
    BY_COMBO(2),
    BY_SCOREV2(3);

    private int id;

    private ScoringType(int id) {
        this.id = id;
    }

    private static ScoringType[] ALL_VALUES = ScoringType.values();

    public static ScoringType getById(int id) {
        for (ScoringType mode : ALL_VALUES) {
            if (mode.id == id) {
                return mode;
            }
        }
        throw new IllegalArgumentException("No approval mode with id: " + id);
    }
}
