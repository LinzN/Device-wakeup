package de.linzn.wakeupDevices;

import de.linzn.simplyConfiguration.FileConfiguration;
import de.linzn.simplyConfiguration.provider.YamlConfiguration;
import de.stem.stemSystem.modules.pluginModule.STEMPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ComputerManager {
    private final STEMPlugin stemPlugin;
    private final Map<String, Computer> computerMap;

    public ComputerManager(STEMPlugin stemPlugin){
        this.stemPlugin = stemPlugin;
        this.computerMap = new HashMap<>();;
        this.loadComputers();
    }

    private void loadComputers() {
        File folder = new File(WakeupDevicesPlugin.wakeupDevicesPlugin.getDataFolder(), "devices");

        if (folder.exists()) {
            for (final File file : folder.listFiles()) {
                if (file.getName().equalsIgnoreCase("example.yml")) {
                    continue;
                }

                FileConfiguration computerConfig = YamlConfiguration.loadConfiguration(file);

                String name = computerConfig.getString("device.name");
                OSType osType = OSType.valueOf(computerConfig.getString("device.osType"));
                String macAddress = computerConfig.getString("device.macAddress");
                String staticIP = computerConfig.getString("device.staticIP");
                int staticSSHPort = computerConfig.getInt("device.staticSSHPort");
                String statusSSHUser = computerConfig.getString("device.staticSSHUser");

                Computer computer = new Computer(name, osType, macAddress, staticIP, staticSSHPort, statusSSHUser);
                this.computerMap.put(computer.getName().toLowerCase(), computer);
            }
        }
    }

    public Computer getComputerByName(String name){
        return this.computerMap.get(name.toLowerCase());
    }
}
