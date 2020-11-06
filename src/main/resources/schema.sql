CREATE TABLE template (
     id INT PRIMARY KEY,
     paragrafo TEXT NOT NULL
);

CREATE TABLE documento (
    id INT PRIMARY KEY,
    titulo  TEXT NOT NULL
);

CREATE TABLE secao (
   id INT PRIMARY KEY,
   id_documento INT NOT NULL,
   titulo  TEXT NOT NULL,
   texto TEXT NOT NULL,
   FOREIGN KEY(id_documento) REFERENCES documento(id)
);

