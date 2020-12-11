package de.linzn.wakeupDevices;

import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.modules.commandModule.ICommand;

public class WakeCommand implements ICommand {
    @Override
    public boolean executeTerminal(String[] strings) {
        if (strings.length > 0) {
            String deviceName = strings[0];
            WakeDevice device = DeviceFinder.getDevice(deviceName);
            if (device != null) {
                device.wakeup();
            } else {
                STEMSystemApp.LOGGER.LIVE("No device found with this name in the config file!");
            }
        } else {
            STEMSystemApp.LOGGER.LIVE("Not enough input for device wake");
        }
        return true;
    }
}
