package com.securenotes.menu;

import com.securenotes.notes.NoteService;
import com.securenotes.user.UserService;

import java.util.Scanner;

public class Menu {

    public static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1 reg");
            System.out.println("2 login");
            System.out.println("0 exit");

            String v = sc.nextLine();

            switch (v) {
                case "1":
                    UserService.reg(sc);
                    break;

                case "2":
                    int id = UserService.login(sc);

                    if (id != -1) {
                        String role = UserService.role(id);

                        if (role.equals("ADMIN")) {
                            adminMenu(sc, id);
                        } else {
                            user(sc, id);
                        }
                    } else {
                        System.out.println("fail");
                    }
                    break;

                case "0":
                    return;

                default:
                    System.out.println("invalid choice");
            }
        }
    }

    public static void user(Scanner sc, int id) {

        while (true) {

            System.out.println("\n- USER MENU -");
            System.out.println("1 add");
            System.out.println("2 show");
            System.out.println("3 edit");
            System.out.println("4 delete");
            System.out.println("5 change password");
            System.out.println("0 logout");

            String v = sc.nextLine();

            switch (v) {
                case "1":
                    NoteService.createNote(sc, id);
                    break;
                case "2":
                    NoteService.showUserNotes(id);
                    break;
                case "3":
                    NoteService.edit(sc, id);
                    break;
                case "4":
                    NoteService.deleteNote(sc, id);
                    break;
                case "5":
                    UserService.changePassword(sc, id);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid choice");
            }
        }
    }

    public static void adminMenu(Scanner sc, int id) {

        while (true) {

            System.out.println("\n- ADMIN MENU -");
            System.out.println("1 add");
            System.out.println("2 show");
            System.out.println("3 edit");
            System.out.println("4 delete");
            System.out.println("5 change password");
            System.out.println("6 show all notes");
            System.out.println("7 delete any note");
            System.out.println("0 logout");

            String v = sc.nextLine();

            switch (v) {
                case "1":
                    NoteService.createNote(sc, id);
                    break;
                case "2":
                    NoteService.showUserNotes(id);
                    break;
                case "3":
                    NoteService.edit(sc, id);
                    break;
                case "4":
                    NoteService.deleteNote(sc, id);
                    break;
                case "5":
                    UserService.changePassword(sc, id);
                    break;
                case "6":
                    NoteService.adminShowAll();
                    break;
                case "7":
                    NoteService.adminDelete(sc);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid choice");
            }
        }
    }
}