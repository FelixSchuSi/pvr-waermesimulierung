# pvr-waermesimulierung

Parallele Wärmesimulierung erstellt von Jannick Bergjan und Felix Schulze Sindern

## Live-Demo

Auch im repo unter ./live_demo.mp4 erreichbar

https://user-images.githubusercontent.com/47390169/176757686-9a546889-6ec2-4956-8a41-e00ae3000ec8.mp4

## Voraussetzungen

- Java 11 (OpenJDK Temurin-11.0.14.1 wurde verwendet)

## Startanleitung

Diese Anwendung wurde als fat jar gebaut, wodurch alle Abhängigkeiten in der .jar Datei entahlten sind und kein
Installieren von Abhängigkeiten notwendig ist.

1. Anwendung starten mit `java -jar pvr-waermesimulierung-fatjar.jar`

2. Die Anwendung ist jetzt unter http://localhost:8080 erreichbar

<details>
<summary><b>(Optional) Anwendung selbst bauen</b></summary>

1. mvn clean install -Pproduction -DskipTests
2. cp target/mytodo-1.0-SNAPSHOT.jar pvr-waermesimulierung-fatjar.jar

</details>