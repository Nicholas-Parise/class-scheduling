package ClassScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromosome {
    private List<Gene> geneList;
    private double fitness;
    private final int size;
    private ScheduleData scheduleData;
    private Random random;

    // default constructor used for
    public Chromosome(ScheduleData scheduleData){
        this.scheduleData = scheduleData;
        this.size = scheduleData.getCourses().size();
        fitness = 0;
        geneList = new ArrayList<>();
        random = new Random();
    }

    // this constructor is used for cross over,
    // where we need to make a new chromosome from a given list of genes
    public Chromosome(ScheduleData scheduleData,List<Gene> geneList){
        this(scheduleData);
        setGeneList(geneList);
        calculateFitness();
    }


    public double getFitness() {

        if(fitness == 0){
            // if we forget to calculate fitness before getting fitness
            // we will run it here
            calculateFitness();
        }
        return fitness;
    }

    public List<Gene> getGeneList() {
        return geneList;
    }

    public void setGeneList(List<Gene> geneList) {
        this.geneList = geneList;
    }

    /**
     * Initialize the chromosome with randomly generated genes
     */
    public void randomInitializer(){

        for (int i = 0; i < size; i++) {
            Course c = scheduleData.getRandomCourses();
            Room r = scheduleData.getRandomRooms();
            TimeSlot t = scheduleData.getRandomTimeSlots();
            geneList.add(new Gene(c,r,t));
        }
    }


    /**
     * calculate the fitness of the Chromosome
     * This method iterates through all the genes in the chromosome and counts conflicts
     * fitness is defined by:
     * 1 : the most fit
     * 0 : the least fit
     *
     */
    public void calculateFitness(){
        int conflicts = 0;

        for (int i = 0; i < size; i++) {

            Gene current = geneList.get(i);
            Course course = current.getCourse();
            Room room = current.getRoom();
            TimeSlot timeSlot = current.getTimeSlot();

            if(course.getStudents() > room.getCapacity()){
                conflicts ++;
            }

            for (int j = 0; j < size; j++) {

                if(i == j)
                    continue; // don't want to miscount conflicts

                Gene other = geneList.get(j);
                if(room.equals(other.getRoom())){ // if were in the same room
                    // and the timeslots overlap
                    if(current.overlaps(other)){
                        conflicts++; // then there is a conflict
                    }
                }

                // if the professor is the same in both courses
                if(course.getProfessor().equals(other.getCourse().getProfessor())){
                    // and the timeslots overlap
                    if(current.overlaps(other)){
                        conflicts++; // then there is a conflict
                    }
                }
            }
        }
        fitness = 1.0/ (1+conflicts);
    }


    /**
     * perform uniform cross over
     * uses a random number generator to get a boolean
     * and with this boolean determines with parent to get the gene from
     *
     * @param other parent 2
     * @return the two child Chromosomes
     */
    public List<Chromosome> uniformCrossover(Chromosome other){

        List<Gene> child1 = new ArrayList<>();
        List<Gene> child2 = new ArrayList<>();
        List<Chromosome> children = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            // this acts as our mask. If its true we get parent 1's gene (this)
            // and if its 0 we get parent 2's gene (other)
            if (random.nextBoolean()) {
                child1.add(new Gene(getGeneList().get(i)));
                child2.add(new Gene(other.getGeneList().get(i)));
            }else{
                child1.add(new Gene(other.getGeneList().get(i)));
                child2.add(new Gene(getGeneList().get(i)));
            }
        }

        children.add(new Chromosome(scheduleData,child1));
        children.add(new Chromosome(scheduleData,child2));

        return children;
    }


    /**
     * perform two point crossover on (this) Chromosome and (other) Chromosome
     * randomly generate two points,then set the start and end point to the smaller and larger number respectively.
     * @param other parent 2
     * @return the two child Chromosomes
     */
    public List<Chromosome> twoPointCrossover(Chromosome other){

        List<Gene> child1 = new ArrayList<>();
        List<Gene> child2 = new ArrayList<>();
        List<Chromosome> children = new ArrayList<>();

        int point1 = random.nextInt(size);
        int point2 = random.nextInt(size);

        int start = Integer.min(point1,point2);
        int end = Integer.max(point1,point2);

        for (int i = 0; i < size; i++) {
            if (i <= start || i >= end) {
                child1.add(new Gene(getGeneList().get(i)));
                child2.add(new Gene(other.getGeneList().get(i)));
            }else{
                child1.add(new Gene(other.getGeneList().get(i)));
                child2.add(new Gene(getGeneList().get(i)));
            }
        }

        children.add(new Chromosome(scheduleData,child1));
        children.add(new Chromosome(scheduleData,child2));

        return children;
    }


    /**
     * Given a mutation rate go through each gene and
     * randomly change each parameter to a new random variable
     * @param mutationRate
     */
    public void mutate(double mutationRate){

        for (Gene current: geneList) {

            if(random.nextDouble() > mutationRate){
                continue;
            }

            // mutate course
            if(random.nextBoolean()){
                current.setCourse(scheduleData.getRandomCourses());
            }
            // mutate room
            if(random.nextBoolean()){
                current.setRoom(scheduleData.getRandomRooms());
            }
            // mutate time slot
            if(random.nextBoolean()){
                current.setTimeSlot(scheduleData.getRandomTimeSlots());
            }
        }
    }


}
