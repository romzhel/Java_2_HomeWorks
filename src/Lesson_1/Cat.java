package Lesson_1;

public class Cat extends Participant {
    private static final String TYPE = "Кошак";
    private static final double MAX_RUN_STRENGTH = 100.0;
    private static final double MAX_JUMP_STRENGTH = 3.0;

    public Cat() {
        super(TYPE, MAX_RUN_STRENGTH, MAX_JUMP_STRENGTH);
    }

    public Cat(int runStrength, int jumpStrength) {
        super(TYPE, runStrength, jumpStrength);
    }
}
