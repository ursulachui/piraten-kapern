package pk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MonkeyParrot {
    static Log logger = new Log();
    static Dice myDice = new Dice();
    static int combination = 0;

    public void monkeyStrategy(boolean trace, Player player) {
        int dice = 8;

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

        for (int i = 0; i < allDice.size(); i++) {
            if (symbolMap.containsKey(allDice.get(i))) {
                symbolMap.put(allDice.get(i), symbolMap.get(allDice.get(i)) + 1);
            } else {
                symbolMap.put(allDice.get(i), 1);
            }
        }
        logger.tracer(trace,symbolMap);

        if (symbolMap.containsKey(Faces.MONKEY)) {
            combination += symbolMap.get(Faces.MONKEY);
        }
        if (symbolMap.containsKey(Faces.PARROT)) {
            combination += symbolMap.get(Faces.PARROT);
        }

        dice -= combination;
        int max = Collections.max(symbolMap.values());

        if (max-combination > 2) {
            while (player.num_skull < 2 && dice > 1) {
                int combo = 0;
                dice += combo;

                for (int l = 0; l < allDice.size(); l++) {
                    if (symbolMap.get(allDice.get(l)) < 3 && symbolMap.size() > 2) {
                        logger.tracer(trace, String.format("This is die #%s", l + 1));
                        Faces symbol = myDice.roll();
                        allDice.set(l, symbol);
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

                for (Integer value : symbolMap.values()) {
                    if (value > 2) {
                        combo += value;
                    }
                }
                dice -= combo;
            }

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

            if (player.num_skull > 2) {
                player.points = 0;
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

        else {
            while (player.num_skull < 2 && dice > 1) {
                dice += combination;

                for (int i = 0; i < allDice.size(); i++) {
                    if (!allDice.get(i).equals(Faces.MONKEY) && !allDice.get(i).equals(Faces.PARROT)) {
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

                combination = 0;
                if (symbolMap.containsKey(Faces.MONKEY)) {
                    combination += symbolMap.get(Faces.MONKEY);
                }
                if (symbolMap.containsKey(Faces.PARROT)) {
                    combination += symbolMap.get(Faces.PARROT);
                }
                dice -= combination;
            }

            if (combination == 3) {
                player.points += 100;
            } else if (combination == 4) {
                player.points += 200;
            } else if (combination == 5) {
                player.points += 500;
            } else if (combination == 6) {
                player.points += 1000;
            } else if (combination == 7) {
                player.points += 2000;
            } else if (combination == 8) {
                player.points += 4000;
            }

            for (int i = 0; i < dice; i++) {
                if (allDice.get(i) == Faces.DIAMOND || allDice.get(i) == Faces.GOLD) {
                    player.points += 100;
                }
            }

            if (player.num_skull > 2) {
                player.points = 0;
            }

            logger.tracer(trace, "Turn ended.");
            logger.header(String.format("Points for this round: %s", player.points));
            player.total_points += player.points;
            logger.header(String.format("Total points: %s", player.total_points));

            allDice.clear();
            symbolMap.clear();
            player.num_skull = 0;
            player.points = 0;
            combination = 0;
        }
    }
}