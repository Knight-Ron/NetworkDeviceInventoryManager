package models;

import java.time.LocalDateTime;

public class NetworkDevice {
    private String id;
    private String hostname;
    private String ipAddress;
    private String osType;
    private String deviceType;
    private String location;
    private boolean isActive;
    private LocalDateTime createdAt;
    private String addedBy;

    // constructor for new devices (default active)
    public NetworkDevice(String id, String hostname, String ipAddress, String osType, String deviceType, String location, String addedBy) {
        this.id = id;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.osType = osType;
        this.deviceType = deviceType;
        this.location = location;
        this.addedBy = addedBy;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
    }

    // constructor for loading from CSV
    public NetworkDevice(String id, String hostname, String ipAddress, String osType, String deviceType, String location, boolean isActive, LocalDateTime createdAt, String addedBy) {
        this.id = id;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.osType = osType;
        this.deviceType = deviceType;
        this.location = location;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.addedBy = addedBy;
    }

    public String getId() {
        return id;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getOsType() {
        return osType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getLocation() {
        return location;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public String toCSV() {
        return id + "," + hostname + "," + ipAddress + "," + osType + "," + deviceType + "," + location + "," + isActive + "," + createdAt + "," + addedBy;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "\nHostname: " + hostname +
               "\nIP Address: " + ipAddress +
               "\nOS Type: " + osType +
               "\nDevice Type: " + deviceType +
               "\nLocation: " + location +
               "\nStatus: " + (isActive ? "Active" : "Inactive") +
               "\nCreated At: " + createdAt +
               "\nAdded By: " + addedBy;
    }
} 
