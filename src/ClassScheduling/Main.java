package ClassScheduling;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ScheduleData scheduleData = new ScheduleData("t1/courses.txt", "t1/rooms.txt", "t1/timeSlots.txt");
        GeneticAlgorithm GA = new GeneticAlgorithm(scheduleData);

        if (args.length < 6) {
            System.out.println("expected Args: <popSize> <maxGenerations> <crossoverRate> <mutationRate> <crossoverType> <Seed>");
            System.out.println("CrossoverType options: UNIFORM, TWO_POINT");
            GA.set(1.0,0.1,200,500,CrossoverType.UNIFORM);
            System.out.println("Applying preset ARGS");
            System.out.println("Seed set to current time");
            System.out.println(Seed.getInstance()); // setting seed to current time
            //System.exit(1);
        }else{
            int populationSize = Integer.parseInt(args[0]);
            int maxGenerations = Integer.parseInt(args[1]);
            double crossoverRate = Double.parseDouble(args[2]);
            double mutationRate = Double.parseDouble(args[3]);
            String crossoverTypeString = args[4];
            long seed = Long.parseLong(args[5]);
            CrossoverType crossoverType;
            try {
                crossoverType = CrossoverType.valueOf(crossoverTypeString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid crossover type. Options are: UNIFORM, TWO_POINT");
                return;
            }
            GA.set(crossoverRate,mutationRate,populationSize,maxGenerations,crossoverType);
            System.out.println(Seed.getInstance(seed));
        }

        System.out.println(GA);

        for (int i = 0; i < 20; i++) {
            GA.run();
            Thread.sleep(1000);
            Seed.renew();
        }

    }

}