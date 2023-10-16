/* - Выдайте всю информацию о спортсменах из таблицы sportsman. */
select *
from sportsman;

/* - Выдайте наименование и мировые результаты по всем соревнованиям. */
select competition_name, world_record
from competition;

/* - Выберите имена всех спортсменов, которые родились в 1990 году. */
select sportsman_name
from sportsman
where year_of_birth = 1990;

/* - Выберите наименование и мировые результаты по всем соревнованиям, установленные 12-05-2010 или 15-05-2010. */
select competition_name, world_record
from competition
where set_date = '2010-05-12'
   or set_date = '2010-05-15';

/* - Выберите дату проведения всех соревнований, которые проводились в Москве и полученные на них результаты равны 10 секунд. */
select hold_date
from result
where city = 'Москва'
  and result.result = 10;

/* - Выберите имена всех спортсменов, у которых персональный рекорд не равен 25 с. */
select sportsman_name
from sportsman
where personal_record != 25;

/* - Выберите названия всех соревнований, у которых мировой рекорд равен 15 с и дата установки рекорда не равна 12-02-2015. */
select competition_name
from competition
where world_record = 15
  and set_date != '2015-02-15';

/* - Выберите города проведения соревнований, где результаты принадлежат множеству {13, 25, 17, 9}. */
select city
from result
where result.result in (13, 25, 17, 9);

/* - Выберите имена всех спортсменов, у которых год рождения 2000 и разряд не принадлежит множеству {3, 7, 9}. */
select sportsman_name
from sportsman
where year_of_birth = 2000
  and rank not in ('3', '7', '9');

/* - Выберите дату проведения всех соревнований, у которых город проведения начинается с буквы "М". */
select hold_date
from result
where city ilike 'м%';

/* - Выберите имена всех спортсменов, у которых имена начинаются с буквы "М" и год рождения не заканчивается на "6". */
select sportsman_name, year_of_birth
from sportsman
where sportsman_name ilike 'м%'
  and year_of_birth % 10 != 6;

/* - Выберите наименования всех соревнований, у которых в названии есть слово "международные". */
select competition_name
from competition
where competition_name ilike '%международные%';

/* - Выберите годы рождения всех спортсменов без повторений. */
select distinct year_of_birth
from sportsman;

/* - Найдите количество результатов, полученных 12-05-2014. */
select count(*)
from result
where hold_date = '2014-05-12'
group by hold_date;

/* - Вычислите максимальный результат, полученный в Москве. */
select max(result.result)
from result
where city = 'Москва';

/* - Вычислите минимальный год рождения спортсменов, которые имеют 1 разряд. */
select min(year_of_birth)
from sportsman
where rank = '1';

/* - Определите имена спортсменов, у которых личные рекорды совпадают с результатами, установленными 12-04-2014. */
select sportsman_name
from sportsman
         join result on result.result = sportsman.personal_record
where result.hold_date = '2014-04-12';

/* - Выведите наименования соревнований, у которых дата установления мирового рекорда совпадает с датой проведения соревнований в Москве 20-04-2015. */
select competition_name
from competition
         join result on competition.set_date = result.hold_date
where result.hold_date = '2015-04-20'
  and result.city = 'Москва';

/* - Вычислите средний результат каждого из спортсменов. */
select sportsman_name, avg(result.result)
from result
         join sportsman on result.sportsman_id = sportsman.sportsman_id
group by sportsman_name;

/* - Выведите годы рождения спортсменов, у которых результат, показанный в Москве выше среднего по всем спортсменам. */
select year_of_birth
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id and result.city = 'Москва'
where result.result > (select avg(result.result) from result where city = 'Москва');

/* - Выведите имена всех спортсменов, у которых год рождения больше, чем год установления мирового рекорда, равного 12 с. */
select sportsman_name
from sportsman
where year_of_birth > (select extract(year from set_date)
                       from competition
                       where world_record = 12
                       limit 1);

