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

-repackageclasses ''
-renamesourcefileattribute 'SourceFile'

-assumenosideeffects class android.util.Log {
 public static *** d(...);
 public static *** i(...);
 public static *** v(...);
}

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.* { *;}

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

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

#Data Binding
-keepnames,allowobfuscation class * implements java.io.Serializable
-keepclassmembers,allowobfuscation class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepattributes *Annotation*
-keepattributes javax.xml.bind.annotation.*
-keepattributes javax.annotation.processing.*
-keepclassmembers,allowobfuscation class * extends java.lang.Enum { *; }
#-dontobfuscate

#Navigation component
-keep class * extends androidx.fragment.app.Fragment{}
-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.* { *; }
-keep class * implements com.google.gson.TypeAdapterFactory.* { *; }
-keep class * implements com.google.gson.JsonSerializer.* { *; }
-keep class * implements com.google.gson.JsonDeserializer.* { *; }

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName.* <fields>;
}

-keep class org.apache.commons.logging.**               { *; }
-keep class com.amazonaws.services.sqs.QueueUrlHandler  { *; }
-keep class com.amazonaws.javax.xml.transform.sax.*     { public *; }
-keep class com.amazonaws.javax.xml.stream.**           { *; }
-keep class com.amazonaws.services.**.model.*Exception* { *; }
-keep class org.codehaus.**                             { *; }

-dontwarn javax.xml.stream.events.**
-dontwarn org.codehaus.jackson.**
-dontwarn org.apache.commons.logging.impl.**
-dontwarn org.apache.http.conn.scheme.**

-keep class ir.siaray.downloadmanagerplus.** {*;}

-keep class com.akexorcist.localizationactivity.** { *; }
-dontwarn com.akexorcist.localizationactivity.**

-keep class com.shockwave.**

-keep class * extends android.webkit.WebChromeClient { *; }
-dontwarn im.delight.android.webview.**

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# Keep specific classes or methods if needed
-keep class com.google.android.gms.maps.model.LatLng { *; }

-dontwarn org.joda.convert.*
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

# R8 full mode strips signatures from non-kept items.
-keep,allowobfuscation,allowshrinking interface com.studio.data.base.NetworkResponse
-dontwarn com.studio.common.ui.*
-keep class com.studio.common.ui.** {*; }
-keep class com.studio.data.** { *; }
-dontwarn java.lang.invoke.StringConcatFactory

# Google Mobile Ads SDK
-keep class com.google.android.gms.ads.** { *; }
-keep class com.google.ads.** { *; }
-keep class com.google.android.gms.common.GooglePlayServicesUtil { *; }
-keep class com.google.android.gms.common.internal.safeparcel.SafeParcelable { *; }
-keep class com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor { *; }
-keep class * extends com.google.android.gms.common.internal.safeparcel.SafeParcelable { *; }
-keepclassmembers class * extends com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    static final *** NULL;
    static final *** CREATOR;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Keep Gson classes and methods
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }

