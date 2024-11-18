package Task3;

public class Main {
    public static void main(String[] args) {
        // Тестирование Calculator
        System.out.println("Sum: " + Calculator.sum(5, 3.5)); // Ожидаемый результат: 8.5
        System.out.println("Multiply: " + Calculator.multiply(5, 3.5)); // Ожидаемый результат: 17.5
        System.out.println("Divide: " + Calculator.divide(5, 2.5)); // Ожидаемый результат: 2.0
        System.out.println("Subtract: " + Calculator.subtract(5, 3.5)); // Ожидаемый результат: 1.5

        // Тестирование compareArrays
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {1, 2, 3};
        Integer[] array3 = {1, 2, 4};
        System.out.println("Compare array1 and array2: " + ArrayUtils.compareArrays(array1, array2)); // Ожидаемый результат: true
        System.out.println("Compare array1 and array3: " + ArrayUtils.compareArrays(array1, array3)); // Ожидаемый результат: false

        // Тестирование Pair
        Pair<String, Integer> pair = new Pair<>("Hello", 123);
        System.out.println(pair.toString()); // Ожидаемый результат: Pair{first=Hello, second=123}
    }
}
