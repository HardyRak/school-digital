-- Active: 1719525958588@@127.0.0.1@5432@andranovelona
CREATE DATABASE andranovelona
\c andranovelona

INSERT INTO niveau(nom) VALUES('6ème');
INSERT INTO niveau(nom) VALUES('5ème');
INSERT INTO niveau(nom) VALUES('4ème');
INSERT INTO niveau(nom) VALUES('3ème');


-------------------------SECTION----------------------

INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('6ème1',1,1);
INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('6ème2',1,1);
INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('6ème3',1,1);

INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('5ème1',2,1);
INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('5ème2',2,1);

INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('4ème1',3,1);
INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('4ème2',3,1);

INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('3ème1',4,1);
INSERT INTO section(section,id_niveau,id_annee_scolaire) VALUES ('3ème2',4,1);