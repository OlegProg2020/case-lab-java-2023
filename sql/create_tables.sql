CREATE TABLE IF NOT EXISTS competition
(
    competition_id   serial NOT NULL,
    competition_name varchar(255),
    world_record     numeric(10, 2),
    set_date         date,

    CONSTRAINT competition_pkey PRIMARY KEY (competition_id)
);

COMMENT ON TABLE competition IS 'В таблице competition хранится информация о проводимых соревнованиях';
COMMENT ON COLUMN competition.competition_id IS 'ID соревнования';
COMMENT ON COLUMN competition.competition_id IS 'наименование соревнования';
COMMENT ON COLUMN competition.competition_id IS 'мировой рекорд';
COMMENT ON COLUMN competition.competition_id IS 'дата установки мирового рекорда';



CREATE TABLE IF NOT EXISTS sportsman
(
    sportsman_id    serial NOT NULL,
    sportsman_name  varchar(255),
    rank            varchar(255),
    year_of_birth   integer,
    personal_record numeric(10, 2),
    country         varchar(255),

    CONSTRAINT sportsman_pkey PRIMARY KEY (sportsman_id)
);

COMMENT ON TABLE sportsman IS 'В таблице sportsman хранится информация о спортсменах';
COMMENT ON COLUMN sportsman.sportsman_id IS 'ID спортсмена';
COMMENT ON COLUMN sportsman.sportsman_name IS 'имя спортсмена';
COMMENT ON COLUMN sportsman.rank IS 'разряд спортсмена';
COMMENT ON COLUMN sportsman.year_of_birth IS 'год рождения';
COMMENT ON COLUMN sportsman.personal_record IS 'персональный рекорд';
COMMENT ON COLUMN sportsman.country IS 'страна спортсмена';



CREATE TABLE IF NOT EXISTS result
(
    competition_id integer NOT NULL,
    sportsman_id   integer NOT NULL,
    result         numeric(10, 2),
    city           varchar(255),
    hold_date      date,

    CONSTRAINT result_competition_id_fk FOREIGN KEY (competition_id) REFERENCES competition (competition_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT result_sportsman_id_fk FOREIGN KEY (sportsman_id) REFERENCES sportsman (sportsman_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

COMMENT ON TABLE result IS 'В таблице result хранится информация  о результатах соревнований';
COMMENT ON COLUMN result.competition_id IS 'ID соревнования';
COMMENT ON COLUMN result.sportsman_id IS 'ID спортсмена';
COMMENT ON COLUMN result.result IS 'результат спортсмена';
COMMENT ON COLUMN result.city IS 'место проведения';
COMMENT ON COLUMN result.hold_date IS 'дата проведения';
