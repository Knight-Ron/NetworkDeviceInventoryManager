package services;

import models.NetworkDevice;
import java.util.*;
import java.io.*;

public class InventoryManager {
    private List<NetworkDevice> devices;

    public InventoryManager() {
        devices = new ArrayList<>();
    }

    public void addDevice(NetworkDevice device) {
        devices.add(device);
        System.out.println("Device added successfully.");
    }

    public void viewDevices() {
        if (devices.isEmpty()) {
            System.out.println("No devices found.");
            return;
        }
        for (NetworkDevice device : devices) {
            System.out.println("----------------------------------------");
            System.out.println(device.toString());
        }
    }

    public void deactivateDevice(String id) {
        for (NetworkDevice d : devices) {
            if (d.getId().equalsIgnoreCase(id)) {
                d.deactivate();
                System.out.println("Device deactivated.");
                return;
            }
        }
        System.out.println("Device not found.");
    }

    public void activateDevice(String id) {
        for (NetworkDevice d : devices) {
            if (d.getId().equalsIgnoreCase(id)) {
                if (d.isActive()) {
                    System.out.println("Device is already active.");
                } else {
                    // Reactivate
                    d.activate();
                    System.out.println("Device reactivated.");
                }
                return;
            }
        }
        System.out.println("Device not found.");
    }


    public void exportToCSV(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("ID,Hostname,IP,OS,Type,Location,Active");
            for (NetworkDevice d : devices) {
                writer.println(d.toCSV());
            }
            System.out.println("Device list exported to: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to export CSV: " + e.getMessage());
        }
    }

    public void writeConfigToFile(NetworkDevice device, String configContent, String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                System.out.println("Failed to create directory: " + directoryPath);
                return;
            }
        }

        String fileName = directoryPath + "/" + device.getId() + "_config.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("# Configuration for device: " + device.getHostname());
            writer.println(configContent);
            System.out.println("Configuration saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing config file: " + e.getMessage());
        }
    }

    public List<NetworkDevice> getDevices() {
        return devices;
    }

    public void readConfigFile(String deviceId, String directoryPath) {
        String fileName = directoryPath + "/" + deviceId + "_config.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No config file found for device: " + deviceId);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("\n--- Config for " + deviceId + " ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("-------------------------------");
        } catch (IOException e) {
            System.out.println("Failed to read config: " + e.getMessage());
        }
    }

    public boolean deleteDevice(String id) {
        Iterator<NetworkDevice> iterator = devices.iterator();
        while (iterator.hasNext()) {
            NetworkDevice d = iterator.next();
            if (d.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
