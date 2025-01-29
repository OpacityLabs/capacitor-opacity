# capacitor-opacity

Opacity SDK for CapacitorJS

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
