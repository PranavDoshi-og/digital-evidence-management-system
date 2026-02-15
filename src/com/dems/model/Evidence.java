package com.dems.model;

import java.sql.Timestamp;

public class Evidence {
    private int id;
    private String fileName;
    private String filePath;
    private String fileHash; // The SHA-256 integrity check
    private String description;
    private int uploadedBy;
    private Timestamp uploadTime;

    public Evidence(int id, String fileName, String filePath, String fileHash, String description, int uploadedBy, Timestamp uploadTime) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileHash = fileHash;
        this.description = description;
        this.uploadedBy = uploadedBy;
        this.uploadTime = uploadTime;
    }

    // Getters for displaying evidence info in the console
    public int getId() { return id; }
    public String getFileName() { return fileName; }
    public String getFileHash() { return fileHash; }
    public String getDescription() { return description; }
}