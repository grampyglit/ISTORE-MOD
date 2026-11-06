# iStore (Fabric, Minecraft 26.3)
Requires JDK 25, Gradle 9.3+ (run `gradle wrapper` once, or copy the wrapper from the Fabric template).
Build: `./gradlew build`  ->  build/libs/istore-1.0.0.jar
CI: .github/workflows/build.yml builds on push/PR; tag vX.Y.Z to publish a release.
Test: `./gradlew runClient`, then `/give @s istore:storage_display`
