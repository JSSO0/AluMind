# Alumind

## Introdução
<p align="left">🚀 API para a startup Alumin analisar os feedbacks vindos dos usuários, onde será classificado
e sugerido possíveis respostas para os feedbacks.</p>

## Tecnologias usadas
![Postgres](https://img.shields.io/badge/postgres_17-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JavaFX](https://img.shields.io/badge/java_21-%23FF0000.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven_3.9.4-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring](https://img.shields.io/badge/spring_3.3.5-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![ChatGPT](https://img.shields.io/badge/chatGPT-74aa9c?style=for-the-badge&logo=openai&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## Pré-requisito

1. Crie um arquivo `.env` com a seguinte variávei:
    * `SPRING_AI_OPENAI_API_KEY`

## Instalação e uso
1. Execute o comando `docker-compose up --build -d` para iniciar o container da aplicação.

## Rotas disponíveis
POST - http://localhost:8080/feedback

Request:
```bash
{
    "feedback": ""
}
```
Response:
```bash
{
 "id": ,
 "sentiment": "",
 "requested_features": [
 {
 "code": "",
 "reason": ""
 }
 ]
}
```

GET - http://localhost:8080/feedback/suggested-answer/{{id}}

Response:
```bash
{
 "suggestedAnswer": ""
}
```
## Possíveis problemas e como resolver

1. Banco não iniciar - Se o banco não iniciar, verifique o terminal do docker, pode ser que ocorra do init.sql 
não criar corretamente, então veja se o init.sql está correto, conforme abaixo:
```bash
CREATE TABLE feedback (
   id SERIAL PRIMARY KEY,
   sentiment VARCHAR(255),
   code VARCHAR(255),
   reason text,
   feedbackOriginal text,
   suggestedAnswer text
   )
```
2. OpenAI ApiKey não ser reconhecida, provavelmente o .env não foi criado corretamente, crie um arquivo .env e coloque
   SPRING_AI_OPENAI_API_KEY={{sua_key}}, sem ela a aplicação inicia, mas já irá falhar na validação de spam.

## Escolhas de implementação da sugestão de resposta
Como eu já faço uma request para a OpenAi, pedindo todo o meu objeto, onde no meu prompt eu defino a estrutura que
eu quero, por questões de processamento, custos e usabilidade, optei por na mesma request pedir a sugestão de resposta
e salvar ela no banco, onde, pode ocorrer casos que não seja necessário a sugestão de resposta, optei por criar um novo
endpoit GET, que recebe o parametro do id do feedback, para pegar a sugestão no banco, sendo mais rápido do que fazer
uma outra requisição para a OPENAI e salvar no banco, onde agora apenas irei consultar ela no meu banco.

## Sugestão de melhoria
A proposta é criar uma funcionalidade que analise o estado emocional e comportamental do usuário ao longo do tempo, 
utilizando feedbacks e interações com o aplicativo. Com base nesses dados, uma LLM seria capaz de identificar padrões 
de comportamento e sugerir ações de bem-estar personalizadas, como novos conteúdos de meditação, técnicas de respiração, 
ou até recomendações de sessões de terapia, dependendo do histórico do usuário.

## Licença

O Alumind é licenciado sob a licença MIT.

