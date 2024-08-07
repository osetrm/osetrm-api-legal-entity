# osetrm-api-legal-entity


## Run Locally in Dev Mode
```shell script
./mvnw clean quarkus:dev
```

Log in with `user - test:Pass123!`

## Build Image Locally

```shell
./mvnw clean package
podman build -f Containerfile -t quay.io/osetrm/osetrm-api-legal-entity:latest .
```

## Run Locally with Podman

```shell
podman-compose up -d
podman-compose down
```

podman run -it --rm --network osetrm-api-legal-entity_default wbitt/network-multitool /bin/bash

```shell
export access_token=$(\
  curl --insecure -X POST http://localhost:8081/realms/osetrm-test/protocol/openid-connect/token \
  --user osetrm-test-api:osetrm-test-api-secret \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'username=test&password=Pass123!&grant_type=password' | jq --raw-output '.access_token' \
)
   
curl -v -X GET \
  http://localhost:8080/v1/legal-entities \
  -H "Authorization: Bearer "$access_token
```

```shell
export access_token=$(\
  curl --insecure -X POST http://osetrm-keycloak:8081/realms/osetrm-test/protocol/openid-connect/token \
  --user osetrm-test-api:osetrm-test-api-secret \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'username=test&password=Pass123!&grant_type=password' | jq --raw-output '.access_token' \
)
   
curl -v -X GET \
  http://localhost:8080/v1/legal-entities \
  -H "Authorization: Bearer "$access_token
```



# Quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/osetrm-api-legal-entity-0.0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- YAML Configuration ([guide](https://quarkus.io/guides/config-yaml)): Use YAML to configure your Quarkus application
