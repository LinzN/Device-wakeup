package de.linzn.wakeupDevices;


import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.taskManagment.operations.defaultOperations.ShellOperation;

public class WakeDevice {
    private final String deviceName;
    private final String mac;

    WakeDevice(String deviceName) {
        this.deviceName = deviceName;
        this.mac = WakeupDevicesPlugin.wakeupDevicesPlugin.getDefaultConfig().getString("devices." + deviceName + ".mac");
    }

    public void wakeup() {
        ShellOperation shellOperation = new ShellOperation();
        shellOperation.setUseOutput(false);
        shellOperation.setUseSSH(false);
        shellOperation.setScriptCommand("etherwake " + mac.toLowerCase());
        STEMSystemApp.LOGGER.INFO("Wakeup device " + deviceName + " with mac " + mac);
        STEMSystemApp.getInstance().getScheduler().runTask(WakeupDevicesPlugin.wakeupDevicesPlugin, shellOperation);
    }
}
