/* - ������� ��� ���������� � ����������� �� ������� sportsman. */
select *
from sportsman;

/* - ������� ������������ � ������� ���������� �� ���� �������������. */
select competition_name, world_record
from competition;

/* - �������� ����� ���� �����������, ������� �������� � 1990 ����. */
select sportsman_name
from sportsman
where year_of_birth = 1990;

/* - �������� ������������ � ������� ���������� �� ���� �������������, ������������� 12-05-2010 ��� 15-05-2010. */
select competition_name, world_record
from competition
where set_date = '2010-05-12'
   or set_date = '2010-05-15';

/* - �������� ���� ���������� ���� ������������, ������� ����������� � ������ � ���������� �� ��� ���������� ����� 10 ������. */
select hold_date
from result
where city = '������'
  and result.result = 10;

/* - �������� ����� ���� �����������, � ������� ������������ ������ �� ����� 25 �. */
select sportsman_name
from sportsman
where personal_record != 25;

/* - �������� �������� ���� ������������, � ������� ������� ������ ����� 15 � � ���� ��������� ������� �� ����� 12-02-2015. */
select competition_name
from competition
where world_record = 15
  and set_date != '2015-02-15';

/* - �������� ������ ���������� ������������, ��� ���������� ����������� ��������� {13, 25, 17, 9}. */
select city
from result
where result.result in (13, 25, 17, 9);

/* - �������� ����� ���� �����������, � ������� ��� �������� 2000 � ������ �� ����������� ��������� {3, 7, 9}. */
select sportsman_name
from sportsman
where year_of_birth = 2000
  and rank not in ('3', '7', '9');

/* - �������� ���� ���������� ���� ������������, � ������� ����� ���������� ���������� � ����� "�". */
select hold_date
from result
where city ilike '�%';

/* - �������� ����� ���� �����������, � ������� ����� ���������� � ����� "�" � ��� �������� �� ������������� �� "6". */
select sportsman_name, year_of_birth
from sportsman
where sportsman_name ilike '�%'
  and year_of_birth % 10 != 6;

/* - �������� ������������ ���� ������������, � ������� � �������� ���� ����� "�������������". */
select competition_name
from competition
where competition_name ilike '%�������������%';

/* - �������� ���� �������� ���� ����������� ��� ����������. */
select distinct year_of_birth
from sportsman;

/* - ������� ���������� �����������, ���������� 12-05-2014. */
select count(*)
from result
where hold_date = '2014-05-12'
group by hold_date;

/* - ��������� ������������ ���������, ���������� � ������. */
select max(result.result)
from result
where city = '������';

/* - ��������� ����������� ��� �������� �����������, ������� ����� 1 ������. */
select min(year_of_birth)
from sportsman
where rank = '1';

/* - ���������� ����� �����������, � ������� ������ ������� ��������� � ������������, �������������� 12-04-2014. */
select sportsman_name
from sportsman
         join result on result.result = sportsman.personal_record
where result.hold_date = '2014-04-12';

/* - �������� ������������ ������������, � ������� ���� ������������ �������� ������� ��������� � ����� ���������� ������������ � ������ 20-04-2015. */
select competition_name
from competition
         join result on competition.set_date = result.hold_date
where result.hold_date = '2015-04-20'
  and result.city = '������';

/* - ��������� ������� ��������� ������� �� �����������. */
select sportsman_name, avg(result.result)
from result
         join sportsman on result.sportsman_id = sportsman.sportsman_id
group by sportsman_name;

/* - �������� ���� �������� �����������, � ������� ���������, ���������� � ������ ���� �������� �� ���� �����������. */
select year_of_birth
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id and result.city = '������'
where result.result > (select avg(result.result) from result where city = '������');

/* - �������� ����� ���� �����������, � ������� ��� �������� ������, ��� ��� ������������ �������� �������, ������� 12 �. */
select sportsman_name
from sportsman
where year_of_birth > (select extract(year from set_date)
                       from competition
                       where world_record = 12
                       limit 1);

