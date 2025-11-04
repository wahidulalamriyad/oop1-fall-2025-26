package Project;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bangladesh Mortgage Calculator (BDT) ===");

        // Input: principal, annual interest rate, period in years
        System.out.print("Enter loan amount (Principal in BDT): ");
        double principal = scanner.nextDouble();

        System.out.print("Enter annual interest rate (e.g., 8.5 for 8.5%): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter loan period (in years): ");
        int years = scanner.nextInt();

        // Convert annual interest rate to monthly and years to months
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        int numberOfPayments = years * 12;

        // Calculate monthly mortgage payment
        double mortgagePayment = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        // Calculate total payment and interest
        double totalPayment = mortgagePayment * numberOfPayments;
        double totalInterest = totalPayment - principal;

        // Format results for Bangladeshi currency
        Locale bdLocale = Locale.of("en", "BD");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(bdLocale);

        System.out.println("\n=== Mortgage Summary ===");
        System.out.println("Loan Amount: " + currencyFormatter.format(principal));
        System.out.println("Monthly Payment: " + currencyFormatter.format(mortgagePayment));
        System.out.println("Total Payment: " + currencyFormatter.format(totalPayment));
        System.out.println("Total Interest: " + currencyFormatter.format(totalInterest));

        scanner.close();
    }
}
