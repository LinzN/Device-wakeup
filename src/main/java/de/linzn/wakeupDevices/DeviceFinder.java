package de.linzn.wakeupDevices;

public class DeviceFinder {

    public static WakeDevice getDevice(String name) {
        if (WakeupDevicesPlugin.wakeupDevicesPlugin.getDefaultConfig().contains("devices." + name)) {
            WakeDevice wakeDevice = new WakeDevice(name);
            return wakeDevice;
        }
        return null;
    }
}
