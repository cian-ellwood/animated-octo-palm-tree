
plugins {
    java
    id ("org.springframework.boot") version "3.2.4"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

group = "CianTestGroup"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

//We need to run this independently of the rest of the build script so that the version can be uptaken when the build task is ran
task<Exec>("updateVersion") {
    if (project.property("confidenceLevel") == "PROD") {
        commandLine("python", "${projectDir.absolutePath}/src/main/resources/scripts/updateServiceVersion.py")
    } else {
        println("Skipping incrementing the version as confidence level is not PROD")
    }
}

task("getVersion") {
    version = if (project.property("confidenceLevel") == "PROD") {
        val serviceVersion = File("src/main/resources/scripts/scriptHelpers/version.json")
        val json = groovy.json.JsonSlurper().parseText(serviceVersion.readText()) as Map<*, *>

        json["version"] as String
    } else {
        "0.0.0"
    }

    doLast{
        println(version)
    }
}
task<Exec>("buildDockerImage"){
    dependsOn("clean", "build", "getVersion")
    tasks.getByName("clean").mustRunAfter("getVersion")
    tasks.getByName("build").mustRunAfter("clean")
    //We won't start tagging the version until the build is successful
    val serviceName = rootProject.name
    val imageTag="${project.property("dockerHub")}:$serviceName-$version"
    commandLine("docker", "build", "-t", imageTag, ".", "--build-arg", "VERSION=$version")

}

task<Exec>("saveDockerImage"){
    dependsOn("buildDockerImage")
    commandLine("docker", "save", "-o", "${rootProject.name}-$version.tar.gz", "${rootProject.name}:$version")
}

task<Exec>("publishDockerImage"){
    val imageTag="${project.property("dockerHub")}:${rootProject.name}-$version"
    commandLine("docker", "push", imageTag)
}
