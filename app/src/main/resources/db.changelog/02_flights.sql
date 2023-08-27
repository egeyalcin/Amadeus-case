CREATE TABLE public.flights(
    id bigserial NOT NULL,
    departure_airport_id bigserial NOT NULL,
    landing_airport_id bigserial NOT NULL,
    departure_date timestamp NULL,
    landing_date timestamp NULL,
    price int NOT NULL,
    created_at timestamp NULL,
    updated_at timestamp NULL,
    CONSTRAINT flights_pkey PRIMARY KEY (id),
    CONSTRAINT fkc5yw0b4bmr3gbroogs67atx FOREIGN KEY (departure_airport_id) REFERENCES public.airport(id),
    CONSTRAINT fkc5yw0b4bmr3gbroogdss67atx FOREIGN KEY (landing_airport_id) REFERENCES public.airport(id)
);