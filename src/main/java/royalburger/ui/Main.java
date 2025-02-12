/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package royalburger.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import royalburger.model.Category;
import royalburger.model.Product;
import royalburger.service.OrderService;
import royalburger.service.PaymentService;

/**
 *
 * @author Iman
 */
public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
        // products according to categories
        Product burger1 = new Product("Cheddar Beef", 5.99);
        Product burger2 = new Product("Grilled Chicken", 6.49);
        Product burger3 = new Product("Veggie Delight", 6.99);

        Product beverage1 = new Product("Lemon Breeze", 1.99);
        Product beverage2 = new Product("Fizzy Orange", 1.89);

        Product dessert1 = new Product("Chocolate Bliss", 2.99);
        Product dessert2 = new Product("Vanilla Dream", 2.49);
        Product dessert3 = new Product("Caramel Crunch", 2.49);

        // creating categories
        Category burgers = new Category("Burgers", Arrays.asList(burger1, burger2, burger3));
        Category beverages = new Category("Beverages", Arrays.asList(beverage1, beverage2));
        Category desserts = new Category("Desserts", Arrays.asList(dessert1, dessert2, dessert3));

        //cart and payment process
        OrderService orderService =  OrderService.getInstance();
        PaymentService paymentService = new PaymentService();

        // main menu
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome to RoyalBurger!");
            System.out.println("Please choose a category:");
            System.out.println("1. Burgers");
            System.out.println("2. Beverages");
            System.out.println("3. Desserts");
            System.out.println("4. View Cart");
            System.out.println("5. Confirm Order");
            System.out.println("6. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); //cleaning buffer

            switch (choice) {
                case 1:
                    displayProducts(burgers, orderService);
                    break;
                case 2:
                    displayProducts(beverages, orderService);
                    break;
                case 3:
                    displayProducts(desserts, orderService);
                    break;
                case 4:
                    orderService.displayCart(); // display cart and total price
                    break;
                case 5:
                    processPayment(paymentService, orderService);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        scanner.close();
    }

    // displaying selected products
    public void displayProducts(Category category, OrderService orderService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available " + category.getName() + ":");
        List<Product> products = category.getProducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName() + " - $" + products.get(i).getPrice());
        }

        System.out.print("Please select a product (or press 0 to go back): ");
        int productChoice = scanner.nextInt();

        if (productChoice == 0) {
            return;
        }

        if (productChoice > 0 && productChoice <= products.size()) {
            Product selectedProduct = products.get(productChoice - 1);
            orderService.addProductToCart(selectedProduct);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void processPayment(PaymentService paymentService, OrderService orderService) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // cleaning buffer
        boolean paymentSuccessful = false;
        double totalAmount = orderService.calculateTotal(); // calculate total price

        paymentSuccessful = paymentService.processBlinkPayment(totalAmount); // blink payment process

        if (paymentSuccessful) {
            System.out.println("Payment successful!");
            //submitting order and displaying receiptSiparişi onayla ve sepeti temizle
            orderService.confirmOrder();  // / submiting order and cleaning cart
//            orderService.generateReceipt();  // displaying receipt

        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

}
