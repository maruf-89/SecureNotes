package com.securenotes.notes;

import com.securenotes.db.DatabaseConnection;

import java.util.Scanner;

public class NoteService {

    public static void createNote(Scanner sc, int uid) {

        System.out.print("text: ");
        String t = sc.nextLine();

        if (t.isEmpty()) {
            System.out.println("text cannot be empty");
            return;
        }

        try (var c = DatabaseConnection.get()) {

            if (c == null) {
                System.out.println("database error");
                return;
            }

            var ps = c.prepareStatement(
                    "INSERT INTO notes(text, user_id) VALUES(?,?)"
            );

            ps.setString(1, t);
            ps.setInt(2, uid);

            ps.executeUpdate();

            System.out.println("note added");

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void showUserNotes(int uid) {

        try (var c = DatabaseConnection.get()) {

            if (c == null) {
                System.out.println("database error");
                return;
            }

            var ps = c.prepareStatement(
                    "SELECT id, text FROM notes WHERE user_id=?"
            );

            ps.setInt(1, uid);

            var rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(rs.getInt("id") + " " + rs.getString("text"));
            }

            if (!found) {
                System.out.println("no notes found");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void edit(Scanner sc, int uid) {

        System.out.print("note id: ");
        String input = sc.nextLine();

        if (input.isEmpty()) {
            System.out.println("invalid input");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("must be a number");
            return;
        }

        System.out.print("new text: ");
        String t = sc.nextLine();

        if (t.trim().isEmpty()) {
            System.out.println("text cannot be empty");
            return;
        }

        try (var c = DatabaseConnection.get()) {

            if (c == null) {
                System.out.println("database error");
                return;
            }

            var ps = c.prepareStatement(
                    "UPDATE notes SET text=? WHERE id=? AND user_id=?"
            );

            ps.setString(1, t);
            ps.setInt(2, id);
            ps.setInt(3, uid);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("note updated");
            } else {
                System.out.println("note not found");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void deleteNote(Scanner sc, int uid) {

        System.out.print("note id: ");
        String input = sc.nextLine();

        if (input.isEmpty()) {
            System.out.println("invalid input");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("must be a number");
            return;
        }

        try (var c = DatabaseConnection.get()) {

            if (c == null) {
                System.out.println("database error");
                return;
            }

            var ps = c.prepareStatement(
                    "DELETE FROM notes WHERE id=? AND user_id=?"
            );

            ps.setInt(1, id);
            ps.setInt(2, uid);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("note deleted");
            } else {
                System.out.println("note not found");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void adminShowAll() {

        try (var c = DatabaseConnection.get()) {

            if (c == null) {
                System.out.println("database error");
                return;
            }

            var ps = c.prepareStatement(
                    "SELECT notes.id, notes.text, users.username " +
                            "FROM notes JOIN users ON notes.user_id = users.id"
            );

            var rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("text") + " (" +
                                rs.getString("username") + ")"
                );
            }

            if (!found) {
                System.out.println("no notes found");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void adminDelete(Scanner sc) {

        System.out.print("note id: ");
        String input = sc.nextLine();

        if (input.isEmpty()) {
            System.out.println("invalid input");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("must be a number");
            return;
        }

        try (var c = DatabaseConnection.get()) {

            if (c == null) {
                System.out.println("database error");
                return;
            }

            var ps = c.prepareStatement(
                    "DELETE FROM notes WHERE id=?"
            );

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("note deleted");
            } else {
                System.out.println("note not found");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}