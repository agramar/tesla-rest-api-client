import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    id("java")
}

group = "io.github.agramar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.1")
    implementation("ch.qos.logback:logback-core:1.4.5")
    implementation("org.slf4j:slf4j-api:2.0.6")
    testImplementation("ch.qos.logback:logback-classic:1.4.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
}

tasks.withType<JavaCompile> {
    java {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(FAILED, STANDARD_ERROR, SKIPPED)
        exceptionFormat = FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}
