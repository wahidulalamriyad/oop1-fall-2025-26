package Lab;

import java.util.Arrays;

public class MultiDimArrays {
    public static void main(String[] args) {
        int[][] numbers = new int[3][4];
        numbers[0][0] = 1;

        // System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.deepToString(numbers));

        int[][] predefined = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        System.out.println(Arrays.deepToString(predefined));
    }
}
