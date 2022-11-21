package de.linzn.wakeupDevices.actions;


import de.linzn.wakeupDevices.Computer;
import de.linzn.wakeupDevices.OSType;
import de.linzn.wakeupDevices.WakeupDevicesPlugin;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.taskManagment.operations.defaultOperations.ShellOperation;

public class ShutdownDevice implements DeviceActions {
    private final Computer computer;

    public ShutdownDevice(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void runAction() {
        ShellOperation shellOperation = new ShellOperation();
        shellOperation.setUseOutput(false);
        shellOperation.setUseSSH(true);
        shellOperation.setSshUser(computer.getStaticSSHUsername());
        shellOperation.setSshHost(computer.getStaticIP());
        shellOperation.setSshPort(computer.getStaticSSHPort());

        if(computer.getOsType() == OSType.WINDOWS) {
            shellOperation.setScriptCommand("shutdown -s -f -t 5");
        } else if(computer.getOsType() == OSType.LINUX){
            shellOperation.setScriptCommand("shutdown -h now");
        }
        STEMSystemApp.getInstance().getScheduler().runTask(WakeupDevicesPlugin.wakeupDevicesPlugin, shellOperation);
    }
}
