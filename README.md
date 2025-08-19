# ForumHub - Desafio Back-end Oracle Next Education

Bem-vindo ao **ForumHub**, um sistema de fórum de discussão desenvolvido como desafio de back-end do programa **Oracle Next Education**. Este projeto foi implementado em **Spring Boot** e possui autenticação via **JWT**, CRUD de tópicos, usuários e cursos.

---

## 🔹 Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- H2 Database (em memória)
- Maven
- Lombok (opcional)
- Postman (para testes)

---

## 🔹 Funcionalidades

1. **Autenticação de usuários**
   - Registro (`/auth/register`)
   - Login (`/auth/login`) → retorna JWT Bearer Token

2. **Gerenciamento de tópicos**
   - Criar tópico (`POST /topicos`) — protegido por JWT
   - Listar todos os tópicos (`GET /topicos`)
   - Buscar tópico por ID (`GET /topicos/{id}`)
   - Atualizar tópico (`PUT /topicos/{id}`)
   - Deletar tópico (`DELETE /topicos/{id}`)

3. **Relacionamentos**
   - Cada tópico pertence a um curso
   - Cada tópico pertence a um usuário (autor)
   - Somente o autor pode alterar ou deletar seu tópico

4. **Banco de dados**
   - H2 em memória para desenvolvimento
   - Console H2 disponível em `/h2-console`
   - Exemplo de criação de curso:
     ```sql
     INSERT INTO cursos (nome) VALUES ('Java');
     ```

5. **Segurança**
   - Endpoints `/auth/**` e `/h2-console/**` liberados
   - Todos os demais endpoints protegidos por JWT
   - Usuários autenticados são obrigatórios para criar/editar/deletar tópicos

---
