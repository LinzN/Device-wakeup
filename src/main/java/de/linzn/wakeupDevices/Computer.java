package de.linzn.wakeupDevices;

import de.stem.stemSystem.STEMSystemApp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class Computer {

    private DeviceStatus deviceStatus;
    private final String name;
    private final OSType osType;
    private final String macAddress;
    private final String staticIP;
    private final String staticSSHUsername;
    private final int staticSSHPort;

    public Computer(String name, OSType osType, String macAddress, String staticIP, int staticSSHPort, String staticSSHUsername){
        this.name = name;
        this.osType = osType;
        this.macAddress = macAddress;
        this.staticIP = staticIP;
        this.staticSSHPort = staticSSHPort;
        this.staticSSHUsername = staticSSHUsername;
        this.deviceStatus = DeviceStatus.UNKNOWN;
        STEMSystemApp.getInstance().getScheduler().runRepeatScheduler(WakeupDevicesPlugin.wakeupDevicesPlugin, this::request_device_status, 1, 2, TimeUnit.SECONDS);
    }

    private void request_device_status(){
        DeviceStatus newStatus;
        try {
            if(InetAddress.getByName(staticIP).isReachable(1500)){
                newStatus = DeviceStatus.ONLINE;
            } else {
                newStatus = DeviceStatus.OFFLINE;
            }
        } catch (IOException e) {
            newStatus = DeviceStatus.UNKNOWN;
            STEMSystemApp.LOGGER.ERROR(e);
        }
        if(this.deviceStatus != newStatus){
            STEMSystemApp.LOGGER.INFO("Computer " + this.name + " changed status to " + newStatus.name());
        }
        this.deviceStatus = newStatus;
    }


    public String getName() {
        return name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getStaticIP() {
        return staticIP;
    }

    public int getStaticSSHPort() {
        return staticSSHPort;
    }

    public OSType getOsType() {
        return osType;
    }

    public String getStaticSSHUsername() {
        return staticSSHUsername;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }
}
