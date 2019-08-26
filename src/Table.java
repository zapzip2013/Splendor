// Code written by Alexander Holmstock

public class Table
{
    // DATA
    private MineCard[][] store = new MineCard[4][3];
    public NobleCard[] nobles = new NobleCard[3];
    private MineDeck[] mineDecks = new MineDeck[3];
    private NobleDeck nobleDeck = new NobleDeck();
    private int redCount = 4;
    private int blueCount = 4;
    private int greenCount = 4;
    private int blackCount = 4;
    private int whiteCount = 4;
    private int goldCount = 5;

    // CONSTRUCTOR
    public Table()
    {
        // making the shuffled decks
        mineDecks[0] = new MineDeck(1);
        mineDecks[1] = new MineDeck(2);
        mineDecks[2] = new MineDeck(3);

        // setting up the noble area
        for( int i = 0; i < 3; i++ ){
            nobles[i] = nobleDeck.DrawCard();
        }

        // setting up the store
        for( int j = 0; j < 3; j++ ) {
            for (int i = 0; i < 4; i++) {
                store[i][j] = mineDecks[j].DrawCard();
            }
        }
    }

    // METHODS
    public MineCard PurchaseMine(int level, int column)
    {
        MineCard ret = getMine( level, column );
        if( !mineDecks[level].IsEmpty() )
        {
            store[column][level] = mineDecks[level].DrawCard();
        }
        else {
            store[column][level] = null;
        }
        return ret;
    }

    public NobleCard VisitNoble( int x )
    {
        NobleCard ret = getNoble( x );
        nobles[x] = null;
        return ret;
    }

    // reduces the number of a gem-type if possible
    public void takeRed()
    {
        if(redCount > 0)
        {
            redCount--;
        }
    }

    public void takeGreen()
    {
        if(greenCount > 0)
        {
            greenCount--;
        }
    }

    public void takeBlue()
    {
        if(blueCount > 0)
        {
            blueCount--;
        }
    }

    public void takeWhite()
    {
        if(whiteCount > 0)
        {
            whiteCount--;
        }
    }

    public void takeBlack()
    {
        if(blackCount > 0)
        {
            blackCount--;
        }
    }

    public void takeGold()
    {
        if(goldCount > 0)
        {
            goldCount--;
        }
    }

    // increases the number of a gem type by a given amount
    public void returnRed(int x)
    {
        redCount += x;
    }

    public void returnGreen(int x)
    {
        greenCount += x;
    }

    public void returnBlue(int x)
    {
        blueCount += x;
    }

    public void returnWhite(int x)
    {
        whiteCount += x;
    }

    public void returnBlack(int x)
    {
        blackCount += x;
    }

    public void returnGold(int x)
    {
        goldCount += x;
    }

    // GETTERS
    // returns a specific mine card on the table
    public MineCard getMine(int level, int column)
    {
        return store[column][level];
    }

    // return a specific noble card on the table
    public NobleCard getNoble(int x)
    {
        return nobles[x];
    }

    // functions that return the number of gems left
    public int getRedCount() {
        return redCount;
    }

    public int getBlueCount() {
        return blueCount;
    }

    public int getGreenCount() {
        return greenCount;
    }

    public int getBlackCount() {
        return blackCount;
    }

    public int getWhiteCount() {
        return whiteCount;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public MineDeck getMineDeck(int x)
    {
        return mineDecks[x];
    }
}
