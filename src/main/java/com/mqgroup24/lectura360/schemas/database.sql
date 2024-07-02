DROP DATABASE IF EXISTS lectura360_db;

CREATE DATABASE lectura360_db;

USE lectura360_db;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(125) NOT NULL UNIQUE,
    role ENUM('ADMIN', 'USER') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(75) NOT NULL,
    author VARCHAR(75) NOT NULL,
    synopsis VARCHAR(245) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    isbn VARCHAR(17) NOT NULL,
    release_year INT NOT NULL,
    url_book_image VARCHAR(125) NOT NULL,
    url_book_pdf VARCHAR(125) NOT NULL,
    rating_average DECIMAL(2,1) NOT NULL CHECK ( rating_average BETWEEN 0 AND 5 ),
    PRIMARY KEY (id)
);

CREATE TABLE user_book_ratings (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    rating DECIMAL(2,1),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    UNIQUE (user_id, book_id),
    PRIMARY KEY (id)
);

CREATE TABLE user_book_favorites (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    UNIQUE (user_id, book_id),
    PRIMARY KEY (id)
);

CREATE TABLE genders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_genders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    book_id BIGINT NOT NULL,
    gender_id BIGINT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (gender_id) REFERENCES genders(id),
    UNIQUE (book_id, gender_id),
    PRIMARY KEY (id)
);

CREATE TABLE comment_books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    comment TEXT NOT NULL,
    like_count INT DEFAULT 0,
    dislike_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (book_id) REFERENCES books (id),
    PRIMARY KEY (id)
);

CREATE TABLE reaction_comments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    comment_books_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reaction_type ENUM('LIKE', 'DISLIKE') NOT NULL,
    FOREIGN KEY (comment_books_id) REFERENCES comment_books (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    PRIMARY KEY (id)
);

DELIMITER //

CREATE TRIGGER IF NOT EXISTS update_ratings_book
    AFTER INSERT ON user_book_ratings
    FOR EACH ROW
    BEGIN

        DECLARE total_rating INT;
        DECLARE sum_rating DECIMAL(10, 2);

        SELECT COUNT(*), SUM(rating)
        INTO total_rating, sum_rating
        FROM user_book_ratings
        WHERE book_id = NEW.book_id;

        UPDATE books
        SET rating_average = sum_rating / total_rating
        WHERE id = NEW.book_id;

    END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER IF NOT EXISTS update_total_reactions_comment_insert
    AFTER INSERT ON reaction_comments
    FOR EACH ROW
    BEGIN

        IF NEW.reaction_type = 'LIKE' THEN
            UPDATE comment_books
                SET like_count = like_count + 1
            WHERE id = NEW.comment_books_id;

        ELSEIF NEW.reaction_type = 'DISLIKE' THEN
            UPDATE comment_books
                SET dislike_count = dislike_count + 1
            WHERE id = NEW.comment_books_id;

        END IF;

    END //

DELIMITER ;

DELIMITER  //

CREATE TRIGGER IF NOT EXISTS update_total_reactions_comment_delete
    BEFORE DELETE ON reaction_comments
    FOR EACH ROW
    BEGIN

        IF OLD.reaction_type = 'LIKE' THEN
            UPDATE comment_books
            SET like_count = like_count - 1
            WHERE id = OLD.comment_books_id;

        ELSEIF OLD.reaction_type = 'DISLIKE' THEN
            UPDATE comment_books
            SET dislike_count = dislike_count - 1
            WHERE id = OLD.comment_books_id;

        END IF;

    END //

DELIMITER ;

