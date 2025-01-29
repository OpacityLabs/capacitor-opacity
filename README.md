# capacitor-opacity

Opacity SDK for CapacitorJS. Only works on iOS and Android.

## Install

```bash
npm install capacitor-opacity
npx cap sync
```

## Android

You need to modify your project root gradle to include jitpack.io and mozilla's maven repos:

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
        maven { url "https://maven.mozilla.org/maven2/" } // ADD THIS
        maven { url 'https://jitpack.io' } // ADD THIS
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:8.7.2'
        classpath 'com.google.gms:google-services:4.4.2'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply from: "variables.gradle"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url "https://maven.mozilla.org/maven2/" } // ADD THIS
        maven { url 'https://jitpack.io' } // ADD THIS
    }
}

```

Bump the minSdk version to 25, in `variables.gradle`:

```groovy
ext {
    minSdkVersion = 25
    ...
}
```

## Usage

You need to initialize the SDK first before calling the get function to get the requested resource:

```js
import { Opacity, OpacityEnvironment } from 'capacitor-opacity';

const apiKey = import.meta.env.VITE_OPACITY_API_KEY;

window.initializeSDK = async () => {
  try {
    await Opacity.initialize({ apiKey, dryRun: false, environment: OpacityEnvironment.Production });
    console.log('SDK Initialized');
  } catch (e) {
    console.error(`SDK Not Initialized: ${e}`);
  }
};

window.getGithubProfile = async () => {
  try {
    const res = await Opacity.get({ name: 'flow:github:profile' });
    console.log('GOT GITHUB PROFILE! 🟦🟩🟩');
    console.log(JSON.stringify(res));
  } catch (e) {
    console.error(`Error: ${e}`);
  }
};

window.getUberRiderProfile = async () => {
  try {
    const res = await Opacity.get({ name: 'flow:uber_rider:profile' });
    console.log('GOT UBER RIDER PROFILE! 🟦🟩🟩');
    console.log(JSON.stringify(res));
  } catch (e) {
    console.error(`Error: ${e}`);
  }
};
```
