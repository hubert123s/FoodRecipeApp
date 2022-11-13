INSERT INTO
    meal (id,name,preperation_time,description,type_meal)
VALUES
    (1,'toast',11,'Put in the toaster for about 2 minutes','breakfast');
INSERT INTO
    ingredients (id,name,amount,meal_id)
VALUES
    (1,'cheese',100,1),
    (2,'slice of bread',2,1);
INSERT INTO
    meal (id,name,preperation_time,description,type_meal)
VALUES
    (2,'hotdog',5,'Put the sausage with cheese in the roll','breakfast'),
    (3,'tortilla',25,'Description coming soon','dinner');
INSERT INTO
    ingredients (id,name,amount,meal_id)
VALUES
    (3,'cheese',50,2),
    (4,'roll',1,2),
    (5,'wheat flour',400,3),
    (6,'melted butter',30,3),
    (7,'salt',5,3);
INSERT INTO
    subscriber(id,name,email,type_newsletter,created_date)
VALUES
    (1,'hubert','hubert.antoniszyn98@gmail.com','daily','2022-10-30');