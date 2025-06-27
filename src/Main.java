import services.InventoryManager;
import models.NetworkDevice;

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
            System.out.println("6. Export to CSV");
            System.out.println("7. Save Config File");
            System.out.println("8. View Config File");
            System.out.println("9. Exit");

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

                    NetworkDevice device = new NetworkDevice(id, hostname, ip, os, type, location);
                    manager.addDevice(device);
                    break;

                case "2":
                    manager.viewDevices();
                    break;

                    case "3":
                        System.out.print("Enter Device ID to delete: ");
                        String deleteId = scanner.nextLine().trim();
                        boolean removed = manager.deleteDevice(deleteId);
                        if (removed) {
                            System.out.println("Device deleted.");
                        } else {
                            System.out.println("Device not found.");
                        }
                        break;


                case "4":
                    System.out.print("Enter Device ID to deactivate: ");
                    String deviceId = scanner.nextLine();
                    manager.deactivateDevice(deviceId);
                    break;

                    case "5":
                        System.out.print("Enter Device ID to activate: ");
                        String activateId = scanner.nextLine();
                        manager.activateDevice(activateId);
                        break;

                case "6":
                    System.out.println("Exporting to: ../data/devices.csv");
                    String filePath = "../data/devices.csv";
                    manager.exportToCSV(filePath);
                    break;

                case "7":
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
                        System.out.print("Enter config content (single-line): ");
                        String config = scanner.nextLine();
                        manager.writeConfigToFile(target, config, "../data/configs");
                    } else {
                        System.out.println("Device not found.");
                    }
                    break;

                case "8":
                    System.out.print("Enter Device ID to view config: ");
                    String configId = scanner.nextLine().trim();
                    manager.readConfigFile(configId, "../data/configs");
                    break;

                case "9":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
