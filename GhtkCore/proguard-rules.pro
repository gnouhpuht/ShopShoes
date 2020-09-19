# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\DepTools\adt-bundle-windows-x86_64-20130917\sdk/tools/proguard/proguard-android.txt
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

# For android test
-dontwarn com.google.common.**

-keepattributes *Annotation*,EnclosingMethod,Signature

########### For Android Support libs ###########
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

########### For Android Support Design ###########
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

########### For Android Support CardView ###########
-keep class android.support.v7.widget.RoundRectDrawable { *; }

########### For Android appcombat ###########
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

########## For RecyclerView$LayoutManager ###########
-keep public class * extends android.support.v7.widget.RecyclerView$LayoutManager {
    public <init>(...);
}

########### For v7 ###########
-keep class android.support.v7.widget.** { *; }
-dontwarn android.support.v7.widget.**

########### For butter knife ###########
-dontwarn butterknife.internal.**
-dontwarn butterknife.Views$InjectViewProcessor
-keep class butterknife.** { *; }
-keep class **$$ViewBinder { *; }
#-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

########### For retrofit2 ###########
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keep public class com.android.vending.licensing.ILicensingService
-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient
-keep class org.apache.http.** { *; }

-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn org.apache.http.**
-dontwarn org.apache.commons.**
-dontwarn android.net.**
-dontwarn rx.**
-dontwarn retrofit.**
-dontwarn retrofit2.Platform$Java8
-dontwarn okio.**
-dontwarn fi.foyt.foursquare.**

-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}


########### For Glide ###########
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# todo check
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule