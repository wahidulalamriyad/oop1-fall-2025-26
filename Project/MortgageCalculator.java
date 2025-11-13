package Project;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MortgageCalculator {

    // ------------------ Constants ------------------
    private static final int MIN_CREDIT_SCORE = 300;
    private static final int MAX_CREDIT_SCORE = 500;
    private static final double MAX_LOAN_MULTIPLIER = 2.0;
    private static final int MONTHS_IN_YEAR = 12;
    private static final int PERCENT = 100;
    private static final int DAYS_BETWEEN_PAYMENTS = 30;

    // ------------------ Entry Point ------------------
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            printSection("Welcome to Mortgage Calculator");

            double salary = promptForDouble(scanner, "Enter your monthly salary: ");
            double creditScore = promptForCreditScore(scanner);
            boolean hasCriminalRecord = promptForBoolean(scanner, "Do you have a criminal record? (true/false): ");

            if (!isEligible(creditScore, hasCriminalRecord)) {
                showError("You are not eligible for a loan.",
                        "Reason: Minimum credit score " + MIN_CREDIT_SCORE + " and no criminal record required.");
                return;
            }

            double principal = promptForDouble(scanner, "Enter your desired loan amount: ");
            if (!isLoanAmountValid(principal, salary)) {
                showError("Loan request denied.",
                        "Reason: Loan cannot exceed " + MAX_LOAN_MULTIPLIER + "× your salary.");
                return;
            }

            double annualRate = promptForDouble(scanner, "Enter annual interest rate (in %): ");
            int years = (int) promptForDouble(scanner, "Enter loan period in years: ");

            double monthlyPayment = calculateMonthlyPayment(principal, annualRate, years);
            double totalPayment = monthlyPayment * years * MONTHS_IN_YEAR;
            double totalInterest = totalPayment - principal;

            printLoanSummary(principal, annualRate, years, monthlyPayment, totalPayment, totalInterest);
            printPaymentSchedule(principal, annualRate, years);

            printSection("Thank you for using Mortgage Calculator!");
        }
    }

    // ------------------ Input Methods ------------------

    private static double promptForDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble())
                return scanner.nextDouble();

            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.next(); // clear invalid token
        }
    }

    private static double promptForCreditScore(Scanner scanner) {
        while (true) {
            System.out.print("Enter your credit score (0 - " + MAX_CREDIT_SCORE + "): ");
            if (scanner.hasNextDouble()) {
                double score = scanner.nextDouble();
                if (score >= 0 && score <= MAX_CREDIT_SCORE)
                    return score;
                System.out.println("Credit score must be between 0 and " + MAX_CREDIT_SCORE + ".");
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }
    }

    private static boolean promptForBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextBoolean())
                return scanner.nextBoolean();

            System.out.println("Invalid input. Please type 'true' or 'false'.");
            scanner.next();
        }
    }

    // ------------------ Validation Methods ------------------

    private static boolean isEligible(double creditScore, boolean hasCriminalRecord) {
        return creditScore >= MIN_CREDIT_SCORE && !hasCriminalRecord;
    }

    private static boolean isLoanAmountValid(double principal, double salary) {
        return principal <= salary * MAX_LOAN_MULTIPLIER;
    }

    // ------------------ Calculation Methods ------------------

    private static double calculateMonthlyPayment(double principal, double annualRate, int years) {
        double monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;
        int totalPayments = years * MONTHS_IN_YEAR;
        return principal * (monthlyRate * Math.pow(1 + monthlyRate, totalPayments))
                / (Math.pow(1 + monthlyRate, totalPayments) - 1);
    }

    private static double calculateRemainingBalance(double principal, double annualRate, int years, int paymentsMade) {
        double monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;
        int totalPayments = years * MONTHS_IN_YEAR;
        return principal * (Math.pow(1 + monthlyRate, totalPayments) - Math.pow(1 + monthlyRate, paymentsMade))
                / (Math.pow(1 + monthlyRate, totalPayments) - 1);
    }

    // ------------------ Display Methods ------------------

    private static void printLoanSummary(double principal, double annualRate, int years,
            double monthlyPayment, double totalPayment, double totalInterest) {
        printSection("LOAN SUMMARY");
        System.out.printf("Principal Loan Amount : %s%n", formatCurrency(principal));
        System.out.printf("Annual Interest Rate  : %.2f%%%n", annualRate);
        System.out.printf("Loan Period           : %d years%n", years);
        System.out.println("--------------------------------------------------");
        System.out.printf("Monthly Payment       : %s%n", formatCurrency(monthlyPayment));
        System.out.printf("Total Payment         : %s%n", formatCurrency(totalPayment));
        System.out.printf("Total Interest Paid   : %s%n", formatCurrency(totalInterest));
        System.out.println("==================================================");
    }

    private static void printPaymentSchedule(double principal, double annualRate, int years) {
        printSection("PAYMENT SCHEDULE");
        System.out.printf("%-10s %-20s %-25s%n", "No.", "Payment Date", "Remaining Balance");
        System.out.println("--------------------------------------------------");

        LocalDate paymentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int totalPayments = years * MONTHS_IN_YEAR;

        for (int paymentsMade = 1; paymentsMade <= totalPayments; paymentsMade++) {
            double balance = calculateRemainingBalance(principal, annualRate, years, paymentsMade);
            String formattedDate = paymentDate.plusDays((long) paymentsMade * DAYS_BETWEEN_PAYMENTS).format(formatter);
            System.out.printf("%-10d %-20s %-25s%n", paymentsMade, formattedDate, formatCurrency(balance));
        }

        System.out.println("==================================================");
    }

    private static void printSection(String title) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================\n");
    }

    private static void showError(String title, String reason) {
        System.out.println("\n" + title);
        System.out.println(reason);
    }

    private static String formatCurrency(double value) {
        Locale bdLocale = Locale.of("en", "BD");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(bdLocale);
        return formatter.format(value).replace("BDT", "BDT ");
    }
}
