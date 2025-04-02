# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.studio.splash.DataBinderMapperImpl { *; }
-dontwarn com.studio.common.ui.base.BaseViewModel
-dontwarn com.studio.common.ui.utils.SingleLiveEvent
-dontwarn com.studio.ads.utils.SharedFlow
# The proguard configuration file for the following section is /Users/apple/Downloads/source/LearnComputerCourse/features/splash/build/intermediates/default_proguard_files/global/proguard-android-optimize.txt-8.5.0
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
#
# Starting with version 2.2 of the Android plugin for Gradle, this file is distributed together with
# the plugin and unpacked at build-time. The files in $ANDROID_HOME are no longer maintained and
# will be ignored by new version of the Android plugin for Gradle.

# Optimizations: If you don't want to optimize, use the proguard-android.txt configuration file
# instead of this one, which turns off the optimization flags.
-allowaccessmodification

# Preserve some attributes that may be required for reflection.
-keepattributes AnnotationDefault,
                EnclosingMethod,
                InnerClasses,
                RuntimeVisibleAnnotations,
                RuntimeVisibleParameterAnnotations,
                RuntimeVisibleTypeAnnotations,
                Signature

-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.google.android.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.google.android.vending.licensing.ILicensingService

# For native methods, see https://www.guardsquare.com/manual/configuration/examples#native
-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

# Keep setters in Views so that animations can still work.
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick.
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# For enumeration classes, see https://www.guardsquare.com/manual/configuration/examples#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Preserve annotated Javascript interface methods.
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# The support libraries contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version. We know about them, and they are safe.
-dontnote android.support.**
-dontnote androidx.**
-dontwarn android.support.**
-dontwarn androidx.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

# These classes are duplicated between android.jar and org.apache.http.legacy.jar.
-dontnote org.apache.http.**
-dontnote android.net.http.**

# These classes are duplicated between android.jar and core-lambda-stubs.jar.
-dontnote java.lang.invoke.**

# End of content from /Users/apple/Downloads/source/LearnComputerCourse/features/splash/build/intermediates/default_proguard_files/global/proguard-android-optimize.txt-8.5.0
# The proguard configuration file for the following section is /Users/apple/Downloads/source/LearnComputerCourse/features/splash/proguard-rules.pro
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.studio.splash.DataBinderMapperImpl { *; }
-dontwarn com.studio.common.ui.base.BaseViewModel
-dontwarn com.studio.common.ui.utils.SingleLiveEvent
# End of content from /Users/apple/Downloads/source/LearnComputerCourse/features/splash/proguard-rules.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/f038cd91fc81459d5438cff000df25b3/transformed/databinding-runtime-8.5.0/proguard.txt
-dontwarn androidx.databinding.ViewDataBinding
-dontwarn androidx.databinding.ViewDataBinding$LiveDataListener

# instant apps load these via reflection so we need to keep them.
-keep public class * extends androidx.databinding.DataBinderMapper

# End of content from /Users/apple/.gradle/caches/8.8/transforms/f038cd91fc81459d5438cff000df25b3/transformed/databinding-runtime-8.5.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/61943db1acc8c653a1b54fc8243846e9/transformed/rules/lib/META-INF/proguard/androidx.datastore_datastore-preferences-core.pro
-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/61943db1acc8c653a1b54fc8243846e9/transformed/rules/lib/META-INF/proguard/androidx.datastore_datastore-preferences-core.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8b8760649d0e787351330c6a3f29e34c/transformed/jetified-UltimateBarX-0.8.0/proguard.txt

# End of content from /Users/apple/.gradle/caches/8.8/transforms/8b8760649d0e787351330c6a3f29e34c/transformed/jetified-UltimateBarX-0.8.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/6e0f354f0413f46b171dd502f1898dc3/transformed/navigation-common-2.7.7/proguard.txt
# Copyright (C) 2019 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# NavArgsLazy creates NavArgs instances using reflection
-if public class ** implements androidx.navigation.NavArgs
-keepclassmembers public class <1> {
    ** fromBundle(android.os.Bundle);
}

# Retain the @Navigator.Name annotation on each subclass of Navigator.
# R8 full mode only retains annotations on items matched by a -keep rule,
# hence the extra -keep rule for the subclasses of Navigator.
#
# A -keep rule for the Navigator.Name annotation class is not required
# since the annotation is referenced from the code.
-keepattributes RuntimeVisibleAnnotations
-keep,allowobfuscation,allowshrinking class * extends androidx.navigation.Navigator

# End of content from /Users/apple/.gradle/caches/8.8/transforms/6e0f354f0413f46b171dd502f1898dc3/transformed/navigation-common-2.7.7/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/f95695b10403d79e60d17783a2b00c98/transformed/navigation-ui-2.7.7/proguard.txt
# Copyright (C) 2019 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# setProgress is called via an ObjectAnimator in AbstractAppBarOnDestinationChangedListener
-keepclassmembers class androidx.appcompat.graphics.drawable.DrawerArrowDrawable {
    void setProgress(float);
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/f95695b10403d79e60d17783a2b00c98/transformed/navigation-ui-2.7.7/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/31104e15905d4539416abbd303a163f7/transformed/material-1.9.0/proguard.txt
# Copyright (C) 2015 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# CoordinatorLayout resolves the behaviors of its child components with reflection.
-keep public class * extends androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>();
}

