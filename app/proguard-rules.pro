# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /mnt/c8d3ad16-4c1d-4b24-9e67-f407a2a655df/licious/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

#Dontwarn for any warnings
-dontwarn **

-keep class in.arshad.offlinetimetable.**

#SearchView Actionbar
#-keep class android.support.v7.widget.SearchView { *; }

#For retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}