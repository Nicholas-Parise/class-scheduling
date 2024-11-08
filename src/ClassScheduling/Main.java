package ClassScheduling;

public class Main {

    public static void main(String[] args) {

        ScheduleData scheduleData = new ScheduleData("t2/courses.txt", "t2/rooms.txt", "t2/timeSlots.txt");
        GeneticAlgorithm GA = new GeneticAlgorithm(scheduleData);
        GA.set(1.0,0.1,200,1000);

        for (int i = 0; i < 50; i++) {
            GA.run();
        }


    }

}
