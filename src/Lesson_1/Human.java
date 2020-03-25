package Lesson_1;

public class Human extends Participant {
    private static final String TYPE = "Человек";
    private static final double MAX_RUN_STRENGTH = 1000.0;
    private static final double MAX_JUMP_STRENGTH = 2.0;

    public Human() {
        super(TYPE, MAX_RUN_STRENGTH, MAX_JUMP_STRENGTH);
    }

    public Human(int runStrength, int jumpStrength) {
        super(TYPE, runStrength, jumpStrength);
    }
}
