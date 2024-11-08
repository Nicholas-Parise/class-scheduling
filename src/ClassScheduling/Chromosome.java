package ClassScheduling;

import java.util.*;

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
        // This makes sure the population actually has each course
        for (Course c:scheduleData.getCourses()) {
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
                conflicts +=2;
            }

            for (int j = 0; j < size; j++) {

                if(i == j)
                    continue; // don't want to miscount conflicts

                Gene other = geneList.get(j);
                if(room.equals(other.getRoom())){ // if were in the same room
                    // and the timeslots overlap
                    if(current.overlaps(other)){
                        conflicts+=3; // then there is a conflict
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

        Set<Course> addedCoursesChild1 = new HashSet<>();
        Set<Course> addedCoursesChild2 = new HashSet<>();

        for (int i = 0; i < size; i++) {
            // this acts as our mask. If its true we get parent 1's gene (this)
            // and if its 0 we get parent 2's gene (other)
            if (random.nextBoolean()) {
                child1.add(new Gene(getGeneList().get(i)));
                addedCoursesChild1.add(getGeneList().get(i).getCourse());
                child2.add(new Gene(other.getGeneList().get(i)));
                addedCoursesChild2.add(other.getGeneList().get(i).getCourse());
            }else{
          //      child1.add(new Gene(other.getGeneList().get(i)));
           //     child2.add(new Gene(getGeneList().get(i)));
            }
        }

        child1 = addMissing(child1, other, addedCoursesChild1);
        child2 = addMissing(child2, this, addedCoursesChild2);

        children.add(new Chromosome(scheduleData,child1));
        children.add(new Chromosome(scheduleData,child2));

        return children;
    }

    private static List<Gene> addMissing(List<Gene> child, Chromosome parent, Set<Course> addedCourses){

        for (Gene g:parent.getGeneList()) {
            if(!addedCourses.contains(g.getCourse())){
                child.add(g);
                addedCourses.add(g.getCourse());
            }
        }
        return child;
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
     * randomly change the room and/or timeslot
     * @param mutationRate
     */
    public void mutate(double mutationRate){

        Gene current = geneList.get(random.nextInt(size));

        if(random.nextDouble() < mutationRate){
            current.setRoom(scheduleData.getRandomRooms());
            current.setTimeSlot(scheduleData.getRandomTimeSlots());
        }
    }



}
