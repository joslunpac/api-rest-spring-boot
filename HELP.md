## Útiles

### Comandos Maven

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

### Para añadir distintos profiles de compilación

**pom.xml**

```bash
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <activatedProperties>dev</activatedProperties>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <activatedProperties>prod</activatedProperties>
        </properties>
    </profile>
</profiles>
```

**application.yaml**

```bash
spring:
  profiles:
    active: @activatedProperties@
```
