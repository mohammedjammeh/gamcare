package com.projects.gamcare.core;

public class StringTool {
    public static String capitalise(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
