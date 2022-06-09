pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com/")
        maven(url = "https://appboy.github.io/appboy-android-sdk/sdk")
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

include(":app")
