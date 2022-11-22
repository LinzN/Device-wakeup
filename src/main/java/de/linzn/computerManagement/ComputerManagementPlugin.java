/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.computerManagement;


import de.linzn.computerManagement.webapi.ComputerWebapiHandler;
import de.linzn.simplyConfiguration.FileConfiguration;
import de.linzn.simplyConfiguration.provider.YamlConfiguration;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.modules.pluginModule.STEMPlugin;

import java.io.File;

public class ComputerManagementPlugin extends STEMPlugin {


    public static ComputerManagementPlugin computerManagementPlugin;
    private ComputerManager computerManager;
    private ComputerWebapiHandler computerWebapiHandler;

    public ComputerManagementPlugin() {
        computerManagementPlugin = this;
    }

    @Override
    public void onEnable() {
        setUpConfig();
        this.computerManager = new ComputerManager(this);
        this.computerWebapiHandler = new ComputerWebapiHandler(this);
        STEMSystemApp.getInstance().getCommandModule().registerCommand("computer", new ComputerCommand());
    }

    @Override
    public void onDisable() {

    }

    private void setUpConfig() {
        this.getDefaultConfig().save();
        File folder = new File(ComputerManagementPlugin.computerManagementPlugin.getDataFolder(), "devices");
        if(!folder.exists()){
            folder.mkdir();
            File exampleFile = new File(folder, "example.yml");
            FileConfiguration exampleConfigFile = YamlConfiguration.loadConfiguration(exampleFile);
            exampleConfigFile.set("device.name", "test");
            exampleConfigFile.set("device.osType", OSType.WINDOWS.name());
            exampleConfigFile.set("device.macAddress", "FF:FF:FF:FF:FF:FF");
            exampleConfigFile.set("device.staticIP", "10.0.0.1");
            exampleConfigFile.set("device.staticSSHPort", 22);
            exampleConfigFile.set("device.staticSSHUser", "testUser");
            exampleConfigFile.save();
        }
    }

    public ComputerManager getComputerManager() {
        return computerManager;
    }

    public ComputerWebapiHandler getComputerWebapiHandler() {
        return computerWebapiHandler;
    }
}
