package application.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import application.inventory.Ingredient;

public class OrderItem {

    private List<Ingredient> fruits;
    private LocalDateTime created;

    public OrderItem(List<Ingredient> fruits) {
        this.fruits = fruits;
        this.created = LocalDateTime.now();
    }

    public void addFruit(Ingredient fruit) {
        this.fruits.add(fruit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(created.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .append(" :: Smoothie: ");
        for (Ingredient i : fruits) {
            sb.append(i.getName()).append(" ,");
        }
        return sb.toString();
    }

    public List<Ingredient> getFruits() {
        return fruits;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
