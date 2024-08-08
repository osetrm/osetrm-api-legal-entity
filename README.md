# osetrm-api-legal-entity

## Run Locally in Dev Mode (test:Pass123!)
```shell script
./mvnw clean quarkus:dev
```
DevUI at <http://localhost:8080/dev-ui/>.

## Build Image Locally
```shell
./mvnw clean package
podman build -f Containerfile -t quay.io/osetrm/osetrm-api-legal-entity:latest .
```

Test