@Suppress("ktlint:standard:property-naming")
object Modules {
    const val app = ":app"
    const val navigation = ":navigation"
    const val commonRes = ":common-res"
    const val commonUI = ":common-ui"
    const val data = ":data"
    const val domain = ":domain"
    const val libraryPermission = ":libraries:permission"
    const val featureSplash = ":features:splash"
}

@Suppress("ktlint:standard:property-naming")
object Releases {
    const val minSdk = 26
    const val compileSdk = 35
    const val targetSdk = 35
}

@Suppress("ktlint:standard:property-naming")
object Versions {
    const val kotlin = "2.1.20"
    const val hilt = "2.56"
    const val material = "1.9.0"
    const val coroutines = "1.6.4"
    const val exifinterFace = "1.3.7"
    const val okhttp = "4.12.0"
    const val retrofit = "2.11.0"
    const val annotationAPI = "1.3.2"
    const val room = "2.6.1"
    const val preference = "1.1.1"
    const val koin = "4.0.0"
    const val appStartup = "1.1.0"
    const val multiDex = "2.0.1"
    const val epoxy = "5.1.1"
    const val flexibleDivider = "1.5.3"
    const val navigation = "2.8.9"
    const val fragment = "1.8.5"
    const val lifecycleViewModel = "2.8.7"
    const val appcompat = "1.7.0"
    const val datastorePreferences = "1.1.3"
    const val recyclerView = "1.4.0"
    const val swipeRefreshLayout = "1.1.0"
    const val constraintLayout = "2.2.1"
    const val pagingRuntimeKtx = "3.3.1"
    const val viewPager2 = "1.1.0"
    const val inAppUpdate = "2.1.0"
    const val coreKtx = "1.9.0"
    const val stetho = "1.5.1"
    const val ultimateBarX = "0.8.0"
    const val rxAdapter3 = "3.0.0"
    const val coil = "2.7.0"
    const val glide = "4.16.0"
    const val googleAds = "24.1.0"
    const val playServicesBase = "18.6.0"
    const val playServicesIntegrity = "1.4.0"
    const val firebaseAnalyticsKtx = "22.2.0"
    const val firebaseMessagingKtx = "24.1.0"
    const val firebaseAuthKtx = "23.1.0"
    const val firebaseBom = "33.5.1"
    const val firebaseCrashlyticsKtx = "19.4.0"
    const val firebaseConfigKts = "22.0.1"
    const val playServicesAuth = "21.2.0"
    const val googleServiceMap = "19.0.0"
    const val googleServiceLocation = "21.3.0"
    const val googleMapsUtils = "3.8.2"
    const val googlePlaces = "3.5.0"
    const val exoplayer = "2.19.1"
    const val checkerFramework = "3.38.0"
    const val shimmer = "0.5.0"
    const val facebookLogin = "17.0.0"
    const val localBroadcastManager = "1.1.0"
    const val timber = "5.0.1"
    const val junit = "4.13.2"
    const val junitExt = "1.2.1"
    const val espressoCore = "3.6.1"
    const val robolectric = "4.11.1"
    const val mockK = "1.9.3"
    const val test = "1.6.1"
    const val archCore = "2.1.0"
    const val zxingCore = "3.4.1"
    const val appIntro = "6.2.0"
    const val pageIndicatorView = "1.0.0"
    const val linkTextToAction = "1.0.1"
    const val rotateLayout = "3.0.0"
    const val zxingAndroidEmbedded = "4.3.0"
    const val localization = "1.2.11"
    const val pinEntryEditText = "2.0.6"
    const val sspAndroid = "1.1.1"
    const val sdpAndroid = "1.1.1"
    const val leakcanary = "2.10"
    const val chucker = "4.1.0"
    const val hyperion = "0.9.38"
    const val swipeLayout = "1.2.0"
    const val jodaTime = "2.12.7"
    const val zoomHelper = "1.1.0"
    const val stepView = "1.5.1"
    const val cameraX = "1.4.1"
    const val cameraView = "2.7.2"
    const val mp4Composer = "0.4.1"
    const val androidSizes = "1.0.5"
    const val PDFViewer = "3.2.0-beta.3"
    const val advancedWebView = "v3.2.1"
    const val libPhoneNumber = "8.13.43"
    const val countryCodePicker = "2.7.3"
    const val pinView = "1.4.4"
    const val stickyHeader = "1.0"
    const val groupie = "2.10.1"
    const val stickyLayoutManager = "1.0.1"
    const val intercomSdk = "15.10.2"
    const val googleVision = "20.1.3"
    const val mlkitObjectDetection = "17.0.2"
    const val mlkitFaceDetection = "16.1.7"
}

