drop table Ingrediente;
drop table Usuario;

create table Ingrediente (
    id integer,
    nome varchar,
    int quantidade,
    PRIMARY KEY(id)
);

create table Usuario (
    id integer,
    login varchar,
    senha varchar,
    administrador integer,
    nome varchar,
    cidade varchar,
    estado varchar,
    telefone varchar,
    PRIMARY KEY(id)
);

insert into Ingrediente values(0, "carne", 10);
insert into Ingrediente values(1, "arroz", 10);
insert into Ingrediente values(2, "kim", 10);
insert into Ingrediente values(3, "repolho", 10);
insert into Ingrediente values(4, "ttok", 10);

insert into Usuario values(0, "admin@a.com", "admin1!", 1, "Administrador", "Sao Carlos", "SP", "00-00000");
insert into Usuario values(1, "user1@a.com", "user1!", 0, "Usuario1", "Sao Carlos", "SP", "00-00000");
