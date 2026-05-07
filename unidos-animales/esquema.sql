DROP TABLE IF EXISTS animales;
DROP TABLE IF EXISTS solicitudes;
DROP TABLE IF EXISTS users;

CREATE TABLE animales (
    id bigint not null auto_increment, 
    especie varchar(255) not null, 
    estado_adopcion varchar(255) not null, 
    nombre varchar(255) not null, 
    primary key (id)
) engine=InnoDB;

CREATE TABLE solicitudes (
    id bigint not null auto_increment, 
    correo varchar(255), 
    mensaje varchar(255), 
    nombre_mascota varchar(255), 
    nombre_solicitante varchar(255), 
    primary key (id)
) engine=InnoDB;

CREATE TABLE users (
    id bigint not null auto_increment, 
    password varchar(255) not null, 
    role varchar(255) not null, 
    username varchar(255) not null unique, 
    primary key (id)
) engine=InnoDB;