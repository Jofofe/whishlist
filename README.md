Pré requisitos

Java 11

Intellij IDE

Ter o docker instalado para executar o 'docker-compose up' para criar o banco MongoDB

Para rodar o projeto:

* Por linha de comando: Na pasta base do projeto, utilizar o comando ./mvnw spring-boot:run no Windows,
  ou mvn spring-boot:run no linux para rodar a aplicação

* Na IDE: Executar direto pela WhishlistApplication.java

Tem um loader para carregar Clients e Products para os testes, então assim que a aplicação iniciar, eles já estarão na base de dados

Com a aplicação iniciada, é possivel acessar a documentação do Swagger no endereço http://localhost:8080/api/swagger-ui/index.html

----------------------------------------------------------------------------------------------------------------------------------------

Ponderações sobre os exercicios

Foquei no desenvolvimento da API do Whishlist conforme solicitado, mas criei os GET do Client e Product para auxiliar nos testes

----------------------------------------------------------------------------------------------------------------------------------------

Arquitetura Utilizada

Foi utilizado o Java 11 e Spring Boot com suas dependências de data-jpa-mongodb e web

Seguindo a arquitetura MVC

Na entrada das requisições no backend são utilizadas o Bean Validation

Para todas as excessões criadas do projeto, são utilizadas um handler
para a resposta da mesma na requsisição

Foi utilizado Docker para a criação do banco

Foi criado um loader para inclusão de clients e products, auxiliando nos testes

Para testes unitarios foi utilizado JUnit, Mockito, Asserts e MockMvc