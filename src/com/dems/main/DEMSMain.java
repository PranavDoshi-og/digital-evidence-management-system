package com.dems.main;

import com.dems.dao.UserDAO;
import com.dems.model.User;
import java.util.Scanner;

public class DEMSMain {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAO();
    private static User currentUser = null; // Tracks who is logged in

    public static void main(String[] args) {
        System.out.println("--- Welcome to Digital Evidence Management System (DEMS) ---");
        
        while (true) {
            if (currentUser == null) {
                showInitialMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showInitialMenu() {
        System.out.println("\n1. Login\n2. Register\n3. Exit");
        System.out.print("Select Option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleRegistration();
                break;
            case 3:
                System.out.println("Exiting System...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void handleRegistration() {
        System.out.print("Enter Username: ");
        String user = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();
        System.out.print("Role (ADMIN/INVESTIGATOR): ");
        String role = scanner.nextLine();

        // Use UserDAO to save to MySQL
        if (userDAO.registerUser(user, pass, role)) {
            System.out.println("✅ Registration Successful!");
        } else {
            System.out.println("❌ Registration Failed. Username might already exist.");
        }
    }

    private static void handleLogin() {
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        // Check credentials against the DB
        currentUser = userDAO.login(user, pass);
        if (currentUser != null) {
            System.out.println("✅ Welcome, " + currentUser.getUsername() + " [" + currentUser.getRole() + "]");
        } else {
            System.out.println("❌ Invalid Credentials.");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Logout");
        System.out.println("2. Exit");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            currentUser = null;
            System.out.println("Logged out successfully.");
        } else {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }
}