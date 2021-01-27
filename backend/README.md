# Figure Store API

# Configuring Database

Execute the following instructions to create database, user and grant privileges to user and database created. Everything via *psql* CLI or pgAdmin.

If database and/or user already exists, is recommended that drop them. For this, run the SQL commands below:
```
DROP DATABASE IF EXISTS figurestore_db;
DROP USER IF EXISTS figurestore_user;
```

To create the application database and its user, run the SQL commands: (required) 
```
CREATE DATABASE figurestore_db;
CREATE USER figurestore_user WITH PASSWORD 'figurestore_password';
```

Then to give the user privileges on the database created, run the following command: (required) 
```
GRANT ALL PRIVILEGES ON DATABASE "figurestore_db" to figurestore_user;
```
