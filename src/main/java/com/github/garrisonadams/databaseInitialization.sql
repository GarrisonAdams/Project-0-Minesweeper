drop table if exists MinesweeperGame;

create table MinesweeperGame (
	primary key(username),
	username varchar(50),
 	wins int,
 	losses int
);

INSERT INTO MineSweeperGame (username, wins, losses)
VALUES ('Garrison', 2, 19); 


