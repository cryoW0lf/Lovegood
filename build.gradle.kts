plugins {
    java
    checkstyle
}

group = "pw.cryow0lf"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.immutables:value:2.7+")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.+")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.+")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
