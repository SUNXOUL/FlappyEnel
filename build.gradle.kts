plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

buildscript {
    val hilVersion = "2.48"
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilVersion")
    }
}