# Keep fields with the @SerializedName annotation
-keepclassmembers class ** {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep fields with Gson annotations (like @Expose)
-keepattributes *Annotation*

# Keep generic types used in Gson TypeToken
-keep class * implements com.google.gson.reflect.TypeToken

# Keep constructors for classes used with Gson
-keepclassmembers class * {
    public <init>(...);
}

# Keep inner classes
-keepattributes InnerClasses

# Keep Kotlin metadata (if using Gson with Kotlin)
-keepattributes KotlinMetadata

# Optional: keep specific custom serializers/deserializers
-keep class com.studio.domain.utils.GsonManager { *; }
-keep class com.studio.domain.model.response.BaseResponse { *; }


# Keep the coroutine internals
-keep class kotlinx.coroutines.** { *; }

# Keep specific internal classes and methods for Kotlin Coroutines
-keepclassmembers class kotlinx.coroutines.internal.** {
    <init>(...);
}

# Keep classes and methods for coroutines debugging
-keep class kotlinx.coroutines.debug.** { *; }

# Keep Kotlin metadata for all classes
-keepattributes KotlinMetadata

# Keep the Main dispatcher
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory {
    *;
}

# Keep classes used for Android dispatchers
-keep class kotlinx.coroutines.android.HandlerDispatcher {
    *;
}

# Keep experimental coroutines API annotations
-keepattributes kotlinx.coroutines.*

# Keep Continuation classes
-keep class kotlin.coroutines.Continuation {
    *;
}

# Keep the actual coroutine API classes
-keep class kotlinx.coroutines.flow.Flow {
    *;
}
-keep class kotlinx.coroutines.flow.FlowCollector {
    *;
}

# Keep coroutines context and related classes
-keep class kotlinx.coroutines.CoroutineContext {
    *;
}
-keep class kotlinx.coroutines.Job {
    *;
}
-keep class kotlinx.coroutines.CoroutineDispatcher {
    *;
}
-keep class kotlinx.coroutines.Dispatchers {
    *;
}

# Preserve the ViewModel classes
-keep public class * extends androidx.lifecycle.ViewModel
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Preserve LiveData classes
-keep public class androidx.lifecycle.LiveData {
    *;
}

-keep public class androidx.lifecycle.MutableLiveData {
    *;
}

# Preserve Lifecycle classes and annotations
-keep class androidx.lifecycle.** { *; }
-keep @androidx.lifecycle.Lifecycle.Event class * { *; }
-keep @androidx.lifecycle.LifecycleObserver class * { *; }
-keep @androidx.lifecycle.OnLifecycleEvent class * { *; }

# Dagger Hilt
-keep class dagger.hilt.** { *; }
-keep class dagger.hilt.android.** { *; }
-keep class dagger.hilt.android.internal.managers.** { *; }
-keep class dagger.hilt.android.internal.lifecycle.** { *; }
-keep class dagger.hilt.internal.** { *; }

# Dagger core
-keep class dagger.internal.** { *; }
-keep class dagger.internal.codegen.** { *; }
-keep class dagger.Component { *; }
-keep class dagger.Subcomponent { *; }
-keep class dagger.Module { *; }
-keep class dagger.Provides { *; }
-keep class javax.inject.Inject { *; }
-keep class javax.inject.Singleton { *; }
-keep class javax.inject.** { *; }
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
}
-keepclassmembers class * {
    @dagger.hilt.InstallIn <fields>;
}

# AndroidX lifecycle
-keepclassmembers class * {
    @androidx.lifecycle.LifecycleObserver <fields>;
}
-keepclassmembers class * {
    @androidx.lifecycle.OnLifecycleEvent <methods>;
}
-keepclassmembers class * {
    @dagger.hilt.android.lifecycle.HiltViewModel <fields>;
}

# General rules for classes annotated with Hilt annotations
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations

# Keep generated Hilt components and modules
-keep class **_HiltComponents {
    <init>(...);
}
-keep class **_HiltModules {
    <init>(...);
}

# Keep the entry points (activities, fragments, services, etc.) annotated with @AndroidEntryPoint
-keep @dagger.hilt.android.AndroidEntryPoint class * {
    <init>(...);
}

# Preserve Groupie model classes
-keep class * extends com.xwray.groupie.Item { *; }
-keep class * extends com.xwray.groupie.databinding.BindableItem { *; }
-keep class * extends com.xwray.groupie.viewbinding.BindableItem { *; }

# Preserve the Groupie library
-keep class com.xwray.groupie.** { *; }

# Preserve data binding classes
-keep class * extends androidx.databinding.ViewDataBinding { *; }

# Keep generated classes for Groupie data binding
-keep class com.xwray.groupie.databinding.* { *; }
-keep class com.studio.ads.* { *; }
-keep class com.studio.domain.* { *; }

