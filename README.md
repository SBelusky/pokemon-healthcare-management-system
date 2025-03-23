## Status
this project is currently under development.

# Pokémon Healthcare Managment System
Pokémon Healthcare Management System (PHMS) is a microservices-based application designed to manage healthcare services for Pokémon. It enables trainers and doctors to efficiently handle medical records, schedule appointments, and oversee treatments in Pokémon Centers.
___

🔹 Key Features:

- Registration of trainers, doctors, and Pokémon
- Management of Pokémon Centers and their services
- Appointment scheduling and tracking
- Treatment history, surgeries, and prescriptions
- Notifications for trainers

## Services Overview

- Pokémon Service
- User Service
- Pokémon Center Service
- Treatment History Service
- Appointment Service
- Notification Service
- API Gateway using Spring Cloud Gateway MVC

## Tech Stack

The technologies used in this project are:

- Spring Boot
- Mongo DB
- MySQL
- Kafka
- Keycloak
- Test Containers with Wiremock
- Grafana Stack (Prometheus, Grafana, Loki and Tempo)
- API Gateway using Spring Cloud Gateway MVC
- Kubernetes

## How to build the backend services

Run the following command to build and package the backend services into a docker container

```shell
mvn spring-boot:build-image -DdockerPassword=<your-docker-account-password>
```

The above command will build and package the services into a docker container and push it to your docker hub account.

## How to run the backend services

Make sure you have the following installed on your machine:

- Java 21
- Docker
- Kind Cluster - https://kind.sigs.k8s.io/docs/user/quick-start/#installation

### Start Kind Cluster
    
Run the k8s/kind/create-kind-cluster.sh script to create the kind Kubernetes cluster

```shell
./k8s/kind/create-kind-cluster.sh
```
This will create a kind cluster and pre-load all the required docker images into the cluster, this will save you time downloading the images when you deploy the application.

### Deploy the infrastructure

Run the k8s/manisfests/infrastructure.yaml file to deploy the infrastructure

```shell
kubectl apply -f k8s/manifests/infrastructure.yaml
```

### Deploy the services

Run the k8s/manifests/applications.yaml file to deploy the services

```shell
kubectl apply -f k8s/manifests/applications.yaml
```

### Access the API Gateway

To access the API Gateway, you need to port-forward the gateway service to your local machine

```shell
kubectl port-forward svc/api-gateway 9000:9000
```

### Access the Keycloak Admin Console
To access the Keycloak admin console, you need to port-forward the keycloak service to your local machine

```shell
kubectl port-forward svc/keycloak 8080:8080
```

### Access the Grafana Dashboards
To access the Grafana dashboards, you need to port-forward the grafana service to your local machine

```shell
kubectl port-forward svc/grafana 3000:3000
```
