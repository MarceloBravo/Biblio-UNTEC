-- ============================================================
-- create_database.sql
-- ============================================================
CREATE DATABASE IF NOT EXISTS `untec`;
USE untec;
CREATE TABLE `libros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(13) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `editorial` varchar(255) NOT NULL COMMENT 'En caso de mas de una editorial separar los nombres por coma',
  `autor` varchar(255) NOT NULL COMMENT 'En caso de mas de un autor, separar los nombres por coma',
  `resumen` text,
  `fecha_publicacion` datetime DEFAULT NULL,
  `idioma` varchar(15) NOT NULL,
  `edicion` tinyint NOT NULL DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `prestamos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `libro_id` int NOT NULL,
  `fecha_prestamo` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_devolucion` datetime NOT NULL COMMENT 'Fecha prevista en la que el libro deberá ser devuelto',
  `fecha_retorno` datetime DEFAULT NULL COMMENT 'Fecha real en la que el libro fue devuelto',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `libro_id` (`libro_id`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`libro_id`) REFERENCES `libros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb3;


-- ============================================================
-- data_libros.sql
-- ============================================================
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437604947', 'Cien años de soledad', 'Debolsillo', 'Gabriel García Márquez', 'La novela narra la historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo.', '1967-05-30', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218231', 'La sombra del viento', 'Planeta', 'Carlos Ruiz Zafón', 'Un amanecer de 1945, un muchacho es conducido por su padre a un misterioso lugar oculto en el corazón de la ciudad vieja: el Cementerio de los Libros Olvidados.', '2001-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474728', 'The Lord of the Rings', 'Mariner Books', 'J.R.R. Tolkien', 'In a sleepy village in the Shire, a young hobbit is entrusted with an immense task.', '1954-07-29', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498388953', 'Harry Potter y la piedra filosofal', 'Salamandra', 'J.K. Rowling', 'Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.', '1997-06-26', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788433914332', 'Rayuela', 'Cátedra', 'Julio Cortázar', 'La historia de Horacio Oliveira y su relación con "la Maga".', '1963-06-28', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273565', 'The Da Vinci Code', 'Anchor', 'Dan Brown', 'A murder in Paris'' Louvre Museum and cryptic clues in some of Leonardo da Vinci''s most famous paintings lead to the discovery of a religious mystery.', '2003-03-18', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412146', 'Don Quijote de la Mancha', 'Alfaguara', 'Miguel de Cervantes', 'La historia de un hidalgo de la Mancha que, tras leer muchos libros de caballerías, decide convertirse en caballero andante.', '1605-01-16', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501142970', 'It', 'Scribner', 'Stephen King', 'Seven adults return to their hometown to confront a nightmare they had first stumbled upon as teenagers.', '1986-09-15', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061031', 'El código Da Vinci', 'Planeta', 'Dan Brown', 'Un asesinato en el Museo del Louvre y pistas en las obras de Da Vinci llevan al descubrimiento de un misterio religioso.', '2003-03-18', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524935', '1984', 'Signet Classics', 'George Orwell', 'A dystopian novel set in Airstrip One, a province of the superstate Oceania in a world of perpetual war.', '1949-06-08', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9789500729307', 'El Aleph', 'Debolsillo', 'Jorge Luis Borges', 'Una colección de cuentos que exploran temas como la inmortalidad, la identidad y el infinito.', '1949-06-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780061120084', 'To Kill a Mockingbird', 'Harper', 'Harper Lee', 'The story of a young girl and her father, a lawyer who defends a black man falsely accused of raping a white woman.', '1960-07-11', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437617299', 'La casa de los espíritus', 'Debolsillo', 'Isabel Allende', 'La saga de la familia Trueba a lo largo de cuatro generaciones.', '1982-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781451673319', 'Fahrenheit 451', 'Simon', 'Ray Bradbury', 'A dystopian novel about a future American society where books are outlawed and "firemen" burn any that are found.', '1953-10-19', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788466336399', 'Patria', 'Tusquets', 'Fernando Aramburu', 'La historia de dos familias divididas por el conflicto vasco.', '2016-09-06', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273566', 'The Hobbit', 'Houghton', 'J.R.R. Tolkien', 'A reluctant hobbit is swept into an epic quest.', '1937-09-21', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498388960', 'Harry Potter y la cámara secreta', 'Salamandra', 'J.K. Rowling', 'El segundo año de Harry en Hogwarts está lleno de nuevos peligros.', '1998-07-02', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474729', 'The Fellowship of the Ring', 'Mariner Books', 'J.R.R. Tolkien', 'The first part of The Lord of the Rings.', '1954-07-29', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218248', 'El juego del ángel', 'Planeta', 'Carlos Ruiz Zafón', 'En la turbulenta Barcelona de los años 20, un joven escritor obsesionado con un amor imposible recibe la oferta de un misterioso editor.', '2008-04-17', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437604954', 'El amor en los tiempos del cólera', 'Debolsillo', 'Gabriel García Márquez', 'La historia de amor entre Florentino Ariza y Fermina Daza.', '1985-01-01', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273567', 'Angels & Demons', 'Pocket Books', 'Dan Brown', 'A Harvard symbologist is awakened by a phone call from the director of a Swiss research facility.', '2000-05-01', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412153', 'Novelas ejemplares', 'Alfaguara', 'Miguel de Cervantes', 'Una colección de doce novelas cortas.', '1613-01-01', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501142987', 'The Shining', 'Anchor', 'Stephen King', 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence.', '1977-01-28', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061048', 'Ángeles y demonios', 'Planeta', 'Dan Brown', 'Un simbologista de Harvard es despertado por una llamada telefónica del director de un centro de investigación suizo.', '2000-05-01', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524942', 'Animal Farm', 'Signet', 'George Orwell', 'A group of farm animals rebel against their human farmer, hoping to create a society where the animals can be equal, free, and happy.', '1945-08-17', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9789500729314', 'Ficciones', 'Debolsillo', 'Jorge Luis Borges', 'Una colección de cuentos que se adentran en el mundo de lo fantástico y lo metafísico.', '1944-01-01', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780061120091', 'Go Set a Watchman', 'Harper', 'Harper Lee', 'Jean Louise Finch returns to Maycomb, Alabama to visit her father, Atticus.', '2015-07-14', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437617305', 'De amor y de sombra', 'Debolsillo', 'Isabel Allende', 'La historia de Irene y Francisco, dos periodistas que se enamoran mientras investigan un crimen en la dictadura chilena.', '1984-01-01', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781451673326', 'The Martian Chronicles', 'Simon', 'Ray Bradbury', 'A collection of science fiction short stories about the colonization of Mars.', '1950-05-04', 'Inglés', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788466336405', 'El corazón helado', 'Tusquets', 'Almudena Grandes', 'La historia de dos familias a lo largo de la historia de España en el siglo XX.', '2007-01-01', 'Español', 2, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273568', 'The Silmarillion', 'Houghton', 'J.R.R. Tolkien', 'The story of the creation of the world and of the First Age.', '1977-09-15', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498388977', 'Harry Potter y el prisionero de Azkaban', 'Salamandra', 'J.K. Rowling', 'El tercer año de Harry en Hogwarts le trae nuevos desafíos y la verdad sobre un peligroso prisionero.', '1999-07-08', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474736', 'The Two Towers', 'Mariner Books', 'J.R.R. Tolkien', 'The second part of The Lord of the Rings.', '1954-11-11', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218255', 'El prisionero del cielo', 'Planeta', 'Carlos Ruiz Zafón', 'Daniel Sempere ya es un hombre casado y dirige la librería familiar con su padre y con su fiel amigo Fermín Romero de Torres.', '2011-11-17', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437604961', 'Crónica de una muerte anunciada', 'Debolsillo', 'Gabriel García Márquez', 'La historia del asesinato de Santiago Nasar.', '1981-01-01', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273569', 'The Lost Symbol', 'Anchor', 'Dan Brown', 'Robert Langdon is summoned to Washington D.C. to give a lecture.', '2009-09-15', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412160', 'Viaje del Parnaso', 'Alfaguara', 'Miguel de Cervantes', 'Un poema narrativo en tercetos encadenados.', '1614-01-01', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501142994', 'The Stand', 'Anchor', 'Stephen King', 'After a deadly flu pandemic, the few survivors must choose between good and evil.', '1978-10-03', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061055', 'El símbolo perdido', 'Planeta', 'Dan Brown', 'Robert Langdon es convocado a Washington D.C. para dar una conferencia.', '2009-09-15', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524959', 'Brave New World', 'Harper', 'Aldous Huxley', 'A dystopian novel that explores a futuristic society driven by technology and conditioning.', '1932-01-01', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9789500729321', 'El hacedor', 'Debolsillo', 'Jorge Luis Borges', 'Una colección de poemas y prosas breves.', '1960-01-01', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780061120107', 'The Catcher in the Rye', 'Little, Brown', 'J.D. Salinger', 'The story of Holden Caulfield, a teenager from New York.', '1951-07-16', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437617312', 'Eva Luna', 'Debolsillo', 'Isabel Allende', 'La vida de Eva Luna, una joven con el don de contar historias.', '1987-01-01', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781451673333', 'Dandelion Wine', 'William Morrow', 'Ray Bradbury', 'A story about a summer in the life of a 12-year-old boy.', '1957-01-01', 'Inglés', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788466336412', 'La catedral del mar', 'Debolsillo', 'Ildefonso Falcones', 'La historia de la construcción de la iglesia de Santa María del Mar en Barcelona.', '2006-01-01', 'Español', 3, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273575', 'Unfinished Tales', 'Houghton', 'J.R.R. Tolkien', 'A collection of stories and essays that were not completed by the author.', '1980-10-02', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498388984', 'Harry Potter y el cáliz de fuego', 'Salamandra', 'J.K. Rowling', 'Harry compite en el Torneo de los Tres Magos.', '2000-07-08', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474743', 'The Return of the King', 'Mariner Books', 'J.R.R. Tolkien', 'The third and final part of The Lord of the Rings.', '1955-10-20', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218262', 'El laberinto de los espíritus', 'Planeta', 'Carlos Ruiz Zafón', 'La conclusión de la saga del Cementerio de los Libros Olvidados.', '2016-11-17', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437604978', 'El otoño del patriarca', 'Debolsillo', 'Gabriel García Márquez', 'La vida de un dictador caribeño.', '1975-01-01', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273576', 'Inferno', 'Anchor', 'Dan Brown', 'Robert Langdon awakens in a hospital with amnesia.', '2013-05-14', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412177', 'La Galatea', 'Alfaguara', 'Miguel de Cervantes', 'Una novela pastoril.', '1585-01-01', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501143007', 'Misery', 'Scribner', 'Stephen King', 'A famous novelist is rescued from a car crash by his "number one fan".', '1987-06-08', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061062', 'Inferno', 'Planeta', 'Dan Brown', 'Robert Langdon se despierta en un hospital con amnesia.', '2013-05-14', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524966', 'The Great Gatsby', 'Scribner', 'F. Scott Fitzgerald', 'The story of the mysterious millionaire Jay Gatsby and his obsession for the beautiful Daisy Buchanan.', '1925-04-10', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9789500729338', 'El informe de Brodie', 'Debolsillo', 'Jorge Luis Borges', 'Una colección de cuentos que regresan a un estilo más realista.', '1970-01-01', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780061120114', 'Pride and Prejudice', 'Modern Library', 'Jane Austen', 'The story of Elizabeth Bennet, one of five sisters from a family of landed gentry.', '1813-01-28', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437617329', 'El plan infinito', 'Debolsillo', 'Isabel Allende', 'La vida de Gregory Reeves, un hombre en busca de su identidad.', '1991-01-01', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781451673340', 'Something Wicked This Way Comes', 'Simon', 'Ray Bradbury', 'Two teenage boys have a harrowing encounter with a traveling carnival.', '1962-01-01', 'Inglés', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788466336429', 'Dime quién soy', 'Debolsillo', 'Julia Navarro', 'Un periodista recibe la propuesta de investigar la vida de su bisabuela.', '2010-01-01', 'Español', 4, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273582', 'The Children of Húrin', 'Houghton', 'J.R.R. Tolkien', 'The story of Túrin Turambar, a tragic hero of the First Age.', '2007-04-17', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498388991', 'Harry Potter y la Orden del Fénix', 'Salamandra', 'J.K. Rowling', 'Harry debe enfrentarse al regreso de Lord Voldemort.', '2003-06-21', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474750', 'The Art of The Lord of the Rings', 'Houghton', 'J.R.R. Tolkien', 'A collection of drawings, inscriptions, maps, and plans of J.R.R. Tolkien.', '2015-10-27', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218279', 'Marina', 'Planeta', 'Carlos Ruiz Zafón', 'En la Barcelona de 1980, Óscar Drai conoce a Marina, una enigmática chica que le cambiará la vida.', '1999-01-01', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437604985', 'El general en su laberinto', 'Debolsillo', 'Gabriel García Márquez', 'Los últimos días de Simón Bolívar.', '1989-01-01', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273583', 'Origin', 'Anchor', 'Dan Brown', 'Robert Langdon travels to the Guggenheim Museum in Bilbao to attend a major announcement.', '2017-10-03', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412184', 'Los trabajos de Persiles y Sigismunda', 'Alfaguara', 'Miguel de Cervantes', 'Una novela bizantina.', '1617-01-01', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501143014', 'Carrie', 'Anchor', 'Stephen King', 'A shy, friendless teenage girl who is sheltered by her domineering, religious mother.', '1974-04-05', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061079', 'Origen', 'Planeta', 'Dan Brown', 'Robert Langdon viaja al Museo Guggenheim de Bilbao para asistir a un importante anuncio.', '2017-10-03', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524973', 'Moby Dick', 'Penguin', 'Herman Melville', 'The saga of Captain Ahab and his obsessive quest to seek revenge on the white whale that bit off his leg.', '1851-10-18', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9789500729345', 'El libro de arena', 'Debolsillo', 'Jorge Luis Borges', 'Una colección de cuentos que continúan explorando los temas recurrentes del autor.', '1975-01-01', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780061120121', 'Sense and Sensibility', 'Penguin', 'Jane Austen', 'The story of the Dashwood sisters, Elinor and Marianne.', '1811-01-01', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437617336', 'Paula', 'Debolsillo', 'Isabel Allende', 'Una memoria conmovedora escrita durante la enfermedad de su hija.', '1994-01-01', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781451673357', 'The Illustrated Man', 'Simon', 'Ray Bradbury', 'A collection of 18 science fiction short stories.', '1951-01-01', 'Inglés', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788466336436', 'La mano de Fátima', 'Debolsillo', 'Ildefonso Falcones', 'La historia de un joven morisco en la Andalucía del siglo XVI.', '2009-01-01', 'Español', 5, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273599', 'Beren and Lúthien', 'Houghton', 'J.R.R. Tolkien', 'The tale of the love and adventures of the mortal Man Beren and the immortal Elf-maiden Lúthien.', '2017-06-01', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498389006', 'Harry Potter y el misterio del príncipe', 'Salamandra', 'J.K. Rowling', 'El sexto año de Harry en Hogwarts, donde descubre más sobre el pasado de Voldemort.', '2005-07-16', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474767', 'The Adventures of Tom Bombadil', 'Houghton', 'J.R.R. Tolkien', 'A collection of poetry.', '1962-01-01', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218286', 'La luz de la noche', 'Planeta', 'Carlos Ruiz Zafón', 'Un thriller ambientado en el mundo editorial.', '2012-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437604992', 'Doce cuentos peregrinos', 'Debolsillo', 'Gabriel García Márquez', 'Doce historias de personajes latinoamericanos en Europa.', '1992-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273590', 'Digital Fortress', 'St. Martin''s', 'Dan Brown', 'The NSA''s unbreakable code-breaking machine encounters a mysterious new code.', '1998-01-01', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412191', 'Rinconete y Cortadillo', 'Alfaguara', 'Miguel de Cervantes', 'Una de las Novelas ejemplares.', '1613-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501143021', 'Salem''s Lot', 'Anchor', 'Stephen King', 'A writer returns to his childhood home to discover that the town is being preyed upon by a vampire.', '1975-10-17', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061086', 'La fortaleza digital', 'Planeta', 'Dan Brown', 'La máquina de desencriptación de la NSA se encuentra con un nuevo y misterioso código.', '1998-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524980', 'Don Quixote', 'Penguin', 'Miguel de Cervantes', 'The adventures of a nobleman who reads so many chivalric romances that he loses his sanity.', '1605-01-16', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9789500729352', 'Historia universal de la infamia', 'Debolsillo', 'Jorge Luis Borges', 'Una colección de relatos cortos sobre criminales y villanos.', '1935-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780061120138', 'Emma', 'Penguin', 'Jane Austen', 'The story of a young, beautiful, and clever woman who delights in matchmaking.', '1815-12-23', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437617343', 'La hija de la fortuna', 'Debolsillo', 'Isabel Allende', 'La historia de una joven chilena que viaja a California durante la fiebre del oro.', '1999-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781451673364', 'I Sing the Body Electric!', 'Bantam', 'Ray Bradbury', 'A collection of short stories.', '1969-01-01', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788466336443', 'La conjura', 'Debolsillo', 'Julia Navarro', 'Un thriller histórico sobre la Sábana Santa.', '2004-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273605', 'The Fall of Gondolin', 'Houghton', 'J.R.R. Tolkien', 'The story of the founding, greatness, and destruction of the last great Elven city.', '2018-08-30', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788498389013', 'Harry Potter y las Reliquias de la Muerte', 'Salamandra', 'J.K. Rowling', 'El enfrentamiento final entre Harry y Lord Voldemort.', '2007-07-21', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780307474774', 'Tales from the Perilous Realm', 'Houghton', 'J.R.R. Tolkien', 'A collection of shorter works.', '1997-01-01', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788432218293', 'El príncipe de la niebla', 'Planeta', 'Carlos Ruiz Zafón', 'La primera novela del autor, un misterio juvenil.', '1993-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788437605005', 'Del amor y otros demonios', 'Debolsillo', 'Gabriel García Márquez', 'La historia de Sierva María de Todos los Ángeles.', '1994-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780743273606', 'Deception Point', 'Pocket Books', 'Dan Brown', 'A NASA satellite discovers a rare object buried deep in the Arctic ice.', '2001-01-01', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788420412207', 'El coloquio de los perros', 'Alfaguara', 'Miguel de Cervantes', 'Otra de las Novelas ejemplares.', '1613-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9781501143038', 'The Dark Tower: The Gunslinger', 'Scribner', 'Stephen King', 'The first book in The Dark Tower series.', '1982-06-10', 'Inglés', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9788408061093', 'La conspiración', 'Planeta', 'Dan Brown', 'Un satélite de la NASA descubre un raro objeto enterrado en el hielo ártico.', '2001-01-01', 'Español', 1, NOW(), NOW());
INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at) VALUES ('9780451524997', 'The Adventures of Huckleberry Finn', 'Penguin', 'Mark Twain', 'A young boy''s adventure down the Mississippi River with a runaway slave.', '1884-12-10', 'Inglés', 1, NOW(), NOW());

