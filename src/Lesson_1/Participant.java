package Lesson_1;

public abstract class Participant implements Sportable {
    private String participantType;
    private double runStrength;
    private double jumpStrength;

    public Participant(String participantType, double runStrength, double jumpStrength) {
        this.participantType = participantType;
        this.runStrength = Math.random() * runStrength;
        this.jumpStrength = Math.random() * jumpStrength;
    }

    @Override
    public boolean run(Track track) {
        System.out.println(String.format("%s преодолевает беговую дорожку размером %.1f м, преодолел %.1f м (было сил на %.1f м)",
                participantType, track.getSize(), Math.min(runStrength, track.getSize()), runStrength));
        return runStrength >= track.getSize();
    }

    @Override
    public boolean jump(Wall wall) {
        System.out.println(String.format("%s преодолевает стену высотой %.1f м, преодолел %.1f м (было сил на %.1f м)",
                participantType, wall.getSize(), Math.min(jumpStrength, wall.getSize()), jumpStrength));
        return jumpStrength >= wall.getSize();
    }

    @Override
    public boolean executeTasks(Task... tasks) {
        for (Task task : tasks) {
            if (task instanceof Track && !run((Track) task) || task instanceof Wall && !jump((Wall) task)) {
                return false;
            }
        }

        return true;
    }

    public String getParticipantType() {
        return participantType;
    }
}
