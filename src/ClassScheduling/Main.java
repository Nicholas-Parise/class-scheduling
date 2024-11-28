package ClassScheduling;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        if (args.length < 7) {
            System.out.println("expected Args: <popSize> <maxGenerations> <crossoverRate> <mutationRate> <crossoverType> <Seed> <dir>");
            System.out.println("CrossoverType options: UNIFORM, TWO_POINT");
            System.out.println("dir: location of input files ex: t1, t2, t3");
            System.exit(1);
        }

        int populationSize = Integer.parseInt(args[0]);
        int maxGenerations = Integer.parseInt(args[1]);
        double crossoverRate = Double.parseDouble(args[2]);
        double mutationRate = Double.parseDouble(args[3]);
        String crossoverTypeString = args[4];
        long seed = Long.parseLong(args[5]);
        String dir = args[6];
        CrossoverType crossoverType;
        try {
            crossoverType = CrossoverType.valueOf(crossoverTypeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid crossover type. Options are: UNIFORM, TWO_POINT");
            return;
        }

        ScheduleData scheduleData = new ScheduleData(dir+"/courses.txt", dir+"/rooms.txt", dir+"/timeSlots.txt");
        GeneticAlgorithm GA = new GeneticAlgorithm(scheduleData);

        GA.set(crossoverRate,mutationRate,populationSize,maxGenerations,crossoverType,dir);
        System.out.println(Seed.getInstance(seed));

        System.out.println(GA);

        for (int i = 0; i < 20; i++) {
            GA.run();
            Thread.sleep(1000);
            Seed.renew();
        }
    }
}