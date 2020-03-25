package Lesson_1;

public interface Sportable {
    boolean run(Track track);
    boolean jump(Wall wall);
    boolean executeTasks(Task... tasks);
}
