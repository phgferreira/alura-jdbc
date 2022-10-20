drop database if exists loja_virtual;
create database if not exists loja_virtual;
use loja_virtual;

create table categoria(
	id_categoria int auto_increment primary key,
    nome varchar(50) not null);

create table produto(
	id_produto int auto_increment primary key,
    id_categoria int not null,
    nome varchar(50) not null,
    descricao varchar(255),
    foreign key (id_categoria) references categoria(id_categoria));
