// Code written by Alexander Holmstock

public class MineCard
{
    // DATA
    private int cardPoints;
    private int redCost;
    private int whiteCost;
    private int greenCost;
    private int blackCost;
    private int blueCost;

    enum Color
    {
        RED, WHITE, GREEN, BLACK, BLUE
    }

    private Color bonusGem;

    private boolean reserved = false;

    // CONSTRUCTOR
    public MineCard(int points, int red, int white, int green, int black, int blue, Color bonus)
    {
        cardPoints = points;
        redCost = red;
        whiteCost = white;
        greenCost = green;
        blackCost = black;
        blueCost = blue;
        bonusGem = bonus;
    }

    // GETTERS
    public int getRedCost() {
        return redCost;
    }

    public Color getBonusGem() {
        return bonusGem;
    }

    public int getBlackCost() {
        return blackCost;
    }

    public int getBlueCost() {
        return blueCost;
    }

    public int getCardPoints() {
        return cardPoints;
    }

    public int getGreenCost() {
        return greenCost;
    }

    public int getWhiteCost() {
        return whiteCost;
    }
}
