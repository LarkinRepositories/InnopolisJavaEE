
CREATE TABLE public.users
(
    id serial NOT NULL,
    name varchar(255) NOT NULL,
    birthday DATE NOT NULL,
    login_id int4 NOT NULL,
    city varchar(255) NULL,
    email varchar(255) NULL,
    description varchar(500) NULL,
    CONSTRAINT login_id_un UNIQUE (login_id),
    CONSTRAINT users_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);
CREATE TYPE ROLE AS ENUM ('Administration', 'Clients', 'Billing');

CREATE TABLE public.roles
(
    id serial NOT NULL,
    name ROLE,
    description varchar(500) NULL,
    CONSTRAINT roles_pk PRIMARY KEY (id),
    CONSTRAINT roles_un UNIQUE (name)
)
WITH (
    OIDS = FALSE
);


CREATE TABLE public.user_role
(
    id serial NOT NULL,
    user_id int4 NOT NULL,
    role_id int4 NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public.logs
(
    id serial NOT NULL,
    "date" date NULL,
    log_level varchar(5) NULL,
    message varchar(255) NULL,
    "exception" varchar(2000) NULL
)
WITH (
    OIDS = FALSE
);