# Make sure we keep annotations for CoordinatorLayout's DefaultBehavior
-keepattributes RuntimeVisible*Annotation*

# Copyright (C) 2018 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# AppCompatViewInflater reads the viewInflaterClass theme attribute which then
# reflectively instantiates MaterialComponentsViewInflater using the no-argument
# constructor. We only need to keep this constructor and the class name if
# AppCompatViewInflater is also being kept.
-if class androidx.appcompat.app.AppCompatViewInflater
-keep class com.google.android.material.theme.MaterialComponentsViewInflater {
    <init>();
}


# End of content from /Users/apple/.gradle/caches/8.8/transforms/31104e15905d4539416abbd303a163f7/transformed/material-1.9.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8ca02b966fc853d13829eeaf7a3b19b0/transformed/appcompat-1.7.0/proguard.txt
# Copyright (C) 2018 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Never inline methods, but allow shrinking and obfuscation.
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper$Impl* {
  <methods>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/8ca02b966fc853d13829eeaf7a3b19b0/transformed/appcompat-1.7.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/66ed1dc81fc40dcce14c1a346b84bfa7/transformed/jetified-hilt-android-2.51/proguard.txt
# Keep for the reflective cast done in EntryPoints.
# See b/183070411#comment4 for more info.
-keep,allowobfuscation,allowshrinking @dagger.hilt.EntryPoint class *# Keep for the reflective cast done in EntryPoints.
# See b/183070411#comment4 for more info.
-keep,allowobfuscation,allowshrinking @dagger.hilt.android.EarlyEntryPoint class *# Keep for the reflective cast done in EntryPoints.
# See b/183070411#comment4 for more info.
-keep,allowobfuscation,allowshrinking @dagger.hilt.internal.ComponentEntryPoint class *
-keep,allowobfuscation,allowshrinking @dagger.hilt.internal.GeneratedEntryPoint class *
# End of content from /Users/apple/.gradle/caches/8.8/transforms/66ed1dc81fc40dcce14c1a346b84bfa7/transformed/jetified-hilt-android-2.51/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/09df74d08862fbc9148efeb4ba577a8e/transformed/jetified-timber-5.0.1/proguard.txt
-dontwarn org.jetbrains.annotations.**

# End of content from /Users/apple/.gradle/caches/8.8/transforms/09df74d08862fbc9148efeb4ba577a8e/transformed/jetified-timber-5.0.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/2ddbb54e2990981ac12c982d4ae0bc30/transformed/jetified-play-services-measurement-21.6.2/proguard.txt
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzju {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/2ddbb54e2990981ac12c982d4ae0bc30/transformed/jetified-play-services-measurement-21.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/9fdf2eae13f3f7243138a69f03890cda/transformed/jetified-play-services-measurement-sdk-21.6.2/proguard.txt
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzju {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/9fdf2eae13f3f7243138a69f03890cda/transformed/jetified-play-services-measurement-sdk-21.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/73d7710185baafface0fc1dcfd9c3b2b/transformed/jetified-play-services-measurement-impl-21.6.2/proguard.txt
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzju {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/73d7710185baafface0fc1dcfd9c3b2b/transformed/jetified-play-services-measurement-impl-21.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/0ab87f17ad3a1bbb0747e7d867d7c08e/transformed/jetified-app-update-ktx-2.1.0/proguard.txt
# Proguard cannot process META-INF/versions/9.
# See https://discuss.gradle.org/t/meta-inf-version-duplicate-error-when-using-proguard/31380
-dontwarn module-info

# End of content from /Users/apple/.gradle/caches/8.8/transforms/0ab87f17ad3a1bbb0747e7d867d7c08e/transformed/jetified-app-update-ktx-2.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/35731140a38297af1a5a07e4a6350b23/transformed/recyclerview-1.3.2/proguard.txt
# Copyright (C) 2015 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# When layoutManager xml attribute is used, RecyclerView inflates
#LayoutManagers' constructors using reflection.
-keep public class * extends androidx.recyclerview.widget.RecyclerView$LayoutManager {
    public <init>(android.content.Context, android.util.AttributeSet, int, int);
    public <init>();
}

-keepclassmembers class androidx.recyclerview.widget.RecyclerView {
    public void suppressLayout(boolean);
    public boolean isLayoutSuppressed();
}
# End of content from /Users/apple/.gradle/caches/8.8/transforms/35731140a38297af1a5a07e4a6350b23/transformed/recyclerview-1.3.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/3eeaf7bfb2862f396c19cb2294843d3c/transformed/jetified-play-services-ads-23.1.0/proguard.txt
-keep public class com.google.android.gms.ads.internal.ClientApi {
  <init>();
}

# When built for Android API level < 30, Proguard warns that it can't find
# android.telephony.TelephonyDisplayInfo (since it was added only in API level
# 30). But, all its usages are guarded by runtime checks of the API level.
# Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.telephony.TelephonyDisplayInfo

# When built for Android API level < 30, Proguard warns that it can't find
# android.view.Surface#setFrameRate(float, int) (since it was added only in API
# level 30). But, all its usages are guarded by runtime checks of the API level.
# Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.view.Surface

# When built for Android API level < 31, Proguard warns that it can't find
# android.media.ApplicationMediaCapabilities (since it was added only in API
# level 31). But, all its usages are guarded by runtime checks of the API level.
# Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.media.ApplicationMediaCapabilities

# When built for Android API level < 31, Proguard warns that it can't find
# android.media.MediaFeature (since it was added only in API level 31). But,
# all its usages are guarded by runtime checks of the API level.
# Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.media.MediaFeature

# When built for Android API level < 31, Proguard warns that it can't find
# android.media.ApplicationMediaCapabilities$Builder (since it was added only in
# API level 31). But, all its usages are guarded by runtime checks of the API
# level. Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.media.ApplicationMediaCapabilities$Builder

# When built for Android API level < 31, Proguard warns that it can't find
# android.media.MediaFeature$HdrType (since it was added only in API level 31).
# But, all its usages are guarded by runtime checks of the API level.
# Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.media.MediaFeature$HdrType

# When built for Android API level < 32, Proguard warns that it can't find
# android.media.AudioAttributes$Builder (since it was added only in API level
# 32). But, all its usages are guarded by runtime checks of the API level.
# Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.media.AudioAttributes$Builder

# When built for Android API level < 33, Proguard warns that it can't find
# android.adservices.measurement.MeasurementManager (since it was added only
# in API level 33). But, all its usages are guarded by runtime checks of the
# API level. Hence, it is safe to suppress Proguard's warnings.
-dontwarn android.adservices.measurement.MeasurementManager

# When built for Android API level < 33, Proguard warns that it can't find
# javax.lang.model.element.Modifier (since it was added only in API level 33).
# But, all its usages are guarded by runtime checks of the API level. Hence, it
# is safe to suppress Proguard's warnings.
-dontwarn javax.lang.model.element.Modifier

# These are checked at runtime for whether they exist, so it is fine if the API level doesn't include these.
-dontwarn android.content.pm.ApkChecksum
-dontwarn android.content.pm.PackageManager$OnChecksumsReadyListener
# Only for the requestChecksums method, but sadly -dontwarn can't take just a single method.
-dontwarn android.content.pm.PackageManager

# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.ads.zzgzv {
  <fields>;
}

# Auto-generated proguard rule(s) with obfuscated symbol
-dontwarn com.google.android.gms.ads.internal.util.zzy


# End of content from /Users/apple/.gradle/caches/8.8/transforms/3eeaf7bfb2862f396c19cb2294843d3c/transformed/jetified-play-services-ads-23.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/331f5119bb646816f68240edb12d9e8d/transformed/jetified-firebase-auth-23.0.0/proguard.txt
-dontwarn com.google.appengine.api.**
-dontwarn okio.**
-dontwarn org.apache.**
-dontwarn retrofit.android.**
-dontwarn retrofit.appengine.**
-dontwarn retrofit.client.**
-dontwarn rx.**

# This is necessary for keeping SecureTokenHttpApi and IdentityToolkitHttpApi
# Otherwise those classes get stripped out, as they are only being used
# reflectively.

-keepclasseswithmembernames interface * {
    @retrofit.http.* <methods>;
}

# This is necessary for parsing JSON responses, since the JSON converter uses reflection to figure out the class/type of response.
# We mainly need the *Response.classes to not be stripped out. All the firebase-auth classes are proguarded into "com.google.android.gms.internal.firebase-auth-api*".

-keep class com.google.android.gms.internal.** { *; }

# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.firebase-auth-api.zzaje {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/331f5119bb646816f68240edb12d9e8d/transformed/jetified-firebase-auth-23.0.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/a4e5793f4c3f5faac423f9cfc99f411a/transformed/jetified-play-services-measurement-api-21.6.2/proguard.txt
# Can be removed once we pull in a dependency on firebase-common that includes
# https://github.com/firebase/firebase-android-sdk/pull/1472/commits/856f1ca1151cdd88679bbc778892f23dfa34fc06#diff-a2ed34b5a38b4c6c686b09e54865eb48
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.google.auto.value.AutoValue$Builder

# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzju {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/a4e5793f4c3f5faac423f9cfc99f411a/transformed/jetified-play-services-measurement-api-21.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8c5c21794beadfe70f46b744434c37c5/transformed/jetified-firebase-auth-interop-20.0.0/proguard.txt
# Can be removed once we pull in a dependency on firebase-common that includes
# https://github.com/firebase/firebase-android-sdk/pull/1472/commits/856f1ca1151cdd88679bbc778892f23dfa34fc06#diff-a2ed34b5a38b4c6c686b09e54865eb48
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.google.auto.value.AutoValue$Builder

# End of content from /Users/apple/.gradle/caches/8.8/transforms/8c5c21794beadfe70f46b744434c37c5/transformed/jetified-firebase-auth-interop-20.0.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/d72260ec2071b433de295060907c1eac/transformed/jetified-firebase-common-21.0.0/proguard.txt
-dontwarn com.google.firebase.platforminfo.KotlinDetector
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.google.auto.value.AutoValue$Builder

# End of content from /Users/apple/.gradle/caches/8.8/transforms/d72260ec2071b433de295060907c1eac/transformed/jetified-firebase-common-21.0.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/a48144f562045d28ac2b760ac7e069f3/transformed/jetified-credentials-play-services-auth-1.2.0-rc01/proguard.txt
-if class androidx.credentials.CredentialManager
-keep class androidx.credentials.playservices.** {
  *;
}
# End of content from /Users/apple/.gradle/caches/8.8/transforms/a48144f562045d28ac2b760ac7e069f3/transformed/jetified-credentials-play-services-auth-1.2.0-rc01/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/7b1b909e8e9b94df1d604f17b662617f/transformed/jetified-recaptcha-18.4.0/proguard.txt
# Proguard cannot process META-INF/versions/9.
# See https://discuss.gradle.org/t/meta-inf-version-duplicate-error-when-using-proguard/31380
-dontwarn module-info

# Ignore the warning becuse ClassValueCtorCache is never used on Android.
-dontwarn kotlinx.coroutines.internal.ClassValueCtorCache

# Ignore warning due to the usage from Guava and kotlinx.coroutines.internal.ClassValueCtorCache
-dontwarn java.lang.ClassValue

# Ignore warning to accommodate the missing injar of kotlinx.coroutines.flow for
# androidx.slidingpanelayout.widget.
-dontwarn kotlinx.coroutines.flow.**

# This prevents the SDK to be obfuscated again when building the android app.
-keep class com.google.android.recaptcha.** { *; }

# This is required for recaptcha mobile to function properly.
# See: https://cloud.google.com/recaptcha-enterprise/docs/instrument-android-apps
-keep class com.google.android.play.core.integrity.** { *; }
-keep class com.google.android.gms.tasks.** {*;}
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.recaptcha.internal.zzit {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/7b1b909e8e9b94df1d604f17b662617f/transformed/jetified-recaptcha-18.4.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/78e712ca566af3e53ecdbb0ab41fb0c2/transformed/coordinatorlayout-1.1.0/proguard.txt
# Copyright (C) 2016 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# CoordinatorLayout resolves the behaviors of its child components with reflection.
-keep public class * extends androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>();
}

# Make sure we keep annotations for CoordinatorLayout's DefaultBehavior and ViewPager's DecorView
-keepattributes *Annotation*

# End of content from /Users/apple/.gradle/caches/8.8/transforms/78e712ca566af3e53ecdbb0ab41fb0c2/transformed/coordinatorlayout-1.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/33ce5106fcfb926783e83646df882e8c/transformed/vectordrawable-animated-1.1.0/proguard.txt
# Copyright (C) 2016 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# keep setters in VectorDrawables so that animations can still work.
-keepclassmembers class androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$* {
   void set*(***);
   *** get*();
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/33ce5106fcfb926783e83646df882e8c/transformed/vectordrawable-animated-1.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/05b2dd3f3ffd52ca48f764ce8cb138c4/transformed/webkit-1.11.0-alpha02/proguard.txt
# Copyright 2018 The Chromium Authors
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file.

# We need to avoid obfuscating the support library boundary interface because
# this API is shared with the Android Support Library.
# Note that we only 'keep' the package org.chromium.support_lib_boundary itself,
# any sub-packages of that package can still be obfuscated.
-keep public class org.chromium.support_lib_boundary.* { public *; }

# Copyright (C) 2018 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Prevent WebViewClientCompat from being renamed, since chromium depends on this name.
-keepnames public class androidx.webkit.WebViewClientCompat

# Prevent ProcessGlobalConfig and member sProcessGlobalConfig from being renamed, since chromium
# depends on this name.
-keepnames public class androidx.webkit.ProcessGlobalConfig {
    private static final *** sProcessGlobalConfig;
}
# End of content from /Users/apple/.gradle/caches/8.8/transforms/05b2dd3f3ffd52ca48f764ce8cb138c4/transformed/webkit-1.11.0-alpha02/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/a9bbdc803d84e4e400097b6af3e72670/transformed/jetified-play-services-ads-lite-23.1.0/proguard.txt
# Keep implementations of the AdMob mediation adapter interfaces. Adapters for
# third party ad networks implement these interfaces and are invoked by the
# AdMob SDK via reflection.

-keep class * implements com.google.android.gms.ads.mediation.MediationAdapter {
  public *;
}
-keep class * implements com.google.ads.mediation.MediationAdapter {
  public *;
}
-keep class * implements com.google.android.gms.ads.mediation.customevent.CustomEvent {
  public *;
}
-keep class * implements com.google.ads.mediation.customevent.CustomEvent {
  public *;
}
-keep class * extends com.google.android.gms.ads.mediation.MediationAdNetworkAdapter {
  public *;
}
-keep class * extends com.google.android.gms.ads.mediation.Adapter {
  public *;
}

# Keep classes used for offline ads created by reflection. WorkManagerUtil is
# created reflectively by callers within GMSCore and OfflineNotificationPoster
# is created reflectively by WorkManager.
-keep class com.google.android.gms.ads.internal.util.WorkManagerUtil {
  public *;
}
-keep class com.google.android.gms.ads.internal.offline.buffering.OfflineNotificationPoster {
  public *;
}
-keep class com.google.android.gms.ads.internal.offline.buffering.OfflinePingSender {
  public *;
}

# Keeps the entry for full SDK to access via reflection.
-keep class com.google.android.gms.ads.internal.client.LiteSdkInfo {
  public *;
}

# Keeps the entry for first party plugins to access via reflection.
-keep class com.google.android.gms.ads.MobileAds {
  private void setPlugin(java.lang.String);
}

# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.ads.zzgzv {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/a9bbdc803d84e4e400097b6af3e72670/transformed/jetified-play-services-ads-lite-23.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/e89596615380670e5622099202042f5f/transformed/work-runtime-2.7.0/proguard.txt
-keep class * extends androidx.work.Worker
-keep class * extends androidx.work.InputMerger
# Keep all constructors on ListenableWorker, Worker (also marked with @Keep)
-keep public class * extends androidx.work.ListenableWorker {
    public <init>(...);
}
# We need to keep WorkerParameters for the ListenableWorker constructor
-keep class androidx.work.WorkerParameters

# End of content from /Users/apple/.gradle/caches/8.8/transforms/e89596615380670e5622099202042f5f/transformed/work-runtime-2.7.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/cff25908a769f21131fb345e579ecb4e/transformed/media-1.0.0/proguard.txt
# Copyright (C) 2017 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Prevent Parcelable objects from being removed or renamed.
-keep class android.support.v4.media.** implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Prevent Parcelable objects from being removed or renamed.
-keep class androidx.media.** implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
# End of content from /Users/apple/.gradle/caches/8.8/transforms/cff25908a769f21131fb345e579ecb4e/transformed/media-1.0.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/7354515b0e28e1142a1e904d9ea85a2e/transformed/transition-1.4.1/proguard.txt
# Copyright (C) 2017 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Keep a field in transition that is used to keep a reference to weakly-referenced object
-keepclassmembers class androidx.transition.ChangeBounds$* extends android.animation.AnimatorListenerAdapter {
  androidx.transition.ChangeBounds$ViewBounds mViewBounds;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/7354515b0e28e1142a1e904d9ea85a2e/transformed/transition-1.4.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/871d76eef04cbbffc803fcdc98f43802/transformed/jetified-window-1.0.0/proguard.txt
# Copyright (C) 2020 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# A rule that will keep classes that implement SidecarInterface$SidecarCallback if Sidecar seems
# be used. See b/157286362 and b/165268619 for details.
# TODO(b/208543178) investigate how to pass header jar to R8 so we don't need this rule
-if class androidx.window.layout.SidecarCompat {
  public setExtensionCallback(androidx.window.layout.ExtensionInterfaceCompat$ExtensionCallbackInterface);
}
-keep class androidx.window.layout.SidecarCompat$TranslatingCallback,
 androidx.window.layout.SidecarCompat$DistinctSidecarElementCallback {
  public onDeviceStateChanged(androidx.window.sidecar.SidecarDeviceState);
  public onWindowLayoutChanged(android.os.IBinder, androidx.window.sidecar.SidecarWindowLayoutInfo);
}
# End of content from /Users/apple/.gradle/caches/8.8/transforms/871d76eef04cbbffc803fcdc98f43802/transformed/jetified-window-1.0.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/367cd70d21d66a6fd8d58405778d06e9/transformed/jetified-play-services-fido-20.1.0/proguard.txt
# Methods enable and disable in this class are complained as unresolved
# references, but they are system APIs and are not used by Fido client apps.
-dontwarn android.nfc.NfcAdapter

# End of content from /Users/apple/.gradle/caches/8.8/transforms/367cd70d21d66a6fd8d58405778d06e9/transformed/jetified-play-services-fido-20.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8b9ba0805bd31f9f06c67fbda7d2b210/transformed/jetified-play-services-auth-base-18.0.4/proguard.txt
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.auth.zzeu {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/8b9ba0805bd31f9f06c67fbda7d2b210/transformed/jetified-play-services-auth-base-18.0.4/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/bf8352e32aea5a1c915d42938bad6e4a/transformed/jetified-play-services-base-18.0.1/proguard.txt
# b/35135904 Ensure that proguard will not strip the mResultGuardian.
-keepclassmembers class com.google.android.gms.common.api.internal.BasePendingResult {
  com.google.android.gms.common.api.internal.BasePendingResult$ReleasableResultGuardian mResultGuardian;
}



# End of content from /Users/apple/.gradle/caches/8.8/transforms/bf8352e32aea5a1c915d42938bad6e4a/transformed/jetified-play-services-base-18.0.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/019442c9f7d2a6eeeee5d79ed5222c57/transformed/core-1.13.0/proguard.txt
# Never inline methods, but allow shrinking and obfuscation.
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.view.ViewCompat$Api* {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.view.WindowInsetsCompat$*Impl* {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.app.NotificationCompat$*$Api*Impl {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.os.UserHandleCompat$Api*Impl {
  <methods>;
}
-keepclassmembernames,allowobfuscation,allowshrinking class androidx.core.widget.EdgeEffectCompat$Api*Impl {
  <methods>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/019442c9f7d2a6eeeee5d79ed5222c57/transformed/core-1.13.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/56303e3a2e7fb4c9b3d737e6e33daede/transformed/jetified-savedstate-1.2.1/proguard.txt
# Copyright (C) 2019 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

-keepclassmembers,allowobfuscation class * implements androidx.savedstate.SavedStateRegistry$AutoRecreated {
    <init>();
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/56303e3a2e7fb4c9b3d737e6e33daede/transformed/jetified-savedstate-1.2.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/e6bf07797b88ce9c5d331dabe7e122c5/transformed/jetified-lifecycle-runtime-release/proguard.txt
-keepattributes AnnotationDefault,
                RuntimeVisibleAnnotations,
                RuntimeVisibleParameterAnnotations,
                RuntimeVisibleTypeAnnotations

-keepclassmembers enum androidx.lifecycle.Lifecycle$Event {
    <fields>;
}

-keep class * implements androidx.lifecycle.GeneratedAdapter {
    <init>(...);
}

-keepclassmembers class ** {
    @androidx.lifecycle.OnLifecycleEvent *;
}

# this rule is need to work properly when app is compiled with api 28, see b/142778206
# Also this rule prevents registerIn from being inlined.
-keepclassmembers class androidx.lifecycle.ReportFragment$LifecycleCallbacks { *; }
# End of content from /Users/apple/.gradle/caches/8.8/transforms/e6bf07797b88ce9c5d331dabe7e122c5/transformed/jetified-lifecycle-runtime-release/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8b9b09eaee4b25f99754c9c55f30458b/transformed/rules/lib/META-INF/com.android.tools/r8-from-1.6.0/coroutines.pro
# Allow R8 to optimize away the FastServiceLoader.
# Together with ServiceLoader optimization in R8
# this results in direct instantiation when loading Dispatchers.Main
-assumenosideeffects class kotlinx.coroutines.internal.MainDispatcherLoader {
    boolean FAST_SERVICE_LOADER_ENABLED return false;
}

-assumenosideeffects class kotlinx.coroutines.internal.FastServiceLoaderKt {
    boolean ANDROID_DETECTED return true;
}

# Disable support for "Missing Main Dispatcher", since we always have Android main dispatcher
-assumenosideeffects class kotlinx.coroutines.internal.MainDispatchersKt {
    boolean SUPPORT_MISSING return false;
}

# Statically turn off all debugging facilities and assertions
-assumenosideeffects class kotlinx.coroutines.DebugKt {
    boolean getASSERTIONS_ENABLED() return false;
    boolean getDEBUG() return false;
    boolean getRECOVER_STACK_TRACES() return false;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/8b9b09eaee4b25f99754c9c55f30458b/transformed/rules/lib/META-INF/com.android.tools/r8-from-1.6.0/coroutines.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/1387d8c0c59b92a738282695b66fd750/transformed/rules/lib/META-INF/com.android.tools/r8/coroutines.pro
# When editing this file, update the following files as well:
# - META-INF/proguard/coroutines.pro
# - META-INF/com.android.tools/proguard/coroutines.pro

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# Same story for the standard library's SafeContinuation that also uses AtomicReferenceFieldUpdater
-keepclassmembers class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}

# These classes are only required by kotlinx.coroutines.debug.AgentPremain, which is only loaded when
# kotlinx-coroutines-core is used as a Java agent, so these are not needed in contexts where ProGuard is used.
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
-dontwarn java.lang.instrument.Instrumentation
-dontwarn sun.misc.Signal

# Only used in `kotlinx.coroutines.internal.ExceptionsConstructor`.
# The case when it is not available is hidden in a `try`-`catch`, as well as a check for Android.
-dontwarn java.lang.ClassValue

# An annotation used for build tooling, won't be directly accessed.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# End of content from /Users/apple/.gradle/caches/8.8/transforms/1387d8c0c59b92a738282695b66fd750/transformed/rules/lib/META-INF/com.android.tools/r8/coroutines.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8beacb6e6b65a83b5740466e09ed5502/transformed/jetified-play-services-tasks-18.1.0/proguard.txt


# End of content from /Users/apple/.gradle/caches/8.8/transforms/8beacb6e6b65a83b5740466e09ed5502/transformed/jetified-play-services-tasks-18.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/e6ca58c249d3f3218e09c45e6348f57f/transformed/jetified-play-services-measurement-sdk-api-21.6.2/proguard.txt
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzju {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/e6ca58c249d3f3218e09c45e6348f57f/transformed/jetified-play-services-measurement-sdk-api-21.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/d93c874ef0722474b6426df3ad04c398/transformed/jetified-play-services-measurement-base-21.6.2/proguard.txt
# We keep all fields for every generated proto file as the runtime uses
# reflection over them that ProGuard cannot detect. Without this keep
# rule, fields may be removed that would cause runtime failures.
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzju {
  <fields>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/d93c874ef0722474b6426df3ad04c398/transformed/jetified-play-services-measurement-base-21.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/11995101e8d80c74bded35c9b8d3f797/transformed/jetified-play-services-basement-18.3.0/proguard.txt
# Needed when building against pre-Marshmallow SDK.
-dontwarn android.security.NetworkSecurityPolicy

# Needed when building against Marshmallow SDK.
-dontwarn android.app.Notification

# Protobuf has references not on the Android boot classpath
-dontwarn sun.misc.Unsafe
-dontwarn libcore.io.Memory

# Annotations used during internal SDK shrinking.
-dontwarn com.google.android.apps.common.proguard.UsedBy*
-dontwarn com.google.android.apps.common.proguard.SideEffectFree

# Annotations referenced by the SDK but whose definitions are contained in
# non-required dependencies.
-dontwarn javax.annotation.**
-dontwarn org.checkerframework.**
-dontwarn com.google.errorprone.annotations.**
-dontwarn org.jspecify.nullness.NullMarked

# Annotations no longer exist. Suppression prevents ProGuard failures in
# SDKs which depend on earlier versions of play-services-basement.
-dontwarn com.google.android.gms.common.util.VisibleForTesting

# Proguard flags for consumers of the Google Play services SDK
# https://developers.google.com/android/guides/setup#add_google_play_services_to_your_project

# Keep SafeParcelable NULL value, needed for reflection by DowngradeableSafeParcel
-keepclassmembers public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

# Needed for Parcelable/SafeParcelable classes & their creators to not get renamed, as they are
# found via reflection.
-keep class com.google.android.gms.common.internal.ReflectedParcelable
-keepnames class * implements com.google.android.gms.common.internal.ReflectedParcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final *** CREATOR;
}

# Keep the classes/members we need for client functionality.
-keep @interface android.support.annotation.Keep
-keep @androidx.annotation.Keep class *
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <methods>;
}

# Keep androidX equivalent of above android.support to allow Jetification.
-keep @interface androidx.annotation.Keep
-keep @androidx.annotation.Keep class *
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <methods>;
}

# Keep the names of classes/members we need for client functionality.
-keep @interface com.google.android.gms.common.annotation.KeepName
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
  @com.google.android.gms.common.annotation.KeepName *;
}

# Keep Dynamite API entry points
-keep @interface com.google.android.gms.common.util.DynamiteApi
-keep @com.google.android.gms.common.util.DynamiteApi public class * {
  public <fields>;
  public <methods>;
}



# End of content from /Users/apple/.gradle/caches/8.8/transforms/11995101e8d80c74bded35c9b8d3f797/transformed/jetified-play-services-basement-18.3.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/9dfb8412aab9bcda4ad3b44a12d8cbb8/transformed/fragment-1.6.2/proguard.txt
# Copyright (C) 2020 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# The default FragmentFactory creates Fragment instances using reflection
-if public class ** extends androidx.fragment.app.Fragment
-keepclasseswithmembers,allowobfuscation public class <1> {
    public <init>();
}

# FragmentTransition will reflectively lookup:
# androidx.transition.FragmentTransitionSupport
# We should ensure that we keep the constructor if the code using this is alive
-if class androidx.fragment.app.FragmentTransition {
   private static androidx.fragment.app.FragmentTransitionImpl resolveSupportImpl();
}
-keep class androidx.transition.FragmentTransitionSupport {
    public <init>();
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/9dfb8412aab9bcda4ad3b44a12d8cbb8/transformed/fragment-1.6.2/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/22fed42310e1eccb1ac12035b79b34f7/transformed/jetified-lifecycle-viewmodel-release/proguard.txt
-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.ViewModel {
    <init>();
}

-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/22fed42310e1eccb1ac12035b79b34f7/transformed/jetified-lifecycle-viewmodel-release/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/f89ec41460125588ac8085846ff5aacb/transformed/jetified-lifecycle-viewmodel-savedstate-2.8.1/proguard.txt
-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.ViewModel {
    <init>(androidx.lifecycle.SavedStateHandle);
}

-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application,androidx.lifecycle.SavedStateHandle);
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/f89ec41460125588ac8085846ff5aacb/transformed/jetified-lifecycle-viewmodel-savedstate-2.8.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/07a628a737e722d4a775b858411986ce/transformed/jetified-lifecycle-process-2.8.1/proguard.txt
# this rule is need to work properly when app is compiled with api 28, see b/142778206
-keepclassmembers class * extends androidx.lifecycle.EmptyActivityLifecycleCallbacks { *; }
# End of content from /Users/apple/.gradle/caches/8.8/transforms/07a628a737e722d4a775b858411986ce/transformed/jetified-lifecycle-process-2.8.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/86fb7f22e8602e6b1c2c2aed7b93bcdc/transformed/jetified-KbListener-0.1.3/proguard.txt

# End of content from /Users/apple/.gradle/caches/8.8/transforms/86fb7f22e8602e6b1c2c2aed7b93bcdc/transformed/jetified-KbListener-0.1.3/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/717c6d7be619cbd1867d777f745ca395/transformed/versionedparcelable-1.1.1/proguard.txt
-keep class * implements androidx.versionedparcelable.VersionedParcelable
-keep public class android.support.**Parcelizer { *; }
-keep public class androidx.**Parcelizer { *; }
-keep public class androidx.versionedparcelable.ParcelImpl

# End of content from /Users/apple/.gradle/caches/8.8/transforms/717c6d7be619cbd1867d777f745ca395/transformed/versionedparcelable-1.1.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/d7d14f53a24bcbac4afc04eb0e700d03/transformed/rules/lib/META-INF/proguard/retrofit2.pro
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# End of content from /Users/apple/.gradle/caches/8.8/transforms/d7d14f53a24bcbac4afc04eb0e700d03/transformed/rules/lib/META-INF/proguard/retrofit2.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/c3ef23f0385e11e249a60f4f466d5170/transformed/rules/lib/META-INF/proguard/okhttp3.pro
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# End of content from /Users/apple/.gradle/caches/8.8/transforms/c3ef23f0385e11e249a60f4f466d5170/transformed/rules/lib/META-INF/proguard/okhttp3.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/65e0f734b16821bb709809ab4ec760d0/transformed/jetified-googleid-1.1.0/proguard.txt
# Proguard cannot process META-INF/versions/9.
# See https://discuss.gradle.org/t/meta-inf-version-duplicate-error-when-using-proguard/31380
-dontwarn module-info

# End of content from /Users/apple/.gradle/caches/8.8/transforms/65e0f734b16821bb709809ab4ec760d0/transformed/jetified-googleid-1.1.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/acbd35feac8d07d9892c49945526f7f1/transformed/jetified-firebase-components-18.0.0/proguard.txt
-dontwarn com.google.firebase.components.Component$Instantiation
-dontwarn com.google.firebase.components.Component$ComponentType

-keep class * implements com.google.firebase.components.ComponentRegistrar
-keep,allowshrinking interface com.google.firebase.components.ComponentRegistrar

# End of content from /Users/apple/.gradle/caches/8.8/transforms/acbd35feac8d07d9892c49945526f7f1/transformed/jetified-firebase-components-18.0.0/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/2ac30ec61e5551cab328fdf5778ca680/transformed/jetified-startup-runtime-1.1.1/proguard.txt
# It's important that we preserve initializer names, given they are used in the AndroidManifest.xml.
-keepnames class * extends androidx.startup.Initializer

# These Proguard rules ensures that ComponentInitializers are are neither shrunk nor obfuscated,
# and are a part of the primary dex file. This is because they are discovered and instantiated
# during application startup.
-keep class * extends androidx.startup.Initializer {
    # Keep the public no-argument constructor while allowing other methods to be optimized.
    <init>();
}

-assumenosideeffects class androidx.startup.StartupLogger { public static <methods>; }

# End of content from /Users/apple/.gradle/caches/8.8/transforms/2ac30ec61e5551cab328fdf5778ca680/transformed/jetified-startup-runtime-1.1.1/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/8f54505ec3396ad84d6a340cd115801b/transformed/room-runtime-2.2.5/proguard.txt
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# End of content from /Users/apple/.gradle/caches/8.8/transforms/8f54505ec3396ad84d6a340cd115801b/transformed/room-runtime-2.2.5/proguard.txt
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/967c49e22e02f2f6dbcc7113d986acf9/transformed/rules/lib/META-INF/proguard/androidx-annotations.pro
-keep,allowobfuscation @interface androidx.annotation.Keep
-keep @androidx.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <init>(...);
}

-keepclassmembers,allowobfuscation class * {
  @androidx.annotation.DoNotInline <methods>;
}

# End of content from /Users/apple/.gradle/caches/8.8/transforms/967c49e22e02f2f6dbcc7113d986acf9/transformed/rules/lib/META-INF/proguard/androidx-annotations.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/d509f4f9e94485b6fec3b13c6923e1e5/transformed/rules/lib/META-INF/com.android.tools/r8/r8.pro
-identifiernamestring @dagger.internal.IdentifierNameString class ** {
    static java.lang.String *;
}
# End of content from /Users/apple/.gradle/caches/8.8/transforms/d509f4f9e94485b6fec3b13c6923e1e5/transformed/rules/lib/META-INF/com.android.tools/r8/r8.pro
# The proguard configuration file for the following section is /Users/apple/.gradle/caches/8.8/transforms/4de9870f5d35b721e9b49329e5d431f1/transformed/rules/lib/META-INF/proguard/rxjava3.pro
-dontwarn java.util.concurrent.Flow*
# End of content from /Users/apple/.gradle/caches/8.8/transforms/4de9870f5d35b721e9b49329e5d431f1/transformed/rules/lib/META-INF/proguard/rxjava3.pro
# The proguard configuration file for the following section is /Users/apple/Downloads/source/LearnComputerCourse/features/splash/build/intermediates/aapt_proguard_file/devRelease/generateDevReleaseLibraryProguardRules/aapt_rules.txt
# Generated by the gradle plugin
-keep class androidx.appcompat.widget.AppCompatImageView { <init>(...); }
-keep class androidx.appcompat.widget.AppCompatTextView { <init>(...); }
-keep class androidx.constraintlayout.widget.ConstraintLayout { <init>(...); }
-keep class androidx.core.widget.ContentLoadingProgressBar { <init>(...); }

# End of content from /Users/apple/Downloads/source/LearnComputerCourse/features/splash/build/intermediates/aapt_proguard_file/devRelease/generateDevReleaseLibraryProguardRules/aapt_rules.txt
# The proguard configuration file for the following section is <unknown>
-keep class **.R
-keep class **.R$* {*;}
# End of content from <unknown>