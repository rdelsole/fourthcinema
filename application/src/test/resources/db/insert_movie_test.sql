DELETE FROM "PUBLIC"."MOVIES_ROOMS_SCHEDULE";

DELETE FROM "PUBLIC"."ROOMS";

DELETE FROM "PUBLIC"."MOVIES";

INSERT INTO "PUBLIC"."ROOMS" ( "NUMBER", "CAPACITY" ) VALUES
(1 , 250);

INSERT INTO "PUBLIC"."MOVIES" ( "ID", "IMDB_ID", "TITLE" ) VALUES
( 20, 'simpleId', 'Integration Test' );

INSERT INTO "PUBLIC"."MOVIES_ROOMS_SCHEDULE" ("ID", "HOUR", "PRICE", "MOVIE_ID", "ROOM_NUMBER" ) VALUES
( 30, TIME'20:00:00', 15.0 , 20, 1 );