drop table IF EXISTS Users CASCADE;
drop table IF EXISTS Payments CASCADE;
drop table IF EXISTS Cuisines CASCADE;
drop table IF EXISTS Tastes CASCADE;
drop table IF EXISTS Restaurants CASCADE;
drop table IF EXISTS RestaurantCuisine CASCADE;
drop table IF EXISTS Menus CASCADE;
drop table IF EXISTS MenusTaste CASCADE;
drop table IF EXISTS Ratings CASCADE;
drop table IF EXISTS Invoices CASCADE;

create table Users (
  userid integer primary key,
  name varchar(128) not null,
  address varchar(128),
  phonenum varchar(16),
  email varchar(128) not null unique,
  passwd varchar(16) not null
);

create table Payments (
  cardnum varchar(16) primary key,
  expdate date,
  cvcnum integer,
  zipcode integer,
  userid integer not null,
  foreign key (userid) references Users (userid)  
);

create table Cuisines (
  name varchar(128) primary key
);

create table Tastes (
  flavor varchar(128) primary key
);

create table Restaurants ( 
  rid integer primary key,
  name varchar(128) not null,
  address varchar(128) not null,
  phonenumber varchar(16) not null
);

create table RestaurantCuisine ( 
   rid integer,
   name varchar(128),
   primary key (rid, name),
   foreign key (rid) references Restaurants  (rid),
   foreign key (name) references Cuisines  (name)
);

create table Menus ( 
  menuid integer primary key,
  name varchar(128),
  description varchar(128),
  price bigint,
  rid integer not null,
  foreign key (rid) references Restaurants (rid)
);

create table MenusTaste ( 
   menuid integer,
   flavor varchar(128),
   primary key (menuid, flavor),
   foreign key (menuid) references Menus (menuid),
   foreign key (flavor) references Tastes (flavor)
);


create table Ratings ( 
  raid integer primary key,
  stars integer,
  comments varchar(256),
  comdate date,
  userid integer,
  rid integer,
  foreign key (userid) references Users (userid),
  foreign key (rid) references Restaurants (rid)
);


create table Invoices ( 
  tid integer primary key,
  invdate date,
  decription varchar(128),
  amount integer not null,
  cardnum varchar(16),
  userid integer,
  rid integer,
  foreign key (cardnum) references Payments (cardnum),
  foreign key (userid) references Users (userid),
  foreign key (rid) references Restaurants (rid)
);

