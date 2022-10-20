use loja_virtual;

insert into categoria (nome) values
('Eletrônicos'),
('Eletrodomesticos'),
('Móveis');

insert into produto (id_categoria, nome, descricao) values
(1, 'Notebook', 'Notebook Samsung'),
(2, 'Geladeira', 'Geladeira Azul'),
(3, 'Cômodo', 'Cômodo Vertical');
