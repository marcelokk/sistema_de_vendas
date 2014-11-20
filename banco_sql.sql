drop table Componente;
drop table Usuario;
drop table Acai;
drop table sugestao;

/*
drop table item;
*/

create table Acai (
    id integer,
    sabor varchar,
    quantidade integer,
    valor real,
    descricao varchar,
    PRIMARY KEY(id)
);

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
    nascimento varchar,
    PRIMARY KEY(id)
);

create table sugestao(
    id integer,
    nome varchar,
    descricao varchar,
    valor real,
    primary key(id)
);

/*
create table Sugestao(
    id integer,
    nome varchar,
    PRIMARY KEY(id)
);

create table Item(
    id integer,
    sugestao_id integer,
    nome varchar,
    descricao varchar,
    valor real,
    PRIMARY KEY(id),
    FOREIGN KEY(sugestao_id) REFERENCES Sugestao(id)
);
*/
insert into Acai values(0, "morango", 100, 7.5, "acai sabor morango");
insert into Acai values(1, "banana", 100, 7.5, "acai sabor banana");
insert into Acai values(2, "natural", 100, 7.0, "acai puro");

insert into Componente values(0, "banana", 10, 1.0, 0, "pedacos de banana");
insert into Componente values(1, "morango", 10, 2.0, 0, "morango picado");
insert into Componente values(2, "leite ninho", 10, 1.5, 0, "cobertura de leite ninho");
insert into Componente values(3, "granola", 10, 2.5, 0, "cobertura de granola");
insert into Componente values(4, "biju", 10, 1.7, 0, "uma biju a parte");

insert into Usuario values(0, "admin@a.com", "Admin1!", 1, "Administrador", "Sao Carlos", "SP", "00-00000", "1990-01-10");
insert into Usuario values(1, "user1@a.com", "User1!", 0, "Usuario1", "Sao Carlos", "SP", "00-00000", "1993-08-23");

