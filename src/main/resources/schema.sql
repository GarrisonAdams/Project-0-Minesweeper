create table MinesweeperGame (
	primary key(username),
	username varchar(50),
 	wins int,
 	losses int
);

create table UserAuthentication (
	primary key(username, password),
	username varchar(50),
 	password varchar(50)
);

