package Lesson_1;

public class Main {
    public static void main(String[] args) {
        Participant[] participants = {new Human(), new Cat(), new Robot()};
        Task[] tasks = {new Track(), new Wall(), new Wall(), new Track()};

        for (Participant participant:participants             ) {
            System.out.println(String.format("%s %sпреодолел все препятствия !!!", participant.getParticipantType(),
                    participant.executeTasks(tasks) ? "" : "не "));
            System.out.println();
        }
    }
}
