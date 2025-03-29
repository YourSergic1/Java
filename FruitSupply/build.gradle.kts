plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4" //убирает необходимость прописывать версии
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
}

group = "com.github.YourSergic1"
version = "1.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:42.7.5")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-config")
}

tasks.test {
    useJUnitPlatform()
}

tasks.bootJar {
    mainClass.set("com.github.YourSergic1.Main") // Укажите ваш главный класс
}