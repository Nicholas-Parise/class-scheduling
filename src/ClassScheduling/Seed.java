package ClassScheduling;

import java.util.Random;

public class Seed {

    private static Seed instance;
    private static long seedNum = 724253011182003L;
    //private static long seedNum = System.currentTimeMillis();
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