--UsersData--
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (1, 'Drexel Joe', '1 Drexel Way ', '1234567890', 'joedrexel@drexel.net', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (2, 'Jean Luc Pickard', '1 Outer Space', '9672146831', 'jp@trek.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (3, 'John Wayne', '12 Old West Lane', '875698700', 'jw@wildwest.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (4, 'Sheldon Cooper', '314 Science Blvd', '314159265', 'science@physics.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (5, 'Will Riker', '299 Trek Lane', '756321412', 'riker2@trek.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (6, 'Octavia Muss', '17 Expanse Way', '6143567891', 'octavia@expanse.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (7, 'Juliette Mao', '79 Aies', '1234567891', 'mao@lost.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (8, 'Amos Guy', '1 Ship Way', '4567891256', 'amos@expanse.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (9, 'Angus Macgyver', '24 Creative Lane', '1455697845', 'mac@innovations.com', 'default');
INSERT INTO Users (userid, name, address, phonenum, email, passwd) VALUES (10, 'Charles Babbage', '2 Mechanical Rd', '1235689753', 'babbage@difeng.com', 'default');

--Payments--
INSERT INTO Payments(cardnum, expdate, cvcnum, zipcode, userid) VALUES ('12345678923', '2017-06-30', 1234, 55344, 1);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('51684651561', '2017-02-25', 546, 44101, 2);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('54415641564', '2020-02-21', 4567, 12356, 3);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('51564651454', '2017-09-22', 5598, 45789, 4);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('51545153141', '2020-09-29', 4456, 45686, 5);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('35216515156', '2019-11-26', 45789, 11236, 6);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('21531565113', '2017-02-16', 123, 65461, 7);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('45686451846', '2017-02-09', 789, 54565, 8);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('45789158785', '2020-02-07', 654, 45893, 9);
INSERT INTO Payments (cardnum, expdate, cvcnum, zipcode, userid) VALUES ('75272525225', '2017-10-12', 4456, 15385, 10);

--Cuisines
insert into Cuisines (name) values ('Cafeteria');
insert into Cuisines (name) values ('BBQ');
insert into Cuisines (name) values ('Fast');
insert into Cuisines (name) values ('Mexican');
insert into Cuisines (name) values ('Chinese');
insert into Cuisines (name) values ('Italian');
insert into Cuisines (name) values ('Vietnamese');
insert into Cuisines (name) values ('Thai');
insert into Cuisines (name) values ('American');
insert into Cuisines (name) values ('Indian');

--Tastes
insert into Tastes (flavor) values ('Sweet');
insert into Tastes (flavor) values ('Sour');
insert into Tastes (flavor) values ('Hot');
insert into Tastes (flavor) values ('Cold');
insert into Tastes (flavor) values ('Spicey');
insert into Tastes (flavor) values ('Savory');
insert into Tastes (flavor) values ('Bitter');
insert into Tastes (flavor) values ('Umami');
insert into Tastes (flavor) values ('Salty');
insert into Tastes (flavor) values ('Mild');
insert into Tastes (flavor) values ('Medium');

--Restaurants Data--
insert into Restaurants (rid, name, address,phonenumber) values (1,'Handschumacher Dining Center','3201 Chestnut St Philadelphia, PA 19104 US','2155713086');
insert into Restaurants (rid, name, address,phonenumber) values (2,'Chipotle','3400 Lancaster Ave, Philadelphia, PA 19104','2153872025');
insert into Restaurants (rid, name, address,phonenumber) values (3,'Jerrys Kitchen','3225 Arch St, Philadelphia, PA 19104','6104001532');
insert into Restaurants (rid, name, address,phonenumber) values (4,'Shake Shack','3200 Chestnut St, Philadelphia, PA 19104 US','2673383464');
insert into Restaurants (rid, name, address,phonenumber) values (5,'Baby Blues BBQ','3402 Sansom St, Philadelphia, PA 19104','2152224444');
insert into Restaurants (rid, name, address,phonenumber) values (6,'Herban Quality Eats','3601 Market St #3, Philadelphia, PA 19104','2153865000');
insert into Restaurants (rid, name, address,phonenumber) values (7,'Chick-fil-A','203 N 34th St, Philadelphia, PA 19104','2158956246');
insert into Restaurants (rid, name, address,phonenumber) values (8,'Han Dynasty','3711 Market St, Philadelphia, PA 19104','2152223711');
insert into Restaurants (rid, name, address,phonenumber) values (9,'Sang Kee Noodle House','3549 Chestnut St, Philadelphia, PA 19104','2153878808');
insert into Restaurants (rid, name, address,phonenumber) values (10,'Pizza Hut','33 S 40th St, Philadelphia, PA 19104','2152222110');
insert into Restaurants (rid, name, address,phonenumber) values (11,'McDonalds','3935 Walnut St, Philadelphia, PA 19104','2152226266');
insert into Restaurants (rid, name, address,phonenumber) values (12,'Ralphs Italian','760 S 9th St, Philadelphia, PA 19147','2156276011');
insert into Restaurants (rid, name, address,phonenumber) values (13,'Bistrot La Minette','623 S 6th St, Philadelphia, PA 19147','2159258000');
insert into Restaurants (rid, name, address,phonenumber) values (14,'Indie Blue','205 S 13th St, Philadelphia, PA 19107','2159258000');
insert into Restaurants (rid, name, address,phonenumber) values (15,'City Tavern','138 S 2nd St, Philadelphia, PA 19106','2154131443');

--Menu Data--
insert into Menus (menuid, name, description, price,rid) values (1,'Breakfast','Cheese Toast Eggs',10,1);
insert into Menus (menuid, name, description, price,rid) values (2,'Bowl','Steak Bowl',6,2);
insert into Menus (menuid, name, description, price,rid) values (3,'BLT','BLT Sandwich',5,3);
insert into Menus (menuid, name, description, price,rid) values (4,'Shake','Vanilla Shake ',2,4);
insert into Menus (menuid, name, description, price,rid) values (5,'Ribs','BBQ Ribs',8,5);
insert into Menus (menuid, name, description, price,rid) values (6,'Healthy Salad','Salads',10,6);
insert into Menus (menuid, name, description, price,rid) values (7,'Handy Chicken','Chicken Fingers',3,7);
insert into Menus (menuid, name, description, price,rid) values (8,'Fried RIce','Fried Rice',4,8);
insert into Menus (menuid, name, description, price,rid) values (9,'Sang Kee Noodle House','Ramen Bowl',7,9);
insert into Menus (menuid, name, description, price,rid) values (10,'Pizza','Cheese Pizza',10,10);
insert into Menus (menuid, name, description, price,rid) values (11,'Value Menu','Big Mac',6,11);
insert into Menus (menuid, name, description, price,rid) values (12,'Pasta','Spaghetti with Meathballs',6,12);
insert into Menus (menuid, name, description, price,rid) values (13,'Bread','Garlic Bread',6,13);
insert into Menus (menuid, name, description, price,rid) values (14,'Rolls','Samosa Rolls',6,14);
insert into Menus (menuid, name, description, price,rid) values (15,'Burger','Hamburger with Fries',6,15);


--Ratings Data--
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (1,3,'OK Food','17-Feb-2017',1,1);
insert into Ratings (raid, stars, comments,comdate,userid,rid)  values (2,5,'Great Burrito','17-Feb-2017',1,5);
insert into Ratings (raid, stars, comments,comdate,userid,rid)  values (3,4,'Selection of Deli Food','17-Feb-2017',3,3);
insert into Ratings (raid, stars, comments,comdate,userid,rid)  values (4,3,'Overall Ok Shakes','17-Feb-2017',4,3);
insert into Ratings (raid, stars, comments,comdate,userid,rid)  values (5,5,'Greate BBQ Selection ','17-Feb-2017',6,1);
insert into Ratings (raid, stars, comments,comdate,userid,rid)  values (6,5,'Quality Food','17-Feb-2017',5,4);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (7,3,'Avera------------------------------------------ge CHicken ','17-Feb-2017',7,4);
insert into Ratings (raid, stars, comments,comdate,userid,rid)  values (8,4,'Overall Not Bad','17-Feb-2017',8,3);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (9,3,'Noodles Are Average','17-Feb-2017',6,3);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (10,2,'Fair Pizza','17-Feb-2017',7,2);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (11,1,'Fast Food','17-Feb-2017',2,2);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (12,3,'Overall OK Italian Food','17-Feb-2017',3,2);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (13,4,'Great French Food','17-Feb-2017',4,4);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (14,5,'Best Indian Food','17-Feb-2017',7,2);
insert into Ratings (raid, stars, comments,comdate,userid,rid) values (15,4,'Above Average Hamburger','17-Feb-2017',2,4);


--Invoices Data--
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (1, '2017-02-17', 'Test Invoice', 25, '12345678923', 1, 1);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (2, '2017-01-17', 'Test Invoice', 25, '51684651561', 2, 4);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (3, '2017-02-01', 'Test Invoice', 25, '54415641564', 3, 5);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (4, '2017-02-25', 'Test Invoice', 25, '51564651454', 4, 6);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (5, '2017-01-17', 'Test Invoice', 25, '51545153141', 5, 7);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (6, '2017-02-17', 'Test Invoice', 25, '35216515156', 6, 8);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (7, '2017-02-17', 'Test Invoice', 25, '21531565113', 7, 9);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (8, '2017-02-17', 'Test Invoice', 25, '45686451846', 8, 10);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (9, '2017-02-17', 'Test Invoice', 25, '45789158785', 9, 11);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (10, '2017-02-17', 'Test Invoice', 25, '75272525225', 10, 12);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (11, '2017-02-17', 'Test Invoice', 25, '12345678923', 1, 3);
INSERT INTO public.invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES (12, '2017-02-17', 'Test Invoice', 25, '12345678923', 1, 2);
