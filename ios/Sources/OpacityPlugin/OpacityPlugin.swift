import Capacitor
import Foundation
import OpacityCore

/// Please read the Capacitor iOS Plugin Development Guide
/// here: https://capacitorjs.com/docs/plugins/ios
@objc(OpacityPlugin)
public class OpacityPlugin: CAPPlugin, CAPBridgedPlugin {
  public let identifier = "OpacityPlugin"
  public let jsName = "Opacity"
  public let pluginMethods: [CAPPluginMethod] = [
    CAPPluginMethod(name: "initialize", returnType: CAPPluginReturnPromise),
    CAPPluginMethod(name: "get", returnType: CAPPluginReturnPromise),
  ]

  @objc func initialize(_ call: CAPPluginCall) {
    let apiKey = call.getString("apiKey")!
    let dryRun = call.getBool("dryRun") ?? false
    let environmentRaw = call.getInt("environment")!
    let shouldShowErrorsInWebView = call.getBool("shouldShowErrorsInWebView") ?? true
    let environment = OpacitySwiftWrapper.Environment(rawValue: environmentRaw)!

    do {
      try OpacitySwiftWrapper
        .initialize(apiKey: apiKey, dryRun: dryRun, environment: environment, shouldShowErrorsInWebView: shouldShowErrorsInWebView)
      call.resolve()
    } catch {
      call.reject("Error initializing the Opacity SDK. Check the native logs")
    }
  }

  @objc func get(_ call: CAPPluginCall) {
    let name = call.getString("name")!
    let params = call.getObject("params")
    
    Task {
      do {
        let res = try await OpacitySwiftWrapper.get(
          name: name,
          params: params
        )
        call.resolve(res)
      } catch {
        call.reject(error.localizedDescription)
      }
    }
  }
}
