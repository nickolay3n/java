--CREATE DATABASE "dbsostav"
--  ENCODING 'UTF8'
--  LC_COLLATE 'en_US.utf8'
--  LC_CTYPE 'en_US.utf8';


--DROP DATABASE IF EXISTS "dbsostav"; так делать нельзя - нельзя удалить базу данных к которой уже присоединен
-- CREATE DATABASE "dbsostav";



-- DROP DATABASE "dbsostav";


--
-- Table structure for table 'Состав'
--
SET client_encoding = 'UTF8';

DROP TABLE IF EXISTS "состав";

CREATE TABLE "состав" (
  "код_сотрудника" SERIAL NOT NULL, 
  "фамилия" VARCHAR(50), 
  "имя" VARCHAR(50), 
  "отчество" VARCHAR(50), 
  "дата_рождения" TIMESTAMP, 
  "личный_номер" VARCHAR(50), 
  "печать" VARCHAR(50), 
  "образование" VARCHAR(150), 
  "адрес" VARCHAR(150), 
  "телефон" VARCHAR(50), 
  "мобильный" VARCHAR(50), 
  "семейное_положение" VARCHAR(50), 
  "стаж" TIMESTAMP, 
  "санбилет" VARCHAR(50), 
  "пропуск" VARCHAR(50), 
  "номер_карточки_допуска" VARCHAR(50), 
  PRIMARY KEY ("код_сотрудника")
);


INSERT INTO "состав" ("код_сотрудника", "фамилия", "имя", "отчество", "дата_рождения", "личный_номер", "печать", "образование", "адрес", "телефон", "мобильный", "семейное_положение", "стаж", "санбилет", "пропуск", "номер_карточки_допуска") VALUES (6, E'Фамилия1', E'Олег', E'Владимирович', '1999-01-01 00:00:00', E'Е-111111', E'508', E'Высшее, ПТУ ремонт ВТ, Московский институт , 1999 г.', E'г.Москва, 7777', E'8-111-111-40-56', E'8-111-111-97-49', E'холост', '1999-01-01 00:00:00', E'11 № 2, п № 1', E'П№ 4', E'№60, 1 лс от 30.35г');
INSERT INTO "состав" ("код_сотрудника", "фамилия", "имя", "отчество", "дата_рождения", "личный_номер", "печать", "образование", "адрес", "телефон", "мобильный", "семейное_положение", "стаж", "санбилет", "пропуск", "номер_карточки_допуска") VALUES (8, E'Фамилия1', E'Олег', E'Алекс', '1999-01-06 00:00:00', E'Е-111111', E'7283', E'Высшее, МИРЭА, 1995 г.', E'г.Москва, Ново', E'8-111-111-95-43', E'8-111-111-62-28', E'холост', '1999-05-31 00:00:00', E'11 № 0, п-ка № 1', E'П № 0', E'№6, 1-лс от 01.01.99г');
INSERT INTO "состав" ("код_сотрудника", "фамилия", "имя", "отчество", "дата_рождения", "личный_номер", "печать", "образование", "адрес", "телефон", "мобильный", "семейное_положение", "стаж", "санбилет", "пропуск", "номер_карточки_допуска") VALUES (10, E'Фамилия1', E'Олег', E'Викторович', '1999-08-10 00:00:00', E'Е-289', E'нет', E'Высшее, Академия  России, 2004 г.', E'г.Москва, улвпозвонить)', NULL, E'8-91545', E'холост', '1999-09-09 00:00:00', E'51 № 9, п-ка № 5', E'ПП  № 0', E'№6,1-лс от 01.01.99г.');


--SELECT setval('"код_сотрудника_seq"', MAX("код_сотрудника")) FROM "состав";