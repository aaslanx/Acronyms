plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.acronyms"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // ViewModel and LifeCycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Navigator
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.4.0")

    //Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //Okhttp3
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.4.0")

    //Timber logging
    implementation("com.jakewharton.timber:timber:4.7.1")
}
