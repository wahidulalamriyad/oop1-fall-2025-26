package Lab;

// public class ProceduralProgramming {

//     public static void main(String[] args) {
//         int baseSalary = 50_000;
//         int extraHours = 10;
//         int hourlyRate = 20;

//         int totalWage = calculateWage(baseSalary, extraHours, hourlyRate);
//         System.out.println("Total Wage: " + totalWage);
//     }

//     public static int calculateWage(int baseSalary, int extraHours, int hourlyRate) {
//         return baseSalary + (extraHours * hourlyRate);
//     }

// }

public class ProceduralProgramming {

    public static void main(String[] args) {
        var employee1 = new Employee();
        employee1.baseSalary = 50000;
        employee1.hourlyRate = 20;
        int totalWage = employee1.calculateWage(10);
        System.out.println("Total Wage: " + totalWage);
    }

}
