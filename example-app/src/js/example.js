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

window.getGithubProfile = async () => {
    try {

        const res = await Opacity.get({name: "flow:github:profile"});
        console.log("GOT GITHUB PROFILE! 🟦🟩🟩")
        console.log(JSON.stringify(res));
    } catch(e) {
        console.error(`Error: ${e}`);
    }
}

window.getUberRiderProfile = async () => {
    try {
        const res = await Opacity.get({name: "flow:uber_rider:profile"});
        console.log("GOT UBER RIDER PROFILE! 🟦🟩🟩")
        console.log(JSON.stringify(res));
    } catch(e) {
        console.error(`Error: ${e}`);
    }
}
