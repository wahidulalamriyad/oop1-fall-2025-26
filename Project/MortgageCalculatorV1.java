package Project;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class MortgageCalculatorV1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double salary = 0;
        double creditScore = 0;
        boolean criminalRecord = false;

        // Get valid salary
        while (true) {
            System.out.print("Enter your monthly salary: ");
            if (scanner.hasNextDouble()) {
                salary = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next(); // clear invalid input
            }
        }

        // Get valid credit score
        while (true) {
            System.out.print("Enter your credit score (0 - 500): ");
            if (scanner.hasNextDouble()) {
                creditScore = scanner.nextDouble();
                if (creditScore >= 0 && creditScore <= 500) {
                    break;
                } else {
                    System.out.println("Credit score must be between 0 and 500.");
                }
            } else {
                System.out.println("Invalid input! Please enter a numeric value between 0 and 500.");
                scanner.next(); // clear invalid input
            }
        }

        // Get valid criminal record input
        while (true) {
            System.out.print("Do you have a criminal record? (true/false): ");
            if (scanner.hasNextBoolean()) {
                criminalRecord = scanner.nextBoolean();
                break;
            } else {
                System.out.println("Invalid input! Please type 'true' or 'false'.");
                scanner.next(); // clear invalid input
            }
        }

        // Loan eligibility check
        boolean eligible = (creditScore >= 300) && !criminalRecord;

        if (!eligible) {
            System.out.println("\nSorry, you are not eligible for a loan.");
            System.out.println("Reason: You must have a good credit score (≥ 300) and no criminal record.");
            scanner.close();
            return;
        }

        // Get valid principal loan amount
        double principal = 0;
        while (true) {
            System.out.print("Enter your desired loan amount: ");
            if (scanner.hasNextDouble()) {
                principal = scanner.nextDouble();
                if (principal <= 2 * salary) {
                    break;
                } else {
                    System.out.println("Loan request denied. Loan amount must be under 2 times your salary.");
                }
            } else {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next(); // clear invalid input
            }
        }

        // Get valid annual interest rate
        double annualInterestRate = 0;
        while (true) {
            System.out.print("Enter annual interest rate (in %): ");
            if (scanner.hasNextDouble()) {
                annualInterestRate = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next();
            }
        }

        // Get valid loan period in years
        int years = 0;
        while (true) {
            System.out.print("Enter loan period in years: ");
            if (scanner.hasNextDouble()) {
                years = (int) scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.next();
            }
        }

        // Mortgage calculation
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int numberOfPayments = years * 12;

        double mortgage = principal *
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        double totalPayment = mortgage * numberOfPayments;
        double totalInterest = totalPayment - principal;

        // Bangladeshi currency format
        Locale bdLocale = Locale.of("en", "BD");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(bdLocale);

        // Summary
        System.out.println("\n=== Mortgage Summary ===");
        System.out.println("Monthly Salary: " + currencyFormatter.format(salary));
        System.out.println("Credit Score: " + creditScore);
        System.out.println("Criminal Record: " + criminalRecord);
        System.out.println("Loan Amount: " + currencyFormatter.format(principal));
        System.out.println("Monthly Payment: " + currencyFormatter.format(mortgage));
        System.out.println("Total Payment: " + currencyFormatter.format(totalPayment));
        System.out.println("Total Interest: " + currencyFormatter.format(totalInterest));

        scanner.close();
    }
}
