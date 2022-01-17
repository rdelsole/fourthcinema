INSERT INTO "PUBLIC"."ROOMS" ( "NUMBER", "CAPACITY" ) VALUES
(1 , 250), (2 , 250), (3 , 250), (4 , 250), (5 , 250), (6 , 250),(7 , 250), (8 , 250), (9 , 250);

INSERT INTO "PUBLIC"."MOVIES" ( "ID", "IMDB_ID", "TITLE" ) VALUES
( 1, 'tt0232500', 'The Fast and the Furious' ),
( 2, 'tt0322259', '2 Fast 2 Furious'),
( 3, 'tt0463985', 'The Fast and the Furious: Tokyo Drift'),
( 4, 'tt1013752', 'Fast & Furious'),
( 5, 'tt1596343', 'Fast Five'),
( 6, 'tt1905041', 'Fast & Furious 6'),
( 7, 'tt2820852', 'Furious 7'),
( 8, 'tt4630562', 'The Fate of the Furious'),
( 9, 'tt5433138', 'F9: The Fast Saga');


INSERT INTO "PUBLIC"."MOVIES_ROOMS_SCHEDULE" ("HOUR", "PRICE", "MOVIE_ID", "ROOM_NUMBER" ) VALUES
( TIME'15:00:00', 15.0 , 1, 1 ), ( TIME'18:00:00', 25.0 , 1, 1 ), ( TIME'21:00:00', 35.0 , 1, 1 ),
( TIME'15:00:00', 15.0 , 2, 1 ), ( TIME'18:00:00', 25.0 , 2, 1 ), ( TIME'21:00:00', 35.0 , 2, 1 ),
( TIME'15:00:00', 15.0 , 3, 1 ), ( TIME'18:00:00', 25.0 , 3, 1 ), ( TIME'21:00:00', 35.0 , 3, 1 ),
( TIME'15:00:00', 15.0 , 4, 1 ), ( TIME'18:00:00', 25.0 , 4, 1 ), ( TIME'21:00:00', 35.0 , 4, 1 ),
( TIME'15:00:00', 15.0 , 5, 1 ), ( TIME'18:00:00', 25.0 , 5, 1 ), ( TIME'21:00:00', 35.0 , 5, 1 ),
( TIME'15:00:00', 15.0 , 6, 1 ), ( TIME'18:00:00', 25.0 , 6, 1 ), ( TIME'21:00:00', 35.0 , 6, 1 ),
( TIME'15:00:00', 15.0 , 7, 1 ), ( TIME'18:00:00', 25.0 , 7, 1 ), ( TIME'21:00:00', 35.0 , 7, 1 ),
( TIME'15:00:00', 15.0 , 8, 1 ), ( TIME'18:00:00', 25.0 , 8, 1 ), ( TIME'21:00:00', 35.0 , 8, 1 ),
( TIME'15:00:00', 15.0 , 9, 1 ), ( TIME'18:00:00', 25.0 , 9, 1 ), ( TIME'21:00:00', 35.0 , 9, 1 );