package ClassScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private final int TOURNAMENT_SIZE = 4;
    private final double ELITISM = 0.02;

    private double crossover;
    private double mutation;
    private int populationSize;
    private int maxGenerations;
    private int eliteProspects;
    private ScheduleData scheduleData;
    private List<Chromosome> population;
    private Random random;


    public GeneticAlgorithm(ScheduleData scheduleData){
        this.scheduleData = scheduleData;
        random = new Random();
        eliteProspects = (int)(populationSize*ELITISM); // amount of elite members to bring over
        population = new ArrayList<>();
    }


    public void set(double crossover, double mutation, int populationSize, int maxGenerations){
        this.crossover = crossover;
        this.mutation = mutation;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
    }


    public void run(){

        population.clear();

        for (int i = 0; i < populationSize; i++) {
            Chromosome c = new Chromosome(scheduleData);
            c.randomInitializer();
            population.add(c);
        }

        for (int i = 0; i < maxGenerations; i++) {
            List<Chromosome> newPopulation = new ArrayList<>();

            // add elite prospects
            // TODO make it more than one in future
            newPopulation.add(tournamentSelection(population));

            while(newPopulation.size()<populationSize){
                // get the two best parents from a subset of parents
                Chromosome parent1 = tournamentSelection(tournamentSubset());
                Chromosome parent2 = tournamentSelection(tournamentSubset());

                if(random.nextDouble() > crossover)
                    continue;

                List<Chromosome> children = parent1.uniformCrossover(parent2);

                children.get(0).mutate(mutation);
                children.get(1).mutate(mutation);
                children.get(0).calculateFitness();
                children.get(1).calculateFitness();

                newPopulation.add(children.get(0));
                newPopulation.add(children.get(1));
            }
            population = newPopulation;
            //System.out.println("generation "+i+" fitness:"+population.get(0).getFitness());
            if(population.get(0).getFitness() == 1 || i == maxGenerations-1){
                System.out.println("found solution on generation: "+i);
                System.out.println("fitness:"+population.get(0).getFitness());
                //for (Gene g:population.get(0).getGeneList()) {
                 //   System.out.println(g.getCourse());
                //}

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


}
