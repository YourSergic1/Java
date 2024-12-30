plugins {
    id("com.gradleup.shadow") version "8.3.2"
    id("application")
}

group = ""
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.baulsupp.kolja:jcurses:0.9.5.3")
    implementation("com.google.code.gson:gson:2.10.1")
}


tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest.attributes["Main-Class"] = "Main"
}

tasks.shadowJar {
    archiveBaseName.set("Rogue")
    archiveClassifier.set("")
    archiveVersion.set("1.0")
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass.set("Main")

}

tasks.register("Rogue", Exec::class) {
    group = "application"
    dependsOn("shadowJar")
    println("Start Rogue Jar file")
    executable = "java"
    args = listOf("-jar", "build/libs/Rogue-1.0.jar")
}