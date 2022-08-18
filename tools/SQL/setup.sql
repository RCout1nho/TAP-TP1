CREATE TABLE tap_db.users(
id INTEGER NOT NULL AUTO_INCREMENT,
name varchar(255) NOT NULL,
email varchar(255) NOT NULL,
type ENUM('CLIENT', 'ADMIN'),
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

CREATE TABLE tap_db.rents(
	id INT NOT NULL AUTO_INCREMENT,
	employee_id INT NOT NULL,
	client_id INT NOT NULL,
	title_id INT NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (employee_id) REFERENCES users(id),
	FOREIGN KEY (client_id) REFERENCES users(id),
	FOREIGN KEY (title_id) REFERENCES titles(id)
);

INSERT INTO tap_db.users (name, email, type, password) VALUES ('Horácio', 'horacio@email.com', 'ADMIN', '123');
