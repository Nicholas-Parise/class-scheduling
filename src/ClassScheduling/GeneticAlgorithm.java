package ClassScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private final int TOURNAMENT_SIZE = 4;
    private final double ELITISM = 0.02;

    private double crossover;
    private double mutation;
    private int populationSize;
    private int maxGenerations;
    private int eliteProspects; // at least one
    private ScheduleData scheduleData;
    private List<Chromosome> population;
    private Random random;
    private CrossoverType crossoverType;

    public GeneticAlgorithm(ScheduleData scheduleData){
        this.scheduleData = scheduleData;
        random = Seed.getInstance().getRandom();
        population = new ArrayList<>();
    }


    public void set(double crossover, double mutation, int populationSize, int maxGenerations, CrossoverType crossoverType){
        this.crossover = crossover;
        this.mutation = mutation;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverType = crossoverType;
        eliteProspects = (int)(Math.ceil(populationSize*ELITISM)); // amount of elite members to bring over
    }



    public void run(){

        population.clear();

        // initialize the population with random chromosomes
        for (int i = 0; i < populationSize; i++) {
            Chromosome c = new Chromosome(scheduleData);
            c.randomInitializer();
            population.add(c);
        }

        // run the algorithm for x generations
        for (int i = 0; i < maxGenerations; i++) {
            List<Chromosome> newPopulation = new ArrayList<>();

            // add elite prospects, sort population
            population.sort(Collections.reverseOrder());
            for (int j = 0; j < eliteProspects; j++) {
                // Now add the amount of elite prospects to our new population
                newPopulation.add(population.get(j));
            }

            while(newPopulation.size()<populationSize){
                // get the two best parents from a subset of parents
                Chromosome parent1 = tournamentSelection(tournamentSubset());
                Chromosome parent2 = tournamentSelection(tournamentSubset());

                List<Chromosome> children = new ArrayList<>();

                if(random.nextDouble() < crossover) {

                    switch (crossoverType) {
                        case TWO_POINT:
                            children = parent1.twoPointCrossover(parent2);
                            break;
                        default:
                        case UNIFORM:
                            children = parent1.uniformCrossover(parent2);
                            break;
                    }

                }else{
                    children.add(parent1);
                    children.add(parent2);
                }
                // now mutate
                children.get(0).mutate(mutation);
                children.get(1).mutate(mutation);
                // calculate fitness
                children.get(0).calculateFitness();
                children.get(1).calculateFitness();
                // and add to the list
                newPopulation.add(children.get(0));
                newPopulation.add(children.get(1));
            }

            population = newPopulation;

            if(population.get(0).getFitness() == 1 || i == maxGenerations-1){

                if(population.get(0).getFitness() == 1) {
                    System.out.println("found solution on generation: " + i);
                }else{
                    System.out.println("Didn't find a solution");
                    System.out.println("the highest fitness reached:"+population.get(0).getFitness());
                }

                System.out.println("Average fitness:"+averageFitness());
                break;
            }


        }

    }

    /**
     * gets the best perform tournament selection on the given subset of Chromosomes
     * @param subset the list of chromosomes to select from
     * @return The best Chromosome
     */
    private Chromosome tournamentSelection(List<Chromosome> subset) {

        Chromosome best = subset.get(0);

        for (Chromosome current:subset) {
            if(current.getFitness() > best.getFitness()){
                best = current;
            }
        }
        return best;
    }


    /**
     * creates a random subset of the population of size TOURNAMENT_SIZE
     * @return the subset
     */
    private List<Chromosome> tournamentSubset() {

        List<Chromosome> subset = new ArrayList<>();

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int index = random.nextInt(population.size());
            subset.add(population.get(index));
        }
        return subset;
    }

    /**
     * get the average fitness of the population
     * @return
     */
    private double averageFitness(){
        double sum = 0;
        for (Chromosome c:population) {
            sum +=  c.getFitness();
        }
        return sum/populationSize;
    }



    @Override
    public String toString() {
        return "GeneticAlgorithm{" +
                "crossover=" + crossover +
                ", mutation=" + mutation +
                ", populationSize=" + populationSize +
                ", maxGenerations=" + maxGenerations +
                ", crossoverType=" + crossoverType +
                '}';
    }
}
