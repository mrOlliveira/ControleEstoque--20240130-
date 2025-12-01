Claro! Aqui está o conteúdo transformado para um arquivo `README.md`:

````markdown
# API de Estoque

Este projeto implementa uma API de gestão de estoque usando **Spring Boot** e **JPA**. Ele permite o gerenciamento de **clientes**, **produtos**, **vendas**, **categorias** e **fornecedores**.

## 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/api-estoque.git
cd api-estoque
````

## 2. Configure o Banco de Dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE estoque_db;
```

## 3. Configure a Aplicação

Edite o arquivo `src/main/resources/application.properties` para configurar o acesso ao banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/estoque_db
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

## 4. Execute a Aplicação

### Usando Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Ou usando Maven

```bash
mvn spring-boot:run
```

## 5. Acesse a API

A API estará disponível em: `http://localhost:8080`

## 📚 Endpoints da API

### 🔹 Clientes

* **GET** `/api/clientes` - Listar todos
* **GET** `/api/clientes/{id}` - Buscar por ID
* **POST** `/api/clientes` - Criar cliente
* **PUT** `/api/clientes/{id}` - Atualizar cliente
* **DELETE** `/api/clientes/{id}` - Excluir cliente

### 🔹 Vendas

* **POST** `/api/vendas` - Registrar nova venda

Exemplo de JSON para registrar uma venda:

```json
{
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 3,
      "quantidade": 1
    }
  ]
}
```

### 🔹 Produtos (já existentes)

* **GET** `/api/produtos` - Listar todos os produtos
* **GET** `/api/produtos/{id}` - Buscar produto por ID
* **POST** `/api/produtos` - Criar novo produto
* **PUT** `/api/produtos/{id}` - Atualizar produto
* **DELETE** `/api/produtos/{id}` - Excluir produto

### 🔹 Categorias (já existentes)

* **GET** `/api/categorias` - Listar todas as categorias
* **GET** `/api/categorias/{id}` - Buscar categoria por ID
* **POST** `/api/categorias` - Criar nova categoria
* **PUT** `/api/categorias/{id}` - Atualizar categoria
* **DELETE** `/api/categorias/{id}` - Excluir categoria

### 🔹 Fornecedores (já existentes)

* **GET** `/api/fornecedores` - Listar todos os fornecedores
* **GET** `/api/fornecedores/{id}` - Buscar fornecedor por ID
* **POST** `/api/fornecedores` - Criar novo fornecedor
* **PUT** `/api/fornecedores/{id}` - Atualizar fornecedor
* **DELETE** `/api/fornecedores/{id}` - Excluir fornecedor

## 🧪 Testando a API

### 1. Criar um cliente

```bash
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome": "João Silva", "email": "joao@email.com"}'
```

### 2. Criar um produto com estoque

```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Notebook",
    "preco": 3500.00,
    "estoque": {"quantidade": 10},
    "categoria": {"id": 1}
  }'
```

### 3. Registrar uma venda (teste principal)

```bash
curl -X POST http://localhost:8080/api/vendas \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 1,
    "itens": [
      {"produtoId": 1, "quantidade": 2}
    ]
  }'
```

**Respostas esperadas:**

* `201 Created`: Venda registrada com sucesso
* `400 Bad Request`: Estoque insuficiente
* `404 Not Found`: Cliente ou produto não encontrado

## 🗄️ Modelo de Dados

### Relacionamentos:

* **Cliente (1) → Venda (N)**: Um cliente pode ter várias vendas
* **Venda (N) ↔ Produto (M) via ItemVenda**: Uma venda pode ter vários produtos
* **Produto (1) ↔ Estoque (1)**: Cada produto tem um registro de estoque
* **Categoria (1) → Produto (N)**: Uma categoria pode ter vários produtos
* **Produto (N) ↔ Fornecedor (M)**: Produtos podem ter vários fornecedores

## 🔧 Solução de Problemas

### Problema: "Acesso negado" no Maven

```bash
# Execute como administrador ou use:
./mvnw clean compile
```

### Problema: Porta 8080 ocupada

Edite o arquivo `application.properties`:

```properties
server.port=8081
```

### Problema: Erro ao conectar no MySQL

* Verifique se o MySQL está rodando.
* Confira o usuário e senha no arquivo `application.properties`.
* Crie o banco de dados: `CREATE DATABASE estoque_db;`

## 📝 Notas de Implementação

### Lógica de Estoque

* Ao registrar uma venda, verifica-se a disponibilidade de cada item.
* Se houver estoque suficiente, a quantidade vendida é subtraída.
* Se algum item faltar, a transação é cancelada (rollback).
* Retorna uma mensagem de erro indicando qual produto falta.

### Segurança de Dados

* Transações garantem consistência do estoque.
* Histórico de preços mantido na tabela `ItemVenda`.
* Validações em tempo real antes de qualquer operação.

## 📄 Licença

Este projeto é para fins educacionais.

## 👨‍💻 Autor

Desenvolvido como projeto acadêmico para estudo de Spring Boot e JPA.

🎯 A API estará pronta para uso após executar `mvn spring-boot:run` e acessar `http://localhost:8080`.

```

Este `README.md` contém todas as instruções necessárias para a configuração e uso da API, assim como exemplos de testes. Certifique-se de substituir as informações de usuário e senha conforme necessário.
```
