package com.github.oopsjpeg.osu4j;

public enum ApprovalMode {
    QUALIFIED(3),
    APPROVED(2),
    RANKED(1),
    PENDING(0),
    WIP(-1),
    GRAVEYARD(-2);

    private static ApprovalMode[] ALL_VALUES = ApprovalMode.values();

    private final int id;

    private ApprovalMode(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public static ApprovalMode getById(int id) {
        for (ApprovalMode mode : ALL_VALUES) {
            if (mode.id == id) {
                return mode;
            }
        }
        throw new IllegalArgumentException("No approval mode with id: " + id);
    }
}
