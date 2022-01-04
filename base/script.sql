CREATE TABLE utilisateur(
    uti_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    pseudo VARCHAR(20) NOT NULL,
    avatar VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    mdp VARCHAR(200) NOT NULL,
    role VARCHAR(15) NOT NULL
);

CREATE TABLE valide(
    uti_id INT NOT NULL,
    defi_id INT NOT NULL
);

CREATE TABLE defi (
    defi_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    points INT NOT NULl,
    categorie INT NOT NULL,
    nom VARCHAR(30) NOT NULL,
    sous_titre VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    validations INT NOT NULL,
    essais INT NOT NULL,
    mdp VARCHAR(100) NOT NULL
);

CREATE TABLE message (
    mes_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    categorie VARCHAR(30) NOT NULL,
    expediteur INT NOT NULL,
    destinaire INT NOT NULL,
    contenu VARCHAR(3000) NOT NULL,
    reponse INT NOT NULL
);

CREATE TABLE appartientA (
    uti_id  INT NOT NULL,
    equi_id INT NOT NULL
);

CREATE TABLE equipe(
    equi_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    chef_id INT NOT NULL,
    nom VARCHAR(30) NOT NULL,
    avatar VARCHAR(50) NOT NULL
);

CREATE TABLE scoreCTF(
     ctf_id INT NOT NULL,
     equi_id INT NOT NULL,
     score INT NOT NULL
);

CREATE TABLE ctf(
    ctf_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    nom VARCHAR(50) NOT NULL,
    image VARCHAR(50) NOT NULL,
    dateDebut DATE NOT NULL,
    heureDebut TIME NOT NULL,
    dateFin DATE NOT NULL,
    heureFin TIME NOT NULL,
    orga_id INT NOT NULL
);

CREATE TABLE commentaire(
    com_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    ctf_id INT NOT NULL,
    uti_id VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    message VARCHAR(500) NOT NULL
);

INSERT INTO defi VALUES(DEFAULT, 1, 1, 'Hidden HTML', 'Un petit peu de HTML bien caché', 'Ce formulaire est désactivé et ne peut pas être utilisé. À vous de trouver le moyen de l’utiliser tout de même.', 32, 100, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 5, 2, 'FTP - Authentification', 'Analyse de capture réseau', 'Un échange authentifié de fichier réalisé grâce au protocole FTP. Retrouvez le mot de passe utilisé par l’utilisateur.', 29, 200, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 10, 2, 'ETHERNET - trame', 'Analyse de trame', 'Retrouvez les données normalement confidentielles contenues dans cette trame.', 25, 120, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 15, 2, 'Bluetooth - Fichier inconnu', 'Google est ton ami', 'Votre ami travaillant à l’ANSSI a récupéré un fichier illisible dans l’ordi d’un hacker. Tout ce qu’il sait est que cela provient d’un échange entre un ordinateur et un téléphone. A vous d’en apprendre le plus possible sur ce téléphone. La réponse est le hash SHA1 de la concaténation de l’adresse MAC (en majuscules) et du nom du téléphone.', 32, 100, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 5, 3, 'Encodage - ASCII', 'Décoder la chaîne.', 'Décoder la chaîne.', 52, 200, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 5, 3, 'Hash - Message Digest 5', 'Hash - Message Digest 5', 'Hash - Message Digest 5', 60, 300, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 5, 4, 'Gunnm', 'Une image pour commencer', 'Une image pour commencer', 5, 50, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 5, 4, 'Point à la ligne', 'Point à la ligne', '“Rien de trop est un point dont on parle sans cesse et qu’on n’observe point.”', 7, 100, 'Voilà le flag');
INSERT INTO defi VALUES(DEFAULT, 5, 4, 'We need to go deeper', 'Poupées Russes', 'Poupées Russes', 12, 400, 'Voilà le flag');

