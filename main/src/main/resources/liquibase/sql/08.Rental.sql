CREATE TABLE Rental (
    id UUID PRIMARY KEY,
    car_id UUID,
    user_id UUID,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (car_id) REFERENCES Car(id),
    FOREIGN KEY (user_id) REFERENCES "User"(id)
);