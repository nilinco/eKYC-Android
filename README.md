# eKYC SDK for Android

Reimagine the way your users authenticate their information with eKYC.

# Table of contents

- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Additional info](#info)


# <a name="requirements"></a> Requirements

Android API 21+

# <a name="quick-start"></a> Quick Start

This Quick Start guide will get you up and performing user authentication as quickly as possible. All steps described in this guide are required for the integration.

This guide closely follows the eKYC-Sample app in the Samples folder of this repository. We highly recommend you try to run the sample app. The sample app should compile and run on your device.

The source code of the sample app can be used as the reference during the integration.

## Initial integration steps

#### Using Maven
Comming soon.

#### Manual integration
1. [Download](https://github.com/nilinco/eKYC-Android/releases) latest release Download .zip or .tar.gz file starting with eKYC.
2. Extract the content of zip file to your local maven directory (~/.m2/repository/{HERE})
3. Add `mavenLocal()` repository to your project build.

     ```
    allprojects {
        repositories {
            google()
            mavenCentral()
            mavenLocal()
        }
    }
    ```
4. Add SDK dependency to your app build.gradle.

    ```
    dependencies {
        /* Other dependency */
        implementation('co.nilin.sdk:ekyc:1.0.0@aar') { transitive = true }
    }
    ```
5. Add these rules to the app module proguard

    ```
    -keep class co.nilin.** { *;}
    -keepclassmembers class ** {
       @org.greenrobot.eventbus.Subscribe <methods>;
    }
    ```


## How to use
**1. Initialize SDK client**

To initialize the SDK client, you need a server domain address and a  [API key](#info).
```
    val sdk: EkycClient = EkycSdk.getClient(
        server = KYC_SERVER_ADDRESS,
        key = YOUR_API_KEY
    )
```

**2. Start process**

The eKYC process has two different flow. One flow for those who have a national card and another for those who have a reference code.
```
fun start(phoneNumber: String) {
    sdk.ekycWithCardIntent(this, phoneNumber)
    startActivityForResult(intent, REQUEST_CODE)
}
```

**3. Get result of eKYC process**

```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE) {
        val kycToken = data?.getStringExtra("token")
            if (kycToken.isNullOrBlank()) {
                // Process failed
            } else {
                // The process was successfully Done
            }
        }
}
```

# <a name="info"></a> Additional info

A valid license key is required to initalize SDK. You can generate a free trial license key, after you register, at [Contact us](https://nilin.co).

Complete API reference can be found [here](https://nilin.co).

For any other questions, feel free to contact us at [info@nilin.co](info@nilin.co).
