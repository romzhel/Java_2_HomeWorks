package Lesson_2;

import java.util.Arrays;

public class Main {
    public static final int NEEDED_ARRAY_SIZE = 4;

    public static void main(String[] args) {
        String[][][] testArrays = {
                {{"1", "2", "3", "4"}, {"2"}, {"3"}, {"4"}},
                {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}},
                {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "два", "3", "4"}}
        };


        for (String[][] testArray : testArrays) {
            System.out.println(String.format("Вычисляем сумму элементов массива: \n%s", tdArrayToString(testArray)));

            try {
                System.out.println(String.format("Сумма элементов массива = %d", getArrayValuesSum(testArray)));
            } catch (MyArraySizeException | MyArrayDataException e) {
                System.out.println("Брошено исключение: " + e.getMessage());
            }

            System.out.println();
        }
    }

    public static int getArrayValuesSum(String[][] tdArray) throws MyArraySizeException, MyArrayDataException {
        if (tdArray.length != NEEDED_ARRAY_SIZE) {
            throw new MyArraySizeException(NEEDED_ARRAY_SIZE, NEEDED_ARRAY_SIZE);
        }

        int result = 0;
        for (int row = 0; row < tdArray.length; row++) {

            if (tdArray[row].length == NEEDED_ARRAY_SIZE) {
                for (int col = 0; col < tdArray[row].length; col++) {
                    try {
                        result += Integer.parseInt(tdArray[row][col]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(row, col);
                    }
                }
            } else {
                throw new MyArraySizeException(NEEDED_ARRAY_SIZE, NEEDED_ARRAY_SIZE);
            }

        }

        return result;
    }

    public static String tdArrayToString(Object[][] tdArray) {
        StringBuilder result = new StringBuilder();

        for (Object[] array : tdArray) {
            result.append(Arrays.toString(array)).append("\n");
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }
}
