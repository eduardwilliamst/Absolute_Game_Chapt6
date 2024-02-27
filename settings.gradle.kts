pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io" )
    }
}

rootProject.name = "Absolute Game"
include(":app")
include(":domain")
include(":data")
include(":data")
include(":di")
include(":presentation")
include(":datas")
