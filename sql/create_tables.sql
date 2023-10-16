CREATE TABLE IF NOT EXISTS competition
(
    competition_id   serial NOT NULL,
    competition_name varchar(255),
    world_record     numeric(10, 2),
    set_date         date,

    CONSTRAINT competition_pkey PRIMARY KEY (competition_id)
);

COMMENT ON TABLE competition IS '� ������� competition �������� ���������� � ���������� �������������';
COMMENT ON COLUMN competition.competition_id IS 'ID ������������';
COMMENT ON COLUMN competition.competition_id IS '������������ ������������';
COMMENT ON COLUMN competition.competition_id IS '������� ������';
COMMENT ON COLUMN competition.competition_id IS '���� ��������� �������� �������';



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

COMMENT ON TABLE sportsman IS '� ������� sportsman �������� ���������� � �����������';
COMMENT ON COLUMN sportsman.sportsman_id IS 'ID ����������';
COMMENT ON COLUMN sportsman.sportsman_name IS '��� ����������';
COMMENT ON COLUMN sportsman.rank IS '������ ����������';
COMMENT ON COLUMN sportsman.year_of_birth IS '��� ��������';
COMMENT ON COLUMN sportsman.personal_record IS '������������ ������';
COMMENT ON COLUMN sportsman.country IS '������ ����������';



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

COMMENT ON TABLE result IS '� ������� result �������� ����������  � ����������� ������������';
COMMENT ON COLUMN result.competition_id IS 'ID ������������';
COMMENT ON COLUMN result.sportsman_id IS 'ID ����������';
COMMENT ON COLUMN result.result IS '��������� ����������';
COMMENT ON COLUMN result.city IS '����� ����������';
COMMENT ON COLUMN result.hold_date IS '���� ����������';
