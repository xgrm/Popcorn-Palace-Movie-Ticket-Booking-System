CREATE TABLE IF NOT EXISTS task (
    description VARCHAR(64) NOT NULL,
    completed   VARCHAR(30) NOT NULL);

CREATE TABLE IF NOT EXISTS movies (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        genre VARCHAR(100) NOT NULL,
        duration INTEGER NOT NULL,
        rating NUMERIC(5, 2) NOT NULL,
        releaseYear INT CHECK (releaseYear BETWEEN 1900 AND 2100));

CREATE TABLE IF NOT EXISTS showtimes (
        id SERIAL PRIMARY KEY,
        movieId INTEGER NOT NULL REFERENCES movies(id),
        price NUMERIC(5, 2) NOT NULL,
        theater VARCHAR(100) NOT NULL,
        startTime DATE NOT NULL,
        endTime DATE NOT NULL);

CREATE TABLE IF NOT EXISTS bookings (
        bookingId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        showtimeId INTEGER NOT NULL REFERENCES showtimes(id),
        seatNumber INTEGER NOT NULL,
        userId UUID NOT NULL);