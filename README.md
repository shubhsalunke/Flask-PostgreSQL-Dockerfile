# Flask + PostgreSQL Docker Project (Without Docker Compose)

## Project Overview

This project demonstrates how to deploy a Flask application connected with PostgreSQL using Docker containers and a custom Docker network.

The setup includes:

- Flask application in Docker
- PostgreSQL database in Docker
- Custom Docker network for container communication
- `.env` file for environment variables
- Dockerfile-based deployment
- No Docker Compose used

---

# Project Architecture

```text
Browser
   ↓
Flask Container
   ↓
Docker Network
   ↓
PostgreSQL Container
```

---

# Project Structure

```text
Flask-PostgreSQL-Dockerfile/
│
├── app.py
├── requirements.txt
├── Dockerfile
├── sample.env
├── .env
└── README.md
```

---

# Server Login

```bash
ssh azureuser@Your-Server-IP
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

Reconnect:

```bash
ssh azureuser@Your-Server-IP
```

Check Docker:

```bash
docker --version
```

---

# Clone Repository

```bash
git clone https://github.com/shubhsalunke/Flask-PostgreSQL-Dockerfile.git
```

```bash
cd Flask-PostgreSQL-Dockerfile
```

---

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
DB_HOST=postgres-db
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
  --env-file .env \
  -p 5000:5000 \
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
http://Your-Server-IP:5000
```

## Database Connection Check

```text
http://Your-Server-IP:5000/db
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

---

# Technologies Used

- Python
- Flask
- PostgreSQL
- Docker
- Docker Networking
- Environment Variables
