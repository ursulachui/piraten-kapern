package pk;

import java.util.*;

public class SeaBattle {
    static Log logger = new Log();
    static Dice myDice = new Dice();
    private int points = 0;
    private int saber_goal = 0;

    public void seaStrategy(boolean trace, Player player, Cards card) {
        if (card == Cards.SEABATTLE_2) {
            saber_goal = 2;
            points = 300;
        }
        else if (card == Cards.SEABATTLE_3) {
            saber_goal = 3;
            points = 500;
        }
        else if (card == Cards.SEABATTLE_4) {
            saber_goal = 4;
            points = 1000;
        }

        int dice = 8;
        int total_saber = 0;

        ArrayList<Faces> allDice = new ArrayList<>();
        HashMap<Faces,Integer> symbolMap = new HashMap<>();

        for (int k = 0; k < dice; k++) {
            Faces symbol = myDice.roll();
            allDice.add(symbol);
        }
        logger.tracer(trace, allDice);

        player.num_skull += Collections.frequency(allDice, Faces.SKULL);
        logger.tracer(trace, String.format("Number of skulls: %s", player.num_skull));
        dice -= Collections.frequency(allDice, Faces.SKULL);
        allDice.removeAll(Collections.singleton(Faces.SKULL));

        total_saber += Collections.frequency(allDice, Faces.SABER);
        dice -= Collections.frequency(allDice, Faces.SABER);

        for (int i = 0; i < allDice.size(); i++) {
            if (symbolMap.containsKey(allDice.get(i))) {
                symbolMap.put(allDice.get(i), symbolMap.get(allDice.get(i))+1);
            } else {
                symbolMap.put(allDice.get(i), 1);
            }
        }
        logger.tracer(trace,symbolMap);

        while (player.num_skull < 3 && dice > 1 && total_saber < saber_goal) {
            dice += total_saber;

            for (int i = 0; i < allDice.size(); i++) {
                if (!allDice.get(i).equals(Faces.SABER)) {
                    Faces symbol = myDice.roll();
                    allDice.set(i, symbol);
                }
            }
            logger.tracer(trace, allDice);

            player.num_skull += Collections.frequency(allDice, Faces.SKULL);
            logger.tracer(trace, String.format("Number of skulls: %s", player.num_skull));
            dice -= Collections.frequency(allDice, Faces.SKULL);
            allDice.removeAll(Collections.singleton(Faces.SKULL));

            symbolMap.clear();
            for (int i = 0; i < allDice.size(); i++) {
                if (symbolMap.containsKey(allDice.get(i))) {
                    symbolMap.put(allDice.get(i), symbolMap.get(allDice.get(i)) + 1);
                } else {
                    symbolMap.put(allDice.get(i), 1);
                }
            }

            logger.tracer(trace,symbolMap);
            total_saber = Collections.frequency(allDice, Faces.SABER);
            dice -= total_saber;
        }

        if (total_saber >= saber_goal) {
            for (Integer value : symbolMap.values()) {
                if (value == 3) {
                    player.points += 100;
                } else if (value == 4) {
                    player.points += 200;
                } else if (value == 5) {
                    player.points += 500;
                } else if (value == 6) {
                    player.points += 1000;
                } else if (value == 7) {
                    player.points += 2000;
                } else if (value == 8) {
                    player.points += 4000;
                }
            }

            for (int i = 0; i < dice; i++) {
                if (allDice.get(i) == Faces.DIAMOND || allDice.get(i) == Faces.GOLD) {
                    player.points += 100;
                }
            }

            player.points += points;
        }

        else {
            player.points -= points;
        }

        logger.tracer(trace, "Turn ended.");
        logger.header(String.format("Points for this round: %s", player.points));
        player.total_points += player.points;
        logger.header(String.format("Total points: %s", player.total_points));


        allDice.clear();
        symbolMap.clear();
        player.num_skull = 0;
        player.points = 0;
    }
}
