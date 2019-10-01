buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("net.serenity-bdd:serenity-gradle-plugin:2.0.60")
    }
}

apply(plugin = "net.serenity-bdd.aggregator")

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.26")
    implementation("org.slf4j:slf4j-jdk14:1.7.26")
    implementation("net.serenity-bdd:serenity-core:2.0.60")
    implementation("net.serenity-bdd:serenity-junit:2.0.60")
    implementation("net.serenity-bdd:serenity-rest-assured:2.0.60")
    testImplementation("org.assertj:assertj-core:3.9.0")
}

tasks.test {
    systemProperty("app.host", System.getProperty("app.host"))
    systemProperty("app.port", System.getProperty("app.port"))
}