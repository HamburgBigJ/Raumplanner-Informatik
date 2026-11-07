# Raumplanner-Mod

Gradle-Unterprojekt für einen einfachen UniversalMixin-Einstiegspunkt in den
Raumplaner. Beide Projekte liegen in diesem Repository und verwenden den
Gradle-Wrapper im Hauptordner. Öffne den Repository-Hauptordner in IntelliJ IDEA,
um beide Projekte zusammen zu laden.

```text
Raumplanner-Informatik/
├── src/                  # Raumplaner
├── settings.gradle.kts   # Bindet das Mod-Projekt ein
├── gradlew               # Gemeinsamer Gradle-Wrapper
└── Raumplanner-Mod/
    ├── src/              # Einstiegspunkt und Mixin
    ├── libs/             # UniversalMixin-Agent
    └── build.gradle.kts
```

## Einstiegspunkt

Eigenen Startcode schreibst du in:

`src/main/java/cho/info/roomplannermod/ModEntryPoint.java`

```java
public static void onInitialize() {
    System.out.println("[Raumplanner-Mod] Mod initialized.");
    // Hier kommt dein Startcode hin.
}
```

`MainMixin` injiziert den Aufruf direkt vor den ursprünglichen Rumpf von
`cho.info.Main.main()`. Danach läuft der Raumplaner normal weiter. Der Hook läuft
bei jedem Aufruf von `main`, beim normalen Programmstart also einmal. Zu diesem
Zeitpunkt wurden das Fenster und die Möbel noch nicht erstellt. Das Mixin wird
vom Agenten gefunden; es muss weder instanziiert noch manuell aufgerufen werden.

## Starten

Voraussetzungen: JDK 26 für den aktuellen Raumplaner. Der Mod selbst wird für
Java 17 kompiliert. Die echte gebündelte UniversalMixin-Agent-JAR ist bereits
unter `libs/universal-mixin.jar` enthalten.

Alle folgenden Befehle werden im Repository-Hauptordner ausgeführt.

Raumplaner mit Mod bauen und starten:

```sh
./gradlew :Raumplanner-Mod:run
```

Dabei werden die Raumplaner-JAR und die Mod-JAR automatisch gebaut.
Erwartete Ausgabe vor dem Öffnen des Fensters:

```text
[Raumplanner-Mod] Mod initialized.
```

Raumplaner ohne Mod starten:

```sh
./gradlew :run
```

Beide Projekte bauen:

```sh
./gradlew build
```

Nur den Mod bauen:

```sh
./gradlew :Raumplanner-Mod:build
```

Eine andere Raumplaner-JAR auswählen (relative Pfade gelten ab Repository-Hauptordner):

```sh
./gradlew :Raumplanner-Mod:run -ProomPlannerJar=/absoluter/pfad/raumplanner.jar
```

Manueller Start nach dem Build, ebenfalls aus dem Repository-Hauptordner:

```sh
java "-javaagent:Raumplanner-Mod/libs/universal-mixin.jar=Raumplanner-Mod/build/libs/Raumplanner-Mod-1.0-SNAPSHOT.jar" \
  -jar build/libs/Raumplanner-Aufgabe-1.0-SNAPSHOT-all.jar
```

Der Mod enthält nur den Einstiegspunkt und das Mixin. Agent und Raumplaner bleiben
separate JARs. Die ursprüngliche Raumplaner-JAR wird auf der Festplatte nicht verändert.
