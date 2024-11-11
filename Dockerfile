FROM postgres:latest
ENV POSTGRES_PASSWORD=1234
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=alumind-db
COPY init.sql /docker-entrypoint-initdb.d/
RUN /usr/local/bin/docker-entrypoint.sh postgres