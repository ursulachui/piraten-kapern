package pk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }
    
}
