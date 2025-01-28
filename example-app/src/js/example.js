import { Opacity, OpacityEnvironment } from 'capacitor-opacity';

const apiKey = import.meta.env.VITE_OPACITY_API_KEY;

window.initializeSDK = async () => {
    try {
        await Opacity.initialize({apiKey, dryRun: false, environment: OpacityEnvironment.Production});
        console.log("SDK Initialized");
    } catch(e) {
        console.error(`SDK Not Initialized: ${e}`);
    }
}

window.getUberRiderProfile = async () => {
    const res = await Opacity.get({name: "flow:uber_rider:profile"});
    console.log(res);
}
