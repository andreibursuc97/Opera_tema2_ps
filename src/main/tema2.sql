SET SQL_SAFE_UPDATES = 0;

##Create database
CREATE DATABASE IF NOT EXISTS opera;
use opera;

##Create table(s)

CREATE TABLE IF NOT EXISTS Admin
(id int unique auto_increment primary key,
username char(20),
parola BINARY(64)
);


CREATE TABLE IF NOT EXISTS Casier
(id int unique auto_increment primary key,
username char(20),
nume char(20),
parola BINARY(32)
);

CREATE TABLE IF NOT EXISTS Spectacol
(id int unique auto_increment primary key,
gen char(20),
titlu char(20),
regia char(32),
distributie char(40),
nr_total_bilete int,
nr_bilete_vandute int
);

CREATE TABLE IF NOT EXISTS Bilet
(id int unique auto_increment primary key,
id_spectacol int,
rand int,
numar int,
index(id_spectacol),
foreign key (id_spectacol) references spectacol(id));

select * from mysql.user; 