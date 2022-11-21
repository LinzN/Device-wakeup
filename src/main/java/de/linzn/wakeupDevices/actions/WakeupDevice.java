package de.linzn.wakeupDevices.actions;


import de.linzn.wakeupDevices.Computer;
import de.linzn.wakeupDevices.WakeupDevicesPlugin;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.taskManagment.operations.defaultOperations.ShellOperation;

public class WakeupDevice implements DeviceActions {
    private final Computer computer;

    public WakeupDevice(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void runAction() {
        ShellOperation shellOperation = new ShellOperation();
        shellOperation.setUseOutput(false);
        shellOperation.setUseSSH(false);
        shellOperation.setScriptCommand("etherwake " + computer.getMacAddress().toLowerCase());
        STEMSystemApp.getInstance().getScheduler().runTask(WakeupDevicesPlugin.wakeupDevicesPlugin, shellOperation);
    }
}
