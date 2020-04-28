create table MinesweeperGame (
	username varchar(50),
 	wins int,
 	losses int,
 	primary key(username),
);

create table UserDatabase (
	primary key(username, password),
	username varchar(50),
 	password varchar(50)
);

