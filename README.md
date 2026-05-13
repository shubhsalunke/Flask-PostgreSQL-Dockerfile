# Flask + PostgreSQL Docker Project (Without Docker Compose)

## Server Login

```bash
ssh azureuser@20.242.50.155
```

---

# Install Docker

```bash
sudo apt update
sudo apt install -y docker.io
sudo systemctl enable docker
sudo systemctl start docker
sudo usermod -aG docker $USER
```

Logout and login again:

```bash
exit
```

```bash
ssh azureuser@20.242.50.155
```

Check Docker:

```bash
docker --version
```

---

# Clone Repository

```bash
git clone https://github.com/shubhsalunke/Flask-PostgreSQL-Dockerfile.git
cd Flask-PostgreSQL-Dockerfile
```

---

# Create Docker Network

```bash
docker network create flask-network
```

---

# Run PostgreSQL Container

```bash
docker run -d \
  --name postgres-db \
  --network flask-network \
  -e POSTGRES_DB=mydb \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  postgres:15
```

Check running containers:

```bash
docker ps
```

---

# Build Flask Docker Image

```bash
docker build -t flask-postgres-app .
```

---

# Run Flask Container

```bash
docker run -d \
  --name flask-app \
  --network flask-network \
  -p 5000:5000 \
  -e DB_HOST=postgres-db \
  -e DB_NAME=mydb \
  -e DB_USER=myuser \
  -e DB_PASS=mypassword \
  flask-postgres-app
```

---

# Check Containers

```bash
docker ps
```

Expected:

```text
flask-app
postgres-db
```

---

# Test Flask Application

```bash
curl http://localhost:5000
```

Expected:

```text
Flask App is Running
```

---

# Test PostgreSQL Connection

```bash
curl http://localhost:5000/db
```

Expected:

```text
PostgreSQL connected successfully
```

---

# Open Firewall Port

```bash
sudo ufw allow 5000
```

---

# Azure NSG Rule

Open Port:

```text
5000
```

Protocol:

```text
TCP
```

Action:

```text
Allow
```

---

# Open Application in Browser

## Flask App

```text
http://20.242.50.155:5000
```

## Database Connection Check

```text
http://20.242.50.155:5000/db
```

---

# Docker Useful Commands

## View Logs

```bash
docker logs flask-app
```

```bash
docker logs postgres-db
```

---

## Stop Containers

```bash
docker stop flask-app postgres-db
```

---

## Start Containers

```bash
docker start flask-app postgres-db
```

---

## Remove Containers

```bash
docker rm -f flask-app postgres-db
```

---

## Remove Docker Network

```bash
docker network rm flask-network
```