/* - �������� ������ ����������� � ���� '��������� ' ['��� ����������'] '������� ���������' ['���������'] '� ������' ['�����'] */
select '��������� ' || sportsman_name || ' ������� ��������� ' || result.result || ' � ������ ' || result.city
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id;

/* - �������� ����� ���� �����������, � ������� ������ ���� �������� ������� ���� �����������, ���������� � 2000 ����. */
/* �� ���������, ��� ��� rank - ������, � �� �����. ���� rank ����� ������, �� ���������.*/
select sportsman_name
from sportsman
where rank < (select avg(rank) from sportsman where year_of_birth = 2000);

/* - �������� ������ � �����������, � ������� ������������ ������ ��������� � �������. */
select distinct sportsman_id, sportsman_name, rank, year_of_birth, personal_record, country
from sportsman
         join competition on personal_record = world_record;

/* - ���������� ���������� ���������� � �������� ������, ������� ����������� � ������������� � ���������, ���������� ����� '������������'. */
select count(*)
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
         join competition on result.competition_id = competition.competition_id
where sportsman_name ilike '%������%'
  and competition_name ilike '%������������%';

/* - �������� ������, � ������� ���� ����������� ������� �������. */
select city
from result
         join competition on result.competition_id = competition.competition_id
where result.result = competition.world_record;

/* - ������� ����������� ������ �����������, ������� ���������� ������� ������. */
select min(rank)
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
         join competition on result.competition_id = competition.competition_id
where personal_record = world_record;

/* - �������� �������� ������������, �� ������� ���� ����������� ������������ ���������� ������� ��������. */
select competition_name
from competition
         JOIN result ON competition.competition_id = result.competition_id
where result = world_record
group by competition.competition_id
order by COUNT(*) desc
limit 1;

/* - ����������, ���������� ����� ������ ����������� � ������������� ������ �����. */
select country
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
group by country
order by count(country) desc
limit 1;

/* - �������� ������ �� 1 ��� �����������, � ������� ������ ������ ��������� � �������. */
update sportsman
set rank = 1
where sportsman_id in (select sportsman_id
                       from sportsman
                                join competition on personal_record = world_record);

/* - ��������� ������� �����������, ������� ����������� � ������������� � ������. */
select sportsman_name, (extract(year from hold_date) - year_of_birth)
from sportsman
         join result on sportsman.sportsman_id = result.sportsman_id
where city = '������';

/* - �������� ���� ���������� ���� ������������, ���������� � ������ �� 4 ��� ������. */
update result
set hold_date = hold_date + interval '4' day
where city = '������';

/* - �������� ������ � �����������, � ������� ������ ����� 1 ��� 2, � ������ �� ������. */
update sportsman
set country = '������'
where (rank = '1' or rank = '2')
  and country = '������';

/* - �������� �������� ������������ � '���' �� '��� � �������������' */
update competition
set competition_name = '��� � �������������'
where competition_name = '���';

/* - ��������� ������� ��������� �� 2 � ��� ������������ ����� 20-03-2005. */
update competition
set world_record = world_record + 2
where set_date < '2005-03-20';

/* - ��������� ���������� �� 2 � ������������, ������� ����������� 20-05-2012 � ���������� ��������� �� ����� 45 �. */
update result
set result = result - 2
where hold_date = '2012-05-20'
  and result >= 45;

/* - ������� ��� ���������� ������������ � ������, ��������� ������� �������� �� ����� 1980 �. */
delete
from result
where city = '������'
  and result.sportsman_id in (select sportsman_id
                              from sportsman
                              where year_of_birth <= 1980);

/* - ������� ��� ������������, � ������� ��������� ����� 20 �. */
/* �������� result, ����������� �� competition �������� �������������, ��� ��� ON UPDATE CASCADE ON DELETE CASCADE */
delete
from competition
where competition_id in (select competition_id
                         from result
                         where result.result = 20);

/* - ������� ��� ���������� �����������, ������� �������� � 2001 ����. */
delete
from result
where sportsman_id in (select sportsman_id
                       from sportsman
                       where year_of_birth = 2001);
