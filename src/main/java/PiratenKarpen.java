import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pk.*;
import java.util.*;

public class PiratenKarpen {
    static boolean trace = false;
    static Player player1 = new Player();
    static Player player2 = new Player();
    static double stdout1, stdout2;
    static int ties = 0;

    public static void main(String[] args) {
        Log logger = new Log();
        trace = logger.checkTrace(args);

        int games = Integer.parseInt(args[0]);
        logger.tracer(trace,String.format("There will be %s games",games));

        for (int j = 0; j < games; j++) {
            logger.header(String.format("This is game #%s", j+1));

            while (player1.total_points < 6000 && player2.total_points < 6000) {
                logger.header("Player 1's Turn");
                if (args[1].equals("random")) {
                    Strategy.random(trace, player1);
                } else if (args[1].equals("combo")) {
                    Strategy.combo(trace, player1);
                } else {
                    logger.tracer(trace,"Please pick a strategy for Player 1.");
                }

                logger.header("Player 2's Turn");
                if (args[2].equals("random")) {
                    Strategy.random(trace, player2);
                } else if (args[2].equals("combo")) {
                    Strategy.combo(trace, player2);
                } else {
                    logger.tracer(trace,"Please pick a strategy for Player 2.");
                }
            }

            if (player1.total_points > player2.total_points) {
                player1.wins += 1;
            }
            else if (player2.total_points > player1.total_points) {
                player2.wins += 1;
            }
            else {
                player1.wins += 1;
                player2.wins += 1;
                ties += 1;
            }
            player1.total_points = 0;
            player2.total_points = 0;
        }

        logger.header(String.format("Player 1 wins: %s",player1.wins));
        logger.header(String.format("Player 2 wins: %s",player2.wins));
        logger.header(String.format("Rounds the players tie: %s",ties));


        stdout1 = (player1.wins/Double.valueOf(games))*100;
        System.out.printf("Player 1's Winning Percentage: %.2f%%\n",stdout1);
        stdout2 = (player2.wins/Double.valueOf(games))*100;
        System.out.printf("Player 2's Winning Percentage: %.2f%%\n",stdout2);
    }
}