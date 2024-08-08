create database Futbolito; 
use Futbolito; 
create table administrador(
cedula varchar(11) primary key,
correo varchar(80) unique not null,
nombre varchar(25) not null,
contraseña varchar(100) not null
);
create table encargados(
cedula varchar(11) primary key,
correo varchar(80) unique not null,
nombre varchar(25) not null,
edad int not null,
contraseña varchar(80) not null,
numero_telefono varchar(80) not null,
direccion varchar(80) not null
);
create table canchas(
codigo varchar(5) primary key,
nombre_cancha varchar(30) unique not null,
imagen longblob not null,
ubicacion varchar(80) not null,
estado varchar(20) not null,
capacidad varchar(30) not null,
horario_8 varchar(20),
cedula_reserva1 varchar(11),
horario_10 varchar(20),
cedula_reserva2 varchar(11),
horario_12 varchar(20),
cedula_reserva3 varchar(11),
horario_14 varchar(20),
cedula_reserva4 varchar(11),
horario_16 varchar(20),
cedula_reserva5 varchar(11),
horario_18 varchar(20),
cedula_reserva6 varchar(11)
);

insert into administrador value("1753653458","santiago@gmail.com","Santiago Cumbal","fce84944b6baec09e31edafac2ed43d0a0063d61c77b0d2300af470976a2a92c");

select * from administrador;
select * from encargados;
select * from canchas;
