CREATE TABLE utilisateur(
    uti_id INT PRIMARY KEY NOT NULL,
    pseudo VARCHAR(20) NOT NULL,
    avatar VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    mdp VARCHAR(200) NOT NULL,
    score INT NOT NULL
);

CREATE TABLE groupe(
    group_id INT PRIMARY KEY NOT NULL,
    pseudo VARCHAR(20) NOT NULL,
    groupe VARCHAR(20) NOT NULL
);

CREATE TABLE defi (
    defi_id INT PRIMARY KEY NOT NULL,
    nom VARCHAR(30) NOT NULL,
    sous_titre VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    categorie VARCHAR(30) NOT NULL,
    validations INT NOT NULL,
    essais INT NOT NULL,
    mdp VARCHAR(100)
);

CREATE TABLE message (
    mes_id INT PRIMARY KEY NOT NULL,
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

