//Code written by Alexander Holmstock 

public class Player
{
    // DATA
    private int mineCount = 0;
    private int redCount = 0;
    private int blueCount = 0;
    private int greenCount = 0;
    private int blackCount = 0;
    private int whiteCount = 0;
    public int goldCount = 0;
    public int redBonus = 0;
    public int blueBonus = 0;
    public int greenBonus = 0;
    public int blackBonus = 0;
    public int whiteBonus = 0;
    private int score = 0;
    private MineCard[] reservedCards = new MineCard[3];
    private int reservedCount = 0;
    private Table table;

    // CONSTRUCTOR
    public Player( Table t )
    {
        table = t;
    }

    // METHODS
    public void TakeRed()
    {
        redCount++;
        table.takeRed();
    }

    public void TakeBlue()
    {
        blueCount++;
        table.takeBlue();
    }

    public void TakeGreen()
    {
        greenCount++;
        table.takeGreen();
    }

    public void TakeBlack()
    {
        blackCount++;
        table.takeBlack();
    }

    public void TakeWhite()
    {
        whiteCount++;
        table.takeWhite();
    }

    public void TakeGold()
    {
        goldCount++;
        table.takeGold();
    }

    public void ReturnRed()
    {
        redCount--;
        table.returnRed(1);
    }

    public void ReturnBlue()
    {
        blueCount--;
        table.returnBlue(1);
    }

    public void ReturnGreen()
    {
        greenCount--;
        table.returnGreen(1);
    }

    public void ReturnBlack()
    {
        blackCount--;
        table.returnBlack(1);
    }

    public void ReturnWhite()
    {
        whiteCount--;
        table.returnWhite(1);
    }

    public void ReturnGold()
    {
        goldCount--;
        table.returnGold(1);
    }

    public void VisitNoble(int x)
    {
        NobleCard myNoble = table.VisitNoble(x);
        score += myNoble.getCardPoints();
    }

    public void PurchaseMine( int level, int column )
    {
        mineCount++;
        MineCard myMine = table.PurchaseMine( level, column);
        redCount -= myMine.getRedCost();
        blueCount -= myMine.getBlueCost();
        greenCount -= myMine.getGreenCost();
        blackCount -= myMine.getBlackCost();
        whiteCount -= myMine.getWhiteCost();
        MineCard.Color bonusColor = myMine.getBonusGem();
        switch (bonusColor)
        {
            case RED:
                redCount++;
                break;
            case BLUE:
                blueCount++;
                break;
            case BLACK:
                blackCount++;
                break;
            case GREEN:
                greenCount++;
                break;
            case WHITE:
                whiteCount++;
                break;
            default:
                System.out.println("what....");
        }
    }

    public void removeRes(int x)
    {
        if(x==0)
        {
            reservedCards[0] = reservedCards[1];
            reservedCards[1] = reservedCards[2];
        }
        if(x==1)
        {
            reservedCards[1] = reservedCards[2];
        }
        reservedCount--;
    }

    // GETTERS
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

    public int getReservedCount() {
        return reservedCount;
    }

    public int getRedBonus() {
        return redBonus;
    }

    public int getBlueBonus() {
        return blueBonus;
    }

    public int getGreenBonus() {
        return greenBonus;
    }

    public int getBlackBonus() {
        return blackBonus;
    }

    public int getWhiteBonus() {
        return whiteBonus;
    }

    public int getScore() {
        return score;
    }

    public MineCard[] getReservedCards() {
        return reservedCards;
    }

    public int getMineCount() {
        return mineCount;
    }

    // SETTERS
    public void addToScore(int x)
    {
        score += x;
    }

    public void reserveCard(MineCard x){
        reservedCards[getReservedCount()] = x;
        reservedCount++;
    }
}
