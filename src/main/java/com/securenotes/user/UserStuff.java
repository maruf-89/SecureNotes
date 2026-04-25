package com.securenotes.user;

import com.securenotes.db.Db;
import com.securenotes.util.Pass;

import java.sql.Connection;
import java.util.Scanner;

public class UserStuff {

    public static void reg(Scanner sc) {

        System.out.print("user: ");
        String u = sc.nextLine();

        System.out.print("pass: ");
        String p = sc.nextLine();

        try (Connection c = Db.get()) {

            var ps = c.prepareStatement(
                    "INSERT INTO users(username,password,role) VALUES(?,?,?)"
            );

            ps.setString(1, u);
            ps.setString(2, Pass.hash(p));
            ps.setString(3, "USER");

            ps.executeUpdate();

            System.out.println("ok");

        } catch (Exception e) {
            System.out.println("user exists");
        }
    }

    public static int login(Scanner sc) {

        System.out.print("user: ");
        String u = sc.nextLine();

        System.out.print("pass: ");
        String p = sc.nextLine();

        try (Connection c = Db.get()) {

            var ps = c.prepareStatement(
                    "SELECT * FROM users WHERE username=?"
            );

            ps.setString(1, u);

            var rs = ps.executeQuery();

            if (rs.next()) {
                if (Pass.check(p, rs.getString("password"))) {
                    return rs.getInt("id");
                }
            }

        } catch (Exception e) {}

        return -1;
    }

    public static String role(int id) {

        try (var c = Db.get()) {

            var ps = c.prepareStatement(
                    "SELECT role FROM users WHERE id=?"
            );

            ps.setInt(1, id);

            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (Exception e) {}

        return "USER";
    }
}