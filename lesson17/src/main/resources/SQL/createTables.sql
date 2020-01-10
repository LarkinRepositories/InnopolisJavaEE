CREATE TABLE IF NOT EXISTS lesson17.USERS (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    birthday DATE,
    login_ID INTEGER NOT NULL UNIQUE,
    city VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TYPE IF NOT EXISTS lesson17.ROLE as ENUM (
    'Administration',
    'Clients',
    'Billing'
);

CREATE TABLE IF NOT EXISTS lesson17.ROLE (
    name ROLE,
    description VARCHAR(255)
    foreign key (ROLE_ID) references USERS_ROLE(user_id)
);

CREATE TABLE IF NOT EXISTS lesson17.USER_ROLE (
   id INTEGER PRIMARY KEY AUTO_INCREMENT,
   foreign key (USER_ID) references USERS(id),
   foreign key (USER_ROLE_ID) references ROLE(ROLE_ID)
);

CREATE TABLE IF NOT EXISTS LOGS (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    log_level VARCHAR(5),
    message VARCHAR(255)
);

