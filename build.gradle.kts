plugins {
    java
    id ("org.springframework.boot") version "3.2.4"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

group = "CianTestGroup"
version = "1.2.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}