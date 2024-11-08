package ClassScheduling;

public class Main {

    public static void main(String[] args) {

        ScheduleData scheduleData = new ScheduleData("t1/courses.txt", "t1/rooms.txt", "t1/timeSlots.txt");
        GeneticAlgorithm GA = new GeneticAlgorithm(scheduleData);
        GA.set(1.0,0.1,100,500);
        GA.run();

        Chromosome c = new Chromosome(scheduleData);
        c.randomInitializer();
        System.out.println(c.getFitness());


    }

}
