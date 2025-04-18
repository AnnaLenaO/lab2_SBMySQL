SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE category;
TRUNCATE TABLE location;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO category(name, symbol, description)
VALUES
    ('Hotel', '🏨', 'place to sleep'),
    ('Restaurant', '🍽', 'place to eat'),
    ('Playground', '🛝', 'place to play'),
    ('Hospital', '🏥', 'place for healthcare'),
    ('Treasure', '🍭', 'place with hidden treasure'),
    ('Clue', '🐣', 'place with clue to treasure');

INSERT INTO location (name, person_id, status, description, coordinate, category)
VALUES
    ('Sleepy A', 'io', 'private', 'Hotel without elevator', '1234', 1),
    ('Garden Buss', 'pb', 'public', 'Fun playground for older children', '96348', 3),
    ('Sleepy B', 'io', 'public', 'Hotel with several elevators', '870874', 1),
    ('Emergency Red', 'kj', 'public', 'Best hospital in town', '43546', 4),
    ('Red eggs', 'io', 'private', 'Big eggs with chocolate', '43546', 5),
    ('Cake', 'kj', 'public', 'Clue to big treasure', '984', 6),
    ('Apple Pizza', 'mj', 'public', 'Serving pizza with 20 different apples', '452', 2);
