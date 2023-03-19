package de.linzn.computerManagement.webapi;

import com.sun.net.httpserver.HttpExchange;
import de.linzn.computerManagement.Computer;
import de.linzn.computerManagement.ComputerManagementPlugin;
import de.linzn.computerManagement.actions.ShutdownDevice;
import de.linzn.webapi.core.ApiResponse;
import de.linzn.webapi.core.HttpRequestClientPayload;
import de.linzn.webapi.modules.RequestInterface;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;

import java.io.IOException;

public class ShutdownComputer extends RequestInterface {
    @Override
    public Object callHttpEvent(HttpExchange httpExchange, HttpRequestClientPayload httpRequestClientPayload) throws IOException {
        ApiResponse apiResponse = new ApiResponse();
        JSONObject postData = (JSONObject) httpRequestClientPayload.getPostData();

        String computerName = postData.get("computerName").toString();
        String requestAction = postData.get("requestAction").toString();

        Computer computer = ComputerManagementPlugin.computerManagementPlugin.getComputerManager().getComputerByName(computerName);

        if (computer != null) {
            if (requestAction.equalsIgnoreCase("WRITE")) {
                new ShutdownDevice(computer).runAction();
            }
        } else {
            apiResponse.setError("Computer with this name not found!");
        }
        return apiResponse.buildResponse();
    }
}
