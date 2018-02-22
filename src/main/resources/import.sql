insert into users values (1,'user1', 'user1')
insert into users values (2,'user2', 'user2')
insert into users values (3,'user3', 'user3')
insert into users values (4,'user4', 'user4')

insert into tickets values(1,'System crashes after click on button...','new','System crash',4,1)
insert into tickets values(2,'Bugs bugs bugs','new','Another bug',2,3)
insert into tickets values(3,'All links not working, whitelabel error displayed','reject','Links not working',1,2)
insert into tickets values(4,'Exception on endpoint /username','done','Exception',2,4)

insert into comments values (1,'OK i will solve this problem',curtime(),1,4)
insert into comments values (2,'I give you hint if You want',curtime(),1,2)
insert into comments values (3,'Everyday that same story',curtime(),2,3)
insert into comments values (4,'That my task',curtime(),4,2)