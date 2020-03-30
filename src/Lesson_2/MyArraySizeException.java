package Lesson_2;

public class MyArraySizeException extends Exception {
    public static final String message = "Неверный размер массива, должен быть %dx%d";

    public MyArraySizeException(int rows, int cols) {
        super(String.format(message, rows, cols));
    }
}
