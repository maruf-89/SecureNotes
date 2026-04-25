package com.securenotes.notes;

import com.securenotes.db.Db;

import java.sql.Connection;
import java.util.Scanner;

public class NoteStuff {

    public static void add(Scanner sc, int uid) {

        System.out.print("text: ");
        String t = sc.nextLine();

        try (Connection c = Db.get()) {

            var ps = c.prepareStatement(
                    "INSERT INTO notes(text,user_id) VALUES(?,?)"
            );

            ps.setString(1, t);
            ps.setInt(2, uid);

            ps.executeUpdate();

        } catch (Exception e) {}
    }

    public static void show(int uid) {

        try (Connection c = Db.get()) {

            var ps = c.prepareStatement(
                    "SELECT * FROM notes WHERE user_id=?"
            );

            ps.setInt(1, uid);

            var rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("text"));
            }

        } catch (Exception e) {}
    }
}