package com.opacitylabs.capacitor;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.opacitylabs.opacitycore.OpacityCore;

@CapacitorPlugin(name = "Opacity")
public class OpacityPlugin extends Plugin {
    OpacityCore core;

    @PluginMethod
    public void initialize(PluginCall call) {
        String apiKey = call.getString("apiKey");
        boolean dryRun = call.getBoolean("dryRun");
        int environmentRaw = call.getInt("environment");

        OpacityCore.Environment environment;
        switch (environmentRaw) {
            case 0:
                environment = OpacityCore.Environment.TEST;
                break;
            case 1:
                environment = OpacityCore.Environment.LOCAL;
                break;
            case 2:
                environment = OpacityCore.Environment.STAGING;
                break;
            default:
                environment = OpacityCore.Environment.PRODUCTION;
                break;
        }

        core.initialize(apiKey, dryRun, environment);
        call.resolve(null);
    }
}
