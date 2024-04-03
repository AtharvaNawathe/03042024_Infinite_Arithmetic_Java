package Sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a class named InfiniteNumber where following operations
 * are performed
 * 1 Addition
 * 2 Subtraction
 * 3 Multiplication
 * @author Atharva Nawathe
 */

/**
 * Class representing an infinite precision number.
 */
public class Infinite_Arithmetic {
    private List<Integer> array;

    /**
     * Constructor for the InfiniteNumber class.
     *
     * @param array The array of digits representing the number.
     */
    public Infinite_Arithmetic(List<Integer> array) {
        validateNumbers(array);
        this.array = array;
    }

    /**
     * Method to validate elements of the array.
     *
     * @param array The array to be validated.
     */
    private void validateNumbers(List<Integer> array) {
        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException("Input must be a non-empty list");
        }

        for (Integer element : array) {
            if (element == null || element < 0) {
                throw new IllegalArgumentException("Array elements must be positive integers");
            }
        }
    }

    /**
     * Method to perform addition with another InfiniteNumber.
     *
     * @param otherArray The other InfiniteNumber to add.
     * @return Result of the addition.
     */
    public Infinite_Arithmetic add(Infinite_Arithmetic otherArray) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        List<Integer> otherList = otherArray.array;

        int i = this.array.size() - 1;
        int j = otherList.size() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? this.array.get(i) : 0;
            int digit2 = j >= 0 ? otherList.get(j) : 0;
            int sum = digit1 + digit2 + carry;

            if (sum > 9) {
                int mod = sum % 10;
                result.add(0, mod);
                carry = sum / 10;
            } else {
                result.add(0, sum);
                carry = 0;
            }

            i--;
            j--;
        }

        if (carry > 0) {
            result.add(0, carry);
        }

        return new Infinite_Arithmetic(result);
    }

    /**
     * Method to perform subtraction with another InfiniteNumber.
     *
     * @param otherArray The other InfiniteNumber to subtract.
     * @return Result of the subtraction.
     */
    public Infinite_Arithmetic subtract(Infinite_Arithmetic otherArray) {
        List<Integer> result = new ArrayList<>();
        int borrow = 0;
        List<Integer> otherList = otherArray.array;

        int i = this.array.size() - 1;
        int j = otherList.size() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? this.array.get(i) : 0;
            int digit2 = j >= 0 ? otherList.get(j) : 0;
            int difference = digit1 - digit2 - borrow;

            if (difference < 0) {
                difference += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.add(0, difference);
            i--;
            j--;
        }

        return new Infinite_Arithmetic(result);
    }

    /**
     * Method to perform multiplication with another InfiniteNumber.
     *
     * @param otherArray The other InfiniteNumber to multiply.
     * @return Result of the multiplication.
     */
    public Infinite_Arithmetic multiply(Infinite_Arithmetic otherArray) {
        List<Integer> result = new ArrayList<>();
        List<Integer> otherList = otherArray.array;

        result.add(0);

        for (int j = otherList.size() - 1; j >= 0; j--) {
            List<Integer> temp = new ArrayList<>();

            for (int k = 0; k < otherList.size() - 1 - j; k++) {
                temp.add(0);
            }

            int carry = 0;

            for (int i = this.array.size() - 1; i >= 0; i--) {
                int product = otherList.get(j) * this.array.get(i) + carry;
                int mod = product % 10;
                carry = product / 10;
                temp.add(0, mod);
            }

            if (carry > 0) {
                temp.add(0, carry);
            }

            result = addLists(result, temp);
        }

        while (result.size() > 1 && result.get(0) == 0) {
            result.remove(0);
        }

        return new Infinite_Arithmetic(result);
    }

    private List<Integer> addLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        int i = list1.size() - 1;
        int j = list2.size() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? list1.get(i) : 0;
            int digit2 = j >= 0 ? list2.get(j) : 0;
            int sum = digit1 + digit2 + carry;

            if (sum > 9) {
                int mod = sum % 10;
                result.add(0, mod);
                carry = sum / 10;
            } else {
                result.add(0, sum);
                carry = 0;
            }

            i--;
            j--;
        }

        if (carry > 0) {
            result.add(0, carry);
        }

        return result;
    }

    public static void main(String[] args) {
    	Infinite_Arithmetic array1 = new Infinite_Arithmetic(List.of(7, 3, 3));
    	Infinite_Arithmetic array2 = new Infinite_Arithmetic(List.of(7, 7, 7));
    	Infinite_Arithmetic array3 = array1.add(array2);
        System.out.println("Addition of two arrays: " + array3.array);

        Infinite_Arithmetic array4 = new Infinite_Arithmetic(List.of(1, 0, 0));
        Infinite_Arithmetic array5 = new Infinite_Arithmetic(List.of(1, 6, 8));
        Infinite_Arithmetic array6 = array4.subtract(array5);
        System.out.println("Subtraction of two arrays: " + array6.array);

        Infinite_Arithmetic array7 = new Infinite_Arithmetic(List.of(2, 3, 4));
        Infinite_Arithmetic array8 = new Infinite_Arithmetic(List.of(9, 6, 0, 4, 3, 6, 4, 3, 3, 7));
        Infinite_Arithmetic array9 = array7.multiply(array8);
        System.out.println("Multiplication of two arrays: " + array9.array);
    }
}

