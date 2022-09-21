package controller;

import model.Ingredient;

import java.util.HashMap;

public class IngredientController implements IngredientControllerI {

    private HashMap<String, Ingredient> ingredients;

    public IngredientController() {
        this.ingredients = new HashMap<>();
    }

    @Override
    public Ingredient storeIngredient(Ingredient ingredient) {
        Ingredient ingredientStored = ingredients.get(ingredient.getName());
        if (ingredientStored != null) {
            if(ingredientStored.getUnit() != ingredient.getUnit()){
                throw new RuntimeException("Forskellige units");
            }else{
                ingredientStored.setAmount(ingredientStored.getAmount() + ingredient.getAmount());
                return ingredientStored;
            }
        } else {
            ingredients.put(ingredient.getName(), ingredient);
            return ingredient;
        }
    }

    @Override
    public void collectIngredient(String name, int amount) {
        Ingredient ingredientStored = ingredients.get(name);
        if (ingredientStored != null) {
            if(ingredientStored.getAmount() - amount < 0){
                throw new RuntimeException("Der er ikke nok i lagerbeholdningerded");
            }else{
                ingredientStored.setAmount(ingredientStored.getAmount() - amount);
            }

        } else {
            String message = String.format("No supply of '%s' registered", name);
            throw new RuntimeException(message);
        }
    }
}
