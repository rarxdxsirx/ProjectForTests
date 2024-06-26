plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.projectfortests"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projectfortests"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
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
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit:1.1.5")

    testImplementation("junit:junit:4.13.2")
    testImplementation ("androidx.test.ext:junit:1.1.5")
    testImplementation("org.mockito:mockito-core:2.19.0")
    testImplementation("androidx.test:runner:1.5.2")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.test:rules:1.5.0")
    testImplementation("com.android.support.test.espresso:espresso-core:3.0.2")

    androidTestImplementation("junit:junit:4.13.2")
   // androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
   // androidTestImplementation ("androidx.test.ext:truth:1.5.0")
   // androidTestImplementation ("androidx.test:core-ktx:1.5.0")
    //androidTestImplementation ("androidx.test.ext:junit-ktx:1.1.5")
   // androidTestImplementation ("com.google.truth:truth:1.0")
    //androidTestImplementation ("org.robolectric:robolectric:4.9")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}