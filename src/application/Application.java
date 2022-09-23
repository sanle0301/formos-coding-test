package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.inventory.Ingredient;
import application.inventory.Inventory;
import application.order.Order;
import application.order.OrderItem;
import application.utils.CommonUtils;

public class Application {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Order order = new Order();
        boolean isExit = false;

        while (!isExit) {
            System.out.println("WELCOME TO SMOOTHIE SHOP");
            System.out.println("[1] Create order");
            System.out.println("[2] List ingredients");
            System.out.println("[3] Daily sales report");
            System.out.println("[0] Exit");

            System.out.print("> Enter a number: ");
            int key = CommonUtils.getInputAsInt(sc);

            switch (key) {
            case 1:
                System.out.println("\n--- Create Order ---");
                createOrder(inventory, order);
                System.out.println("----------\n");
                break;
            case 2:
                System.out.println("\n--- Inventory ---");
                inventory.showInventory();
                System.out.println("----------\n");
                break;
            case 3:
                System.out.println("\n--- Daily Sales Report ---");
                order.showOrderReport();
                System.out.println("----------\n");
                break;
            case 0:
                sc.close();
                isExit = true;
                System.out.println("----------\n");
                break;
            default:
                System.out.println(
                        "!!! Your input is not valid.\nPlease enter a corresponding number according to the menu.");
                break;
            }
        }

        System.out.println("!!! GOODBYE!");
    }

    /**
     * Create an order
     * @param inventory
     * @param order
     */
    private static void createOrder(Inventory inventory, Order order) {
        if (!inventory.isAdditivesAvailable()) {
            System.out.println("\n!!! Not enough additives to order.\n");
            return;
        }

        List<Ingredient> fruits = new ArrayList<>(inventory.getFruits());
        List<Ingredient> orderedFruits = new ArrayList<>();
        boolean orderCheck = true;

        while (!fruits.isEmpty()) {
            for (int i = 0; i < fruits.size(); i++) {
                System.out.format("[%d] %s\n", i + 1, fruits.get(i).getName());
            }

            if (orderedFruits.isEmpty()) {
                System.out.println("[0] Cancel order");
                System.out.print("> Choose your smoothie: ");
            } else {
                System.out.println("[0] No, thanks");
                System.out.print("> Choose your mixed fruit: ");
            }

            int key = CommonUtils.getInputAsInt(sc);
            if (key == 0) {
                break;
            }

            if (key < 0 || key > fruits.size()) {
                System.out.println("!!! Your input is not valid.");
                continue;
            }

            orderedFruits.add(fruits.remove(key - 1));
            orderCheck = inventory.isFruitsAvailable(orderedFruits);

            if (!orderCheck) {
                break;
            }
        }

        if (orderedFruits.isEmpty()) {
            return;
        }

        if (!orderCheck) {
            System.out.format("!!! Not enough fruit to order.\n%s\n", orderedFruits.get(orderedFruits.size() - 1));
            return;
        }

        OrderItem orderItem = new OrderItem(orderedFruits);
        inventory.reduceIngredients(orderItem);
        order.addOrder(orderItem);
        System.out.println("\n!!! Order success.");
        System.out.println(orderItem);
    }
}
