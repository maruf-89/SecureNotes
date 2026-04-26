package com.securenotes.menu;

import com.securenotes.notes.NoteStuff;
import com.securenotes.user.UserStuff;

import java.util.Scanner;

public class Menu {

    public static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1 reg");
            System.out.println("2 login");
            System.out.println("0 exit");

            String v = sc.nextLine();

            if (v.equals("1")) {
                UserStuff.reg(sc);
            }
            else if (v.equals("2")) {

                int id = UserStuff.login(sc);

                if (id != -1) {

                    String role = UserStuff.role(id);

                    if (role.equals("ADMIN")) {
                        adminMenu(sc);
                    } else {
                        user(sc, id);
                    }

                } else {
                    System.out.println("fail");
                }
            }
            else if (v.equals("0")) break;
        }
    }

    public static void user(Scanner sc, int id) {

        while (true) {

            System.out.println("\n1 add");
            System.out.println("2 show");
            System.out.println("3 edit");
            System.out.println("4 delete");
            System.out.println("5 change password");
            System.out.println("0 logout");

            String v = sc.nextLine();

            if (v.equals("1")) NoteStuff.add(sc, id);
            else if (v.equals("2")) NoteStuff.show(id);
            else if (v.equals("3")) NoteStuff.edit(sc, id);
            else if (v.equals("4")) NoteStuff.delete(sc, id);
            else if (v.equals("5")) UserStuff.changePassword(sc, id);
            else if (v.equals("0")) break;
        }
    }
}