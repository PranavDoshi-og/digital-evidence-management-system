package com.dems.main;

import com.dems.dao.UserDAO;
import com.dems.model.User;
import java.util.Scanner;

public class DEMSMain {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAO();
    private static User currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                System.out.println("\n1. Login\n2. Register\n3. Exit");
                int choice = scanner.nextInt(); scanner.nextLine();
                if (choice == 1) handleLogin();
                else if (choice == 2) handleRegister();
                else System.exit(0);
            } else {
                System.out.println("\n--- Logged in as: " + currentUser.getUsername() + " ---");
                System.out.println("1. Logout\n2. Exit");
                int choice = scanner.nextInt(); scanner.nextLine();
                if (choice == 1) currentUser = null;
                else System.exit(0);
            }
        }
    }

    private static void handleRegister() {
        System.out.print("Username: "); String u = scanner.nextLine();
        System.out.print("Password: "); String p = scanner.nextLine();
        System.out.print("Role: "); String r = scanner.nextLine();
        if (userDAO.registerUser(u, p, r)) System.out.println("Success!");
    }

    private static void handleLogin() {
        System.out.print("Username: "); String u = scanner.nextLine();
        System.out.print("Password: "); String p = scanner.nextLine();
        currentUser = userDAO.login(u, p);
        if (currentUser == null) System.out.println("Failed!");
    }
}