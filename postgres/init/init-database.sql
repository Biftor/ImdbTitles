DO
$$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'imdb') THEN
            CREATE DATABASE imdb;
        END IF;
    END
$$;

\c imdb


-- tconst (string) - alphanumeric unique identifier of the title
-- titleType (string) – the type/format of the title (e.g. movie, short, tvseries, tvepisode, video, etc)
-- primaryTitle (string) – the more popular title / the title used by the filmmakers on promotional materials at the point of release
-- originalTitle (string) - original title, in the original language
-- isAdult (boolean) - 0: non-adult title; 1: adult title
-- startYear (YYYY) – represents the release year of a title. In the case of TV Series, it is the series start year
-- endYear (YYYY) – TV Series end year. '\N' for all other title types
-- runtimeMinutes – primary runtime of the title, in minutes
-- genres (string array) – includes up to three genres associated with the title
CREATE TABLE title_basics
(
    tconst          VARCHAR PRIMARY KEY,
    title_type      TEXT,
    primary_title   TEXT,
    original_title  TEXT,
    is_adult        BOOLEAN,
    start_year      INT,
    end_year        INT,
    runtime_minutes INT,
    genres          TEXT
);

-- titleId (string) - a tconst, an alphanumeric unique identifier of the title
-- ordering (integer) – a number to uniquely identify rows for a given titleId
-- title (string) – the localized title
-- region (string) - the region for this version of the title
-- language (string) - the language of the title
-- types (array) - Enumerated set of attributes for this alternative title. One or more of the following: "alternative", "dvd", "festival", "tv", "video", "working", "original", "imdbDisplay". New values may be added in the future without warning
-- attributes (array) - Additional terms to describe this alternative title, not enumerated
-- isOriginalTitle (boolean) – 0: not original title; 1: original title
CREATE TABLE title_akas
(
    title_id          VARCHAR,
    ordering          INT,
    title             TEXT,
    region            TEXT,
    language          TEXT,
    types             TEXT,
    attributes        TEXT,
    is_original_title BOOLEAN,
    PRIMARY KEY (title_id, ordering),
    FOREIGN KEY (title_id) REFERENCES title_basics (tconst)
);

-- tconst (string) - alphanumeric unique identifier of the title
-- directors (array of nconsts) - director(s) of the given title
-- writers (array of nconsts) – writer(s) of the given title
CREATE TABLE title_crew
(
    tconst    VARCHAR PRIMARY KEY,
    directors TEXT,
    writers   TEXT,
    FOREIGN KEY (tconst) REFERENCES title_basics (tconst)
);

-- tconst (string) - alphanumeric identifier of episode
-- parentTconst (string) - alphanumeric identifier of the parent TV Series
-- seasonNumber (integer) – season number the episode belongs to
-- episodeNumber (integer) – episode number of the tconst in the TV series
CREATE TABLE title_episode
(
    tconst         VARCHAR PRIMARY KEY,
    parent_tconst  VARCHAR,
    season_number  INT,
    episode_number INT,
    FOREIGN KEY (tconst) REFERENCES title_basics (tconst),
    FOREIGN KEY (parent_tconst) REFERENCES title_basics (tconst)
);

-- tconst (string) - alphanumeric unique identifier of the title
-- averageRating – weighted average of all the individual user ratings
-- numVotes - number of votes the title has received
CREATE TABLE title_ratings
(
    tconst         VARCHAR PRIMARY KEY,
    average_rating DECIMAL(3, 1),
    num_votes      INT,
    FOREIGN KEY (tconst) REFERENCES title_basics (tconst)
);

-- nconst (string) - alphanumeric unique identifier of the name/person
-- primaryName (string)– name by which the person is most often credited
-- birthYear – in YYYY format
-- deathYear – in YYYY format if applicable, else '\N'
-- primaryProfession (array of strings)– the top-3 professions of the person
-- knownForTitles (array of tconsts) – titles the person is known for
CREATE TABLE name_basics
(
    nconst             VARCHAR PRIMARY KEY,
    primary_name       TEXT,
    birth_year         INT,
    death_year         INT,
    primary_profession TEXT,
    known_for_titles   TEXT
);


