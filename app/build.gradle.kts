plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}
android {
    namespace = "com.sagrd.flappyenel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sagrd.flappyenel"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.3"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

//Diseño
//Dependencias
//Configurar room https://developer.android.com/training/data-storage/room#kts
//Configurar hilt  https://developer.android.com/training/dependency-injection/hilt-android
//  crear la clase que hereda de Application
//  ponerla en el manifest
//  indicar el entry point en la activity


    dependencies {

        implementation("androidx.core:core-ktx:1.12.0")

        val lifeCycleVersion = "2.6.2"
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion")
        implementation ("androidx.lifecycle:lifecycle-runtime-compose:$lifeCycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifeCycleVersion") //viewmodel

        implementation("androidx.activity:activity-compose:1.7.2")

        val composeBom = platform("androidx.compose:compose-bom:2023.09.00")
        implementation(composeBom)
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")


        //Room
        val roomVersion = "2.5.2"
        implementation("androidx.room:room-runtime:$roomVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")
        //  To use Kotlin annotation processing tool (kapt)
        ksp("androidx.room:room-compiler:$roomVersion")

        //  optional - Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$roomVersion")

        //hilt
        val hiltVersion = "2.48"
        implementation("com.google.dagger:hilt-android:$hiltVersion")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
        ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

        //navegacion
        implementation("androidx.navigation:navigation-compose:2.7.2")

        //retrofit
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
        implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        androidTestImplementation(composeBom)
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        //Img picker
        implementation ("io.coil-kt:coil-compose:2.1.0")
        //Icons Extended
        implementation("androidx.compose.material:material-icons-extended")

        implementation ("androidx.compose.ui:ui:1.0.0-alpha04")
        implementation ("androidx.compose.foundation:foundation:1.0.0-alpha04")
        implementation ("androidx.compose.material:material:1.0.0-alpha04")

        implementation ("androidx.core:core-ktx:1.7.0")
        implementation ("androidx.appcompat:appcompat:1.4.0")
        implementation ("com.google.android.material:material:1.4.0")

        implementation ("androidx.compose.ui:ui-text-google-fonts:1.5.2")

        //DataStorage
        implementation ("androidx.datastore:datastore-preferences:1.0.0")

        //gifs and more coil's things
        implementation ("io.coil-kt:coil-compose:2.1.0")
        implementation ("io.coil-kt:coil-gif:2.1.0")
    }
}

