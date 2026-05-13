# Java + PostgreSQL Docker Project

## Without Docker Compose

## Step 1: Login Server

```bash
ssh azureuser@Your-Server-IP
```

## Step 2: Clone Repository

```bash
git clone https://github.com/shubhsalunke/Java-PostgreSQL-Docker.git
cd Java-PostgreSQL-Docker
```
# Create Environment File

Copy sample environment file:

```bash
cp sample.env .env
```

Open `.env` file:

```bash
nano .env
```

Update values if needed:

```env
DB_HOST=postgres-db-java
DB_NAME=mydb
DB_USER=myuser
DB_PASS=mypassword
```

Save and exit:

```text
CTRL + O
ENTER
CTRL + X
```

## Step 9: Create Docker Network

```bash
docker network create java-network
```

## Step 10: Run PostgreSQL Container

```bash
docker run -d \
  --name postgres-db-java \
  --network java-network \
  -e POSTGRES_DB=mydb \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  postgres:15
```

Check:

```bash
docker ps
```

## Step 11: Build Java Docker Image

```bash
docker build -t java-postgres-app .
```

## Step 12: Run Java Container

```bash
docker run -d \
  --name java-app \
  --network java-network \
  --env-file .env \
  -p 8080:8080 \
  java-postgres-app
```

## Step 13: Check Containers

```bash
docker ps
```

Expected:

```text
java-app
postgres-db-java
```

## Step 14: Test Java App

```bash
curl http://localhost:8080
```

Expected:

```text
Java App is Running
```

## Step 15: Test PostgreSQL Connection

```bash
curl http://localhost:8080/db
```

Expected:

```text
PostgreSQL connected successfully
```

## Step 16: Open Firewall Port

```bash
sudo ufw allow 8080
```

Azure NSG inbound rule:

```text
Port: 8080
Protocol: TCP
Action: Allow
```

## Step 17: Open in Browser

```text
http://Your-Server-IP:8080
```

```text
http://Your-Server-IP:8080/db
```

## Login to PostgreSQL Container

Check running containers:

```bash
docker ps
```

```bash
docker exec -it postgres-db-java psql -U myuser -d mydb
```

## Docker Useful Commands

## View Logs

```bash
docker logs java-app
```

```bash
docker logs postgres-db-java
```
---

## Stop Containers

```bash
docker stop java-app postgres-db-java
```
---

## Start Containers

```bash
docker start java-app postgres-db-java
```

---

## Remove Containers

```bash
docker rm -f java-app postgres-db-java
```

---

## Remove Docker Network

```bash
docker network rm java-network
```
