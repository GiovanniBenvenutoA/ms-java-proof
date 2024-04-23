# ms-java-proof

## Descripción
Este proyecto tiene como objetivo la creación de una aplicación de productos sobre la cual poder buscar productos similes.

## Tecnologías
Este proyecto está desarrollado utilizando las siguientes tecnologías:

- Java 17
- Sonarqube
- Maven 3.8.5
- Spring Boot 2.7.3
- JWT
- CORS
- Unit-Test

## Complementos
Se han utilizado los siguientes complementos:

- Coretto-17 versión "17.0.10"
- IntelliJ IDEA
- Google Chrome

## Despliegue
Para desplegar la aplicación en un entorno local, sigue estos pasos:

1. Clona el repositorio desde [https://github.com/GiovanniBenvenutoA/ms-java-proof](https://github.com/GiovanniBenvenutoA/ms-java-proof).
2. Ábrelo en algún compilador de Java (Yo utilicé IntelliJ IDEA).
3. Ejecuta Maven Clean Install. En la sección de configuraciones, escribe "clean install" con Maven y ejecútalo.
4. Luego de eso, aparecerá `MsProductProofApplication`, que es una aplicación de Spring Boot. Ejecútala.
5. Cuando termine el despliegue, podrás ingresar [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html).
6. Necesitas primero ejecutar el servicio de obtención de token [http://localhost:8082/api/token?userToken=userToken](http://localhost:8082/api/token?userToken=userToken).
