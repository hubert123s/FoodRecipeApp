INSERT INTO
    meal (id,name,preperation_time,description,type_meal)
VALUES
    (1,'tosty',11,'Wlozyc do tostera na około 2 minuty','breakfast');
INSERT INTO
    ingredients (id,name,amount,meal_id)
VALUES
    (1,'ser',100,1),
    (2,'kromka chleba',2,1);
INSERT INTO
    meal (id,name,preperation_time,description,type_meal)
VALUES
    (2,'hotdog',5,'Do bulki wkladamy parowke z serem','breakfast'),
    (3,'tortilla',25,'opis wkrotce','dinner');
INSERT INTO
    ingredients (id,name,amount,meal_id)
VALUES
    (3,'ser',50,2),
    (4,'bulka',1,2),
    (5,'maka pszenna tortowa',400,3),
    (6,'roztopione maslo',30,3),
    (7,'sol',5,3);