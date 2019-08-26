// Code written by Alexander Holmstock

import java.util.Collections;
import java.util.ArrayList;

public class MineDeck {
    // DATA
    private int cardCount;
    private ArrayList<MineCard> deck = new ArrayList<MineCard>();

    // CONSTRUCTOR
    public MineDeck(int deckLevel)
    {
        if( deckLevel == 1 )
        {
            ConstructLevel1Deck();
        }
        else if( deckLevel == 2 )
        {
            ConstructLevel2Deck();
        }
        else if( deckLevel == 3 )
        {
            ConstructLevel3Deck();
        }
    }

    // METHODS
    public void Print()
    {
        for (MineCard mineCard : deck) {
            System.out.println("Points: " + mineCard.getCardPoints() + " red: " + mineCard.getRedCost() + " bonus: " + mineCard.getBonusGem());
        }
    }

    public boolean IsEmpty()
    {
        return deck.isEmpty();
    }

    public MineCard DrawCard()
    {
       MineCard ret = deck.get(0);
       deck.remove(0);

       return ret;
    }

    public int GetCardCount() {
        return cardCount;
    }

    private void ConstructLevel1Deck()
    {
        cardCount = 40;
        deck.add(new MineCard(0, 0, 2, 1, 1, 1, MineCard.Color.RED));
        deck.add(new MineCard(0, 0, 1, 1, 1, 1, MineCard.Color.RED));
        deck.add(new MineCard(0, 1, 1, 0, 3, 0, MineCard.Color.RED));
        deck.add(new MineCard(0, 2, 2, 0, 0, 0, MineCard.Color.RED));
        deck.add(new MineCard(0, 0, 0, 1, 0, 2, MineCard.Color.RED));
        deck.add(new MineCard(0, 0, 3, 0, 0, 0, MineCard.Color.RED));
        deck.add(new MineCard(0, 0, 2, 1, 2, 0, MineCard.Color.RED));
        deck.add(new MineCard(1, 0, 4, 0, 0, 0, MineCard.Color.RED));
        deck.add(new MineCard(0, 1, 1, 0, 1, 1, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 1, 1, 0, 2, 1, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 0, 1, 1, 0, 3, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 2, 0, 0, 2, 1, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 2, 0, 0, 0, 2, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 0, 2, 0, 0, 1, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 3, 0, 0, 0, 0, MineCard.Color.GREEN));
        deck.add(new MineCard(1, 0, 0, 0, 4, 0, MineCard.Color.GREEN));
        deck.add(new MineCard(0, 2, 0, 0, 1, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 0, 0, 0, 2, 2, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 0, 0, 2, 1, 2, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 1, 0, 2, 1, 1, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 0, 0, 0, 0, 3, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 1, 0, 1, 1, 1, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 0, 3, 0, 1, 1, MineCard.Color.WHITE));
        deck.add(new MineCard(1, 0, 0, 4, 0, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(0, 1, 1, 1, 0, 2, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 1, 1, 1, 0, 1, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 3, 0, 1, 1, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 1, 2, 0, 0, 2, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 0, 2, 2, 0, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 1, 0, 2, 0, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 0, 0, 3, 0, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(1, 0, 0, 0, 0, 4, MineCard.Color.BLACK));
        deck.add(new MineCard(0, 0, 0, 2, 2, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(0, 0, 1, 0, 2, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(0, 1, 0, 3, 0, 1, MineCard.Color.BLUE));
        deck.add(new MineCard(0, 2, 1, 2, 0, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(0, 2, 1, 1, 1, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(0, 1, 1, 1, 1, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(0, 0, 0, 0, 3, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(1, 4, 0, 0, 0, 0, MineCard.Color.BLUE));
        Collections.shuffle(deck);
    }

    private void ConstructLevel2Deck()
    {
        cardCount = 30;
        deck.add(new MineCard(3, 0, 0, 0, 6, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(1, 2, 2, 0, 3, 0, MineCard.Color.RED));
        deck.add(new MineCard(1, 2, 0, 0, 3, 3, MineCard.Color.RED));
        deck.add(new MineCard(2, 0, 1, 2, 0, 4, MineCard.Color.RED));
        deck.add(new MineCard(2, 0, 3, 0, 5, 0, MineCard.Color.RED));
        deck.add(new MineCard(2, 0, 0, 0, 5, 0, MineCard.Color.RED));
        deck.add(new MineCard(3, 6, 0, 0, 0, 0, MineCard.Color.RED));
        deck.add(new MineCard(2, 0, 0, 5, 0, 0, MineCard.Color.GREEN));
        deck.add(new MineCard(3, 0, 0, 0, 0, 6, MineCard.Color.BLUE));
        deck.add(new MineCard(2, 0, 0, 0, 0, 5, MineCard.Color.BLUE));
        deck.add(new MineCard(1, 0, 2, 0, 2, 3, MineCard.Color.GREEN));
        deck.add(new MineCard(1, 0, 3, 2, 0, 2, MineCard.Color.BLACK));
        deck.add(new MineCard(2, 3, 0, 5, 0, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(2, 0, 4, 0, 1, 2, MineCard.Color.GREEN));
        deck.add(new MineCard(2, 2, 0, 4, 0, 1, MineCard.Color.BLACK));
        deck.add(new MineCard(2, 0, 0, 3, 0, 5, MineCard.Color.GREEN));
        deck.add(new MineCard(3, 0, 0, 6, 0, 0, MineCard.Color.GREEN));
        deck.add(new MineCard(2, 4, 0, 1, 2, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(2, 5, 0, 0, 3, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(1, 0, 3, 3, 2, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(1, 3, 3, 2, 0, 0, MineCard.Color.GREEN));
        deck.add(new MineCard(2, 0, 5, 0, 0, 2, MineCard.Color.BLUE));
        deck.add(new MineCard(1, 3, 2, 0, 0, 3, MineCard.Color.WHITE));
        deck.add(new MineCard(3, 0, 6, 0, 0, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(1, 3, 0, 2, 0, 2, MineCard.Color.BLUE));
        deck.add(new MineCard(2, 0, 5, 0, 0, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(2, 1, 2, 0, 4, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(1, 0, 0, 3, 3, 2, MineCard.Color.BLUE));
        deck.add(new MineCard(2, 5, 0, 0, 0, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(1, 2, 0, 3, 2, 0, MineCard.Color.WHITE ));
        Collections.shuffle(deck);
    }

    private void ConstructLevel3Deck()
    {
        cardCount = 20;
        deck.add(new MineCard(4, 0, 6, 0, 3, 3, MineCard.Color.BLUE));
        deck.add(new MineCard(4, 0, 0, 7, 0, 0, MineCard.Color.RED));
        deck.add(new MineCard(5, 0, 7, 0, 0, 3, MineCard.Color.BLUE));
        deck.add(new MineCard(3, 3, 3, 3, 5, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(4, 3, 3, 0, 6, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(4, 0, 0, 0, 7, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(5, 0, 3, 0, 7, 0, MineCard.Color.WHITE));
        deck.add(new MineCard(4, 7, 0, 0, 0, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(4, 6, 0, 3, 3, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(5, 7, 0, 0, 3, 0, MineCard.Color.BLACK));
        deck.add(new MineCard(5, 3, 0, 7, 0, 0, MineCard.Color.RED));
        deck.add(new MineCard(4, 3, 0, 6, 0, 3, MineCard.Color.RED));
        deck.add(new MineCard(4, 0, 3, 3, 0, 6, MineCard.Color.GREEN));
        deck.add(new MineCard(4, 0, 0, 0, 0, 7, MineCard.Color.GREEN));
        deck.add(new MineCard(5, 0, 0, 3, 0, 7, MineCard.Color.GREEN));
        deck.add(new MineCard(3, 3, 5, 0, 3, 3, MineCard.Color.GREEN));
        deck.add(new MineCard(3, 5, 0, 3, 3, 3, MineCard.Color.WHITE));
        deck.add(new MineCard(3, 3, 3, 5, 0, 3, MineCard.Color.BLACK));
        deck.add(new MineCard(4, 0, 7, 0, 0, 0, MineCard.Color.BLUE));
        deck.add(new MineCard(3, 0, 3, 3, 3, 5, MineCard.Color.RED));
        Collections.shuffle(deck);
    }

}
