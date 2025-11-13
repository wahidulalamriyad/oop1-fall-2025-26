package Lab;

public class Methods {
    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Methods methods = new Methods();
        int sum = methods.add(5, 10);
        System.out.println("The sum is: " + sum);
    }
}
