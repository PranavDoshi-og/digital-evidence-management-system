package com.dems.main;

import com.dems.dao.*;
import com.dems.model.User;
import com.dems.util.HashUtil;
import java.util.Scanner;

public class DEMSMain {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAO();
    private static EvidenceDAO evidenceDAO = new EvidenceDAO();
    private static AuditDAO auditDAO = new AuditDAO();
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("--- Welcome to Digital Evidence Management System (DEMS) ---");
        while (true) {
            if (currentUser == null) showInitialMenu();
            else showMainMenu();
        }
    }

    private static void showInitialMenu() {
        System.out.println("\n1. Login\n2. Register\n3. Exit");
        System.out.print("Select Option: ");
        int choice = scanner.nextInt(); scanner.nextLine(); 

        if (choice == 1) handleLogin();
        else if (choice == 2) handleRegistration();
        else System.exit(0);
    }

    private static void handleRegistration() {
        System.out.print("Enter Username: "); String user = scanner.nextLine();
        System.out.print("Enter Password: "); String pass = scanner.nextLine();
        System.out.print("Role (ADMIN/INVESTIGATOR): "); String role = scanner.nextLine();

        if (userDAO.registerUser(user, pass, role)) System.out.println("✅ Registration Successful!");
        else System.out.println("❌ Registration Failed.");
    }

    private static void handleLogin() {
        System.out.print("Username: "); String user = scanner.nextLine();
        System.out.print("Password: "); String pass = scanner.nextLine();
        currentUser = userDAO.login(user, pass);
        if (currentUser != null) System.out.println("✅ Welcome, " + currentUser.getUsername());
        else System.out.println("❌ Invalid Credentials.");
    }

    private static void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Add Evidence Record");
        System.out.println("2. Logout");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
        int choice = scanner.nextInt(); scanner.nextLine();

        switch (choice) {
            case 1: handleAddEvidence(); break;
            case 2: currentUser = null; break;
            case 3: System.exit(0);
        }
    }

    private static void handleAddEvidence() {
        System.out.print("Evidence File Name: "); String name = scanner.nextLine();
        System.out.print("Full File Path: "); String path = scanner.nextLine();
        System.out.print("Description: "); String desc = scanner.nextLine();

        // Calculate a unique hash for file integrity
        String fileHash = HashUtil.generateHash(path + name); 

        // Save to DB and get the generated ID
        int evidenceId = evidenceDAO.addEvidence(name, path, fileHash, desc, currentUser.getUserId());

        if (evidenceId != -1) {
            // Log the action for Chain of Custody
            auditDAO.logAction(evidenceId, currentUser.getUserId(), "EVIDENCE_UPLOADED");
            System.out.println("✅ Evidence Added! Integrity Hash: " + fileHash);
        } else {
            System.out.println("❌ Error adding evidence.");
        }
    }
}