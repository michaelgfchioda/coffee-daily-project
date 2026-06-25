<!-- <h1 align="center"> <strong> CoffeeDaily Project☕ </strong> </h1> -->
<p align="center">
  <!-- <img width="865" height="245" alt="Banner-logo" src="https://github.com/user-attachments/assets/abcfb2a3-3d3d-443b-ac8d-b1e0221dd308" /> -->
  <img width="1456" height="720" alt="Banner-v1" src="https://github.com/user-attachments/assets/8d82c0f9-16e8-48f6-b153-af22274aa7fe" />
</p>

<h2> SOBRE O PROJETO🌱 </h2>
<p> <strong> 
Durante a disciplina de Laboratório de Programação Orientada a Objetos, tive a oportunidade de escolher um entre diversos temas propostos pelo meu professor. Então, escolhi o tema Cafeteria. O nome CoffeeDaily foi pensado junto com o objetivo de criar um sistema simples, fácil e satisfatório de ser usado, assim como a nossa boa dose de café diária!

Antes de partir para a codificação, primeiro, eu precisava idealizar o Banco de Dados e a Interface para que pudesse tornar todo o processo de desenvolvimento eficiente e eficaz, poupando-o de futuros erros que poderiam ser gerados na base do projeto pela falta de planejamento e de organização. Então, utilizei a ferramenta gratuita Lucid para criar o DER (Diagrama Entidade-Relacionamento), idealizando e definindo o relacionando de cada uma das entidades entre si, e, logo em seguida, parti para a prototipagem da Interface na ferramenta Figma, me preocupando somente como cada funcionalidade seria representada para o usuário depois que todo o Banco de Dados já havia sido criado e definido.

Usando somente a IDE do NetBeans, todo o Front-End e Back-End do projeto foi desenvolvido inteiramente com a linguagem Java. Comecei criando um projeto do tipo Java Application with Maven e organizei todos os diretórios seguindo o padrão arquitetural MVC (Model-View-Controller) para trabalhar de forma organizada e eficiente. Depois disso, me dirigi às dependências do arquivo pom.xml para adicionar o Driver JDBC que me permitiria conectar a minha aplicação com o BD criado no SGBD PostgreSQL.

Com a estrutura do projeto organizada, eu comecei a desenvolver o Front-End que havia prototipado com um dos frameworks nativos do Java: o Swing; E para deixar a Interface mais “moderna” e bonita, optei por utilizar o framework FlatLaf de forma complementar, tirando aquele visual de “sistema antigo” que o Swing transmite.
Depois de pronta, e com cada variável estrategicamente nomeada, comecei a trabalhar no Back-End. Seguindo o paradigma de Programação Orientada a Objetos, implementei:

No Model: 
- Cada tabela do BD como classe.

No View:
- Implementação da Interface.
- Configuração das tabelas presentes na Interface.

No Controller:
- Script SQL para criação das tabelas do BD.
- Teste de conexão com BD.
- Padrão de projeto DAO (Data Access Object): Responsável por centralizar operações de acesso ao BD, isolando a lógica de acesso aos dados de todo o restante da implementação.
</strong> </p>

<h2> PROPOSTA DO PROJETO📝 </h2>
<p> <strong> 
Com isso, o projeto CoffeeDaily é capaz de:

- Cadastrar novos usuários;
- Fazer login de usuários já existentes;
- Realizar a autenticação de usuários (senhas protegidas com hash MD5);
- Atualizar e consultar dados de usuários existentes;
- Cadastrar, atualizar e consultar produtos;
- Adicionar e remover produtos do carrinho de compras;
- Realizar venda de produtos;
- Consultar histórico de venda de produtos.
</strong> </p>

<h2 align="center"> SGBD UTILIZADO NO PROJETO⚒️ </h2>
<p align="center"> <img src="https://img.icons8.com/?size=100&id=Pv4IGT0TSpt8&format=png&color=000000"/img> </p>
<p> <strong>
Para a criação do Banco de Dados, utilizei o SGBD (Sistema de Gerenciamento de Banco de Dados) PostgreSQL, isso significa que a linguagem SQL será um dialeto SGBD escolhido, ou seja, haverá algumas exclusividades em relação a extensões, funcionalidades, assim como em qualquer outro SGBD, como a Oracle, por exemplo, ou MySQL Workbench.
</strong> </p>
<hr>
