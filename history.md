
```shell
quarkus create app --wrapper --no-code \
    -x config-yaml,resteasy-reactive-jackson \
    com.osetrm:osetrm-api-legal-entity:0.0.1-SNAPSHOT
```

```shell
quarkus ext add smallrye-openapi
quarkus ext add hibernate-validator,hibernate-orm-panache,jdbc-postgresql,flyway
```

