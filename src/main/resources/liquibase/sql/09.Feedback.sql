CREATE TABLE Feedback (
    id UUID PRIMARY KEY,
    user_id UUID,
    car_id UUID,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES "User"(id),
    FOREIGN KEY (car_id) REFERENCES Car(id)
);