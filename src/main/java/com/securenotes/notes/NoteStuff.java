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

    public static void edit(Scanner sc, int uid) {

        System.out.print("note id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("new text: ");
        String t = sc.nextLine();

        try (var c = Db.get()) {

            var ps = c.prepareStatement(
                    "UPDATE notes SET text=? WHERE id=? AND user_id=?"
            );

            ps.setString(1, t);
            ps.setInt(2, id);
            ps.setInt(3, uid);

            ps.executeUpdate();

        } catch (Exception e) {}
    }

    public static void delete(Scanner sc, int uid) {

        System.out.print("note id: ");
        int id = Integer.parseInt(sc.nextLine());

        try (var c = Db.get()) {

            var ps = c.prepareStatement(
                    "DELETE FROM notes WHERE id=? AND user_id=?"
            );

            ps.setInt(1, id);
            ps.setInt(2, uid);

            ps.executeUpdate();

        } catch (Exception e) {}
    }

    public static void showAll() {

        try (var c = Db.get()) {

            var ps = c.prepareStatement(
                    "SELECT notes.id, notes.text, users.username " +
                            "FROM notes JOIN users ON notes.user_id = users.id"
            );

            var rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("text") + " (" +
                                rs.getString("username") + ")"
                );
            }

        } catch (Exception e) {}
    }
}