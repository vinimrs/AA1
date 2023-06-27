drop database if exists locadora;

create database locadora;

drop table if exists Locacao;
drop table if exists Cliente;
drop table if exists Locadora;

create table Locadora(id serial primary key,
                     cnpj varchar(20) unique,
                      nome varchar(256) not null,
                      email varchar(256) not null,
                      senha varchar(256) not null,
                      cidade varchar(256) not null);

create table Cliente(id serial primary key,
                     cpf varchar(15) unique,
                     nome varchar(256) not null,
                     email varchar(256) not null unique,
                     senha varchar(64) not null,
                     telefone varchar(50) not null,
                     sexo varchar(50) not null,
                     data_nascimento date not null);

create table Admin(id serial primary key,
                    nome varchar(256) not null,
                    login varchar(20) not null unique,
                    senha varchar(64) not null);


create table Locacao(id serial primary key,
                     data_locacao date not null,
                     horario_locacao time not null,
                     cpf_cliente varchar(15) not null,
                     editora_cnpj varchar(20) not null,
                     foreign key (editora_cnpj) references Locadora(cnpj),
                     foreign key (cpf_cliente) references Cliente(cpf),
                     constraint unique_cliente unique (data_locacao, horario_locacao, cpf_cliente)
);

create table Usuario(id serial primary key,
                     nome varchar(256) not null,
                     login varchar(20) not null unique,
                     senha varchar(64) not null,
                     papel varchar(10));


insert into Locadora(cnpj, nome, email, senha, cidade) values ('55.789.390/0008-99',
                                                               'Locação dos Brito',
                                                               'locacaobrito@email.com',
                                                               '12345678',
                                                               'São Carlos');
insert into Locadora(cnpj, nome, email, senha, cidade) values ('71.150.470/0001-40',
                                                               'Locação dos Oliveiras',
                                                               'locacaooliveiras@email.com',
                                                               '12345678',
                                                               'São Carlos');

insert into Cliente(cpf,nome,email,senha,telefone, sexo,data_nascimento) values (
                                                                                    '111.111.111-00',
                                                                                    'Joaquin das Neves',
                                                                                    'joaquin@email.com',
                                                                                    '12345678',
                                                                                    '(82)99183-9092',
                                                                                    'Masculino',
                                                                                    '18/02/2004');

insert into Cliente(cpf,nome,email,senha,telefone, sexo,data_nascimento) values (
                                                                                    '222.222.222-00',
                                                                                    'João Ferreira',
                                                                                    'ferreira@email.com',
                                                                                    '12345678',
                                                                                    '(41)99999-9999',
                                                                                    'Masculino',
                                                                                    '18/09/2000');

insert into Locacao(data_locacao,horario_locacao,cpf_cliente,editora_cnpj) values (
                                                                                      '22/12/2023',
                                                                                      '12:00',
                                                                                      '222.222.222-00',
                                                                                      '71.150.470/0001-40'
                                                                                  );

insert into Locacao(data_locacao,horario_locacao,cpf_cliente,editora_cnpj) values (
                                                                                      '22/12/2023',
                                                                                      '12:00',
                                                                                      '111.111.111-00',
                                                                                      '55.789.390/0008-99'
                                                                                  );

insert into Admin(nome, login, senha) values ('Administrador', 'admin', 'admin');
