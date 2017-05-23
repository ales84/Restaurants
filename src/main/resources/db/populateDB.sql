DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (name, password) VALUES
  ('user1', 'pass1'),
  ('user2', 'pass2'),
  ('admin', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_USER', 2),
  ('ROLE_ADMIN', 3);

INSERT INTO restaurants (name) VALUES
  /*4*/('Бочонок'),
  /*5*/('Форпост'),
  /*6*/('Рубин');

INSERT INTO menus (restaurant_id, date) VALUES
  /*7*/(4, now()),
  /*8*/(5, now()),
  /*9*/(6, now());

INSERT INTO dishes (menu_id, name, price) VALUES
  /*10*/(7, 'Шашлык', 500),
  /*11*/(7, 'Салат', 200),
  /*12*/(8, 'Утка', 700),
  /*13*/(9, 'Окрошка', 200),
  /*14*/(9, 'Хлеб', 50),
  /*15*/(9, 'Салат', 100);