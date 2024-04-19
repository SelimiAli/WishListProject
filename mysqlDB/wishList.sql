CREATE DATABASE IF NOT EXISTS your_database_name;

USE your_database_name;

CREATE TABLE IF NOT EXISTS wish (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );