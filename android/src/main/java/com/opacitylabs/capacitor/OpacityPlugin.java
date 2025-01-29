package com.opacitylabs.capacitor;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.opacitylabs.opacitycore.OpacityCore;

@CapacitorPlugin(name = "Opacity")
public class OpacityPlugin extends Plugin {
    @PluginMethod
    public void initialize(PluginCall call) {
        String apiKey = call.getString("apiKey");
        boolean dryRun = call.getBoolean("dryRun");
        int environmentRaw = call.getInt("environment");

        OpacityCore.Environment environment = switch (environmentRaw) {
            case 0 -> OpacityCore.Environment.TEST;
            case 1 -> OpacityCore.Environment.LOCAL;
            case 2 -> OpacityCore.Environment.STAGING;
            default -> OpacityCore.Environment.PRODUCTION;
        };

        OpacityCore.initialize(apiKey, dryRun, environment);
        call.resolve(null);
    }

    @PluginMethod
    public void get(PluginCall call) {
        String name = call.getString("name");
        JSObject params = call.getObject("params");
    }
}
