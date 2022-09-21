package controller;

import model.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientControllerTest {

    @Test
    void storeIngredient() {
        // Arrange
        var ingredient1 = new Ingredient("Butter", Ingredient.Unit.PCS,10);
        var ingredient2 = new Ingredient("Butter", Ingredient.Unit.TSP,10);
        IngredientController ingredientController = new IngredientController();

        // Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ingredientController.storeIngredient(ingredient1);
            ingredientController.storeIngredient(ingredient2);
        });

        // Assert
        assertEquals(exception.getMessage(),"Forskellige units");
    }

    @Test
    void collectIngredient() {
        //Arrange
        IngredientController ingredientController = new IngredientController();
        Ingredient ingredient = new Ingredient("Butter", Ingredient.Unit.GRAM,150);
        ingredientController.storeIngredient(ingredient);

        Exception exception = assertThrows(RuntimeException.class,() -> {
            ingredientController.collectIngredient("Butter",200);
        });

        assertEquals(exception.getMessage(),"Der er ikke nok i lagerbeholdningerded");
    }
}