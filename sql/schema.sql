CREATE TABLE rooms (
  id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  capacity INTEGER
);

CREATE TABLE users (
  id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(100)
);
  
CREATE TABLE trainings (
  id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  "limit" INTEGER,
  date TIMESTAMP,
  duration INTEGER,
  description TEXT,
  organizer_id INTEGER,
  room_id INTEGER,

  CONSTRAINT fk_trainings_organizer
    FOREIGN KEY (organizer_id) REFERENCES users (id)
);

CREATE TABLE training_user (
  training_id INTEGER,
  user_id INTEGER,

  CONSTRAINT fk_training_user_training
    FOREIGN KEY (training_id) REFERENCES trainings (id),
  CONSTRAINT fk_training_user_user
    FOREIGN KEY (user_id) REFERENCES users (id)
);


INSERT INTO rooms (name, capacity)
VALUES
  ('Hedgehog', 6),
  ('Heart', 6),
  ('Aquarium', 15);

INSERT INTO users (name, email, password)
VALUES
  ('Rafał Piotrowski', 'rafal.piotrowski@siili.com', 'secret'),
  ('Michał Światowy', 'michal.swiatowy@siili.com', 'secret')
  ('Szymon Pacanowski', 'szymon.pacanowski@siili.com', 'secret');
