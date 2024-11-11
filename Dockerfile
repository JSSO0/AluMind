FROM postgres:latest
ENV POSTGRES_PASSWORD=1234
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=alumind
COPY init.sql /docker-entrypoint-initdb.d/