INSERT INTO users (username, password, email, role, status)
VALUES
    ('alice_jones', 'alice123', 'alice.jones@example.com', 'USER', 'ACTIVE'),
    ('bob_brown', 'brownie456', 'bob.brown@example.com', 'USER', 'ACTIVE'),
    ('emma_davis', 'edavis789', 'emma.davis@example.com', 'USER', 'ACTIVE'),
    ('charlie_green', 'charlieG!23', 'charlie.green@example.com', 'USER', 'ACTIVE'),
    ('sophia_clark', 'sophiaC@89', 'sophia.clark@example.com', 'USER', 'ACTIVE'),
    ('jack_wilson', 'wilson1234', 'jack.wilson@example.com', 'USER', 'ACTIVE'),
    ('olivia_thomas', 'oliviaT!56', 'olivia.thomas@example.com', 'USER', 'ACTIVE'),
    ('lucas_rodriguez', 'lucasR#78', 'lucas.rodriguez@example.com', 'USER', 'ACTIVE'),
    ('mia_martinez', 'miaM@90', 'mia.martinez@example.com', 'USER', 'ACTIVE'),
    ('noah_hernandez', 'noah123', 'noah.hernandez@example.com', 'USER', 'ACTIVE'),
    ('amelia_gonzalez', 'ameliaG456', 'amelia.gonzalez@example.com', 'USER', 'ACTIVE'),
    ('ethan_perez', 'ethanP!78', 'ethan.perez@example.com', 'USER', 'ACTIVE'),
    ('isabella_gomez', 'isabellaG@90', 'isabella.gomez@example.com', 'USER', 'ACTIVE'),
    ('mason_diaz', 'masonD123', 'mason.diaz@example.com', 'USER', 'ACTIVE'),
    ('ava_rivera', 'avaR!56', 'ava.rivera@example.com', 'USER', 'ACTIVE'),
    ('logan_lopez', 'loganL@78', 'logan.lopez@example.com', 'USER', 'ACTIVE'),
    ('harper_hall', 'harperH123', 'harper.hall@example.com', 'USER', 'ACTIVE'),
    ('liam_young', 'liamY!90', 'liam.young@example.com', 'USER', 'ACTIVE'),
    ('ava_scott', 'avaS123', 'ava.scott@example.com', 'USER', 'ACTIVE'),
    ('noah_king', 'noahK!56', 'noah.king@example.com', 'USER', 'ACTIVE');



-- Insertar datos en la tabla books
INSERT INTO books (title, author, synopsis, publisher, isbn, release_year, url_book_image, url_book_pdf, rating_average)
VALUES
    ('Cien años de soledad', 'Gabriel García Márquez', 'Una historia épica de la familia Buendía.', 'Editorial Sudamericana', '978-958-06-0221-3', 1967, '100-años-de-soledad.jpg', '100-años-de-soledad.pdf', 4.5),
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Las aventuras de un hidalgo manchego.', 'Francisco de Robles', '978-84-376-0494-7', 1605, 'don-quijote-de-la-mancha.png', 'don-quijote-de-la-mancha.pdf', 4.7),
    ('La sombra del viento', 'Carlos Ruiz Zafón', 'Un joven descubre un libro maldito en la Barcelona de posguerra.', 'Planeta', '978-84-08-03720-9', 2001, 'la-sombra-del-viento.jpeg', 'la-sombra-del-viento.pdf', 4.6),
    ('1984', 'George Orwell', 'Una distopía sobre un estado totalitario.', 'Secker & Warburg', '978-0451524935', 1949, '1984.jpeg', '1984.pdf', 4.9),
    ('El Señor de los Anillos', 'J.R.R. Tolkien', 'Una épica fantasía sobre la lucha por la Tierra Media.', 'Allen & Unwin', '978-0618640157', 1954, 'el-señor-de-los-anillos.jpg', 'el-señor-de-los-anillos.pdf', 4.8),
    ('Orgullo y prejuicio', 'Jane Austen', 'Una novela sobre el amor y las diferencias de clase.', 'T. Egerton, Whitehall', '978-0141439518', 1813, 'orgullo-y-prejuicio.jpeg', 'orgullo-y-prejuicio.pdf', 4.7),
    ('Matar a un ruiseñor', 'Harper Lee', 'Una historia sobre la injusticia racial en el sur de EE.UU.', 'J.B. Lippincott & Co.', '978-0061120084', 1960, 'matar-a-un-ruiseñor.jpeg', 'matar-a-un-ruiseñor.pdf', 4.9),
    ('Crimen y castigo', 'Fyodor Dostoevsky', 'Un análisis profundo de la moralidad y la redención.', 'The Russian Messenger', '978-0143058144', 1866, 'crimen-y-castigo.jpeg', 'crimen-y-castigo.pdf', 4.8);

