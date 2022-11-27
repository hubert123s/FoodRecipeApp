# FoodRecipeApp
## Tech/framework used 
• Java 17

• Spring Boot

• Maven

• H2 Database

• Liquibase

• Lombok

• Hibernate/Jpa

• Integration tests

### How to run:
- Open project with InteliJ, go to: **src/main/java/com/example/foodrecipeapp/FoodRecipeAppApplication.java -> right click to FoodRecipeAppApplication.java -> Run 'FoodRecipeAppApplication.java.main()'**
- Open command line and type: **mvn spring-boot:run**
### Endpoints:

### MealController
####  GET /meal - return all meals
####  GET /meal/{id} - return meal by id
####  GET /meal/{id}/ingredient - return meal ingredient
####  POST /meal - save new meal
####  PUT /meal/{id} - update meal
####  GET /meal/name - return meal by name
####  GET /meal/name/image - return photo of the meal from PixaBay API
####  GET /meal/ingredients - returns meals without the selected ingredients

### SubscriberController
####  POST /subscriber/add - add new subscriber

### IngredientController
####  GET /ingredient/{id} - return ingredient by id
####  POST /ingredient - save new ingredient
####  DELETE /ingredient/{id} 






