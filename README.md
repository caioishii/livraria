# Livraria (DSW1)

Exercício web Java (Servlet/JSP) para gerenciamento de **livros** e **editoras**, desenvolvido para a disciplina DSW1.

## Tecnologias
- Java 11
- Maven
- Servlet/JSP (JSTL)
- MySQL
- Empacotamento WAR

## Estrutura
- `src/main/java`: código Java (model, dao, controller)
- `src/main/webapp`: páginas JSP e configuração web
- `schema.sql`: script de criação do banco de dados
- `pom.xml`: dependências e build Maven

## Pré-requisitos
- JDK 11+
- Maven 3.8+
- MySQL 8+
- Servidor compatível com Servlet 4 (ex.: Apache Tomcat 9)

## Configuração do banco de dados
1. Crie um banco no MySQL.
2. Execute o script `schema.sql`.
3. Ajuste as credenciais de conexão em:
   - `src/main/java/br/ufscar/dsw/livraria/util/ConnectionFactory.java`

## Como executar
1. Gere o WAR:
   ```bash
   mvn clean package
   ```
2. O arquivo será gerado em `target/livraria.war`.
3. Faça o deploy no Tomcat.
4. Acesse no navegador (exemplo):
   - `http://localhost:8080/livraria/`

## Funcionalidades
- CRUD de Editoras
- CRUD de Livros

## Observações
- O exercício usa padrão MVC simples com Servlet + JSP.
- Dependendo do ambiente, pode ser necessário ajustar URL, porta ou contexto no servidor.
