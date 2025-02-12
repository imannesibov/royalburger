package royalburger.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import royalburger.model.Product;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Iman
 */
public class OrderService {

    private static OrderService instance; // Singleton instance
    private Map<Product, Integer> cart;  // Cart storage

    // Private constructor to prevent external instantiation
    private OrderService() {
        cart = new HashMap<>();
    }

    // Public method to return the single instance
    public static OrderService getInstance() {
        if (instance == null) { // Lazy initialization
            instance = new OrderService();
        }
        return instance;
    }

//adding product to cart
    public void addProductToCart(Product product) {
        System.out.println("addProductToCart() start");
        cart.put(product, cart.getOrDefault(product, 0) + 1);

        System.out.println("addProductToCart() end");

    }

//displaying cart
    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            double totalPrice = 0;
            DecimalFormat df = new DecimalFormat("#.00");

            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double productTotalPrice = product.getPrice() * quantity;
                totalPrice += productTotalPrice;

                System.out.println(product.getName() + " x" + quantity + " - $" + df.format(productTotalPrice));
            }

            System.out.println("Total Price: $" + df.format(totalPrice));
        }
    }

    // Calculating total price
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public void confirmOrder() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Please add items to your cart before confirming the order.");
        } else {
            // submitting order
            System.out.println("Your order has been confirmed.");

            System.out.println("service girmeden onceki cart : " + cart);
            ReceiptService.generateReceipt(this, new ArrayList<>(cart.keySet()));
            // cleaning cart
            cart.clear();
        }
    }

    // generating random order number
    public String generateOrderNumber() {
        UUID uuid = UUID.randomUUID(); // UUID  
        String orderNumber = "#" + uuid.toString().substring(0, 6).toUpperCase(); // getting first 6 characters
        return orderNumber;
    }

    // creating receipt
    public void generateReceipt() {
//        TEST
        System.out.println("generateReceipt() start");
        ReceiptService.generateReceipt(this, new ArrayList<>(cart.keySet()));

// Map<Product, Integer>
//Map<Product, Integer> cart1 = new HashMap<>();
// List<Product> 
//List<Product> cart2 = new ArrayList<>(cart1.keySet());
//        
//        //
//        String orderNumber = generateOrderNumber();
//        Date now = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String formattedDate = sdf.format(now);
//
//        System.out.println("\n----- Receipt -----");
//        System.out.println("Order Number: " + orderNumber);
//        System.out.println("Date: " + formattedDate);
//        System.out.println("-------------------");
//
//        double totalPrice = 0;
//        DecimalFormat df = new DecimalFormat("#.00");
//        
//        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
//            Product product = entry.getKey();
//            int quantity = entry.getValue();
//            double productTotalPrice = product.getPrice() * quantity;
//            totalPrice += productTotalPrice;
//
//            // 
//            System.out.println(product.getName() + " x" + quantity + " - $" + df.format(product.getPrice()) + " each - $" + df.format(productTotalPrice));
//        }
//
//        //
//        System.out.println("\nTotal Price: $" + df.format(totalPrice));
//        System.out.println("-------------------");
    }
}
