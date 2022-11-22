package de.linzn.computerManagement.webapi;

import de.linzn.webapi.WebApiPlugin;
import de.linzn.webapi.modules.WebModule;
import de.stem.stemSystem.modules.pluginModule.STEMPlugin;

public class ComputerWebapiHandler {

    private STEMPlugin stemPlugin;

    public ComputerWebapiHandler(STEMPlugin stemPlugin){
        this.stemPlugin = stemPlugin;
        this.registerHandler();
    }

    private void registerHandler(){
        WebModule computerWebApiModule = new WebModule("computermanagement");
        computerWebApiModule.registerSubCallHandler(new WakeupComputer(), "wakeup");
        computerWebApiModule.registerSubCallHandler(new ShutdownComputer(), "shutdown");
        computerWebApiModule.registerSubCallHandler(new StatusComputer(), "status");
        WebApiPlugin.webApiPlugin.getWebServer().enableCallModule(computerWebApiModule);
    }
}