-- Insertar datos en la tabla user_book_ratings
INSERT INTO user_book_ratings (user_id, book_id, rating)
VALUES
    (1, 1, 4.8),
    (2, 2, 4.2),
    (3, 3, 4.5),
    (4, 4, 3.9),
    (5, 5, 4.1),
    (6, 6, 4.3),
    (7, 7, 4.7),
    (8, 8, 4.0),
    (9, 1, 4.6),
    (10, 2, 4.4),
    (11, 3, 4.9),
    (12, 4, 4.5),
    (13, 5, 3.8),
    (14, 6, 4.2),
    (15, 7, 4.0),
    (16, 8, 4.8),
    (17, 1, 4.6),
    (18, 2, 4.3),
    (19, 3, 4.1),
    (20, 4, 4.7);

-- Insertar datos en la tabla user_book_favorites
INSERT INTO user_book_favorites (user_id, book_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

-- Insertar datos en la tabla genders
INSERT INTO genders (name)
VALUES
    ('Romance'),
    ('Ciencia Ficción'),
    ('Fantasía'),
    ('Histórico'),
    ('Suspenso'),
    ('Terror'),
    ('Aventura'),
    ('Misterio'),
    ('Humor'),
    ('Drama'),
    ('Biografía'),
    ('Autoayuda'),
    ('Infantil'),
    ('Juvenil'),
    ('Poesía'),
    ('Thriller'),
    ('Religión'),
    ('Educativo'),
    ('Arte'),
    ('Culinario');

-- Insertar datos en la tabla book_genders
INSERT INTO book_genders (book_id, gender_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8);

-- Insertar datos en la tabla comment_books
INSERT INTO comment_books (user_id, book_id, comment, like_count, dislike_count, created_at)
VALUES
    (1, 1, 'Una historia apasionante que te atrapa desde la primera página.', 0, 0, '2023-06-26 14:45:00'),
    (2, 1, 'Intrigante y bien desarrollado, recomendado para todos los lectores.', 0, 0, '2023-06-26 15:15:00'),
    (3, 1, 'Me encantó cada personaje y la trama es simplemente magnífica.', 0, 0, '2023-06-26 15:45:00'),
    (4, 4, 'Una obra que te hace reflexionar sobre la condición humana.', 0, 0, '2023-06-26 16:00:00'),
    (5, 5, 'Excelente narrativa y giros inesperados, ¡no pude soltarlo!', 0, 0, '2023-06-26 16:30:00'),
    (6, 6, 'Un libro que te transporta a mundos desconocidos, fascinante.', 0, 0, '2023-06-27 10:00:00'),
    (7, 7, 'Historia épica con personajes memorables y un final impactante.', 0, 0, '2023-06-27 10:30:00'),
    (8, 8, 'Una novela que te mantiene en suspenso hasta la última página.', 0, 0, '2023-06-27 11:00:00');

-- Insertar datos en la tabla reaction_comments
INSERT INTO reaction_comments (comment_books_id, user_id, reaction_type)
VALUES
    (1, 1, 'LIKE'),
    (2, 2, 'LIKE'),
    (3, 3, 'DISLIKE'),
    (1, 9, 'LIKE'),
    (1, 11, 'LIKE'),
    (2, 13, 'LIKE'),
    (7, 15, 'DISLIKE'),
    (8, 17, 'LIKE');