/* - Выведите список спортсменов в виде 'Спортсмен ' ['имя спортсмена'] 'показал результат' ['результат'] 'в городе' ['город'] */
select 'Спортсмен ' || sportsman_name || ' показал результат ' || result.result || ' в городе ' || result.city
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id;

/* - Выведите имена всех спортсменов, у которых разряд ниже среднего разряда всех спортсменов, родившихся в 2000 году. */
/* Не сработает, так как rank - строка, а не число. Если rank будет числом, то сработает.*/
select sportsman_name
from sportsman
where rank < (select avg(rank) from sportsman where year_of_birth = 2000);

/* - Выведите данные о спортсменах, у которых персональный рекорд совпадает с мировым. */
select distinct sportsman_id, sportsman_name, rank, year_of_birth, personal_record, country
from sportsman
         join competition on personal_record = world_record;

/* - Определите количество участников с фамилией Иванов, которые участвовали в соревнованиях с названием, содержащим слово 'Региональные'. */
select count(*)
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
         join competition on result.competition_id = competition.competition_id
where sportsman_name ilike '%Иванов%'
  and competition_name ilike '%Региональные%';

/* - Выведите города, в которых были установлены мировые рекорды. */
select city
from result
         join competition on result.competition_id = competition.competition_id
where result.result = competition.world_record;

/* - Найдите минимальный разряд спортсменов, которые установили мировой рекорд. */
select min(rank)
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
         join competition on result.competition_id = competition.competition_id
where personal_record = world_record;

/* - Выведите названия соревнований, на которых было установлено максимальное количество мировых рекордов. */
select competition_name
from competition
         JOIN result ON competition.competition_id = result.competition_id
where result = world_record
group by competition.competition_id
order by COUNT(*) desc
limit 1;

/* - Определите, спортсмены какой страны участвовали в соревнованиях больше всего. */
select country
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
group by country
order by count(country) desc
limit 1;

/* - Измените разряд на 1 тех спортсменов, у которых личный рекорд совпадает с мировым. */
update sportsman
set rank = 1
where sportsman_id in (select sportsman_id
                       from sportsman
                                join competition on personal_record = world_record);

/* - Вычислите возраст спортсменов, которые участвовали в соревнованиях в Москве. */
select sportsman_name, (extract(year from hold_date) - year_of_birth)
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
where city = 'Москва';

/* - Измените дату проведения всех соревнований, проходящих в Москве на 4 дня вперед. */
update result
set hold_date = hold_date + interval '4' day
where city = 'Москва';

/* - Измените страну у спортсменов, у которых разряд равен 1 или 2, с Италии на Россию. */
update sportsman
set country = 'Россия'
where (rank = '1' or rank = '2')
  and country = 'Италия';

/* - Измените название соревнований с 'Бег' на 'Бег с препятствиями' */
update competition
set competition_name = 'Бег с препятствиями'
where competition_name = 'Бег';

/* - Увеличьте мировой результат на 2 с для соревнований ранее 20-03-2005. */
update competition
set world_record = world_record + 2
where set_date < '2005-03-20';

/* - Уменьшите результаты на 2 с соревнований, которые проводились 20-05-2012 и показанный результат не менее 45 с. */
update result
set result = result - 2
where hold_date = '2012-05-20'
  and result >= 45;

/* - Удалите все результаты соревнований в Москве, участники которых родились не позже 1980 г. */
delete
from result
where city = 'Москва'
  and result.sportsman_id in (select sportsman_id
                              from sportsman
                              where year_of_birth <= 1980);

/* - Удалите все соревнования, у которых результат равен 20 с. */
/* Сущности result, ссылающиеся на competition удалятся автоматически, так как ON UPDATE CASCADE ON DELETE CASCADE */
delete
from competition
where competition_id in (select competition_id
                         from result
                         where result.result = 20);

/* - Удалите все результаты спортсменов, которые родились в 2001 году. */
delete
from result
where sportsman_id in (select sportsman_id
                       from sportsman
                       where year_of_birth = 2001);
