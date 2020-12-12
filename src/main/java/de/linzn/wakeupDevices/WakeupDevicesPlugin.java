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

package de.linzn.wakeupDevices;


import de.linzn.restfulapi.RestFulApiPlugin;
import de.linzn.wakeupDevices.push.POST_WakeupDevice;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.modules.pluginModule.STEMPlugin;

public class WakeupDevicesPlugin extends STEMPlugin {

    public static WakeupDevicesPlugin wakeupDevicesPlugin;


    public WakeupDevicesPlugin() {
        wakeupDevicesPlugin = this;
    }

    @Override
    public void onEnable() {
        setUpConfig();
        STEMSystemApp.getInstance().getCommandModule().registerCommand("wakeup", new WakeCommand());
        RestFulApiPlugin.restFulApiPlugin.registerIPostJSONClass(new POST_WakeupDevice());
    }

    @Override
    public void onDisable() {

    }

    private void setUpConfig() {
        if (!this.getDefaultConfig().contains("devices")) {
            this.getDefaultConfig().getString("devices.testdevice1.mac", "00:00:00:00:00:01");
            this.getDefaultConfig().getString("devices.testdevice2.mac", "00:00:00:00:00:02");
        }
        this.getDefaultConfig().save();
    }

}
