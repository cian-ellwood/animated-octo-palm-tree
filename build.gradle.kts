plugins {
    id ("java")
    id("org.springframework.boot") version "3.2.4"
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}

group = "CianTestGroup"
version = "1.2.3"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}