package application.inventory;

import java.util.Arrays;
import java.util.List;

import application.constant.Constants;
import application.constant.Unit;
import application.order.OrderItem;
import application.utils.CommonUtils;

public class Inventory {

    private List<Ingredient> fruits;
    private List<Ingredient> additives;

    public Inventory() {
        // Initializes ingredients
        double min = 1000, max = 5000;
        fruits = Arrays.asList(
                new Ingredient("Strawberry", CommonUtils.randomDoubleWithRound(min, max, 0), 50, Unit.GRAM),
                new Ingredient("Banana", CommonUtils.randomDoubleWithRound(min, max, 0), 60, Unit.GRAM),
                new Ingredient("Mango", CommonUtils.randomDoubleWithRound(min, max, 0), 70, Unit.GRAM));

        additives = Arrays.asList(
                new Ingredient("Ice", CommonUtils.randomDoubleWithRound(min, max, 0), 30, Unit.MILLILIT),
                new Ingredient("Milk", CommonUtils.randomDoubleWithRound(min, max, 0), 20, Unit.MILLILIT),
                new Ingredient("Sugar", CommonUtils.randomDoubleWithRound(min, max, 0), 8, Unit.GRAM));
    }

    public void showInventory() {
        fruits.forEach(System.out::println);
        additives.forEach(System.out::println);
    }

    /**
     * Check the additives are enough to order.
     * 
     * @return
     */
    public boolean isAdditivesAvailable() {
        for (Ingredient i : additives) {
            double remaining = i.getRemainingMass() - i.getRequireMass() * 3;
            if (remaining <= 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check fruits are enough to order.
     * 
     * @param fruits
     * @return
     */
    public boolean isFruitsAvailable(List<Ingredient> fruits) {
        int numOfFruits = fruits.size();
        double smassPerFruit = (Constants.DEFAULT_SIZE / numOfFruits) / 100;

        for (Ingredient i : fruits) {
            double remaining = i.getRemainingMass() - i.getRequireMass() * smassPerFruit;
            if (remaining <= 0) {
                return false;
            }

            i.setRemainingMass(remaining);
        }

        return true;
    }

    /**
     * Reduces mass of ingredients.
     * 
     * @param orderItem
     */
    public void reduceIngredients(OrderItem orderItem) {
        for (Ingredient orderFruit : orderItem.getFruits()) {
            for (Ingredient inventoryFruit : fruits) {
                if (orderFruit == inventoryFruit) {
                    inventoryFruit.setRemainingMass(orderFruit.getRemainingMass());
                }
            }
        }

        for (Ingredient i : additives) {
            i.setRemainingMass(i.getRemainingMass() - i.getRequireMass() * 3);
        }
    }

    public List<Ingredient> getFruits() {
        return fruits;
    }

    public List<Ingredient> getAdditives() {
        return additives;
    }

}
