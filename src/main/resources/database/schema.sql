CREATE TABLE IF NOT EXISTS public.users(
	id SERIAL PRIMARY KEY,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL UNIQUE,
	role VARCHAR(20) NOT NULL CHECK (role IN ('admin', 'owner')),
	full_name VARCHAR(100) NOT NULL,
	phone_number VARCHAR(20)
);