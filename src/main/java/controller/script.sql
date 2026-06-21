-- Criando as tabelas do Banco de Dados DB_CoffeeDaily
-- ----------------------------------------------------------
CREATE TABLE tb_usuario (
	id_usuario		SERIAL,
	nome			VARCHAR(120) NOT NULL,
	email			VARCHAR(100) NOT NULL,
	telefone		VARCHAR(20),
	senha			VARCHAR(32) NOT NULL,
		CONSTRAINT pk_tb_usuario_id_usuario PRIMARY KEY (id_usuario)
);

CREATE TABLE tb_produto (
	id_produto		SERIAL,
	nm_produto		VARCHAR(100) NOT NULL,
	categoria		VARCHAR(50),
	descricao		VARCHAR(200),
	preco			DECIMAL(10,2),
	qtd_estoque		INT NOT NULL,
	dt_fabricacao	DATE NOT NULL,
	dt_validade		DATE NOT NULL,
	marca			VARCHAR(20),
		CONSTRAINT pk_tb_produto_id_produto PRIMARY KEY (id_produto)
);

CREATE TABLE tb_venda (
	id_venda		SERIAL,
	dt_venda		DATE,
	id_usuario		INT,
		CONSTRAINT pk_tb_venda_id_venda PRIMARY KEY (id_venda),
		CONSTRAINT fk_tb_venda_id_usuario FOREIGN KEY (id_usuario)
			REFERENCES tb_usuario (id_usuario)
);

CREATE TABLE tb_item_venda (
	id_item_venda	SERIAL,
	quantidade		INT,
	subtotal		DECIMAL(10,2),
	id_venda		INT,
	id_produto		INT,
		CONSTRAINT pk_tb_item_venda_id_item_venda PRIMARY KEY (id_item_venda),
		CONSTRAINT fk_tb_item_venda_id_venda FOREIGN KEY (id_venda)
			REFERENCES tb_venda (id_venda),
		CONSTRAINT fk_tb_item_venda_id_produto FOREIGN KEY (id_produto)
			REFERENCES tb_produto (id_produto)
);

CREATE TABLE tb_pagamento (
	id_pagamento	SERIAL,
	forma_pagamento	VARCHAR(15),
	valor_pago		DECIMAL(10,2),
	parcelas		INT,
	id_venda		INT,
		CONSTRAINT pk_tb_pagamento_id_pagamento PRIMARY KEY (id_pagamento),
		CONSTRAINT fk_tb_pagamento_id_venda FOREIGN KEY (id_venda)
			REFERENCES tb_venda (id_venda)
);
-- ----------------------------------------------------------

-- 