package Lesson_1;

public class Track extends Task {
    public static final int MAX_SIZE_DEFAULT = 100;

    public Track() {
        super(MAX_SIZE_DEFAULT);
    }

    public Track(int maxSize) {
        super(maxSize);
    }
}
