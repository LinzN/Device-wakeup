package de.linzn.wakeupDevices;

import de.linzn.wakeupDevices.actions.ShutdownDevice;
import de.linzn.wakeupDevices.actions.WakeupDevice;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.modules.commandModule.ICommand;

public class ComputerCommand implements ICommand {
    @Override
    public boolean executeTerminal(String[] args) {
        if (args.length > 1) {
            Computer computer = WakeupDevicesPlugin.wakeupDevicesPlugin.getComputerManager().getComputerByName(args[1]);
            if(computer == null){
                STEMSystemApp.LOGGER.WARNING("No computer found with name " + args[1] + " in device folder!");
                return true;
            }

            if(args[0].equalsIgnoreCase("status")){
                STEMSystemApp.LOGGER.INFO("Status: " + computer.getDeviceStatus().name());
            }else if (args[0].equalsIgnoreCase("wakeup")){
                new WakeupDevice(computer).runAction();
                STEMSystemApp.LOGGER.INFO("Wakeup signal send!");
            }else if (args[0].equalsIgnoreCase("shutdown")){
                new ShutdownDevice(computer).runAction();
                STEMSystemApp.LOGGER.INFO("Shutdown signal send!");
            } else {
                STEMSystemApp.LOGGER.WARNING("No action found with name " + args[0] + "!");
            }
        }
        return true;
    }
}
