CREATE TABLE Car (
    id UUID PRIMARY KEY,
    license_plate VARCHAR(255) NOT NULL UNIQUE,
    model_id UUID,
    location_id UUID,
    status VARCHAR(255) NOT NULL,
    price_per_hour DECIMAL(10, 2) NOT NULL,
    price_per_day DECIMAL(10, 2) NOT NULL,
    insurance_id UUID,
    FOREIGN KEY (model_id) REFERENCES CarModel(id),
    FOREIGN KEY (location_id) REFERENCES Location(id),
    FOREIGN KEY (insurance_id) REFERENCES Insurance(id)
)