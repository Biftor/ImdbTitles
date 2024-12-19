#!/bin/bash

docker exec -it imdb-db psql -U admin -d imdb \
    -c "SET session_replication_role = replica;" \
    -c "COPY name_basics FROM '/postgres/data/name.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
    -c "SET session_replication_role = DEFAULT;"


docker exec -it imdb-db psql -U admin -d imdb \
    -c "SET session_replication_role = replica;" \
    -c "COPY title_basics FROM '/postgres/data/title.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
    -c "SET session_replication_role = DEFAULT;"

#docker exec -it imdb-db psql -U admin -d imdb \
#    -c "SET session_replication_role = replica;" \
#    -c "COPY title_akas FROM '/postgres/data/title.akas.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
#    -c "SET session_replication_role = DEFAULT;"

docker exec -it imdb-db psql -U admin -d imdb \
    -c "SET session_replication_role = replica;" \
    -c "COPY title_crew FROM '/postgres/data/title.crew.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
    -c "SET session_replication_role = DEFAULT;"

#docker exec -it imdb-db psql -U admin -d imdb \
#    -c "SET session_replication_role = replica;" \
#    -c "COPY title_episode FROM '/postgres/data/title.episode.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
#    -c "SET session_replication_role = DEFAULT;"


docker exec -it imdb-db psql -U admin -d imdb \
    -c "SET session_replication_role = replica;" \
    -c "COPY title_principals FROM '/postgres/data/title.principals.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
    -c "SET session_replication_role = DEFAULT;"


docker exec -it imdb-db psql -U admin -d imdb \
    -c "SET session_replication_role = replica;" \
    -c "COPY title_ratings FROM '/postgres/data/title.ratings.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');" \
    -c "SET session_replication_role = DEFAULT;"