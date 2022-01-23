<h2>Alura Challenge Edição #2: Criação de uma API Rest funcional focada em controle financeiro</h2>


<h3>O que foi utilizado?</h3>

* JAVA 11
* Spring Boot (Web, JPA)
* MongoDB
* Docker
* Docker Compose
* MapStruct
* Maven

Antes de executar o projeto, basta ir na raiz do projeto e executar o comando abaixo para a criação do banco de dados utilizando o docker compose.

```shell script
docker-compose up --build -d
```

Execute a classe FinanceApplication e faça requisições para criar uma nova despesa, por exemplo:

```
http://localhost:8080/api/v1/despesas
```

Links de referência

* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr](https://start.spring.io/)
* [Documentação oficial do Lombok](https://projectlombok.org/)
* [Documentação oficial do Map Struct](https://mapstruct.org/)




