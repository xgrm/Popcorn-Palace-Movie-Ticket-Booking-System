CREATE TABLE IF NOT EXISTS task (
    description VARCHAR(64) NOT NULL,
    completed   VARCHAR(30) NOT NULL);

CREATE TABLE IF NOT EXISTS movies (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100) UNIQUE,
        genre VARCHAR(100) NOT NULL,
        duration INTEGER NOT NULL,
        rating NUMERIC(5, 2) NOT NULL,
        releaseYear INT,
        is_deleted BOOLEAN DEFAULT FALSE);

CREATE TABLE IF NOT EXISTS showtimes (
        id SERIAL PRIMARY KEY,
        movie_id INTEGER NOT NULL REFERENCES movies(id),
        price NUMERIC(5, 2) NOT NULL,
        theater VARCHAR(100) NOT NULL,
        start_time TIMESTAMP NOT NULL,
        end_time TIMESTAMP NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE);

CREATE TABLE IF NOT EXISTS bookings (
        bookingId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        showtimeId INTEGER NOT NULL REFERENCES showtimes(id),
        seatNumber INTEGER NOT NULL,
        userId UUID NOT NULL);