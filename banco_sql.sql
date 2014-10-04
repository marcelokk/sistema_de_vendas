drop table Componente;
drop table Usuario;

create table Componente (
    id integer,
    nome varchar,
    quantidade integer,
    valor real,
    status integer,
    descricao varchar,
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

insert into Componente values(0, "componente0", 10, 1.0, 1, "descricao do componente 1");
insert into Componente values(1, "componente1", 10, 2.0, 1, "descricao do componente 2");
insert into Componente values(2, "componente2", 10, 1.5, 1, "descricao do componente 3");
insert into Componente values(3, "componente3", 10, 2.5, 1, "descricao do componente 4");
insert into Componente values(4, "componente4", 10, 1.7, 1, "descricao do componente 5");

insert into Usuario values(0, "admin@a.com", "Admin1!", 1, "Administrador", "Sao Carlos", "SP", "00-00000");
insert into Usuario values(1, "user1@a.com", "User1!", 0, "Usuario1", "Sao Carlos", "SP", "00-00000");
