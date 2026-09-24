# devshowcase api — plataforma de portfólios de desenvolvedores

api restful completa para cadastro, avaliação e compartilhamento de perfis profissionais e projetos de tecnologia, desenvolvida para a disciplina de **programação backend** do curso de tecnologia em sistemas para internet da uapi / uespi.

**aluno:** mizael dos santos ferreira  
**professor:** pedro alex lemos martins  

---

## 🔗 links rápidos para avaliação

- **código-fonte no github:** [https://github.com/TakiHz/devshowcase-api](https://github.com/TakiHz/devshowcase-api)
- **api rodando na nuvem (render):** [https://devshowcase-api-5n0w.onrender.com](https://devshowcase-api-5n0w.onrender.com)
- **swagger ui interativo em produção:** [https://devshowcase-api-5n0w.onrender.com/swagger-ui.html](https://devshowcase-api-5n0w.onrender.com/swagger-ui.html)
- **especificação openapi (json):** [https://devshowcase-api-5n0w.onrender.com/v3/api-docs](https://devshowcase-api-5n0w.onrender.com/v3/api-docs)

---

## 🛠️ tecnologias e ferramentas utilizadas

- **linguagem:** java 21 (lts)
- **framework:** spring boot 3.3.4
- **persistência de dados:** spring data jpa (hibernate orm)
- **validações de entrada:** jakarta validation (bean validation)
- **bancos de dados:** 
  - h2 database (em memória para desenvolvimento local)
  - postgresql (banco relacional provisionado em produção)
- **documentação viva:** springdoc openapi 3 / swagger ui
- **infraestrutura e deploy:** docker & render.com

---

## 🏛️ arquitetura em camadas

o projeto adota o princípio da responsabilidade única (srp), separando as preocupações em camadas estritamente isoladas:

- **`controller/`**: adaptadores http responsáveis por receber as requisições, acionar validações e responder com os status semânticos adequados (200, 201, 204).
- **`service/`**: núcleo da aplicação onde residem as regras de negócio (cálculo de média de avaliações, incremento de curtidas e validações lógicas).
- **`repository/`**: abstração de acesso a dados com suporte a paginação e consultas personalizadas via jpa.
- **`model/`**: mapeamento das entidades relacionais (`Profile`, `Project`, `Technology`, `Feedback`).
- **`dto/`**: records imutáveis que isolam o banco da internet, com validações declarativas.
- **`exception/`**: manipulador centralizado de erros (`@RestControllerAdvice`) devolvendo o padrão rfc 7807 problem details.

---

## 📬 resumo das rotas (endpoints)

### tecnologias
- `GET /api/technologies` — lista todas as tecnologias cadastradas
- `POST /api/technologies` — cadastra uma nova tecnologia

### perfis de desenvolvedores
- `GET /api/profiles` — lista todos os perfis cadastrados
- `POST /api/profiles` — cadastra um novo perfil profissional
- `GET /api/profiles/{id}` — busca perfil por id acompanhado de seus projetos

### projetos e avaliações
- `GET /api/projects` — lista projetos com suporte a paginação e filtro por tecnologia (ex: `?technology=Java`)
- `POST /api/projects` — cadastra um novo projeto vinculado a um perfil
- `PUT /api/projects/{id}/upvote` — incrementa o número de curtidas/estrelas do projeto
- `POST /api/projects/{id}/feedbacks` — registra avaliação (nota de 1 a 5 e comentário) e recalcula automaticamente a média do projeto
- `POST /api/projects/{id}/technologies` — vincula uma tecnologia existente ao projeto

---

## 🧪 testes práticos no postman

na raiz deste repositório há o arquivo **`colecao_postman_devshowcase.json`**.  
basta abrir o postman, clicar em **import** e selecionar o arquivo para ter todas as chamadas prontas:
1. cadastro e listagem de tecnologias.
2. cadastro de perfil e busca por id.
3. cadastro de projeto, upvote e feedback com cálculo de média.
4. simulação de erro 404 (recurso inexistente) e erro 400 (dados inválidos).

---

## 💻 como executar o projeto na sua máquina

### pré-requisitos:
- java 21
- git

### passos:
```bash
# 1. clonar o repositório
git clone https://github.com/TakiHz/devshowcase-api.git
cd devshowcase-api

# 2. executar via maven wrapper (não precisa ter maven instalado)
# no windows:
.\mvnw.cmd spring-boot:run

# no linux ou mac:
./mvnw spring-boot:run
```

a api estará acessível em `http://localhost:8080` e o swagger ui em `http://localhost:8080/swagger-ui.html`.
