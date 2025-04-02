´# Permission-Kit

Library implement with Architecture MVVM pattern using Kotlin.

NOTE for DEV

## Features

- Grant permission system
- Support multi language (en, vi, th, ko,...)
- Support for Activity, fragment, DialogFragment

## How to build library aar
Step 1: check task
```
./gradlew tasks
```

Step 2: add permission gradlew
```
chmod +x ./gradlew
```
Step 3: Build file aar

For env debug

```
./gradlew assembleBuildAARDebug
```

For env release

```
./gradlew assembleBuildAARRelease
```


Note

- We should build aar in the env prod release

- File output the last in folder app/libs

## How to build library to maven local

Check task build with gradle

Step 1: Build library to aar

```
./gradlew buildAllLibraries
```

Step 2: Push file aar to maven-local

```
./gradlew publishAllLibraries
```

You can copy folder maven-local to root folder other project

Note:
Please update folder mavenLocal (default: ~/.m2) in file setting.gradle

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url "https://jitpack.io" }
        google()
        mavenCentral()
        mavenLocal()
        maven {
            url = uri("https://maven.pkg.github.com/longquangpham90/permission-kit")
            credentials {
                    username = "yourusername"
                    password = "your-github-token"
                }
            }
        }
    }
```

## Usage Example

 ```
 class DemoActivity : AppCompatActivity() {
      private val permissionHelper by lazy { PermissionHelper(activity = this@DemoActivity) }

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          lifecycleScope.launch {
              permissionHelper
                  .requestPermissions(
                      Manifest.permission.READ_SMS, //require
                      language = Locale.getDefault().language, //option
                      onGranted = { Timber.e("--- permission success") }, //require
                      onDenied = {
                        Timber.e("--- permission denied but rationale is true") },//option
                      onPermanentlyDenied = {
                        Timber.e("--- permission permanently denied") }//option
                  ).launchIn(this)
          }
      }
  }
  ```

## Add the dependency in app build.gradle

```
dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar", "*.aar"])
    implementation ("com.studio.permission:permission-kit:1.0.0")
}
```

## Requirement

JavaVersion=VERSION_17
Android Gradle plugin Greater than 8.0.0

## Test case

## APK file here