@Suppress("ktlint:standard:property-naming")
object Deps {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // di
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // navigation
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    // ui
    const val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val datastorePreferences = "androidx.datastore:datastore-preferences:${Versions.datastorePreferences}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntimeKtx}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val inAppUpdate = "com.google.android.play:app-update-ktx:${Versions.inAppUpdate}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    const val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"
    const val epoxyDataBinding = "com.airbnb.android:epoxy-databinding:${Versions.epoxy}"
    const val flexibleDivider =
        "com.github.qq549631030:recyclerview-flexibledivider:${Versions.flexibleDivider}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilSVG = "io.coil-kt:coil-svg:${Versions.coil}"
    const val coilVideo = "io.coil-kt:coil-video:${Versions.coil}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val playServicesBase =
        "com.google.android.gms:play-services-base:${Versions.playServicesBase}"
    const val playServicesIntegrity =
        "com.google.android.play:integrity:${Versions.playServicesIntegrity}"
    const val googleAds =
        "com.google.android.gms:play-services-ads:${Versions.googleAds}"
    const val firebaseAnalyticsKtx =
        "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalyticsKtx}"
    const val firebaseCrashlyticsKtx =
        "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebaseCrashlyticsKtx}"
    const val firebaseConfigKts =
        "com.google.firebase:firebase-config-ktx:${Versions.firebaseConfigKts}"
    const val firebaseMessagingKtx =
        "com.google.firebase:firebase-messaging-ktx:${Versions.firebaseMessagingKtx}"
    const val firebaseAuthKtx = "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuthKtx}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val playServicesAuth =
        "com.google.android.gms:play-services-auth:${Versions.playServicesAuth}"
    const val googleServiceMap =
        "com.google.android.gms:play-services-maps:${Versions.googleServiceMap}"
    const val googleServiceLocation =
        "com.google.android.gms:play-services-location:${Versions.googleServiceLocation}"
    const val googleMapsUtils =
        "com.google.maps.android:android-maps-utils:${Versions.googleMapsUtils}"
    const val googlePlaces =
        "com.google.android.libraries.places:places:${Versions.googlePlaces}"
    const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"
    const val exoplayerHLS = "com.google.android.exoplayer:exoplayer-hls:${Versions.exoplayer}"
    const val exoplayerUI = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer}"
    const val exoplayerCronet =
        "com.google.android.exoplayer:extension-cronet:${Versions.exoplayer}"
    const val checkerFramework = "org.checkerframework:checker-qual:${Versions.checkerFramework}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val localBroadcastManager = "androidx.localbroadcastmanager:localbroadcastmanager:${Versions.localBroadcastManager}"

    // data
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val preference = "androidx.preference:preference:${Versions.preference}"
    const val stetho = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val annotationAPI = "javax.annotation:javax.annotation-api:${Versions.annotationAPI}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val exifinterFace =
        "androidx.exifinterface:exifinterface:${Versions.exifinterFace}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val ultimateBarX = "com.gitee.zackratos:UltimateBarX:${Versions.ultimateBarX}"
    const val rxAdapter3 = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.rxAdapter3}"
    const val zxingCore = "com.google.zxing:core:${Versions.zxingCore}"
    const val zxingAndroidEmbedded =
        "com.journeyapps:zxing-android-embedded:${Versions.zxingAndroidEmbedded}"
    const val localization = "com.akexorcist:localization:${Versions.localization}"
    const val pinEntryEditText = "com.alimuzaffar.lib:pinentryedittext:${Versions.pinEntryEditText}"
    const val appIntro = "com.github.AppIntro:AppIntro:${Versions.appIntro}"
    const val pageIndicatorView = "io.github.goddb:pageindicatorview:${Versions.pageIndicatorView}"
    const val linkTextToAction = "org.bluecabin.textoo:textoo:${Versions.linkTextToAction}"
    const val rotateLayout = "rongi.rotate-layout:rotate-layout:${Versions.rotateLayout}"

    const val sdpAndroid = "com.intuit.sdp:sdp-android:${Versions.sdpAndroid}"
    const val sspAndroid = "com.intuit.ssp:ssp-android:${Versions.sspAndroid}"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    const val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:${Versions.hyperion}"
    const val hyperionCoreRelease = "com.willowtreeapps.hyperion:hyperion-core-no-op:${Versions.hyperion}"
    const val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:${Versions.hyperion}"
    const val hyperionAttr = "com.willowtreeapps.hyperion:hyperion-attr:${Versions.hyperion}"
    const val hyperionBuildConfig =
        "com.willowtreeapps.hyperion:hyperion-build-config:${Versions.hyperion}"
    const val hyperionMeasurement =
        "com.willowtreeapps.hyperion:hyperion-measurement:${Versions.hyperion}"
    const val hyperionPhoenix = "com.willowtreeapps.hyperion:hyperion-phoenix:${Versions.hyperion}"
    const val hyperionRecorder =
        "com.willowtreeapps.hyperion:hyperion-recorder:${Versions.hyperion}"
    const val hyperionSharedPreferences =
        "com.willowtreeapps.hyperion:hyperion-shared-preferences:${Versions.hyperion}"
    const val swipeLayout = "com.daimajia.swipelayout:library:${Versions.swipeLayout}"
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"
    const val zoomHelper = "io.github.aghajari:ZoomHelper:${Versions.zoomHelper}"
    const val stepView = "com.params.stepview:stepview:${Versions.stepView}"
    const val cameraXCore = "androidx.camera:camera-core:${Versions.cameraX}"
    const val cameraX2 = "androidx.camera:camera-camera2:${Versions.cameraX}"
    const val cameraXLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
    const val cameraXVideo = "androidx.camera:camera-video:${Versions.cameraX}"
    const val cameraXView = "androidx.camera:camera-view:${Versions.cameraX}"
    const val cameraXMLKit = "androidx.camera:camera-mlkit-vision:${Versions.cameraX}"
    const val cameraXExtensions = "androidx.camera:camera-extensions:${Versions.cameraX}"
    const val cameraView = "com.otaliastudios:cameraview:${Versions.cameraView}"
    const val mp4Composer = "com.github.MasayukiSuda:GPUVideo-android:${Versions.mp4Composer}"
    const val androidSizes = "com.github.sokolyaka:androidsizes:${Versions.androidSizes}"
    const val PDFViewer = "com.github.mhiew:android-pdf-viewer:${Versions.PDFViewer}"
    const val advancedWebView = "com.github.delight-im:Android-AdvancedWebView:${Versions.advancedWebView}"
    const val libPhoneNumber = "com.googlecode.libphonenumber:libphonenumber:${Versions.libPhoneNumber}"
    const val countryCodePicker = "com.hbb20:ccp:${Versions.countryCodePicker}"
    const val pinView = "io.github.chaosleung:pinview:${Versions.pinView}"
    const val stickyHeader = "com.saber:stickyheader:${Versions.stickyHeader}"
    const val groupie = "com.github.lisawray.groupie:groupie:${Versions.groupie}"
    const val groupieViewbinding = "com.github.lisawray.groupie:groupie-viewbinding:${Versions.groupie}"
    const val groupieDatabinding = "com.github.lisawray.groupie:groupie-databinding:${Versions.groupie}"
    const val groupieKotlin = "com.github.lisawray.groupie:groupie-kotlin-android-extensions:${Versions.groupie}"
    const val stickyLayoutManager = "com.github.qiujayen:sticky-layoutmanager:${Versions.stickyLayoutManager}"
    const val intercomSdk = "io.intercom.android:intercom-sdk:${Versions.intercomSdk}"
    const val googleVision = "com.google.android.gms:play-services-vision:${Versions.googleVision}"
    const val mlkitObjectDetection = "com.google.mlkit:object-detection:${Versions.mlkitObjectDetection}"
    const val mlkitFaceDetection = "com.google.mlkit:face-detection:${Versions.mlkitFaceDetection}"
}

@Suppress("ktlint:standard:property-naming")
object TestDeps {
    const val testCore = "androidx.test:core-ktx:${Versions.test}"
    const val testRunner = "androidx.test:runner:${Versions.test}"
    const val testRules = "androidx.test:rules:${Versions.test}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val mockk = "io.mockk:mockk:${Versions.mockK}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockK}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val koin = "org.koin:koin-test:${Versions.koin}"
}
