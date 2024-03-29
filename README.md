<h1 align="center">
  Desafio CREA
</h1>

CREA simplificado: API de cadastro de profissionais e títulos.

Esse repositório contém a api desenvolvida no teste técnico para a vaga de estágio de desenvolvimento fullstack do CREA-PI.

# Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Postgresql](https://www.postgresql.org/download/)

# Práticas adotadas

- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de exceções
- Geração automática do Swagger com a OpenAPI 3

# Como Executar
  ## Requistos
  - [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  - [Maven](https://maven.apache.org/download.cgi)
  - [Postgresql](https://www.postgresql.org/download/)
  ## Configurações
  - Clonar repositório git
  ```shell
  cd "diretório a livre escolha"
  git clone https://github.com/joguirod/desafio-crea
  ```
  - Inicie o PostgreSQL e crie uma database para o desafio(ex: create database desafio_crea_bd)
  - No `application.properties` (em crea-api/src/main/resources), atualize as configurações do banco de dados. Exemplo:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/desafio_crea_bd
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha

  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
```

  ## Executando

``` shell
cd diretorio_do_projeto
./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/crea-api-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

# API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/downloads/):

## Profissionais

- Criar profissional
```
POST http://localhost:8080/professional 
JSON Raw Body:

{
  "name": "string",
  "email": "string",
  "password": "string",
  "birthdate": "2024-02-13",
  "phoneNumber": "string",
  "professionalType": "REGISTERED",
  "registrationDate": "2024-02-13"
}
```

- Adicionar título a profissional
```
POST http://localhost:8080/professional/{id_profissional}/add-title
JSON Raw Body:

{
  "titleId": 0
}
```

- Ativar profissional
```
POST http://localhost:8080/professional/activate/{professional_id}
```

- Desativar profissional
```
POST http://localhost:8080/professional/inactivate/{professional_id}
```

- Cancelar profissional
```
POST http://localhost:8080/professional/cancel/{professional_id}
```

- Listar profissionais
```
GET http://localhost:8080/professional
Response:

[
  {
    "id": 0,
    "uniqueCode": "string",
    "name": "string",
    "email": "string",
    "password": "string",
    "birthdate": "2024-02-13",
    "phoneNumber": "string",
    "professionalType": "REGISTERED",
    "registrationStatus": "ACTIVE",
    "visaDate": "2024-02-13",
    "registrationDate": "2024-02-13",
    "titles": [
      {
        "id": 0,
        "description": "string"
      }
    ]
  }
]
```

- Listar profissional com id específico
```
GET http://localhost:8080/professional/id/{id}
Response:

{
  "id": 0,
  "uniqueCode": "string",
  "name": "string",
  "email": "string",
  "password": "string",
  "birthdate": "2024-02-13",
  "phoneNumber": "string",
  "professionalType": "REGISTERED",
  "registrationStatus": "ACTIVE",
  "visaDate": "2024-02-13",
  "registrationDate": "2024-02-13",
  "titles": [
    {
      "id": 0,
      "description": "string"
    }
  ]
}
```

- Listar profissional com codigo único específico
```
GET http://localhost:8080/professional/{codigo_unico}
Response:

{
  "id": 0,
  "uniqueCode": "string",
  "name": "string",
  "email": "string",
  "password": "string",
  "birthdate": "2024-02-13",
  "phoneNumber": "string",
  "professionalType": "REGISTERED",
  "registrationStatus": "ACTIVE",
  "visaDate": "2024-02-13",
  "registrationDate": "2024-02-13",
  "titles": [
    {
      "id": 0,
      "description": "string"
    }
  ]
}
```

- Listar profissional com email específico
```
GET http://localhost:8080/professional/email/{email}
Response:

{
  "id": 0,
  "uniqueCode": "string",
  "name": "string",
  "email": "string",
  "password": "string",
  "birthdate": "2024-02-13",
  "phoneNumber": "string",
  "professionalType": "REGISTERED",
  "registrationStatus": "ACTIVE",
  "visaDate": "2024-02-13",
  "registrationDate": "2024-02-13",
  "titles": [
    {
      "id": 0,
      "description": "string"
    }
  ]
}
```

- Atualizar profissional
```
PUT http://localhost:8080/professional 
JSON Raw Body:

{
  "id": 0,
  "uniqueCode": "string",
  "name": "string",
  "email": "string",
  "password": "string",
  "birthdate": "2024-02-13",
  "phoneNumber": "string",
  "professionalType": "REGISTERED",
  "registrationStatus": "ACTIVE",
  "visaDate": "2024-02-13",
  "registrationDate": "2024-02-13",
  "titles": [
    {
      "id": 0,
      "description": "string"
    }
  ]
}
```

- Remover profissional
```
DELETE http://localhost:8080/professional/{id}
```
## Títulos

- Criar título
```
POST http://localhost:8080/title
JSON Raw Body:

{
  "description": "string"
}
```

- Listar títulos
```
GET http://localhost:8080/title
Response:

[
  {
    "id": 0,
    "description": "string"
  }
]
```

- Listar título com id específico
```
GET http://localhost:8080/title/{id}
Response:

{
  "id": 0,
  "description": "string"
}
```

- Atualizar título
```
PUT http://localhost:8080/title
JSON Raw Body:

{
  "id": 0,
  "description": "string"
}
```

- Remover título
```
DELETE http://localhost:8080/title/{id}
```