INSERT INTO utilisateur VALUES (DEFAULT, 'NicolasAragon', 'nicolas.jpg', 'nico.aragon87100@gmail.com', 'IyhR7ksEehYTcBDDd8hFjq7HJHjOw1iLOUEPFm5ix38=','admin');
INSERT INTO utilisateur VALUES (DEFAULT, 'freddy', 'avatar.jpg', 'frederic-9@hotmail.fr', 'qrghe44I1qSXgesETKH/eCuP4yFjslswobQWBF/ECWE=','admin');
INSERT INTO utilisateur VALUES (DEFAULT, 'conchon', 'avatar.jpg', 'c0nch0n@hotmail.fr', 'JXQcetMaHLna9I9DI7okd6jLiZUBS7SHPQIMRlBhOcE=','admin');
INSERT INTO utilisateur VALUES (DEFAULT, 'adham', 'avatar.jpg', 'addddddham@hotmail.fr', 'PFcquxSUrd2YXAujQAq3Zo3u1bGrP7Fmmzupjw5PuCQ=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'thomas', 'avatar.jpg', 'thomas.campredon@hotmail.fr', 'hAGXiuHSkfe8HJnnTBKxl9Ue3bS4dQLke3CGiQqpecQ=','admin');
INSERT INTO utilisateur VALUES (DEFAULT, 'quentin', 'avatar.jpg', 'quentin.delage19260@hotmail.fr', 'M3tUhMWH9n8m9xb6HNiY8p/zRNPSgyBj8b/5fOr3PEY=','organisateur');
INSERT INTO utilisateur VALUES (DEFAULT, 'benoit', 'avatar.jpg', 'paul-ponntt@hotmail.fr', '8btLZJvjKgNCmygTNn1cjhN+r0fn2kgoBH0k6mpfTeo=','organisateur');
INSERT INTO utilisateur VALUES (DEFAULT, 'corentin', 'avatar.jpg', 'corentiiin@hotmail.fr', 'RwonymYJ9E7cKoS6WfqzrgJzX3TjYYk9hz5QKewzRDA=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'jeanbapt', 'avatar.jpg', 'jeanbaptiiste@hotmail.fr', '57/2PIZV+ZctZj0IQtvTFyc2CEbVxW9zpp2Et+xmKng=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'pierrefb', 'avatar.jpg', 'pfbpfbpfbpfb@hotmail.fr', 'vJ9ho3etO3/6K1CpJWniRfftvs9dSAXUEj7yFM74kfM=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'mmaria', 'avatar.jpg', 'mmariaia@hotmail.fr', 'UngB733VpvWOfI6ESeC+qZzwUCx8AD3JdduMuB6FEHE=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'onete', 'avatar.jpg', 'cristina-onete99@hotmail.fr', '+me82Vii6Hc8dL9pKHgRNhYKbeOL0+DX0CeD4KStSbI=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'macron', 'avatar.jpg', 'emmanu-macron7421@hotmail.fr', 'lGJ45ZPM+h5wDQltKca103NZuwNZw+/eboTaBxwiHFA=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'faisal', 'avatar.jpg', 'faisal-841201@hotmail.fr', '9kfD6B8vHYFhHFc83yw3LNnxROplZCvL3XFHH8BeLio=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'iblasquez', 'avatar.jpg', 'isa.blasquez.tdd@hotmail.fr', '3rZ2ODCIcWZ+p4Eisfw/t+IQ93E5WHmFUZMe8+hqi4M=','organisateur');
INSERT INTO utilisateur VALUES (DEFAULT, 'maite', 'avatar.jpg', 'maiteOrdonez-40@hotmail.fr', 'eL9C0nehkPWd9U+84ZLZ1AeqK4xGcnyd7ZkvR+lOGOo=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'hackerman', 'avatar.jpg', 'h4ck3rlald@hotmail.fr', 'NFowSWfNAa+Uoa0a89+cyH4L5F9dxRAkSmbqDPr9LfA=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'mynthos', 'avatar.jpg', 'laMynthosTV69@hotmail.fr', 'Jy88pFyrHJcMWC6MLg+serRSVoxYVNTFQIq1rjj/Z3s=','organisateur');
INSERT INTO utilisateur VALUES (DEFAULT, 'shivana', 'avatar.jpg', 'shivavavava@hotmail.fr', '3lnt3eVmwsZv89eo8RLex4Dl9qYrpOe1k4bJwEWHcNw=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'lombertie', 'avatar.jpg', 'titi-lomb3rti@hotmail.fr', '2tkJiJF3oSVFO/cz5n90wStjESbmSu2GUEWtHEAcXAc=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'aneShrek', 'avatar.jpg', 'lanedeshrekeheh@hotmail.fr', 'Qj/Tg8dDQ5w7cvNbFL2AA7pfp0iiWpQjAhjBGDsdhvI=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'sauveron', 'avatar.jpg', 'damien-sauveron-javacard@hotmail.fr', 'YlzSUafPSY7YF+2VdK/fCc49XS6sfUhFhr10FvOf06w=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'mathilde', 'avatar.jpg', 'mathilde-desmaretssalants.fr', 'JEc0dpdryQrzLln2VVahLnzaXxi8yPDv72AW7gf/DpQ=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'maximeBros', 'avatar.jpg', 'crackmebros@hotmail.fr', '2jxKeRIQjYEibpvd4ePymzrNxED66/1oGtHrYPlNdUc=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'mKlingler', 'avatar.jpg', 'mKl1ngl3r@hotmail.fr', 'yCClqSSp5cF7atbDp+ts10ge4xlji88hMGxxdrDdKLk=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'jeanCastex', 'avatar.jpg', 'j34n-c4st3xxx@hotmail.fr', 'MIQZ1BDNddrbX6WwxWh5oI6Irrh/E6Juzm3WUk3PbM4=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'isaac', 'avatar.jpg', 'is4444cdd@hotmail.fr', 'QCXQl8CwMThOypvn1/YpigLdQy4Y5Qchdcl99oXSK9E=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'albert', 'avatar.jpg', '4lb3rtthomaseinstein@hotmail.fr', 'eHkW9yUCw9eaotaxvYF8nCQtZiPDDHBypnjw8jlVsMg=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'mimie', 'avatar.jpg', 'mimimimi-mathy85@hotmail.fr', 'ygZZ5g0PZY/rCzpK8okn998r9YI1Brvom3sPVS8CRVc=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'patrick', 'avatar.jpg', 'patoche-sebastoche23@hotmail.fr', 'yQRr6pgv56fRxUiTIfb3PTyiMo+KQiG/BTHfqer4W6I=','participant');
INSERT INTO utilisateur VALUES (DEFAULT, 'linux', 'avatar.jpg', 'torvalds44523@hotmail.fr', 'WNXDD5Hpb31OCi11W/jjy1kstqsGx6lXXx0p1buUrLY=','participant');

INSERT INTO equipe VALUES (DEFAULT, 1, 'Les profs', 'equipe.jpg');
INSERT INTO equipe VALUES (DEFAULT, 114, 'Gouvernement', 'equipe.jpg');
INSERT INTO equipe VALUES (DEFAULT, 109, 'Les boss', 'equipe.jpg');
INSERT INTO equipe VALUES (DEFAULT, 116, 'TeamIUT', 'equipe.jpg');
INSERT INTO equipe VALUES (DEFAULT, 117, 'Les stars', 'equipe.jpg');
INSERT INTO equipe VALUES (DEFAULT, 125, 'TeamCryptis', 'equipe.jpg');

INSERT INTO appartientA VALUES(102, 1);
INSERT INTO appartientA VALUES(111, 1);
INSERT INTO appartientA VALUES(112, 1);
INSERT INTO appartientA VALUES(123, 1);

INSERT INTO appartientA VALUES(114, 2);
INSERT INTO appartientA VALUES(127, 2);

INSERT INTO appartientA VALUES(109, 3);
INSERT INTO appartientA VALUES(114, 3);
INSERT INTO appartientA VALUES(111, 3);

INSERT INTO appartientA VALUES(116, 4);
INSERT INTO appartientA VALUES(113, 4);
INSERT INTO appartientA VALUES(110, 4);
INSERT INTO appartientA VALUES(123, 4);

INSERT INTO appartientA VALUES(117, 5);
INSERT INTO appartientA VALUES(119, 5);
INSERT INTO appartientA VALUES(130, 5);
INSERT INTO appartientA VALUES(131, 5);

INSERT INTO appartientA VALUES(125, 6);
INSERT INTO appartientA VALUES(126, 6);
INSERT INTO appartientA VALUES(108, 6);

INSERT INTO ctf VALUES(DEFAULT, 'CTF CRYPTIS 2021', 'equipe.jpg', '2021-11-13', '09:00:00', '2021-11-13', '21:00:00', 108);
INSERT INTO ctf VALUES(DEFAULT, 'TRACS 2021', 'equipe.jpg', '2021-12-09', '00:00:00', '2021-12-09', '22:00:00', 119);
INSERT INTO ctf VALUES(DEFAULT, 'FCSC 2021', 'equipe.jpg', '2021-04-18', '06:00:00', '2021-05-02', '20:00:00', 107);
INSERT INTO ctf VALUES(DEFAULT, 'TRACS 2020', 'equipe.jpg', '2020-12-09', '12:00:00', '2020-12-09', '21:00:00', 119);
INSERT INTO ctf VALUES(DEFAULT, 'FCSC 2020', 'equipe.jpg', '2020-04-14', '08:00:00', '2020-04-29', '18:00:00', 107);

INSERT INTO scoreCTF VALUES(1, 1, 100);
INSERT INTO scoreCTF VALUES(1, 2, 120);
INSERT INTO scoreCTF VALUES(1, 3, 60);
INSERT INTO scoreCTF VALUES(1, 5, 80);
INSERT INTO scoreCTF VALUES(2, 3, 100);
INSERT INTO scoreCTF VALUES(2, 4, 40);
INSERT INTO scoreCTF VALUES(2, 6, 65);
INSERT INTO scoreCTF VALUES(3, 1, 70);
INSERT INTO scoreCTF VALUES(3, 3, 80);
INSERT INTO scoreCTF VALUES(4, 1, 30);
INSERT INTO scoreCTF VALUES(4, 2, 55);
INSERT INTO scoreCTF VALUES(4, 3, 140);
INSERT INTO scoreCTF VALUES(4, 5, 60);
INSERT INTO scoreCTF VALUES(4, 6, 100);
INSERT INTO scoreCTF VALUES(5, 2, 70);
INSERT INTO scoreCTF VALUES(5, 3, 90);
INSERT INTO scoreCTF VALUES(5, 4, 105);
INSERT INTO scoreCTF VALUES(5, 5, 40);

SELECT * FROM CTF ORDER BY dateDebut DESC, heureDebut DESC FETCH FIRST 3 ROWS ONLY;
SELECT e.equi_id, chef_id, nom, avatar, SUM(score) AS scoreTotal FROM equipe e JOIN scoreCTF ON e.equi_id = scoreCTF.equi_id GROUP BY e.equi_id, chef_id, nom, avatar ORDER BY scoreTotal DESC;
