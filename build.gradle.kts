import Build_gradle.ServiceVars.serviceName
object ServiceVars {
    const val serviceName = "apilogger"
}

plugins {
    java
    id ("org.springframework.boot") version "3.2.4"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

group = "CianTestGroup"
version = if (project.property("version") != "unspecified") {
    "${project.property("version")}"
} else {
    "0.0.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}




task<Exec>("buildDockerImage"){
    dependsOn("clean", "build")
    tasks.getByName("build").mustRunAfter("clean")

    //When overriding global var
    if (project.hasProperty("version")){
        version = "${project.property("version")}"
    }
    commandLine("docker", "build", "-t", "$serviceName:$version", ".", "--build-arg", "VERSION=$version")

}

task<Exec>("saveDockerImage"){
    dependsOn("buildDockerImage")
    commandLine("docker", "save", "-o", "$serviceName-$version.tar.gz", "$serviceName:$version")
}

task<Exec>("tagDockerImage"){
    val imageTag="${project.property("dockerHub")}:$serviceName-$version"
    commandLine("docker", "tag", "$serviceName:$version", imageTag)
}

task<Exec>("publishDockerImage"){
    dependsOn("tagDockerImage")
    val imageTag="${project.property("dockerHub")}:$serviceName-$version"
    commandLine("docker", "push", imageTag)
}
