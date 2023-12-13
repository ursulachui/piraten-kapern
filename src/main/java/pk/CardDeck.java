package pk;

import java.util.*;

public class CardDeck {
    static Log logger = new Log();
    //can take out trace after
    public static Cards select() {
        Cards[] cardDeck = new Cards[34];

        for (int i=0; i<2; i++) {
            cardDeck[i] = Cards.SEABATTLE_2;
        }
        for (int i=2; i<4; i++) {
            cardDeck[i] = Cards.SEABATTLE_3;
        }
        for (int i=4; i<6; i++) {
            cardDeck[i] = Cards.SEABATTLE_4;
        }
        for (int i=6; i<10; i++) {
            cardDeck[i] = Cards.MONKEY_BUSINESS;
        }
        for (int i=10; i<cardDeck.length; i++) {
            cardDeck[i] = Cards.NOP;
        }
        int num = (int) (Math.random()*(34));

        Cards card = cardDeck[num];
        return card;
    }
}
