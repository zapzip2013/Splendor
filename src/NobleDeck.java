// Code written by Alexander Holmstock

import java.util.Collections;
import java.util.ArrayList;

public class NobleDeck
{
    // DATA
    private ArrayList<NobleCard> deck = new ArrayList<NobleCard>();

    // CONSTRUCTOR
    public NobleDeck()
    {
        Construct2PlayerDeck();
    }

    // METHODS
    public boolean IsEmpty()
    {
        return deck.isEmpty();
    }

    public NobleCard DrawCard()
    {
        NobleCard ret = deck.get(0);
        deck.remove( 0 );

        return ret;
    }

    private void Construct2PlayerDeck() {
        deck.add(new NobleCard(3, 3, 0, 3, 3, 0));
        deck.add(new NobleCard(3, 4, 0, 0, 4, 0));
        deck.add(new NobleCard(3, 0, 3, 3, 0, 3));
        deck.add(new NobleCard(3, 3, 3, 0, 3, 0));
        deck.add(new NobleCard(3, 3, 0, 3, 0, 3));
        deck.add(new NobleCard(3, 4, 0, 4, 0, 0));
        deck.add(new NobleCard(3, 0, 3, 0, 3, 3));
        deck.add(new NobleCard(3, 0, 4, 0, 4, 0));
        deck.add(new NobleCard(3, 0, 0, 4, 0, 4));
        deck.add(new NobleCard(3, 0, 4, 0, 0, 4));
        Collections.shuffle(deck);
        while (deck.size() > 3)
        {
            deck.remove(0);
        }
    }
}