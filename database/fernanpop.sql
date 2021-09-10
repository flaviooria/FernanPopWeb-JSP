drop database if exists fernanpop;
create database fernanpop;
use fernanpop;
drop table if exists usuarios;
create table usuarios(
    id int auto_increment,
    nombre varchar(250) not null,
    apellido varchar(250) default "",
    correo varchar(250) not null unique,
    contrasenia varchar(250) not null,
    contraseniaCifrada text not null,
    tokenUsuario varchar(250) default "1357",
    autentificado boolean default false,
    edad tinyint default 0,
    movil int default 0,
    avatarUser longblob null,
    fechaCreacion timestamp default now(),
    notaMedia float(5,1) default 0,
    constraint pk_usuario PRIMARY KEY (id)
);

drop table if exists usuariosEliminados;
create table usuariosEliminados(
    id int primary key,
    nombre varchar(250) not null,
    apellido varchar(250) null,
    correo varchar(250) not null,
    edad varchar(250)  null,
    fechaCreacion timestamp not null
);

drop table if exists productos;
create table productos(
    id int auto_increment,
    nombre varchar(250) not null,
    descrip varchar(250) not null,
    precio float(6,2) not null,
    idUsuario int not null,
    imagen longblob not null,
    constraint pk_productos PRIMARY KEY (id),
    constraint fk_producto_idUsuario FOREIGN key (idUsuario) REFERENCES usuarios(id) on delete cascade
);

drop table if exists productoEliminados;
create table productoEliminados(
    id int primary key,
    nombre varchar(250) not null,
    precio varchar(250) not null,
    idUsuario int not null
);

drop table if exists tratos;
create table tratos(
    id int auto_increment,
    tipo varchar(100) not null,
    correoUsuario varchar(250) not null,
    nombreProducto varchar(250) not null,
    idUsuario int not null,
    precio float(6,2) not null,
    comentario varchar(500) default "Sin comentario.",
    puntuacion int default 0,
    fechaTrato timestamp default now(),
    imgProducto longblob not null,
    constraint pk_trato PRIMARY KEY (id),
    constraint fk_trato_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(id) on delete cascade
);

drop table if exists productosSolicitados;
create table productosSolicitados(
    id int auto_increment,
    nombreComprador varchar(250) not null,
    correoComprador varchar(250) not null,
    idProductoSolicitado int not null,
    fecHaSolicitud  timestamp default now(),
    constraint pk_productoSolicitado PRIMARY KEY (id),
    constraint fk_productoSolicitado_producto FOREIGN KEY (idProductoSolicitado) REFERENCES productos(id) on delete cascade
);

drop table if exists valoracionesPendientes;
create table valoracionesPendientes(
    id int auto_increment,
    correoUsuario varchar(250) not null,
    idTrato int not null,
    constraint pk_valoracionesPendientes primary key (id)
);

drop table if exists mensajesRecibidos;
create table mensajesRecibidos(
    id int auto_increment,
    contenido varchar(300) not null ,
    fechaEnvio timestamp default current_timestamp,
    emisor int not null,
    receptor int not null,
    asunto varchar(300) not null, 
    estaLeido boolean default false,
    fechaLectura timestamp,
    constraint primary key (id),
    constraint foreign key (emisor) references usuarios(id) on delete cascade,
    constraint foreign key (receptor) references usuarios(id) on delete cascade
);

drop table if exists mensajesEnviados;
create table mensajesEnviados(
    id int auto_increment,
    contenido varchar(300) not null ,
    fechaEnvio timestamp default current_timestamp,
    emisor int not null,
    receptor int not null,
    asunto varchar(300) not null, 
    estaLeido boolean default false,
    fechaLectura timestamp,
    constraint primary key (id),
    constraint foreign key (emisor) references usuarios(id) on delete cascade,
    constraint foreign key (receptor) references usuarios(id) on delete cascade
);


/*triggers*/
delimiter $
drop trigger if exists usuariosEliminados;
create trigger usuariosEliminados
AFTER DELETE on usuarios
for each row
BEGIN
insert into usuariosEliminados values (old.id,old.nombre,old.apellido,old.correo,old.edad,old.fechaCreacion);
END$
delimiter ;

delimiter $
drop trigger if exists productoEliminados;
create trigger productoEliminados
AFTER DELETE on productos
FOR EACH ROW
BEGIN
insert into productoEliminados (id,nombre,precio,idUsuario) values (old.id,old.nombre,old.precio,old.idUsuario);
end$
delimiter ;

delimiter $
drop trigger if exists generaNotaMediaAlHacerInsert;
create trigger generaNotaMediaAlHacerInsert
AFTER INSERT on tratos
for each ROW
begin
set @nota = (select avg(puntuacion) from tratos where idUsuario = new.idUsuario);
update usuarios set notaMedia = @nota where id = new.idUsuario;
end$
delimiter ;

delimiter $
drop trigger if exists generaNotaMediaAlHacerUpdate;
create trigger generaNotaMediaAlHacerUpdate
    AFTER UPDATE on tratos
    for each ROW
begin
    set @nota = (select avg(puntuacion) from tratos where idUsuario = new.idUsuario);
    update usuarios set notaMedia = @nota where id = new.idUsuario;
end$
delimiter ;

insert into usuarios (nombre,apellido,correo,contrasenia) values ("flavio","oria","flavio@dev.com","fc120899");
insert into usuarios (nombre,apellido,correo,contrasenia) values ("carlos","barroso","carlos@dev.com","fc120899");
