package com.securenotes.db;

import com.securenotes.util.Env;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {

    public static Connection get() {
        try {
            return DriverManager.getConnection(
                    Env.get("DB_URL"),
                    Env.get("DB_USER"),
                    Env.get("DB_PASS")
            );
        } catch (Exception e) {
            System.out.println("db fail");
            return null;
        }
    }
}