import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

val credentialsPropFile = rootProject.file("credentials.properties")
val credentials = Properties().apply {
    load(credentialsPropFile.inputStream())
}

val githubAccessToken = credentials["githubAccessToken"] as String

android {
    namespace = "com.xheghun.repolens"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xheghun.repolens"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "GITHUB_ACCESS_TOKEN", githubAccessToken)
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.compose.navigation)

    //SPLASH SCREEN
    implementation(libs.androidx.splashscreen)

    //DI
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.navigation)
    implementation(libs.koin.android)

    //NETWORK
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.gson)
    implementation(libs.squareup.gson)
    implementation(libs.squareup.logging.interceptor)

    //IMAGE LOADING
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    //TIME UTILS
    implementation(libs.threeten)

    //TEST
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}