# Network Device Inventory & Configuration Manager (Java CLI Project)

A Java-based command-line tool that simulates an enterprise-level inventory and configuration management system for network devices.
Ideal for demonstrating object-oriented design, audit logging, file I/O, CLI interaction, and system-thinking workflows often seen in SRE environments.

---

## ➽ Features

* Add new network devices with full metadata (ID, IP, Hostname, OS, etc.)
* Automatic timestamping (`createdAt`) and operator tracking (`addedBy`)
* View all registered devices in detailed or summary view
* Deactivate or reactivate any device from the CLI
* Delete devices from inventory with logs
* Save per-device configuration snapshots (`.conf` format)
* Export full inventory to CSV (with audit fields)
* Auto-create folders for configs and logs if not present
* Maintain a running log of all actions performed (`logs/actions.log`)

---

## ➽ Folder Structure

```
NetworkDeviceInventoryManager/
├── data/
│   ├── devices.csv          # Inventory exports
│   └── configs/             # Per-device config files
├── logs/
│   └── actions.log          # CLI command logs
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
6. Save Config File
7. Exit
```

Just enter the number for the action you want.

---

## ➽ Export, Config & Logs

* Inventory CSV: `data/devices.csv`
* Config files: `data/configs/{DEVICE_ID}_config.conf`
* Logs (all activity): `logs/actions.log`

> These folders will be created automatically by the tool if they don’t already exist.

---

## ➽ Author

Ronald Jacob (Knight Ron)