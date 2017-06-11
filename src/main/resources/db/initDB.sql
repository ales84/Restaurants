DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1;
CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  CONSTRAINT user_name_idx UNIQUE (name)
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL,
  CONSTRAINT restaurants_name_idx UNIQUE (name)
);

CREATE TABLE menus (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  date          TIMESTAMP           DEFAULT now(),
  CONSTRAINT menus_restaurant_date_idx UNIQUE (restaurant_id, date),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes (
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_id INTEGER NOT NULL,
  name    VARCHAR NOT NULL,
  price   NUMERIC NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  date          TIMESTAMP DEFAULT now(),
  CONSTRAINT votes_user_date_idx UNIQUE (user_id, date),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
)
