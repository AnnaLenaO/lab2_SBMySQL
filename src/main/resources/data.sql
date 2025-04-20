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
    ('Sleepy A', 'io', 'private', 'Hotel without elevator', ST_GeomFromText('POINT(63.5400 18.6901)', 4326), 1),
    ('Garden Buss', 'pb', 'public', 'Fun playground for older children', ST_GeomFromText('POINT(62.5809 18.6900)', 4326), 3),
    ('Sleepy B', 'io', 'public', 'Hotel with several elevators', ST_GeomFromText('POINT(63.2400 28.6923)', 4326), 1),
    ('Emergency Red', 'kj', 'public', 'Best hospital in town', ST_GeomFromText('POINT(63.5401 13.6700)', 4326), 4),
    ('Red eggs', 'io', 'private', 'Big eggs with chocolate', ST_GeomFromText('POINT(67.5465 15.6903)', 4326), 5),
    ('Cake', 'kj', 'public', 'Clue to big treasure', ST_GeomFromText('POINT(61.5400 38.5454)', 4326), 6),
    ('Apple Pizza', 'mj', 'public', 'Serving pizza with 20 different apples', ST_GeomFromText('POINT(61.5428 24.6905)', 4326), 2);
