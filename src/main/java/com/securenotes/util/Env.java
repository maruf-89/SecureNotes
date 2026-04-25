package com.securenotes.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Env {

    static HashMap<String, String> map = new HashMap<>();
    static boolean loaded = false;

    static void load() {
        if (loaded) return;
        loaded = true;

        try (BufferedReader br = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("=")) {
                    String[] p = line.split("=");
                    map.put(p[0].trim(), p[1].trim());
                }
            }
        } catch (Exception e) {
            System.out.println("env missing");
        }
    }

    public static String get(String key) {
        load();
        return map.get(key);
    }
}