import services.InventoryManager;
import models.NetworkDevice;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        while (true) {
            System.out.println("\n=== Network Device Inventory Manager ===");
            System.out.println("1. Add Device");
            System.out.println("2. View Devices");
            System.out.println("3. Delete Device");
            System.out.println("4. Deactivate Device");
            System.out.println("5. Activate Device");
            System.out.println("6. Save Config File");
            System.out.println("7. Exit");

            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Device ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Hostname: ");
                    String hostname = scanner.nextLine();
                    System.out.print("Enter IP Address: ");
                    String ip = scanner.nextLine();
                    System.out.print("Enter OS Type: ");
                    String os = scanner.nextLine();
                    System.out.print("Enter Device Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter your name (added by): ");
                    String addedBy = scanner.nextLine();

                    NetworkDevice device = new NetworkDevice(id, hostname, ip, os, type, location, addedBy);
                    boolean added = manager.addDevice(device);
                    if (added) {
                        System.out.println("Device added successfully.");
                    } else {
                        System.out.println("Failed to add device. Duplicate ID or invalid IP.");
                    }
                    break;

                case "2":
                    List<String> summaries = manager.getDeviceSummaries();
                    if (summaries.isEmpty()) {
                        System.out.println("No devices found.");
                    } else {
                        for (String info : summaries) {
                            System.out.println("----------------------------------------");
                            System.out.println(info);
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter Device ID to delete: ");
                    String deleteId = scanner.nextLine().trim();
                    boolean removed = manager.deleteDevice(deleteId);
                    System.out.println(removed ? "Device deleted." : "Device not found.");
                    break;

                case "4":
                    System.out.print("Enter Device ID to deactivate: ");
                    String deviceId = scanner.nextLine();
                    boolean deactivated = manager.deactivateDevice(deviceId);
                    System.out.println(deactivated ? "Device deactivated." : "Device not found.");
                    break;

                case "5":
                    System.out.print("Enter Device ID to activate: ");
                    String activateId = scanner.nextLine();
                    boolean activated = manager.activateDevice(activateId);
                    System.out.println(activated ? "Device activated." : "Device not found.");
                    break;

                case "6":
                    System.out.print("Enter Device ID to save config: ");
                    String targetId = scanner.nextLine();
                    NetworkDevice target = null;
                    for (NetworkDevice d : manager.getDevices()) {
                        if (d.getId().equalsIgnoreCase(targetId)) {
                            target = d;
                            break;
                        }
                    }
                    if (target != null) {
                        manager.writeConfigToFile(target, "data/configs");
                        System.out.println("Config snapshot saved.");
                    } else {
                        System.out.println("Device not found.");
                    }
                    break;

                case "7":
                    System.out.println("Exiting... Goodbye.");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
