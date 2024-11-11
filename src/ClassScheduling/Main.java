package ClassScheduling;

public class Main {

    public static void main(String[] args) {

        if (args.length < 5) {
            System.out.println("Usage: java Main <populationSize> <maxGenerations> <crossoverRate> <mutationRate> <crossoverType>");
            System.out.println("CrossoverType options: UNIFORM, TWO_POINT");
            System.exit(1);
        }

        int populationSize = Integer.parseInt(args[0]);
        int maxGenerations = Integer.parseInt(args[1]);
        double crossoverRate = Double.parseDouble(args[2]);
        double mutationRate = Double.parseDouble(args[3]);
        String crossoverTypeString = args[4];


        ScheduleData scheduleData = new ScheduleData("t2/courses.txt", "t2/rooms.txt", "t2/timeSlots.txt");
        GeneticAlgorithm GA = new GeneticAlgorithm(scheduleData);
        GA.set(1.0,0.2,200,1000,CrossoverType.UNIFORM);

        for (int i = 0; i < 50; i++) {
            GA.run();
        }


    }

}
