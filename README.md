# FoodRecipeApp
## Tech/framework used 
• Java 17

• Spring Boot

• Maven

• H2 Database

• Liquibase

• Lombok

• JUnit

### How to run:
- Open project with InteliJ, go to: **src/main/java/com/example/foodrecipeapp/FoodRecipeAppApplication.java -> right click to FoodRecipeAppApplication.java -> Run 'FoodRecipeAppApplication.java.main()'**
- Open command line and type: **mvn spring-boot:run**
### Endpoints:

### MealController
####  GET /meal - return all meals
####  GET /meal/{id} - return meal by id
####  GET /meal/{id}/ingredients - return meal ingredients
####  POST /meal - save new meal
####  PUT /meal/{id} - update meal
####  GET /meal/name - return meal by name
####  GET /meal/name/image - return photo of the meal from PixaBay API
####  GET /meal/ingredient - returns meals without the selected ingredient

### SubscriberController
####  POST /subscriber/add - add new subscriber

### IngredientsController
####  GET /ingredients/{id} - return ingredients by id
####  POST /ingredients - save new ingredients
####  DELETE /ingredients/{id} 






