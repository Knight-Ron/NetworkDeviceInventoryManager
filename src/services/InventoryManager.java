package services;

import models.NetworkDevice;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventoryManager {
    private List<NetworkDevice> devices;
    private static final String CSV_PATH = "data/devices.csv";
    private static final String LOG_PATH = "../logs/actions.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public InventoryManager() {
        devices = new ArrayList<>();
        loadFromCSV(CSV_PATH);
    }

    private void loadFromCSV(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 9) continue;
                NetworkDevice device = new NetworkDevice(
                    parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                    Boolean.parseBoolean(parts[6]),
                    LocalDateTime.parse(parts[7], FORMATTER),
                    parts[8]
                );
                devices.add(device);
            }
        } catch (IOException e) {
            logAction("[ERROR] Failed to load devices: " + e.getMessage());
        }
    }

    public void saveToCSV(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("ID,Hostname,IP,OS,Type,Location,Active,CreatedAt,AddedBy");
            for (NetworkDevice d : devices) {
                writer.println(d.toCSV());
            }
        } catch (IOException e) {
            logAction("[ERROR] Failed to save devices: " + e.getMessage());
        }
    }

    public boolean addDevice(NetworkDevice device) {
        if (isDuplicateId(device.getId())) return false;
        if (!isValidIp(device.getIpAddress())) return false;
        devices.add(device);
        saveToCSV(CSV_PATH);
        logAction("Device added: " + device.getId());
        return true;
    }

    public List<String> getDeviceSummaries() {
        List<String> summaries = new ArrayList<>();
        for (NetworkDevice d : devices) {
            summaries.add(d.toString());
        }
        return summaries;
    }

    public boolean deactivateDevice(String id) {
        for (NetworkDevice d : devices) {
            if (d.getId().equalsIgnoreCase(id)) {
                d.deactivate();
                saveToCSV(CSV_PATH);
                logAction("Device deactivated: " + id);
                return true;
            }
        }
        return false;
    }

    public boolean activateDevice(String id) {
        for (NetworkDevice d : devices) {
            if (d.getId().equalsIgnoreCase(id)) {
                d.activate();
                saveToCSV(CSV_PATH);
                logAction("Device activated: " + id);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDevice(String id) {
        Iterator<NetworkDevice> iterator = devices.iterator();
        while (iterator.hasNext()) {
            NetworkDevice d = iterator.next();
            if (d.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                saveToCSV(CSV_PATH);
                logAction("Device deleted: " + id);
                return true;
            }
        }
        return false;
    }

    public void writeConfigToFile(NetworkDevice device, String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) dir.mkdirs();

        String fileName = directoryPath + "/" + device.getId() + "_config.conf";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("[device]");
            writer.println("id = " + device.getId());
            writer.println("hostname = " + device.getHostname());
            writer.println("ip = " + device.getIpAddress());
            writer.println("os = " + device.getOsType());
            writer.println("type = " + device.getDeviceType());
            writer.println("location = " + device.getLocation());
            writer.println("active = " + device.isActive());
            writer.println("created_at = " + device.getCreatedAt());
            writer.println("added_by = " + device.getAddedBy());
            writer.println("saved_on = " + LocalDateTime.now());

            logAction("Config saved for: " + device.getId());
        } catch (IOException e) {
            logAction("[ERROR] Config save failed for " + device.getId() + ": " + e.getMessage());
        }
    }

    public List<NetworkDevice> getDevices() {
        return devices;
    }

    public boolean isDuplicateId(String id) {
        for (NetworkDevice d : devices) {
            if (d.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public boolean isValidIp(String ip) {
        return ip.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");
    }

    private void logAction(String action) {
        File dir = new File("logs");
        if (!dir.exists()) dir.mkdirs();

        try (FileWriter fw = new FileWriter(LOG_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            out.println("[" + timestamp + "] " + action);
        } catch (IOException e) {
            // suppress logging failure
        }
    }
} 
