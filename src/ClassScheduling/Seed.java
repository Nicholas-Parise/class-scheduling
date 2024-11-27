package ClassScheduling;

import java.util.Random;
// singleton seed class to manage random
public class Seed {

    private static Seed instance;
    private static long seedNum = System.currentTimeMillis();
    private Random random;

    private Seed(){
        this.random = new Random(seedNum);
    }

    public static void renew(){
        seedNum = System.currentTimeMillis();
        instance = null;
    }


    // public getter to make sure it's the same instance
    public static Seed getInstance(){
        if(instance == null){
            instance = new Seed();
        }
        return instance;
    }

    // getter with a seed to instantiate
    public static Seed getInstance(long newSeed){
        if(newSeed > 0)
            seedNum = newSeed;

        if(instance == null){
            instance = new Seed();
        }
        return instance;
    }


    public Random getRandom() {
        return random;
    }

    public static long getSeedNum() {
        return seedNum;
    }

    @Override
    public String toString() {
        return "Seed Number = " + seedNum;
    }
}
