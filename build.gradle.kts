// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta05" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.google.dagger.hilt.android") version "2.44.1" apply false
    id("com.diffplug.spotless") version "6.19.0"
}

val installPreCommit = copy {
    from("./scripts/pre-commit")
    into("./.git/hooks")
    fileMode = Integer.parseInt("777",8)
}

task("installGitHooks") { installPreCommit }

apply(from = "${project.rootDir}/spotless.gradle")

