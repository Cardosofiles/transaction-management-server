<div align="center">

# 💰 Transaction Management Server

### Sistema de Gerenciamento de Transações Financeiras

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-success?style=for-the-badge)](https://github.com)

</div>

---

## 🛠️ Tecnologias

<div align="center">

![Java](https://skillicons.dev/icons?i=java)
![Spring](https://skillicons.dev/icons?i=spring)
![Gradle](https://skillicons.dev/icons?i=gradle)
![PostgreSQL](https://skillicons.dev/icons?i=postgresql)
![Docker](https://skillicons.dev/icons?i=docker)
![Git](https://skillicons.dev/icons?i=git)

</div>

---

## 📋 Menu de Navegação

- [📖 Sobre o Projeto](#sobre-o-projeto)
- [✨ Funcionalidades](#funcionalidades)
- [🏗️ Arquitetura](#️arquitetura)
- [🚀 Como Executar](#como-executar)
  - [Pré-requisitos](#pré-requisitos)
  - [Instalação](#instalação)
  - [Executando a Aplicação](#executando-a-aplicação)
- [🔧 Configuração](#configuração)
- [📡 Endpoints da API](#endpoints-da-api)
- [🧪 Testes](#testes)
- [📦 Deploy](#deploy)
- [🤝 Contribuindo](#contribuindo)
- [📄 Licença](#licença)
- [👤 Autor](#-autor)

---

<h2 id="sobre-o-projeto"> 📖 Sobre o Projeto</h2>

O **Transaction Management Server** é uma aplicação backend robusta e escalável desenvolvida em Java com Spring Boot, projetada para gerenciar transações financeiras de forma segura e eficiente. O sistema oferece uma API RESTful completa para operações de criação, consulta, atualização e exclusão de transações.

### 🎯 Objetivo

Fornecer uma solução confiável para o gerenciamento de transações financeiras, com foco em:

- **Segurança**: Implementação de autenticação e autorização
- **Performance**: Otimização de consultas e cache
- **Escalabilidade**: Arquitetura preparada para crescimento
- **Manutenibilidade**: Código limpo e bem documentado

---

<h2 id="funcionalidades"> ✨ Funcionalidades</h2>

- ✅ **CRUD Completo de Transações**

  - Criar, listar, atualizar e deletar transações
  - Filtros avançados e paginação
  - Validação de dados com Bean Validation

- 🔐 **Autenticação e Autorização**

  - JWT (JSON Web Tokens)
  - Controle de acesso baseado em roles

- 💾 **Persistência de Dados**

  - Integração com PostgreSQL
  - JPA/Hibernate para ORM
  - Migrations com Flyway

- 📊 **Relatórios e Analytics**

  - Geração de relatórios de transações
  - Estatísticas e dashboards

- 🔄 **Integração**
  - API RESTful documentada com Swagger/OpenAPI
  - Suporte a diferentes formatos (JSON, XML)

---

<h2 id="arquitetura"> 🏗️ Arquitetura</h2>

O projeto segue os princípios de **Clean Architecture** e **Domain-Driven Design (DDD)**:

```
transaction-management-server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/transaction/
│   │   │       ├── config/          # Configurações
│   │   │       ├── controller/      # Controllers REST
│   │   │       ├── service/         # Lógica de negócio
│   │   │       ├── repository/      # Acesso a dados
│   │   │       ├── model/           # Entidades
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       ├── exception/       # Tratamento de exceções
│   │   │       └── security/        # Segurança
│   │   └── resources/
│   │       ├── application.yml      # Configurações
│   │       └── db/migration/        # Scripts SQL
│   └── test/                        # Testes unitários e integração
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

### Stack Tecnológica

- **Backend**: Java 17+, Spring Boot 3.x
- **Persistência**: PostgreSQL, Spring Data JPA, Hibernate
- **Segurança**: Spring Security, JWT
- **Build**: Maven
- **Documentação**: SpringDoc OpenAPI (Swagger)
- **Testes**: JUnit 5, Mockito, TestContainers
- **Containerização**: Docker, Docker Compose

---

<h2 id="como-executar"> 🚀 Como Executar</h2>

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- PostgreSQL 14+
- Docker e Docker Compose (opcional)

### Instalação

1. **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/transaction-management-server.git
cd transaction-management-server
```

2. **Configure o banco de dados**

```bash
# Criar banco de dados PostgreSQL
createdb transaction_db
```

3. **Configure as variáveis de ambiente**

```bash
cp .env.example .env
# Edite o arquivo .env com suas configurações
```

### Executando a Aplicação

#### Opção 1: Com Maven

```bash
# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

#### Opção 2: Com Docker Compose

```bash
# Subir todos os serviços
docker-compose up -d

# Ver logs
docker-compose logs -f app
```

A aplicação estará disponível em: `http://localhost:8080`

Swagger UI: `http://localhost:8080/swagger-ui.html`

---

<h2 id="configuração">🔧 Configuração</h2>

### application.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/transaction_db
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080

jwt:
  secret: ${JWT_SECRET:your-secret-key}
  expiration: 86400000
```

### Variáveis de Ambiente

| Variável      | Descrição              | Padrão         |
| ------------- | ---------------------- | -------------- |
| `DB_HOST`     | Host do banco de dados | localhost      |
| `DB_PORT`     | Porta do PostgreSQL    | 5432           |
| `DB_NAME`     | Nome do banco          | transaction_db |
| `DB_USERNAME` | Usuário do banco       | postgres       |
| `DB_PASSWORD` | Senha do banco         | postgres       |
| `JWT_SECRET`  | Secret para JWT        | -              |

---

<h2 id="endpoints-da-api">📡 Endpoints da API</h2>

### Transações

| Método   | Endpoint                   | Descrição                  |
| -------- | -------------------------- | -------------------------- |
| `POST`   | `/api/transactions`        | Criar nova transação       |
| `GET`    | `/api/transactions`        | Listar todas as transações |
| `GET`    | `/api/transactions/{id}`   | Buscar transação por ID    |
| `PUT`    | `/api/transactions/{id}`   | Atualizar transação        |
| `DELETE` | `/api/transactions/{id}`   | Deletar transação          |
| `GET`    | `/api/transactions/report` | Gerar relatório            |

### Autenticação

| Método | Endpoint             | Descrição              |
| ------ | -------------------- | ---------------------- |
| `POST` | `/api/auth/login`    | Autenticar usuário     |
| `POST` | `/api/auth/register` | Registrar novo usuário |
| `POST` | `/api/auth/refresh`  | Renovar token          |

**Documentação completa**: Acesse o Swagger UI em `/swagger-ui.html`

---

<h2 id="testes">🧪 Testes</h2>

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura
mvn test jacoco:report

# Ver relatório de cobertura
open target/site/jacoco/index.html
```

### Tipos de Testes

- **Testes Unitários**: Validação de lógica de negócio
- **Testes de Integração**: Validação de fluxos completos
- **Testes de API**: Validação de endpoints REST

---

<h2 id="deploy">📦 Deploy</h2>

### Build da Aplicação

```bash
# Gerar JAR
mvn clean package -DskipTests

# JAR estará em target/transaction-management-server.jar
```

### Docker

```bash
# Build da imagem
docker build -t transaction-management-server:latest .

# Executar container
docker run -p 8080:8080 \
  -e DB_HOST=postgres \
  -e DB_PASSWORD=secret \
  transaction-management-server:latest
```

---

<h2 id="contribuindo">🤝 Contribuindo</h2>

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

<h2 id="licença">📄 Licença</h2>

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<h2 id="autor">👤 Autor</h2>

**Desenvolvedor Java Sênior**

- GitHub: [Cardosofiles](https://github.com/Cardosofiles)
- LinkedIn: [João Batista Desenvolvedor Full Stack](https://linkedin.com/in/cardosofiles)
- Email: cardosofiles@gmail.com

---

<div align="center">

### ⭐ Se este projeto foi útil, considere dar uma estrela!

**Feito com ❤️ e ☕ por um Desenvolvedor em busca da primeira vaga Java Júnior**

</div>
