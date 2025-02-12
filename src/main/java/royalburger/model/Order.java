/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package royalburger.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iman
 */
public class Order {

    private List<Product> products = new ArrayList<>();
    private double totalPrice;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (Product product : products) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
            double total = calculateTotal();
            System.out.println("Total Price: $" + total);
        }
    }

    private double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}
