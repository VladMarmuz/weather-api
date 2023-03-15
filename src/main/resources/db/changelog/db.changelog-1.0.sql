    --liquibase formatted sql

    --changeset vmarmuz:1
    CREATE TABLE IF NOT EXISTS weather_data
    (
        id BIGSERIAL PRIMARY KEY ,
        temperature INT NOT NULL,
        wind_speed FLOAT(8) NOT NULL,
        atmosphere_pressure INT NOT NULL,
        air_humidity INT NOT NULL,
        weather_conditions VARCHAR(64) NOT NULL,
        location VARCHAR(64) NOT NULL,
        weather_date DATE NOT NULL
    );