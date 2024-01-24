plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.sentimentanalysis"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sentimentanalysis"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.firebase:firebase-auth")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment:2.2.0")
    implementation("androidx.navigation:navigation-ui:2.2.0")

    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    //GSON Convertor
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Circle Image
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Any Chart
    implementation("com.github.AnyChart:AnyChart-Android:1.1.5")

    // MPAndroid Chart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Audio Picker
//    implementation("com.github.majidarabi:AndroidFilePicker:v0.2.2")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")


}