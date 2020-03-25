package Lesson_1;

public class Robot extends Participant {
    private static final String TYPE = "Робот";
    private static final double MAX_RUN_STRENGTH = 5000.0;
    private static final double MAX_JUMP_STRENGTH = 1.0;

    public Robot() {
        super(TYPE, MAX_RUN_STRENGTH, MAX_JUMP_STRENGTH);
    }

    public Robot(int runStrength, int jumpStrength) {
        super(TYPE, runStrength, jumpStrength);
    }
}
