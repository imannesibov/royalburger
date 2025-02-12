/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package royalburger.service;

import java.text.DecimalFormat;
import java.util.List;
import royalburger.model.Product;

/**
 *
 * @author Iman
 */
public class ReceiptService {

    // printing receipt
    public static void generateReceipt(OrderService orderService, List<Product> cart) {
        System.out.println("generate receipt cart:  " + cart );
        //generating order number
        String orderNumber = orderService.generateOrderNumber();

        // getting date and time
        String date = java.time.LocalDate.now().toString(); // dd/mm/yyyy 
        String time = java.time.LocalTime.now().toString().substring(0, 5); // (HH:mm)

        // receipt title
        System.out.println("==================================");
        System.out.println("            RoyalBurger           ");
        System.out.println("           Order Receipt          ");
        System.out.println("==================================");
        System.out.println("Order Number: " + orderNumber);
        System.out.println("Date: " + date + " " + time);
        System.out.println("----------------------------------");
        
        // list of products
        double totalAmount = 0;
        DecimalFormat df = new DecimalFormat("#.00");

        for (Product product : cart) {
            double productTotal = product.getPrice(); // product price
            totalAmount += productTotal;
            System.out.println(product.getName() + " - " + product.getQuantity() + " x $" + df.format(product.getPrice()) + " - $" + df.format(productTotal));
        }

        // total price
        System.out.println("----------------------------------");
        System.out.println("Total: $" + df.format(totalAmount));
        System.out.println("==================================");
        System.out.println("Thank you for your purchase!");
        System.out.println("==================================");
    }
}
