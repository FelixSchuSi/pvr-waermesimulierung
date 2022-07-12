# pvr-waermesimulierung

Parallel heat simulation software created by [Jannick Bergjan](https://github.com/jannikbergjan) and [Felix Schulze Sindern](https://github.com/FelixSchuSi) for a university project.
Use it yourself [here](https://pvr-waermesimulierung-production.up.railway.app/) or watch the demo video below!

## Live Demo

https://user-images.githubusercontent.com/47390169/177590996-062f2a50-93c5-41e1-acc7-0954a3655415.mp4

## How to run
There are two options:

1. Use Java 11 and start the service with `java -jar pvr-waermesimulierung-fatjar.jar`

2. Or run the service using docker: `docker run --rm -it -p 8080:8080/tcp $(docker build -q .)` 

The Service is now available at http://localhost:8080!

<details>
<summary><b>(Optional) Build the `.jar` file yourself</b></summary>

1. mvn clean install -Pproduction -DskipTests
2. cp target/mytodo-1.0-SNAPSHOT.jar pvr-waermesimulierung-fatjar.jar

</details>
