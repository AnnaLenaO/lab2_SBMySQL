SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS location_category;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE category
(
    id INT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    symbol VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT '',
    PRIMARY KEY (id)
);

CREATE TABLE location
(
    id INT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    person_id VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT '',
    coordinate GEOMETRY NOT NULL SRID 4326,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    category INT NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (category) REFERENCES category (id)
);

CREATE TABLE location_category
(
    location_id INT,
    category_id INT,
    PRIMARY KEY (location_id, category_id),
    FOREIGN KEY (location_id) REFERENCES location (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);
