# Teste Dev Application

Este projeto é uma aplicação Spring Boot que processa pedidos de forma assíncrona, utilizando RabbitMQ para processamento de mensagens, MySQL como banco de dados e Docker para containerização.

## Funcionalidades

- Endpoint para CRUD de Produtos (nome, descrição, preço).
- Endpoint para CRUD de Pedidos (data do pedido, lista de produtos, quantidade, status).
- Endpoint para alterar o status de um pedido ("pendente", "processando", "concluído").
- Processamento assíncrono de mudanças de status de pedidos via RabbitMQ.
- Banco de dados MySQL para persistência.
- Dockerização da aplicação para facilitar a implantação.

## Pré-requisitos

- **Docker** e **Docker Compose**.
- Conta na **AWS**.

## Configuração Local (Docker)

### 1. Imagem Docker

```bash
docker build -t teste-dev
```

### 2. Docker Compose

```bash
docker-compose up -d
```

### 3. Acesso Local

```bash
http://localhost:8090
```

## API Reference

## Produtos

### Get all items 

```http
  GET /api/produtos
```

### Get item

```http
  GET /api/produtos/${id}
```

### Post item

```http
  POST /api/produtos
```

| Parâmetro     | Tipo        | Descrição              |
| :------------ | :---------- | :----------------------|
| `nome`        | `String`    | **Obrigatório**.       |
| `descricao`   | `String`    | **Obrigatório**.       |
| `preco`       | `Double`    | **Obrigatório**.       |


### Delete item

```http
  DELETE /api/produtos/${id}
```

## Pedidos

### Get all items 

```http
  GET /api/pedidos
```

### Get item

```http
  GET /api/pedidos/${id}
```

### Post item

```http
  POST /api/pedidos
```

| Parâmetro     | Tipo        | Descrição                       |
| :------------ | :---------- | :-------------------------------|
| `dataPedido`  | `localDate` | **Obrigatório**.                |
| `produtos`    | `Long`      | **Obrigatório**. Id do Produto  |

### Put status

```http
  PUT /api/pedidos/${id}/status
```

| Parâmetro     | Tipo        | Descrição                                             |
| :------------ | :---------- | :-----------------------------------------------------|
| `status`      | `String`    | **Obrigatório**. PENDENTE, PROCESSANDO ou CONCLUIDO;  |


## IMPLANTAÇÃO AWS

- Fazer Push da imagem Docker no Amazon ECR
- Criar um Cluster ECS e criar uma task definition para definir os containers que o ECS irá rodar (aplicação e RabbitMQ*).
- Criar instância RDS com MySQL e configurar o Security Group para permitir conexões da aplicação ECS.
- Definir variáveis de ambiente para conexão do banco.

* O Amazon MQ é uma opção para o RabbitMQ.