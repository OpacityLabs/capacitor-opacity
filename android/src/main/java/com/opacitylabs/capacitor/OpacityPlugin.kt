package com.opacitylabs.capacitor

import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import com.opacitylabs.opacitycore.OpacityCore
import com.opacitylabs.opacitycore.OpacityCore.initialize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@CapacitorPlugin(name = "Opacity")
class OpacityPlugin : Plugin() {

    override fun load() {
        super.load()
        OpacityCore.setContext(activity)
    }

    @PluginMethod
    fun initialize(call: PluginCall) {
        val apiKey = call.getString("apiKey")
        val dryRun = call.getBoolean("dryRun")
        val environmentRaw = call.getInt("environment")
        val shouldShowErrorsInWebView = call.getBoolean("shouldShowErrorsInWebView")

        if (apiKey == null || environmentRaw == null) {
            call.reject("Invalid Arguments")
            return
        }

        val environment: OpacityCore.Environment = when (environmentRaw) {
            0 -> OpacityCore.Environment.TEST
            1 -> OpacityCore.Environment.LOCAL
            2 -> OpacityCore.Environment.STAGING
            else -> OpacityCore.Environment.PRODUCTION
        }

        initialize(apiKey, dryRun ?: false, environment, shouldShowErrorsInWebView ?: true)
        call.resolve(null)
    }

    @PluginMethod
    fun get(call: PluginCall) {
        val name = call.getString("name")
        val params = call.getObject("params", null)
        if (name == null) {
            call.reject("Name is required")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                if (params != null) {
                    val res = OpacityCore.get(name, Converter.jsObjectToMap(params))
                    call.resolve(Converter.mapToJsObject(res))
                } else {
                    val res = OpacityCore.get(name, null)
                    call.resolve(Converter.mapToJsObject(res))
                }
            } catch (e: Exception) {
                call.reject(e.toString())
            }
        }

    }
}
