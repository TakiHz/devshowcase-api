# devshowcase api

api rest desenvolvida para a disciplina de programação backend do curso de tecnologia em sistemas para internet (uapi / uespi).

**aluno:** mizael dos santos ferreira  
**professor:** pedro alex lemos martins  

---

## 🛠️ tecnologias utilizadas

- java 21 (lts)
- spring boot 3.3.4
- spring data jpa (hibernate)
- bean validation (jakarta validation)
- h2 database (banco em memória para ambiente local)
- postgresql (banco relacional para produção)
- springdoc openapi 3 / swagger ui
- maven

---

## 🏛️ arquitetura do projeto

o projeto segue a arquitetura em camadas com separação estrita de responsabilidades:

```
src/main/java/br/com/palm/devshowcase/
├── config/             # configuração do swagger / openapi
├── controller/         # adaptadores rest e mapeamento http
├── dto/                # data transfer objects (records) com validações
├── exception/          # tratamento global de erros (rfc 7807)
├── model/              # entidades jpa e mapeamento relacional
├── repository/         # interfaces de persistência spring data jpa
└── service/            # regras de negócio e transações
```

### modelo relacional

- **profile (perfil do desenvolvedor)**: armazena dados profissionais e redes sociais.
- **project (projeto)**: associado ao perfil (1 : N), tecnologias (N : N) e feedbacks (1 : N).
- **technology (tecnologia)**: tecnologias associadas aos projetos.
- **feedback (avaliação)**: comentários e notas (1 a 5) associados aos projetos.

---

## 🚀 como executar localmente

### pré-requisitos
- java 21 instalado
- git

### passo a passo
1. clone o repositório:
```bash
git clone https://github.com/TakiHz/devshowcase-api.git
cd devshowcase-api
```

2. execute a aplicação via maven wrapper:
```bash
# windows
.\mvnw.cmd spring-boot:run

# linux / mac
./mvnw spring-boot:run
```

3. a api estará disponível em: `http://localhost:8080`
- **swagger ui:** `http://localhost:8080/swagger-ui.html`
- **openapi json:** `http://localhost:8080/v3/api-docs`
- **console h2:** `http://localhost:8080/h2-console` (jdbc url: `jdbc:h2:mem:devshowcasedb`, usuário: `sa`, senha: em branco)

---

## 📬 endpoints da api

| método | rota | descrição |
|---|---|---|
| `GET` | `/api/technologies` | lista todas as tecnologias |
| `POST` | `/api/technologies` | cadastra uma nova tecnologia |
| `GET` | `/api/profiles` | lista todos os perfis |
| `POST` | `/api/profiles` | cadastra um novo perfil |
| `GET` | `/api/profiles/{id}` | busca um perfil pelo id |
| `GET` | `/api/projects` | lista projetos com filtro por tecnologia e paginação |
| `POST` | `/api/projects` | cadastra um novo projeto |
| `PUT` | `/api/projects/{id}/upvote` | incrementa as curtidas de um projeto |
| `POST` | `/api/projects/{id}/feedbacks` | cadastra um feedback e recalcula a nota média do projeto |
| `POST` | `/api/projects/{id}/technologies` | vincula uma tecnologia a um projeto |

---

## 🧪 testes via postman

o arquivo `DevShowcase.postman_collection.json` está disponível na raiz do repositório. basta importá-lo no postman para testar todos os endpoints e as validações rfc 7807.

---

## ☁️ deploy no render.com

o projeto possui um `Dockerfile` multi-stage otimizado para deploy em serviços paas (render).
para subir no render:
1. conecte o repositório `https://github.com/TakiHz/devshowcase-api` como **web service**.
2. runtime: **docker**.
3. configure as variáveis de ambiente do banco postgresql:
   - `DATABASE_URL`
   - `DATABASE_USER`
   - `DATABASE_PASSWORD`
