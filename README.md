# Alura Challenge Edição #2: Criação de uma API Rest funcional focada em controle financeiro</h2>

<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
</p>

## Descrição do Projeto

Criação de uma API REST de Controle de Orçamento familiar. 

## Funcionalidades

:heavy_check_mark: Deve adicionar, consultar, atualizar e deletar despesas.

:heavy_check_mark: Deve adicionar, consultar, atualizar e deletar receitas.

:heavy_check_mark: Deve gerar um resumo mensal com o total de despesas, receitas, saldo mensal e o total gasto por categoria.

## Tarefas em aberto

:memo: Adicionar o Spring Security na aplicação

:memo: Realizar o deploy no Heroku


## Pré-requisitos

* [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [Docker](https://www.docker.com/)
* [Maven](https://maven.apache.org/)
* [Git](https://git-scm.com/)


## Como rodar a aplicação :arrow_forward:

No terminal, clone o projeto:

```
git clone https://github.com/robert-adam-dev/finance.git
```

Entre na raiz do projeto e executer o docker-compose para criar o banco de dados - MongoDB

```
docker-compose up --build -d
```

Execute a classe FinanceApplication e acesse o link abaixo para a consultar a documentação da API.

```
http://localhost:8080/swagger-ui.html
```
Caso prefirir, todas as chamadas do projeto estão numa collection do Postman.

* [Collection Postman](https://github.com/robert-adam-dev/finance/tree/main/Postman)


## Links de referência

* [Spring](https://spring.io/)
* [Spring Initialzr](https://start.spring.io/)
* [Lombok](https://projectlombok.org/)
* [Map Struct](https://mapstruct.org/)
* [Spring Doc](https://springdoc.org/)




