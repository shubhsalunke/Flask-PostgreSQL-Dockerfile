package com.example;

import static spark.Spark.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> "Java App is Running");

        get("/db", (req, res) -> {
            String dbHost = System.getenv("DB_HOST");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPass = System.getenv("DB_PASS");

            String url = "jdbc:postgresql://" + dbHost + ":5432/" + dbName;

            try {
                Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
                conn.close();
                return "PostgreSQL connected successfully";
            } catch (Exception e) {
                return "Database connection failed: " + e.getMessage();
            }
        });
    }
}
