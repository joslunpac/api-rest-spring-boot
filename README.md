<h1 align="center">API Rest Spring Boot</h1>
<div align="center">

![GitHub](https://img.shields.io/github/license/joslunpac/api-rest-spring-boot?label=Licencia&style=flat-square)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/joslunpac/api-rest-spring-boot?label=Release&logo=Github&style=flat-square)
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/joslunpac/api-rest-spring-boot?label=Tag&logo=Github&style=flat-square)
![GitHub last commit](https://img.shields.io/github/last-commit/joslunpac/api-rest-spring-boot?label=%C3%9Altimo%20commit&logo=Github&style=flat-square)

</div>

API Restful utilizando Spring Boot, Spring Data JPA y MySQL. Securizada con autenticación JWT basada en roles usando Spring Security. Documentada con Swagger, bajo la especificación OpenAPI (OAS3).

**Herramientas empleadas:**

- ![](https://img.shields.io/badge/Java-11-%23007396?style=flat-square&logo=java)
- ![Maven Central](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot?color=%236DB33F&label=Spring%20Boot&logo=Spring%20Boot&style=flat-square&versionSuffix=2.5.2)
- ![Maven Central](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-starter-data-jpa?color=%236DB33F&label=Spring%20Data%20JPA&logo=Spring%20Boot&style=flat-square&versionSuffix=2.5.2) ![Maven Central](https://img.shields.io/maven-central/v/mysql/mysql-connector-java?color=%234479A1&label=MySQL&logo=MySQL&logoColor=FFF&style=flat-square)
- ![Maven Central](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-starter-security?color=%236DB33F&label=Spring%20Security&logo=Spring%20Boot&style=flat-square&versionSuffix=2.5.2) ![Maven Central](https://img.shields.io/maven-central/v/io.jsonwebtoken/jjwt?color=blueviolet&label=JWT&logo=JSON%20Web%20Tokens&style=flat-square&versionSuffix=0.9.1)
- ![Maven Central](<https://img.shields.io/maven-central/v/org.springdoc/springdoc-openapi-ui?color=%236BA539&label=OpenAPI%20(OAS3)&logo=OpenAPI%20Initiative&logoColor=%236BA539&style=flat-square&versionSuffix=1.5.9>)
- ![Maven Central](https://img.shields.io/maven-central/v/org.projectlombok/lombok?label=Lombok&style=flat-square) ![Maven Central](https://img.shields.io/maven-central/v/org.modelmapper.extensions/modelmapper-spring?label=Model%20Mapper&style=flat-square) ![](https://img.shields.io/badge/Exception%20Handler-%20-red?style=flat-square)

## Configuración paso a paso

**1. Clone la aplicación**

```bash
git clone https://github.com/joslunpac/api-rest-spring-boot.git
```

**2. Cree una base de datos MySQL**

```bash
create database mydb
```

**3. Configure las propiedades de la aplicación**

- Abre `src/main/resources/application.yaml`.
- Cambia `spring.datasource.username` y `spring.datasource.password` según su instalación de MySQL.

**4. Cree y ejecute la aplicación con maven**

```bash
mvn clean package
java -jar target/api-1.0.0.jar
```

Alternativamente, puede ejecutar la aplicación sin empaquetarla usando

```bash
mvn spring-boot:run
```

Podrá acceder a la documentación de la aplicación en <http://localhost:8080/api/swagger>

## Contribución

Cualquier sugerencia es bienvenida, también se recomienda encarecidamente presentar un problema o enviar un PR.

## Encuéntrame

[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- MARKDOWN LINKS & IMAGES -->

[linkedin-shield]: https://img.shields.io/badge/LinkedIn-joslunpac-0A66C2?style=flat-square&logo=linkedin
[linkedin-url]: https://linkedin.com/in/joslunpac
