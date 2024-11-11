# Use a imagem oficial do PostgreSQL
FROM postgres:latest

# Define a senha padrão para o usuário "postgres"
ENV POSTGRES_PASSWORD=1234
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=alumind

# Copia o arquivo SQL para dentro do contêiner
COPY init.sql /docker-entrypoint-initdb.d/