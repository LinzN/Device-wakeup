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

package de.linzn.wakeupDevices.push;

import de.linzn.restfulapi.api.jsonapi.IRequest;
import de.linzn.restfulapi.api.jsonapi.RequestData;
import de.linzn.wakeupDevices.DeviceFinder;
import de.linzn.wakeupDevices.WakeDevice;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.utils.Color;
import org.json.JSONObject;

public class POST_WakeupDevice implements IRequest {


    @Override
    public Object proceedRequestData(RequestData requestData) {
        JSONObject jsonObject = new JSONObject();

        String deviceName = requestData.getSubChannels().get(0);
        STEMSystemApp.LOGGER.INFO(Color.GREEN + "[Restful API] Post Request: Wake device::" + deviceName);

        WakeDevice device = DeviceFinder.getDevice(deviceName);
        if (device != null) {
            device.wakeup();
        } else {
            STEMSystemApp.LOGGER.ERROR("No device found with this name in the config file!");
            jsonObject.put("error", "No device found with this name in the config file!");
        }

        jsonObject.put("status", device != null);

        return jsonObject;
    }

    @Override
    public String name() {
        return "wakeup-device";
    }
}
