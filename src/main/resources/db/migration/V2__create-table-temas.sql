CREATE TABLE temas(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(1500) not null,
    lenguaje varchar(100) not null,
    fecha varchar(200) not null,
    activo tinyint not null,
    primary key(id)


);