-- ============================================================
-- data_usuarios.sql
-- ============================================================

INSERT INTO usuarios (nombre, apellidos, email, password) VALUES
('Ana', 'García', 'ana.garcia@example.com', 'password123'),
('Luis', 'Hernández', 'luis.hernandez@example.com', 'password123'),
('Sofía', 'Martínez', 'sofia.martinez@example.com', 'password123'),
('Juan', 'López', 'juan.lopez@example.com', 'password123'),
('María', 'González', 'maria.gonzalez@example.com', 'password123'),
('Carlos', 'Pérez', 'carlos.perez@example.com', 'password123'),
('Laura', 'Sánchez', 'laura.sanchez@example.com', 'password123'),
('Miguel', 'Ramírez', 'miguel.ramirez@example.com', 'password123'),
('Elena', 'Torres', 'elena.torres@example.com', 'password123'),
('Pedro', 'Flores', 'pedro.flores@example.com', 'password123'),
('Isabel', 'Rivera', 'isabel.rivera@example.com', 'password123'),
('Javier', 'Gómez', 'javier.gomez@example.com', 'password123'),
('Carmen', 'Díaz', 'carmen.diaz@example.com', 'password123'),
('Ricardo', 'Vásquez', 'ricardo.vasquez@example.com', 'password123'),
('Patricia', 'Moreno', 'patricia.moreno@example.com', 'password123'),
('Fernando', 'Jiménez', 'fernando.jimenez@example.com', 'password123'),
('Rosa', 'Silva', 'rosa.silva@example.com', 'password123'),
('Alejandro', 'Ruiz', 'alejandro.ruiz@example.com', 'password123'),
('Teresa', 'Navarro', 'teresa.navarro@example.com', 'password123'),
('Jorge', 'Mendoza', 'jorge.mendoza@example.com', 'password123'),
('Beatriz', 'Castro', 'beatriz.castro@example.com', 'password123'),
('Raúl', 'Ortega', 'raul.ortega@example.com', 'password123'),
('Verónica', 'Romero', 'veronica.romero@example.com', 'password123'),
('Daniel', 'Serrano', 'daniel.serrano@example.com', 'password123'),
('Mónica', 'Blanco', 'monica.blanco@example.com', 'password123'),
('Francisco', 'Ramos', 'francisco.ramos@example.com', 'password123'),
('Silvia', 'Molina', 'silvia.molina@example.com', 'password123'),
('Roberto', 'Suárez', 'roberto.suarez@example.com', 'password123'),
('Gloria', 'Vega', 'gloria.vega@example.com', 'password123'),
('Eduardo', 'Guerrero', 'eduardo.guerrero@example.com', 'password123'),
('Natalia', 'Reyes', 'natalia.reyes@example.com', 'password123'),
('Alberto', 'Cabrera', 'alberto.cabrera@example.com', 'password123'),
('Lorena', 'Campos', 'lorena.campos@example.com', 'password123'),
('David', 'Paredes', 'david.paredes@example.com', 'password123'),
('Adriana', 'Ochoa', 'adriana.ochoa@example.com', 'password123'),
('Héctor', 'Salazar', 'hector.salazar@example.com', 'password123'),
('Liliana', 'Rojas', 'liliana.rojas@example.com', 'password123'),
('Óscar', 'Ponce', 'oscar.ponce@example.com', 'password123'),
('Yolanda', 'Soto', 'yolanda.soto@example.com', 'password123'),
('Mario', 'Acosta', 'mario.acosta@example.com', 'password123'),
('Eva', 'Luna', 'eva.luna@example.com', 'password123'),
('Jaime', 'Solís', 'jaime.solis@example.com', 'password123'),
('Claudia', 'Mora', 'claudia.mora@example.com', 'password123'),
('Víctor', 'Aguilar', 'victor.aguilar@example.com', 'password123'),
('Irene', 'León', 'irene.leon@example.com', 'password123'),
('Sergio', 'Pineda', 'sergio.pineda@example.com', 'password123'),
('Esther', 'Gallardo', 'esther.gallardo@example.com', 'password123'),
('Arturo', 'Bravo', 'arturo.bravo@example.com', 'password123'),
('Luisa', 'Herrera', 'luisa.herrera@example.com', 'password123'),
('Manuel', 'Ibarra', 'manuel.ibarra@example.com', 'password123'),
('Paula', 'Zamora', 'paula.zamora@example.com', 'password123'),
('Alfredo', 'Vargas', 'alfredo.vargas@example.com', 'password123'),
('Cristina', 'Corona', 'cristina.corona@example.com', 'password123'),
('Pablo', 'Lara', 'pablo.lara@example.com', 'password123'),
('Rocío', 'Montes', 'rocio.montes@example.com', 'password123'),
('Gustavo', 'Ávila', 'gustavo.avila@example.com', 'password123'),
('Margarita', 'Rangel', 'margarita.rangel@example.com', 'password123'),
('Julio', 'Padilla', 'julio.padilla@example.com', 'password123'),
('Norma', 'Bautista', 'norma.bautista@example.com', 'password123'),
('Enrique', 'Cervantes', 'enrique.cervantes@example.com', 'password123'),
('Diana', 'Escobar', 'diana.escobar@example.com', 'password123'),
('Emilio', 'Valdez', 'emilio.valdez@example.com', 'password123'),
('Alicia', 'Gallegos', 'alicia.gallegos@example.com', 'password123'),
('Gabriel', 'Santana', 'gabriel.santana@example.com', 'password123'),
('Fabiola', 'Delgado', 'fabiola.delgado@example.com', 'password123'),
('Rafael', 'Marín', 'rafael.marin@example.com', 'password123'),
('Elvira', 'Parra', 'elvira.parra@example.com', 'password123'),
('Marcos', 'Arias', 'marcos.arias@example.com', 'password123'),
('Leticia', 'Figueroa', 'leticia.figueroa@example.com', 'password123'),
('Andrés', 'Carranza', 'andres.carranza@example.com', 'password123'),
('Graciela', 'Castañeda', 'graciela.castaneda@example.com', 'password123'),
('Hugo', 'Fuentes', 'hugo.fuentes@example.com', 'password123'),
('Constanza', 'Barajas', 'constanza.barajas@example.com', 'password123'),
('Joaquín', 'Nieto', 'joaquin.nieto@example.com', 'password123'),
('Estela', 'Chávez', 'estela.chavez@example.com', 'password123'),
('Samuel', 'Ríos', 'samuel.rios@example.com', 'password123'),
('Olivia', 'Salas', 'olivia.salas@example.com', 'password123'),
('Martín', 'Osorio', 'martin.osorio@example.com', 'password123'),
('Cecilia', 'Peralta', 'cecilia.peralta@example.com', 'password123'),
('Guillermo', 'Valenzuela', 'guillermo.valenzuela@example.com', 'password123'),
('Dolores', 'Escamilla', 'dolores.escamilla@example.com', 'password123'),
('Federico', 'Bonilla', 'federico.bonilla@example.com', 'password123'),
('Elisa', 'Cuevas', 'elisa.cuevas@example.com', 'password123'),
('Israel', 'Mercado', 'israel.mercado@example.com', 'password123'),
('Noemí', 'Quintana', 'noemi.quintana@example.com', 'password123'),
('Simón', 'Alarcón', 'simon.alarcon@example.com', 'password123'),
('Rebeca', 'Zavala', 'rebeca.zavala@example.com', 'password123'),
('Mauricio', 'Montero', 'mauricio.montero@example.com', 'password123'),
('Ángela', 'Carrillo', 'angela.carrillo@example.com', 'password123'),
('Benjamín', 'Sandoval', 'benjamin.sandoval@example.com', 'password123'),
('Eugenia', 'Esquivel', 'eugenia.esquivel@example.com', 'password123'),
('Abel', 'Maldonado', 'abel.maldonado@example.com', 'password123'),
('Amelia', 'Uribe', 'amelia.uribe@example.com', 'password123'),
('Gerardo', 'Benítez', 'gerardo.benitez@example.com', 'password123'),
('Brenda', 'Palacios', 'brenda.palacios@example.com', 'password123');

