# Network Device Inventory & Configuration Manager (Java CLI Project)

A Java-based command-line tool that simulates an enterprise-level inventory and configuration management system for network devices.
Ideal for demonstrating object-oriented design, file I/O, CLI interaction, and system-thinking.

---

## ➽ Features

* Add new network devices with metadata
* View all registered devices
* Deactivate or reactivate a device
* Delete devices from inventory
* Save device configuration snapshots to individual text files
* View saved config files from the CLI
* Export full inventory to CSV

---

## ➽ Folder Structure

```
NetworkDeviceInventoryManager/
├── data/
│   ├── devices.csv          # Inventory exports
│   └── configs/             # Per-device config files
├── src/
│   ├── Main.java            # CLI entry point
│   ├── models/
│   │   └── NetworkDevice.java
│   └── services/
│       └── InventoryManager.java
├── README.md
└── .gitignore
```

---

## ➽ Requirements

* Java 8 or higher
* VS Code, IntelliJ, or any terminal/command line
* Git (optional, for cloning)

---

## ➽ How to Run the Project (From Scratch)

### 1. Clone the repository

```bash
git clone https://github.com/Knight-Ron/NetworkDeviceInventoryManager.git
cd NetworkDeviceInventoryManager/src
```

### 2. Compile the project

```bash
javac -d . Main.java services/InventoryManager.java models/NetworkDevice.java
```

### 3. Run the project

```bash
java Main
```

### 4. Use the CLI

You'll see a menu like:

```
=== Network Device Inventory Manager ===
1. Add Device
2. View Devices
3. Delete Device
4. Deactivate Device
5. Activate Device
6. Export to CSV
7. Save Config File
8. View Config File
9. Exit
```

Just enter the number for the action you want.

---

## ➽ Export & Config Details

* All exported inventory is saved to:
  `data/devices.csv`

* Config files are saved in:
  `data/configs/{DEVICE_ID}_config.txt`

> You don’t need to create these folders manually — the program will handle it automatically.

---

## ➽ Author

Ronald Jacob (Knight Ron)
