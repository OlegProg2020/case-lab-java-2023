insert into competition
(competition_name, world_record, set_date)
values ('Чемпионат мира по легкой атлетике', 9.58, '2022-07-15'),
       ('Олимпийские игры', 9.63, '2021-08-08'),
       ('Чемпионат Европы по плаванию', 46.91, '2022-08-14'),
       ('Чемпионат мира по футболу', 171, '2022-12-18'),
       ('Чемпионат мира по боксу', 50.8, '2022-11-20'),
       ('Чемпионат мира по теннису', 263, '2022-09-11'),
       ('Чемпионат мира по гребле на байдарках и каноэ', 31.56, '2022-08-28'),
       ('Чемпионат мира по гимнастике', 16.533, '2022-10-23'),
       ('Чемпионат мира по баскетболу', 100, '2022-12-11'),
       ('Чемпионат мира по волейболу', 3.47, '2022-12-04'),
       ('Чемпионат мира по хоккею', 128.8, '2022-05-22'),
       ('Чемпионат мира по биатлону', 12.3, '2022-02-13'),
       ('Чемпионат мира по лыжным гонкам', 23.5, '2022-02-27'),
       ('Чемпионат мира по фигурному катанию', 330.98, '2022-03-27'),
       ('Чемпионат мира по шахматам', 2851, '2022-11-06'),
       ('Чемпионат мира по покеру', 22.3, '2022-07-31'),
       ('Чемпионат мира по шашкам', 0.5, '2022-09-18'),
       ('Чемпионат мира по го', 9.5, '2022-10-09'),
       ('Чемпионат мира по бильярду', 9.9, '2022-11-27'),
       ('Чемпионат мира по настольному теннису', 1.5, '2022-12-25'),
       ('Чемпионат мира по бадминтону', 4.5, '2022-08-21'),
       ('Чемпионат мира по гольфу', 61, '2022-06-05'),
       ('Чемпионат мира по боксу', 50.8, '2022-11-20'),
       ('Чемпионат мира по кикбоксингу', 2.5, '2022-10-02'),
       ('Чемпионат мира по каратэ', 2.5, '2022-11-13'),
       ('Чемпионат мира по тхэквондо', 2.5, '2022-12-04'),
       ('Чемпионат мира по борьбе', 2.5, '2022-09-25'),
       ('Чемпионат мира по самбо', 2.5, '2022-08-14');

insert into sportsman
(sportsman_name, rank, year_of_birth, personal_record, country)
values ('Усейн Болт', 'Мастер спорта международного класса', 1986, 9.58, 'Ямайка'),
       ('Тайсон Гэй', 'Кандидат в мастера спорта', 1982, 9.69, 'США'),
       ('Трэйвон Бромелл', 'Мастер спорта России', 1995, 9.77, 'США'),
       ('Кристиан Коулман', 'Кандидат в мастера спорта', 1996, 9.76, 'США'),
       ('Юссейн Болт', 'Мастер спорта России', 1986, 9.63, 'Ямайка'),
       ('Майкл Норман', 'Мастер спорта России', 1997, 43.45, 'США'),
       ('Уилсон Кипкет', 'Кандидат в мастера спорта', 1984, 26.69, 'Кения'),
       ('Донаван Брэйлин', 'Мастер спорта России', 1987, 9.76, 'США'),
       ('Крис Колеман', 'Кандидат в мастера спорта', 1996, 9.76, 'США'),
       ('Трэйвис Скотт', 'Мастер спорта России', 1994, 9.84, 'США'),
       ('Кендрик Моррисон', 'Кандидат в мастера спорта', 1988, 9.80, 'США'),
       ('Кристофер Белл', 'Мастер спорта России', 1994, 9.90, 'США'),
       ('Кристиан Тэйлор', 'Кандидат в мастера спорта', 1990, 18.21, 'США'),
       ('Райан Кроуфорд', 'Мастер спорта России', 1989, 19.72, 'США'),
       ('Ноа Лайлз', 'Кандидат в мастера спорта', 1997, 9.86, 'США'),
       ('Андрей Прохоров', 'Мастер спорта международного класса', 1994, 10.03, 'Россия'),
       ('Андрей Яковлев', 'Кандидат в мастера спорта', 1995, 10.05, 'Россия'),
       ('Александр Шевченко', 'Мастер спорта России', 1996, 10.08, 'Россия'),
       ('Александр Левин', 'Кандидат в мастера спорта', 1997, 10.12, 'Россия'),
       ('Александр Козлов', 'Мастер спорта России', 1998, 10.15, 'Россия'),
       ('Александр Кузнецов', 'Кандидат в мастера спорта', 1999, 10.18, 'Россия'),
       ('Александр Королев', 'Мастер спорта России', 2000, 10.21, 'Россия'),
       ('Александр Иванов', 'Кандидат в мастера спорта', 2001, 10.24, 'Россия'),
       ('Александр Григорьев', 'Мастер спорта России', 2002, 10.27, 'Россия'),
       ('Александр Белов', 'Кандидат в мастера спорта', 2003, 10.30, 'Россия'),
       ('Александр Богданов', 'Мастер спорта России', 2004, 10.33, 'Россия'),
       ('Александр Васильев', 'Кандидат в мастера спорта', 2005, 10.36, 'Россия'),
       ('Александр Гаврилов', 'Мастер спорта России', 2006, 10.42, 'Россия');

