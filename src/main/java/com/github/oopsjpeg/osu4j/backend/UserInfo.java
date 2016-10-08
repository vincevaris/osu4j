package com.github.oopsjpeg.osu4j.backend;

public class UserInfo {
    private String userID;
    private boolean isTextualID;

    private UserInfo(String userID, boolean isTextual) {
        this.userID = userID;
        this.isTextualID = isTextual;
    }

    public String getUserID() {
        return userID;
    }

    public boolean isTextualID() {
        return isTextualID;
    }

    public static UserInfo create(String userName) {
        return new UserInfo(userName, true);
    }

    public static UserInfo create(int userID) {
        return new UserInfo(Integer.toString(userID), false);
    }
}
