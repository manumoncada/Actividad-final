-- Base de datos: mascotas.db (SQLite)
CREATE TABLE mascota (
    id      INTEGER NOT NULL UNIQUE,   -- identificador unico de la mascota
    nombre  TEXT    NOT NULL,
    foto    INTEGER NOT NULL,          -- id del recurso drawable
    rating  INTEGER NOT NULL
);

-- Ultimas 5 mascotas con rating (la mas reciente primero)
SELECT id, nombre, foto, rating FROM mascota ORDER BY rowid DESC LIMIT 5;
