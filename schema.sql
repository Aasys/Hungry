drop table Users;
drop table Payments;
drop table Cuisines;
drop table Tastes;
drop table Restaurants;
drop table RestaurantCuisine;
drop table Menus;
drop table MenusTaste;
drop table Ratings;
drop table Invoices;

create table Users (
  userid integer primary key,
  name varchar(128) not null,
  address varchar(128),
  phonenum varchar(16),
  email varchar(128) not null,
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

