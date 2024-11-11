package ClassScheduling;

import java.util.Random;

public class Seed {

    private static Seed instance;
    private static long seedNum = 7242530L;
    private Random random;

    public Seed(){
        this.random = new Random(seedNum);
    }

    public static Seed getInstance(){
        if(instance == null){
            instance = new Seed();
        }
        return instance;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public String toString() {
        return "Seed Number = " + seedNum;
    }
}
