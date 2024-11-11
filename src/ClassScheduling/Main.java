package ClassScheduling;

public class Main {



    public static void main(String[] args) {

        ScheduleData scheduleData = new ScheduleData("t2/courses.txt", "t2/rooms.txt", "t2/timeSlots.txt");
        GeneticAlgorithm GA = new GeneticAlgorithm(scheduleData);

        if (args.length < 5) {
            System.out.println("expected Args: <popSize> <maxGenerations> <crossoverRate> <mutationRate> <crossoverType>");
            System.out.println("CrossoverType options: UNIFORM, TWO_POINT");
            //System.exit(1);
        }else{
            int populationSize = Integer.parseInt(args[0]);
            int maxGenerations = Integer.parseInt(args[1]);
            double crossoverRate = Double.parseDouble(args[2]);
            double mutationRate = Double.parseDouble(args[3]);
            String crossoverTypeString = args[4];
            CrossoverType crossoverType;
            try {
                crossoverType = CrossoverType.valueOf(crossoverTypeString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid crossover type. Options are: UNIFORM, TWO_POINT");
                return;
            }
            GA.set(crossoverRate,mutationRate,populationSize,maxGenerations,crossoverType);
        }

//        GA.set(1.0,0.2,200,1000,CrossoverType.UNIFORM);

        System.out.println(GA);
        System.out.println(Seed.getInstance());

        for (int i = 0; i < 50; i++) {
            GA.run();
        }


    }

}
