CREATE TABLE IF NOT EXISTS armour (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    armour_name VARCHAR (50) NOT NULL,
    type_value VARCHAR (50) NOT NULL,
    description TEXT
)