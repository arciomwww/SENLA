CREATE TABLE Insurance (
    id UUID PRIMARY KEY,
    policy_number VARCHAR(255) NOT NULL UNIQUE,
    provider VARCHAR(255) NOT NULL,
    coverage_amount DECIMAL(10, 2) NOT NULL
);