# PinewoodDerby UI
This is a small project that provides a web-based UI to the sensor data used in Derby Magic.

## Building
This a Spring Boot based application. As such, running `mvn clean install` should generate a java binary
file that in turn can be executed (assuming you have java install).

For the non-technical folks, I'll be creating other installation methods

## Interfaces
Once running, the UI can be seen from the following link:

	http://localhost:8080/swagger-ui.html

In addition, the swagger documentation for this app can be found here:

	http://localhost:8080/swagger-ui.html

## Enhancements
The UI is built using Javascript that relies in REST endpoints to interact with (in-memory) data. I'm 
specifically keeping this separation so that in the future I could maybe get a better UI (or different UI) but with
little logic change in the backend.

## Future Ideas

* Build an Electron binary (using NodeJS instead of Spring Boot)
* Create an installer for Spring Boot.