-- ============================================================
-- data_prestamos.sql
-- ============================================================
INSERT INTO prestamos (usuario_id, libro_id, fecha_prestamo, fecha_devolucion, fecha_retorno) VALUES
(1, 1, '2024-01-01 10:00:00', '2024-01-15 10:00:00', '2024-01-14 10:00:00'),
(2, 3, '2024-01-02 11:00:00', '2024-01-16 11:00:00', '2024-01-15 11:00:00'),
(3, 5, '2024-01-03 12:00:00', '2024-01-17 12:00:00', NULL),
(4, 7, '2024-01-04 13:00:00', '2024-01-18 13:00:00', '2024-01-17 13:00:00'),
(5, 9, '2024-01-05 14:00:00', '2024-01-19 14:00:00', NULL),
(6, 11, '2024-01-06 15:00:00', '2024-01-20 15:00:00', '2024-01-19 15:00:00'),
(7, 13, '2024-01-07 16:00:00', '2024-01-21 16:00:00', NULL),
(8, 15, '2024-01-08 17:00:00', '2024-01-22 17:00:00', '2024-01-21 17:00:00'),
(9, 17, '2024-01-09 18:00:00', '2024-01-23 18:00:00', NULL),
(10, 19, '2024-01-10 19:00:00', '2024-01-24 19:00:00', '2024-01-23 19:00:00'),
(11, 21, '2024-02-01 10:00:00', '2024-02-15 10:00:00', '2024-02-14 10:00:00'),
(12, 23, '2024-02-02 11:00:00', '2024-02-16 11:00:00', NULL),
(13, 25, '2024-02-03 12:00:00', '2024-02-17 12:00:00', '2024-02-16 12:00:00'),
(14, 27, '2024-02-04 13:00:00', '2024-02-18 13:00:00', NULL),
(15, 29, '2024-02-05 14:00:00', '2024-02-19 14:00:00', '2024-02-18 14:00:00'),
(16, 31, '2024-02-06 15:00:00', '2024-02-20 15:00:00', NULL),
(17, 33, '2024-02-07 16:00:00', '2024-02-21 16:00:00', '2024-02-20 16:00:00'),
(18, 35, '2024-02-08 17:00:00', '2024-02-22 17:00:00', NULL),
(19, 37, '2024-02-09 18:00:00', '2024-02-23 18:00:00', '2024-02-22 18:00:00'),
(20, 39, '2024-02-10 19:00:00', '2024-02-24 19:00:00', NULL),
(21, 41, '2024-03-01 10:00:00', '2024-03-15 10:00:00', '2024-03-14 10:00:00'),
(22, 43, '2024-03-02 11:00:00', '2024-03-16 11:00:00', NULL),
(23, 45, '2024-03-03 12:00:00', '2024-03-17 12:00:00', '2024-03-16 12:00:00'),
(24, 47, '2024-03-04 13:00:00', '2024-03-18 13:00:00', NULL),
(25, 49, '2024-03-05 14:00:00', '2024-03-19 14:00:00', '2024-03-18 14:00:00'),
(26, 51, '2024-03-06 15:00:00', '2024-03-20 15:00:00', NULL),
(27, 53, '2024-03-07 16:00:00', '2024-03-21 16:00:00', '2024-03-20 16:00:00'),
(28, 55, '2024-03-08 17:00:00', '2024-03-22 17:00:00', NULL),
(29, 57, '2024-03-09 18:00:00', '2024-03-23 18:00:00', '2024-03-22 18:00:00'),
(30, 59, '2024-03-10 19:00:00', '2024-03-24 19:00:00', NULL),
(31, 61, '2024-04-01 10:00:00', '2024-04-15 10:00:00', '2024-04-14 10:00:00'),
(32, 63, '2024-04-02 11:00:00', '2024-04-16 11:00:00', NULL),
(33, 65, '2024-04-03 12:00:00', '2024-04-17 12:00:00', '2024-04-16 12:00:00'),
(34, 67, '2024-04-04 13:00:00', '2024-04-18 13:00:00', NULL),
(35, 69, '2024-04-05 14:00:00', '2024-04-19 14:00:00', '2024-04-18 14:00:00'),
(36, 71, '2024-04-06 15:00:00', '2024-04-20 15:00:00', NULL),
(37, 73, '2024-04-07 16:00:00', '2024-04-21 16:00:00', '2024-04-20 16:00:00'),
(38, 75, '2024-04-08 17:00:00', '2024-04-22 17:00:00', NULL),
(39, 77, '2024-04-09 18:00:00', '2024-04-23 18:00:00', '2024-04-22 18:00:00'),
(40, 79, '2024-04-10 19:00:00', '2024-04-24 19:00:00', NULL),
(41, 81, '2024-05-01 10:00:00', '2024-05-15 10:00:00', '2024-05-14 10:00:00'),
(42, 83, '2024-05-02 11:00:00', '2024-05-16 11:00:00', NULL),
(43, 85, '2024-05-03 12:00:00', '2024-05-17 12:00:00', '2024-05-16 12:00:00'),
(44, 87, '2024-05-04 13:00:00', '2024-05-18 13:00:00', NULL),
(45, 89, '2024-05-05 14:00:00', '2024-05-19 14:00:00', '2024-05-18 14:00:00'),
(46, 91, '2024-05-06 15:00:00', '2024-05-20 15:00:00', NULL),
(47, 93, '2024-05-07 16:00:00', '2024-05-21 16:00:00', '2024-05-20 16:00:00'),
(48, 95, '2024-05-08 17:00:00', '2024-05-22 17:00:00', NULL),
(49, 97, '2024-05-09 18:00:00', '2024-05-23 18:00:00', '2024-05-22 18:00:00'),
(50, 99, '2024-05-10 19:00:00', '2024-05-24 19:00:00', NULL);
