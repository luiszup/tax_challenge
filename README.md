# Desafio: Construindo uma API de Cálculo de Impostos com TDD, Spring Security e JWT

## Descrição do Projeto

Este projeto consiste em uma API RESTful para gerenciar e calcular impostos no Brasil. A API permite o registro de diferentes tipos de impostos, como ICMS, ISS, IPI, entre outros, e realiza cálculos com base no tipo de imposto e no valor base fornecido. A segurança da API é garantida por autenticação e autorização utilizando Spring Security e JWT.

---

## Funcionalidades

### 1. Gerenciamento de Tipos de Impostos
- **Listar todos os tipos de impostos disponíveis**: `GET /tipos`
- **Cadastrar novos tipos de impostos**: `POST /tipos` (Acesso restrito ao papel ADMIN)
- **Obter detalhes de um tipo de imposto específico pelo ID**: `GET /tipos/{id}`
- **Excluir um tipo de imposto pelo ID**: `DELETE /tipos/{id}` (Acesso restrito ao papel ADMIN)

### 2. Cálculo de Impostos
- **Calcular o valor do imposto com base no tipo de imposto e no valor base**: `POST /calculo` (Acesso restrito ao papel ADMIN)

### 3. Segurança
- **Autenticação e autorização com JWT**:
    - Apenas usuários autenticados podem acessar os endpoints.
    - Apenas usuários com o papel ADMIN podem acessar os endpoints de criação, exclusão e cálculo de impostos.
- **Registro de usuários**: `POST /user/registrar`
- **Login de usuários**: `POST /user/login`

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **JPA/Hibernate**
- **H2 Database (para desenvolvimento e testes)**
- **JUnit 5**
- **Swagger/OpenAPI**

---

## Endpoints da API

### **1. Gerenciamento de Tipos de Impostos**

#### `GET /tipos`
- **Descrição**: Retorna a lista de todos os tipos de impostos cadastrados.
- **Resposta**:
  ```json
  [
    {
      "id": 1,
      "nome": "ICMS",
      "descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
      "aliquota": 18.0
    },
    {
      "id": 2,
      "nome": "ISS",
      "descricao": "Imposto sobre Serviços",
      "aliquota": 5.0
    }
  ]
#### `POST /tipos`
- **Descrição**: Cadastra um novo tipo de imposto. (Acesso restrito ao papel ADMIN)
- **Entrada**:
    ```json
    {
        "nome": "IPI",
        "descricao": "Imposto sobre Produtos Industrializados",
        "aliquota": 12.0
    }
    ```
- **Resposta**:
    ```json
    {
        "id": 3,
        "nome": "IPI",
        "descricao": "Imposto sobre Produtos Industrializados",
        "aliquota": 12.0
    }
    ```

#### `GET /tipos/{id}`
- **Descrição**: Retorna os detalhes de um tipo de imposto específico pelo ID.
- **Resposta**:
    ```json
    {
        "id": 1,
        "nome": "ICMS",
        "descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
        "aliquota": 18.0
    }
    ```

#### `DELETE /tipos/{id}`
- **Descrição**: Exclui um tipo de imposto pelo ID. (Acesso restrito ao papel ADMIN)
- **Resposta**: `204 No Content`

---

### **2. Cálculo de Impostos**

#### `POST /calculo`
- **Descrição**: Calcula o valor do imposto com base no tipo de imposto e no valor base. (Acesso restrito ao papel ADMIN)
- **Entrada**:
    ```json
    {
        "tipoImpostoId": 1,
        "valorBase": 1000.0
    }
    ```
- **Resposta**:
    ```json
    {
        "tipoImposto": "ICMS",
        "valorBase": 1000.0,
        "aliquota": 18.0,
        "valorImposto": 180.0
    }
    ```

---

### **3. Segurança**

#### `POST /usuario/registrar`
- **Descrição**: Registra um novo usuário no sistema.
- **Entrada**:
    ```json
    {
        "usuario": "usuario123",
        "senha": "senhaSegura",
        "cargos": ["USER"]
    }
    ```
- **Resposta**:
    ```json
    {
        "id": 1,
        "usuario": "usuario123",
        "cargos": ["USER"]
    }
    ```

#### `POST /usuario/login`
- **Descrição**: Autentica um usuário e gera um token JWT.
- **Entrada**:
    ```json
    {
        "usuario": "usuario123",
        "senha": "senhaSegura"
    }
    ```
- **Resposta**:
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

## Configuração e Execução

### **Pré-requisitos**
- Java 17+
- Maven 3.8+
- Postman ou ferramenta similar para testar a API

### **Passos para Executar**
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/desafio-calculo-impostos.git
   cd desafio-calculo-impostos

### **Compile e Execute o Projeto**

1. Certifique-se de que você está no diretório raiz do projeto:
   ```bash
   cd desafio-calculo-impostos

### **Compile e Execute o Projeto usando o Maven**

1. Certifique-se de que você está no diretório raiz do projeto:
   ```bash
   cd desafio-calculo-impostos
   
2. Compile o projeto:
   ```bash
   mvn clean install
   
3. Execute o projeto com Spring Boot:
   ```bash
   mvn spring-boot:run

## Testes

**Framework de Testes:** JUnit 5

**Execução dos Testes:**
  ```bash
  mvn test
  ```

## Contribuição

Sinta-se à vontade para abrir issues ou enviar pull requests para melhorias no projeto.