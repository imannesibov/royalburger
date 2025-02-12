/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package royalburger.service;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Iman
 */
public class PaymentService {

    private static final String VALID_BLINK_CODE = "123456";  

    // blink payment process
    public boolean processBlinkPayment(double totalAmount) {
        Scanner scanner = new Scanner(System.in);

        // formatting price
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Total Price: $" + df.format(totalAmount)); 

        System.out.print("Please enter your 6-digit Blink payment code: ");
        String enteredCode = scanner.nextLine();

        // checking blink code
        if (enteredCode.equals(VALID_BLINK_CODE)) {
            System.out.println("Payment successful. Your order is confirmed.");
            return true;
        } else {
            System.out.println("Invalid payment code. Payment failed.");
            return false;
        }
    }
}
