// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta05" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.diffplug.spotless") version "6.19.0"
}

apply(from = "${project.rootDir}/spotless.gradle")

tasks.register("updateGitHooks", Copy::class.java) {
    from("./scripts/pre-commit")
    into("./.git/hooks")
}
tasks.withType<JavaCompile> { dependsOn("updateGitHooks") }