INSERT INTO gift_certificate(id,name,description,price,create_date,last_update_date,duration)
VALUES(1,'testNameFirst','testDescriptionFirst',12,'2000-10-10 12:12:12','2000-10-10 13:13:13',10);

INSERT INTO gift_certificate(id,name,description,price,create_date,last_update_date,duration)
VALUES(2,'testNameSecond','testDescriptionSecond',24,'2001-10-10 12:12:12','2001-10-10 13:13:13',11);

insert into tag(id,name) VALUES (1,'testTagFirst');
insert into tag(id,name) VALUES (2,'testTagSecond');
insert into tag(id,name) VALUES (3,'testTagThird');

insert into certificates_tags(cert_id, tag_id) VALUES(1,1);
insert into certificates_tags(cert_id, tag_id) VALUES(1,2);
insert into certificates_tags(cert_id, tag_id) VALUES(2,2);
insert into certificates_tags(cert_id, tag_id) VALUES(2,3);