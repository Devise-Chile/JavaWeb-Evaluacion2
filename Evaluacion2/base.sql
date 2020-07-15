CREATE TABLE `ciudad` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo`)
);

CREATE TABLE `division` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo`)
);

CREATE TABLE `posicion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo`)
);

CREATE TABLE `estadio` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `cod_ciudad` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_estadio_ciudad_idx` (`cod_ciudad`),
  CONSTRAINT `fk_estadio_ciudad` FOREIGN KEY (`cod_ciudad`) REFERENCES `ciudad` (`codigo`) ON UPDATE CASCADE
);

CREATE TABLE `equipo` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `cod_ciudad` int(11) NOT NULL,
  `cod_estadio` int(11) NOT NULL,
  `cod_division` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_equipo_ciudad_idx` (`cod_ciudad`),
  KEY `fk_equipo_estadio_idx` (`cod_estadio`),
  KEY `fk_equipo_division_idx` (`cod_division`),
  CONSTRAINT `fk_equipo_ciudad` FOREIGN KEY (`cod_ciudad`) REFERENCES `ciudad` (`codigo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_division` FOREIGN KEY (`cod_division`) REFERENCES `division` (`codigo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_estadio` FOREIGN KEY (`cod_estadio`) REFERENCES `estadio` (`codigo`) ON UPDATE CASCADE
);

CREATE TABLE `jugador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `f_nacimiento` date NOT NULL,
  `cod_posicion` int(11) NOT NULL,
  `sueldo` decimal(10,0) NOT NULL,
  `cod_equipo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cod_posicion_idx` (`cod_posicion`),
  KEY `cod_equipo_idx` (`cod_equipo`),
  CONSTRAINT `fk_jugador_equipo` FOREIGN KEY (`cod_equipo`) REFERENCES `equipo` (`codigo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_jugador_posicion` FOREIGN KEY (`cod_posicion`) REFERENCES `posicion` (`codigo`) ON UPDATE CASCADE
);

CREATE TABLE `usuario` (
  `run` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`run`)
);

create or replace view v_estadio as
select e.codigo as cod_estadio, e.nombre as nombre_estadio, e.cod_ciudad, e.capacidad, c.nombre as nombre_ciudad
from estadio e inner join ciudad c on e.cod_ciudad = c.codigo;

create or replace view v_jugador as
select j.id, j.nombre as nombre_jugador, j.apellido as apellido_jugador, f_nacimiento, cod_posicion, pos.nombre as nombre_posicion, sueldo, cod_equipo, e.nombre as nombre_equipo
from jugador j inner join posicion pos on j.cod_posicion = pos.codigo inner join equipo e on j.cod_equipo = e.codigo;

create or replace view v_equipo as
select eq.codigo, eq.nombre as nombre_equipo, eq.cod_ciudad, c.nombre as nombre_ciudad, eq.cod_estadio, es.nombre as nombre_estadio, es.capacidad, eq.cod_division, d.nombre as nombre_division
from equipo eq inner join ciudad c on eq.cod_ciudad = c.codigo inner join estadio es on eq.cod_estadio = es.codigo inner join division d on eq.cod_division = d.codigo;