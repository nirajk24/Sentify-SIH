// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    repositories{
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
    }
}
//allprojects {
//    repositories {
//        maven { url = uri("https://www.jitpack.io" ) }
//    }
//}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}