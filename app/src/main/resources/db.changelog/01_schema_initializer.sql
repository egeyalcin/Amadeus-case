CREATE TABLE public.address (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	"version" int8 NULL,
	city varchar(255) NULL,
	country varchar(255) NULL,
	town varchar(255) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	"version" int8 NULL,
	email varchar(255) NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_information (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	"version" int8 NULL,
	birth_day timestamp NOT NULL,
	first_name varchar(25) NULL,
	gender varchar(255) NULL,
	image_path varchar(255) NULL,
	last_name varchar(25) NOT NULL,
	verified_at timestamp NULL,
	address_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT user_information_pkey PRIMARY KEY (id),
	CONSTRAINT fkc5yw0b4bmr3gbroogs67atvlx FOREIGN KEY (user_id) REFERENCES public.users(id),
	CONSTRAINT fkc7knsmqtfuwr1ib3e2bp8v6f3 FOREIGN KEY (address_id) REFERENCES public.address(id)
);


CREATE TABLE public.airport (
    id bigserial NOT NULL,
    city varchar(255) NOT NULL,
    created_at timestamp NULL,
    updated_at timestamp NULL,
    CONSTRAINT airport_pkey PRIMARY KEY (id)
);