insert into result
(competition_id, sportsman_id, result, city, hold_date)
values (1, 1, 108.79, 'Одинцово', '2016-04-07'),
       (1, 2, 318.15, 'Одинцово', '2011-09-25'),
       (1, 3, 797.0, 'Одинцово', '1992-04-02'),
       (2, 4, 1241.32, 'Клин', '1982-12-12'),
       (2, 5, 6.81, 'Клин', '1982-02-09'),
       (3, 1, 689.48, 'Москва', '1997-01-09'),
       (3, 9, 7.5, 'Москва', '2015-06-07'),
       (4, 25, 309.58, 'Ногинск', '1975-06-01'),
       (4, 14, 23.0, 'Ногинск', '2000-06-01'),
       (4, 17, 133.88, 'Ногинск', '1982-07-28'),
       (4, 27, 5.09, 'Шаховская', '1991-10-17'),
       (4, 19, 18.61, 'Шаховская', '2001-04-14'),
       (5, 19, 170.60, 'Можайск', '1978-03-26'),
       (5, 26, 7319.96, 'Можайск', '1993-05-03'),
       (5, 8, 422.27, 'Можайск', '1983-11-05'),
       (6, 7, 113.8, 'Дмитров', '2009-07-31'),
       (7, 1, 275.97, 'Дорохово', '1992-08-17'),
       (8, 1, 9.38, 'Красногорск', '1993-03-05'),
       (9, 3, 23.51, 'Люберцы', '2015-03-24'),
       (10, 6, 3231.47, 'Чехов', '1988-07-19'),
       (11, 11, 73.47, 'Истра', '1991-06-12'),
       (12, 10, 1751.07, 'Дорохово', '2016-06-26'),
       (13, 11, 279.03, 'Ступино', '1973-05-16'),
       (14, 13, 763.92, 'Егорьевск', '2014-05-22'),
       (15, 15, 0.59, 'Подольск', '2021-06-30'),
       (16, 19, 3054.05, 'Серпухов', '2000-02-25'),
       (17, 16, 2086.47, 'Москва', '1976-04-10'),
       (18, 27, 5, 'Раменское', '1973-09-25'),
       (19, 27, 17.78, 'Пушкино', '2014-08-24'),
       (20, 28, 337.69, 'Ногинск', '2020-02-21'),
       (21, 26, 2741.5, 'Клин', '2019-02-12'),
       (22, 21, 1101.11, 'Пушкино', '2010-10-29'),
       (23, 22, 0.81, 'Серпухов', '1971-06-11'),
       (24, 23, 37.3, 'Лотошино', '1996-03-25'),
       (25, 24, 261.22, 'Ногинск', '1972-12-24'),
       (26, 22, 6.90, 'Красногорск', '2002-05-07'),
       (27, 18, 42.64, 'Шатура', '2010-03-11'),
       (28, 17, 2941.60, 'Можайск', '1980-06-09'),
       (28, 27, 39.07, 'Воскресенск', '2021-07-15'),
       (28, 11, 5.88, 'Чехов', '1991-01-27');
