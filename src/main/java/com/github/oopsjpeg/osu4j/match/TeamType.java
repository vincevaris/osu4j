package com.github.oopsjpeg.osu4j.match;

public enum TeamType {
    HEAD_TO_HEAD(0),
    TAG_COOP(1),
    TEAM_VS(2),
    TAG_TEAM_VS(3);

    private int id;

    private TeamType(int id) {
        this.id = id;
    }

    private static TeamType[] ALL_VALUES = TeamType.values();

    public static TeamType getById(int id) {
        for (TeamType mode : ALL_VALUES) {
            if (mode.id == id) {
                return mode;
            }
        }
        throw new IllegalArgumentException("No approval mode with id: " + id);
    }
}
