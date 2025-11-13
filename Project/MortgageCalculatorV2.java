package Project;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class MortgageCalculatorV2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double salary = getValidDouble(input, "Enter your monthly salary: ");
        double creditScore = getValidCreditScore(input);
        boolean criminalRecord = getValidBoolean(input, "Do you have a criminal record? (true/false): ");

        // Loan eligibility
        boolean eligible = (creditScore >= 300) && !criminalRecord;

        if (!eligible) {
            System.out.println("\nSorry, you are not eligible for a loan.");
            System.out.println("Reason: You must have a good credit score (≥ 300) and no criminal record.");
            input.close();
            return;
        }

        double principal = getValidDouble(input, "Enter your desired loan amount: ");

        // Principal limit check
        if (principal > 2 * salary) {
            System.out.println("\nLoan request denied.");
            System.out.println("Reason: Loan amount must be under 2 times your salary.");
            input.close();
            return;
        }

        double annualInterestRate = getValidDouble(input, "Enter annual interest rate (in %): ");
        int years = (int) getValidDouble(input, "Enter loan period in years: ");

        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int numberOfPayments = years * 12;

        double mortgage = principal *
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        // Bangladeshi currency format
        Locale bdLocale = Locale.of("en", "BD");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(bdLocale);
        String formattedMortgage = currencyFormat.format(mortgage);

        System.out.println("\nMonthly Mortgage Payment: " + formattedMortgage);
        input.close();
    }

    // Validate double input
    private static double getValidDouble(Scanner input, String message) {
        while (true) {
            System.out.print(message);
            if (input.hasNextDouble()) {
                return input.nextDouble();
            } else {
                System.out.println("Invalid input! Please enter a numeric value.");
                input.next(); // clear invalid input
            }
        }
    }

    // Validate credit score input
    private static double getValidCreditScore(Scanner input) {
        while (true) {
            System.out.print("Enter your credit score (0 - 500): ");
            if (input.hasNextDouble()) {
                double score = input.nextDouble();
                if (score >= 0 && score <= 500)
                    return score;
                else
                    System.out.println("Credit score must be between 0 and 500.");
            } else {
                System.out.println("Invalid input! Please enter a numeric value between 0 and 500.");
                input.next();
            }
        }
    }

    // Validate boolean input
    private static boolean getValidBoolean(Scanner input, String message) {
        while (true) {
            System.out.print(message);
            if (input.hasNextBoolean()) {
                return input.nextBoolean();
            } else {
                System.out.println("Invalid input! Please type 'true' or 'false'.");
                input.next(); // clear invalid input
            }
        }
    }
}
