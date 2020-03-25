package Lesson_1;

public abstract class Task {
    private double size;

    public Task(double maxSize) {
        this.size = Math.random() * maxSize;
    }

    public double getSize() {
        return size;
    }
}
