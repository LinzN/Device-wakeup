package de.linzn.wakeupDevices;

public class DeviceFinder {

    public static WakeDevice getDevice(String name) {
        if (WakeupDevicesPlugin.wakeupDevicesPlugin.getDefaultConfig().contains("devices." + name.toLowerCase())) {
            WakeDevice wakeDevice = new WakeDevice(name.toLowerCase());
            return wakeDevice;
        }
        return null;
    }
}
