package Lesson_2;

public class MyArrayDataException extends Exception {
    public static final String message = "Нечисловое значение элемента массива %dx%d [0: %dx%d]";

    public MyArrayDataException(int row, int col) {
        super(String.format(message, row + 1, col + 1, row, col));
    }
}
