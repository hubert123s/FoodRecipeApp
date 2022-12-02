FROM openjdk:19
ADD target/FoodRecipeApp-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar FoodRecipeApp-0.0.1-SNAPSHOT.jar
