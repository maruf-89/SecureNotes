package com.securenotes.util;

import org.mindrot.jbcrypt.BCrypt;

public class Pass {

    public static String hash(String p) {
        return BCrypt.hashpw(p, BCrypt.gensalt());
    }

    public static boolean check(String p, String h) {
        return BCrypt.checkpw(p, h);
    }
}