-- tconst (string) - alphanumeric unique identifier of the title
-- ordering (integer) – a number to uniquely identify rows for a given titleId
-- nconst (string) - alphanumeric unique identifier of the name/person
-- category (string) - the category of job that person was in
-- job (string) - the specific job title if applicable, else '\N'
-- characters (string) - the name of the character played if applicable, else '\N'
CREATE TABLE title_principals
(
    tconst     VARCHAR,
    ordering   INT,
    nconst     TEXT,
    category   TEXT,
    job        TEXT,
    characters TEXT,
    PRIMARY KEY (tconst, ordering, nconst),
    FOREIGN KEY (tconst) REFERENCES title_basics (tconst),
    FOREIGN KEY (nconst) REFERENCES name_basics (nconst)
);


CREATE INDEX IF NOT EXISTS idx_title_akas_titleId ON title_akas (title_id);
CREATE INDEX IF NOT EXISTS idx_title_principals_tconst ON title_principals (tconst);
CREATE INDEX IF NOT EXISTS idx_title_principals_nconst ON title_principals (nconst);
CREATE INDEX IF NOT EXISTS idx_title_crew_tconst ON title_crew (tconst);
CREATE INDEX IF NOT EXISTS idx_title_episode_tconst ON title_episode (tconst);
CREATE INDEX IF NOT EXISTS idx_title_episode_parentTconst ON title_episode (parent_tconst);





-- Disable check for Foreign key constraints because data sets have lots of issues
-- SET session_replication_role = replica;
-- COPY name_basics FROM '/postgres/data/name.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_basics FROM '/postgres/data/title.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_akas FROM '/postgres/data/title.akas.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_crew FROM '/postgres/data/title.crew.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_episode FROM '/postgres/data/title.episode.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_principals FROM '/postgres/data/title.principals.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_ratings FROM '/postgres/data/title.ratings.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
-- SET session_replication_role = DEFAULT;

-- Import just limited data to speedup
-- SET session_replication_role = replica;
-- COPY name_basics FROM '/postgres/data/limited/name.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_basics FROM '/postgres/data/limited/title.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_akas FROM '/postgres/data/limited/title.akas.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_crew FROM '/postgres/data/limited/title.crew.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_episode FROM '/postgres/data/limited/title.episode.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_principals FROM '/postgres/data/limited/title.principals.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- COPY title_ratings FROM '/postgres/data/limited/title.ratings.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
-- SET session_replication_role = DEFAULT;

-- To use VARCHAR I have to convert data
-- If I want to convert separated string by comma to postgresql array
-- This is one way, create temp table import from it to new table
-- Problem is our database is huge and it takes time

-- CREATE TEMP TABLE temp_name_basics (
--                                        nconst TEXT,
--                                        primary_name TEXT,
--                                        birth_year TEXT,
--                                        death_year TEXT,
--                                        primary_profession TEXT,
--                                        known_for_titles TEXT
-- );

-- COPY temp_name_basics FROM '/postgres/data/name.basics.tsv' WITH (FORMAT CSV, DELIMITER E'\t', QUOTE E'\b', HEADER true, NULL '\N');
--
-- INSERT INTO name_basics (nconst, primary_name, birth_year, death_year, primary_profession, known_for_titles)
-- SELECT
--     nconst,
--     primary_name,
--     NULLIF(birth_year, '\N')::INT,
--     NULLIF(death_year, '\N')::INT,
--     CASE WHEN primary_profession != '\N' THEN ARRAY[primary_profession] ELSE NULL END,
--     CASE WHEN known_for_titles != '\N' THEN ARRAY[known_for_titles] ELSE NULL END
-- FROM temp_name_basics;
--
-- DROP TABLE temp_name_basics;
