package Lab;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        int[] array = new int[5];
        array[0] = 10;
        array[1] = 20;
        array[2] = 30;
        array[3] = 40;

        System.out.println(array);
        System.out.println(Arrays.toString(array));

        // Create an array of integers
        int[] numbers = { 10, 20, 30, 40, 50 };
        Arrays.sort(numbers);

        System.out.println("Sorted numbers: " + Arrays.toString(numbers));
        System.out.println("Length of numbers array: " + numbers.length);
    }
}
