// Code written by Alexander Holmstock

public class NobleCard
{
    // DATA
    private int cardPoints;
    private int redCost;
    private int whiteCost;
    private int greenCost;
    private int blackCost;
    private int blueCost;
    private boolean visited = false;

    // CONSTRUCTOR
    public NobleCard( int points, int red, int white, int green, int black, int blue )
    {
        cardPoints = points;
        redCost = red;
        whiteCost = white;
        greenCost = green;
        blackCost = black;
        blueCost = blue;
    }

    // GETTERS
    public int getCardPoints() {
        return cardPoints;
    }

    public int getRedCost() {
        return redCost;
    }

    public int getWhiteCost() {
        return whiteCost;
    }

    public int getGreenCost() {
        return greenCost;
    }

    public int getBlackCost() {
        return blackCost;
    }

    public int getBlueCost() {
        return blueCost;
    }

    public boolean isVisited() {
        return visited;
    }

    //SETTERS
    public void setVisited()
    {
        visited = true;
    }
}

