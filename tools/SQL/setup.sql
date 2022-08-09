CREATE TABLE tap_db.users(
id INTEGER NOT NULL AUTO_INCREMENT,
name varchar(255) NOT NULL,
email varchar(255) NOT NULL,
password varchar(255) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE tap_db.titles(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
type ENUM('BLURAY', 'DVD', 'CD', 'VIDEO_TAPE'),
quantity INTEGER NOT NULL,
max_period_rent INTEGER NOT NULL,
PRIMARY KEY(id)
);
