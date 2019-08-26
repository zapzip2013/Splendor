//Code written by Alexander Holmstock

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class Game {
    // DATA
    Table table = new Table();
    Player player1 = new Player(table);
    Player player2 = new Player(table);
    int pointsToWin = 15;
    int whosTurn = 1; // 1 if currently player 1's turn, two if currently player 2's turn
    boolean gameOver = false;

    // SWING DATA
    private JFrame gameFrame;
    private Dimension windowSize = new Dimension( 800,700 );
    private JPanel[] mineCards;
    private JLabel redGems;
    JLabel whiteGems;
    JLabel greenGems;
    JLabel blackGems;
    JLabel blueGems;
    JLabel goldGems;
    JPanel[] nobelCards;
    JLabel p1RedGems;
    JLabel p1RedBonus;
    JLabel p1WhiteGems;
    JLabel p1WhiteBonus;
    JLabel p1GreenGems;
    JLabel p1GreenBonus;
    JLabel p1BlackGems;
    JLabel p1BlackBonus;
    JLabel p1BlueGems;
    JLabel p1BlueBonus;
    JLabel p1GoldGems;
    JLabel p2RedGems;
    JLabel p2RedBonus;
    JLabel p2WhiteGems;
    JLabel p2WhiteBonus;
    JLabel p2GreenGems;
    JLabel p2GreenBonus;
    JLabel p2BlackGems;
    JLabel p2BlackBonus;
    JLabel p2BlueGems;
    JLabel p2BlueBonus;
    JLabel p2GoldGems;
    JLabel p1ScoreText;
    JLabel p2ScoreText;
    JLabel[] mineBonusArray = new JLabel[12];
    JLabel[] minePointsArray = new JLabel[12];
    JLabel[] mineCostArray = new JLabel[12];
    JLabel[] redCostArray = new JLabel[12];
    JLabel[] whiteCostArray = new JLabel[12];
    JLabel[] greenCostArray = new JLabel[12];
    JLabel[] blackCostArray = new JLabel[12];
    JLabel[] blueCostArray = new JLabel[12];



    // CONSTRUCTOR
    public Game() {
    }

    // METHODS
    public void begin() {
        // summon the frame
        gameFrame = new JFrame("Splendor");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(windowSize);
        gameFrame.setResizable(false);
        gameFrame.add( new SplendorPanel() );
        gameFrame.setVisible(true);
    }

    class SplendorPanel extends JPanel
    {

        private CardListener cardListener = new CardListener();
        private GemListener gemListener = new GemListener();


        public SplendorPanel()
        {
            setLayout(new BorderLayout());

            // CARD PANEL
            GridLayout cardLayout = new GridLayout(4,5,3,3);

            // setting up the panel that displays the cards(mine, noble and the draw decks), putting it in the center
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(cardLayout);

            // creating an array which holds each of the mine card panels, and generating the mine card's layout
            mineCards = new JPanel[12];
            for(int i = 0; i < 12; i++) {
                int level = i / 4;
                int column = i % 4;
                MineCard myMine = table.getMine(level,column);
                JPanel card = new JPanel();
                card.addMouseListener(cardListener);
                card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));
                minePointsArray[i] = new JLabel("Points: " + Integer.toString(myMine.getCardPoints()));
                card.add(minePointsArray[i]);
                mineBonusArray[i] = new JLabel("Something went wrong!");
                MineCard.Color bonusColor = myMine.getBonusGem();

                switch (bonusColor)
                {
                    case RED:
                        mineBonusArray[i].setText("Bonus Gem: RED");
                        mineBonusArray[i].setBackground(Color.red);
                        mineBonusArray[i].setOpaque(true);
                        break;
                    case BLUE:
                        mineBonusArray[i].setText("Bonus Gem: BLUE");
                        mineBonusArray[i].setBackground(Color.cyan);
                        mineBonusArray[i].setOpaque(true);
                        break;
                    case BLACK:
                        mineBonusArray[i].setText("Bonus Gem: BLACK");
                        mineBonusArray[i].setBackground(Color.black);
                        mineBonusArray[i].setForeground(Color.white);
                        mineBonusArray[i].setOpaque(true);
                        break;
                    case GREEN:
                        mineBonusArray[i].setText("Bonus Gem: GREEN");
                        mineBonusArray[i].setBackground(Color.green);
                        mineBonusArray[i].setOpaque(true);
                        break;
                    case WHITE:
                        mineBonusArray[i].setText("Bonus Gem: WHITE");
                        break;
                    default:
                        System.out.println("what....");
                }

                card.add(mineBonusArray[i]);
                // order: red white green black blue
                mineCostArray[i] = new JLabel("Cost:");
                card.add(mineCostArray[i]);
                //if(myMine.getRedCost() > 0)
                //{
                    String temp = "RED- " + myMine.getRedCost();
                    redCostArray[i] = new JLabel(temp);
                    card.add(redCostArray[i]);
                //}
                //if(myMine.getWhiteCost() > 0)
                //{
                    temp = "WHITE- " + myMine.getWhiteCost();
                    whiteCostArray[i] = new JLabel(temp);
                    card.add(whiteCostArray[i]);
                //}
                //if(myMine.getGreenCost() > 0)
                //{
                    temp = "GREEN- " + Integer.toString(myMine.getGreenCost());
                    greenCostArray[i] = new JLabel(temp);
                    card.add(greenCostArray[i]);
                //}
                //if(myMine.getBlackCost() > 0)
                //{
                    temp = "BLACK- " + Integer.toString(myMine.getBlackCost());
                    blackCostArray[i] = new JLabel(temp);
                    card.add(blackCostArray[i]);
                //}
                //if(myMine.getBlueCost() > 0)
                //{
                    temp = "BLUE- " + Integer.toString(myMine.getBlueCost());
                    blueCostArray[i] = new JLabel(temp);
                    card.add(blueCostArray[i]);
                //}
                card.setBorder(new LineBorder(Color.magenta));
                mineCards[i] = card;
            }
            // creating an array that holds each of the noble cards, setting the nobel cards up, and adding them
            nobelCards = new JPanel[3];
            for(int i = 0; i < 3; i++)
            {
                NobleCard myNoble = table.getNoble(i);
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));
                if(i == 0)
                {
                    card.add(new JLabel("Name: Randolph Langley"));
                }
                else if(i == 1)
                {
                    card.add(new JLabel("Name: John Thrasher"));
                }
                else
                {
                    card.add(new JLabel("Name: Ronald Franco"));
                }
                String points = "Points: " + myNoble.getCardPoints();
                JLabel pointLabel = new JLabel(points);
                card.add(pointLabel);
                JPanel cardBottom = new JPanel();
                cardBottom.setLayout(new BoxLayout(cardBottom,BoxLayout.Y_AXIS));
                cardBottom.add(new JLabel("Desires:"));
                if(myNoble.getRedCost() > 0)
                {
                    String temp = "RED- " + myNoble.getRedCost();
                    cardBottom.add(new JLabel(temp));
                }
                if(myNoble.getWhiteCost() > 0)
                {
                    String temp = "WHITE- " + myNoble.getWhiteCost();
                    cardBottom.add(new JLabel(temp));
                }
                if(myNoble.getGreenCost() > 0)
                {
                    String temp = "GREEN- " + Integer.toString(myNoble.getGreenCost());
                    cardBottom.add(new JLabel(temp));
                }
                if(myNoble.getBlackCost() > 0)
                {
                    String temp = "BLACK- " + Integer.toString(myNoble.getBlackCost());
                    cardBottom.add(new JLabel(temp));
                }
                if(myNoble.getBlueCost() > 0)
                {
                    String temp = "BLUE- " + Integer.toString(myNoble.getBlueCost());
                    cardBottom.add(new JLabel(temp));
                }
                card.setBorder(new LineBorder(new Color(255,215,0)));
                card.add(cardBottom);
                nobelCards[i] = card;
            }

            for(int i = 0 ; i < 20; i++)
            {
                if(i < 5) // top row, two blank spaces and the noble cards
                {
                    if( i > 0 && i < 4)
                    {
                        cardPanel.add(nobelCards[i-1]);
                    }
                    else{
                        if(i == 0)
                        {
                            JLabel instr1 = new JLabel("<html>Click the magenta cards to purchase/reserve<br>Click bottom gems to take gems</html>");
                            cardPanel.add(instr1);
                        }
                        if(i == 4)
                        {
                            JLabel instr2 = new JLabel("<html>Player 1 Gems tracked on left<br>Player 2 gems tracked on right<br>Alt-color = permanent bonus</html>");
                            cardPanel.add(instr2);
                        }
                    }
                }
                else if( i % 5 == 0 )
                {
                    JPanel deckTop = new JPanel();
                    deckTop.setLayout(new GridLayout());
                    JLabel deckLevel = new JLabel();
                    String levelText = "Level " + i/5;
                    deckLevel.setText(levelText);
                    deckLevel.setOpaque(true);
                    deckLevel.setBackground(new Color(161,165,144));
                    deckTop.setBorder(new LineBorder(Color.black));
                    deckTop.add(deckLevel);
                    cardPanel.add(deckTop);
                }
                else
                {
                    int mineIndex = 0;
                    if(i > 5 && i < 10)
                    {
                        mineIndex = i - 6;
                    }
                    else if(i < 15)
                    {
                        mineIndex = i - 7;
                    }
                    else
                    {
                        mineIndex = i - 8;
                    }
                    cardPanel.add(mineCards[mineIndex]);
                }
            }
            add(cardPanel,BorderLayout.CENTER);

            // SCORE PANEL
            JPanel scorePanel =  new JPanel();
            JPanel p1Score =  new JPanel();
            JPanel p2Score = new JPanel();
            p1Score.setBackground(Color.lightGray);
            p2Score.setBackground(Color.lightGray);
            p1Score.setBorder(new LineBorder(Color.darkGray));
            p2Score.setBorder(new LineBorder(Color.darkGray));
            p1Score.setOpaque(true);
            p2Score.setOpaque(true);
            p1ScoreText = new JLabel("Player 1 Score: " + player1.getScore());
            p2ScoreText = new JLabel("Player 2 Score: " + player2.getScore());
            p1Score.add(p1ScoreText);
            p2Score.add(p2ScoreText);
            scorePanel.add(p1Score);
            scorePanel.add(p2Score);
            add(scorePanel,BorderLayout.NORTH);

            // PLAYER 1 GEM PANEL
            JPanel p1GemPanel = new JPanel();
            p1GemPanel.setLayout(new GridLayout(11,1));
            //red
            p1RedGems = new JLabel(" "+Integer.toString(player1.getRedCount())+" ");
            p1RedGems.setBackground(Color.red);
            p1RedGems.setOpaque(true);
            p1RedBonus = new JLabel(" "+Integer.toString(player1.getRedBonus())+" ");
            p1RedBonus.setBackground(new Color(150,0,0));
            p1RedBonus.setOpaque(true);
            //white
            p1WhiteGems = new JLabel(" "+Integer.toString(player1.getWhiteCount())+" ");
            p1WhiteBonus = new JLabel(" "+Integer.toString(player1.getWhiteBonus())+" ");
            p1WhiteBonus.setBackground(Color.lightGray);
            p1WhiteBonus.setOpaque(true);
            //green
            p1GreenGems = new JLabel(" "+Integer.toString(player1.getGreenCount())+" ");
            p1GreenGems.setBackground(Color.green);
            p1GreenGems.setOpaque(true);
            p1GreenBonus = new JLabel(" "+Integer.toString(player1.getGreenBonus())+" ");
            p1GreenBonus.setBackground(new Color(0,150,0));
            p1GreenBonus.setOpaque(true);
            //black
            p1BlackGems = new JLabel(" "+Integer.toString(player1.getBlackCount())+" ");
            p1BlackGems.setBackground(Color.black);
            p1BlackGems.setForeground(Color.white);
            p1BlackGems.setOpaque(true);
            p1BlackBonus = new JLabel(" "+Integer.toString(player1.getBlackBonus())+" ");
            p1BlackBonus.setBackground(Color.darkGray);
            p1BlackBonus.setForeground(Color.white);
            p1BlackBonus.setOpaque(true);
            //blue
            p1BlueGems = new JLabel(" "+Integer.toString(player1.getBlueCount())+" ");
            p1BlueGems.setBackground(Color.blue);
            p1BlueGems.setOpaque(true);
            p1BlueBonus = new JLabel(" "+Integer.toString(player1.getBlueBonus())+" ");
            p1BlueBonus.setBackground(new Color(0,0,150));
            p1BlueBonus.setOpaque(true);
            //gold
            p1GoldGems = new JLabel(" "+Integer.toString(player1.getGoldCount())+" ");
            p1GoldGems.setBackground(new Color(255,215,0));
            p1GoldGems.setOpaque(true);
            p1GemPanel.add(p1RedGems);
            p1GemPanel.add(p1RedBonus);
            p1GemPanel.add(p1WhiteGems);
            p1GemPanel.add(p1WhiteBonus);
            p1GemPanel.add(p1GreenGems);
            p1GemPanel.add(p1GreenBonus);
            p1GemPanel.add(p1BlackGems);
            p1GemPanel.add(p1BlackBonus);
            p1GemPanel.add(p1BlueGems);
            p1GemPanel.add(p1BlueBonus);
            p1GemPanel.add(p1GoldGems);
            add(p1GemPanel,BorderLayout.WEST);

            // PLAYER 2 GEM PANEL
            JPanel p2GemPanel = new JPanel();
            p2GemPanel.setLayout(new GridLayout(11,1));
            //red
            p2RedGems = new JLabel(" "+Integer.toString(player2.getRedCount())+" ");
            p2RedGems.setBackground(Color.red);
            p2RedGems.setOpaque(true);
            p2RedBonus = new JLabel(" "+Integer.toString(player2.getRedBonus())+" ");
            p2RedBonus.setBackground(new Color(150,0,0));
            p2RedBonus.setOpaque(true);
            //white
            p2WhiteGems = new JLabel(" "+Integer.toString(player2.getWhiteCount())+" ");
            p2WhiteBonus = new JLabel(" "+Integer.toString(player2.getWhiteBonus())+" ");
            p2WhiteBonus.setBackground(Color.lightGray);
            p2WhiteBonus.setOpaque(true);
            //green
            p2GreenGems = new JLabel(" "+Integer.toString(player2.getGreenCount())+" ");
            p2GreenGems.setBackground(Color.green);
            p2GreenGems.setOpaque(true);
            p2GreenBonus = new JLabel(" "+Integer.toString(player2.getGreenBonus())+" ");
            p2GreenBonus.setBackground(new Color(0,150,0));
            p2GreenBonus.setOpaque(true);
            //black
            p2BlackGems = new JLabel(" "+Integer.toString(player2.getBlackCount())+" ");
            p2BlackGems.setBackground(Color.black);
            p2BlackGems.setForeground(Color.white);
            p2BlackGems.setOpaque(true);
            p2BlackBonus = new JLabel(" "+Integer.toString(player2.getBlackBonus())+" ");
            p2BlackBonus.setBackground(Color.darkGray);
            p2BlackBonus.setForeground(Color.white);
            p2BlackBonus.setOpaque(true);
            //blue
            p2BlueGems = new JLabel(" "+Integer.toString(player2.getBlueCount())+" ");
            p2BlueGems.setBackground(Color.blue);
            p2BlueGems.setOpaque(true);
            p2BlueBonus = new JLabel(" "+Integer.toString(player2.getBlueBonus())+" ");
            p2BlueBonus.setBackground(new Color(0,0,150));
            p2BlueBonus.setOpaque(true);
            //gold
            p2GoldGems = new JLabel(" "+Integer.toString(player2.getGoldCount())+" ");
            p2GoldGems.setBackground(new Color(255,215,0));
            p2GoldGems.setOpaque(true);
            p2GemPanel.add(p2RedGems);
            p2GemPanel.add(p2RedBonus);
            p2GemPanel.add(p2WhiteGems);
            p2GemPanel.add(p2WhiteBonus);
            p2GemPanel.add(p2GreenGems);
            p2GemPanel.add(p2GreenBonus);
            p2GemPanel.add(p2BlackGems);
            p2GemPanel.add(p2BlackBonus);
            p2GemPanel.add(p2BlueGems);
            p2GemPanel.add(p2BlueBonus);
            p2GemPanel.add(p2GoldGems);
            add(p2GemPanel,BorderLayout.EAST);

            // AVAILABLE GEM PANEL
            JPanel gemPanel = new JPanel();
            gemPanel.setLayout(new GridLayout(1,6));
            //red
            redGems = new JLabel("    " + Integer.toString(table.getRedCount()));
            redGems.setName("red");
            redGems.addMouseListener(gemListener);
            redGems.setBackground(Color.red);
            redGems.setOpaque(true);
            //white
            whiteGems = new JLabel("    " + Integer.toString(table.getWhiteCount()));
            whiteGems.setName("white");
            whiteGems.addMouseListener(gemListener);
            //green
            greenGems = new JLabel("    " + Integer.toString(table.getGreenCount()));
            greenGems.setName("green");
            greenGems.addMouseListener(gemListener);
            greenGems.setBackground(Color.green);
            greenGems.setOpaque(true);
            //black
            blackGems = new JLabel("    " + Integer.toString(table.getBlackCount()));
            blackGems.setName("black");
            blackGems.addMouseListener(gemListener);
            blackGems.setBackground(Color.black);
            blackGems.setForeground(Color.white);
            blackGems.setOpaque(true);
            //blue
            blueGems = new JLabel("    " + Integer.toString(table.getBlueCount()));
            blueGems.setName("blue");
            blueGems.addMouseListener(gemListener);
            blueGems.setBackground(Color.blue);
            blueGems.setOpaque(true);
            //gold
            goldGems = new JLabel("    " + Integer.toString(table.getGoldCount()));
            goldGems.setName("gold");
            goldGems.addMouseListener(gemListener);
            goldGems.setBackground(new Color(255,215,0));
            goldGems.setOpaque(true);
            gemPanel.add(redGems);
            gemPanel.add(whiteGems);
            gemPanel.add(greenGems);
            gemPanel.add(blackGems);
            gemPanel.add(blueGems);
            gemPanel.add(goldGems);
            add(gemPanel,BorderLayout.SOUTH);

            /* currently on standby because...this isn't how swing works...so this is only for reference now
            // main game loop
            while (!gameOver) {
                number.setText(Integer.toString(i));
                if (whosTurn == 1) {
                    queryMove(player1);
                    whosTurn = 2;
                } else {
                    queryMove(player2);
                    whosTurn = 1;
                }
                if (checkWin()) {
                    gameOver = true;
                }
            }
            // if player 1 gets 15 points player two takes another turn before the game ends
            if (whosTurn == 2) {
                queryMove(player2);
            }
            if (player1.getScore() > player2.getScore()) {
                // player 1 wins

            } else if (player2.getScore() > player1.getScore()) {
                // player 2 wins

            } else {
                // check tie breaker(person with least number of mines)
                if ( player1.getMineCount() < player2.getMineCount() ){
                    // player 1 wins

                } else if ( player2.getMineCount() < player1.getMineCount() ){
                    // player 2 wins

                } else {
                    // there's a tie

                }
            }*/
        }

        private class CardListener implements MouseListener
        {
            public void mouseClicked( MouseEvent event)
            {
                if( !transactionStarted ) { // make sure they're not in the process of taking gems
                    Player thePlayer;
                    if(whosTurn == 1)
                        thePlayer = player1;
                    else
                        thePlayer = player2;
                    int index = Arrays.asList(mineCards).indexOf(event.getSource());
                    MineCard clickedCard = table.getMine(index / 4, index % 4);
                    Object[] options = {"Purchase Mine", "Reserve Mine","Purchase Reserved Mine"};
                    // reserve returns 1, purchase returns 0, 2 probably returns from purchase reserved mine
                    int buyOrReserve = JOptionPane.showOptionDialog(gameFrame, "Would you like to purchase this mine, reserve this mine, or purchase a reserved mine?", "Player " + whosTurn, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    switch(buyOrReserve) {
                        case(0): // if buy
                            // check if they can buy it
                            int redWant, whiteWant, greenWant, blackWant, blueWant;
                            redWant = clickedCard.getRedCost() - thePlayer.getRedBonus() - thePlayer.getRedCount();
                            whiteWant = clickedCard.getWhiteCost() - thePlayer.getWhiteBonus() - thePlayer.getWhiteCount();
                            greenWant = clickedCard.getGreenCost() - thePlayer.getGreenBonus() - thePlayer.getGreenCount();
                            blackWant = clickedCard.getBlackCost() - thePlayer.getBlackBonus() - thePlayer.getBlackCount();
                            blueWant = clickedCard.getBlueCost() - thePlayer.getBlueBonus() - thePlayer.getBlueCount();
                            if(redWant<0)
                                redWant = 0;
                            if(whiteWant<0)
                                whiteWant = 0;
                            if(greenWant<0)
                                greenWant = 0;
                            if(blackWant<0)
                                blackWant = 0;
                            if(blueWant<0)
                                blueWant = 0;
                            if(redWant+whiteWant+greenWant+blackWant+blueWant <= thePlayer.getGoldCount())
                            {
                                switch(clickedCard.getBonusGem())
                                {
                                    case RED:
                                        thePlayer.redBonus++;
                                        if(whosTurn == 1)
                                        {
                                            p1RedBonus.setText(" "+thePlayer.redBonus+" ");
                                        }
                                        else
                                        {
                                            p2RedBonus.setText(" "+thePlayer.redBonus+" ");
                                        }
                                        break;
                                    case WHITE:
                                        thePlayer.whiteBonus++;
                                        if(whosTurn == 1)
                                        {
                                            p1WhiteBonus.setText(" "+thePlayer.whiteBonus+" ");
                                        }
                                        else
                                        {
                                            p2WhiteBonus.setText(" "+thePlayer.whiteBonus+" ");
                                        }
                                        break;
                                    case GREEN:
                                        thePlayer.greenBonus++;
                                        if(whosTurn == 1)
                                        {
                                            p1GreenBonus.setText(" "+thePlayer.greenBonus+" ");
                                        }
                                        else
                                        {
                                            p2GreenBonus.setText(" "+thePlayer.greenBonus+" ");
                                        }
                                        break;
                                    case BLACK:
                                        thePlayer.blackBonus++;
                                        if(whosTurn == 1)
                                        {
                                            p1BlackBonus.setText(" "+thePlayer.blackBonus+" ");
                                        }
                                        else
                                        {
                                            p2BlackBonus.setText(" "+thePlayer.blackBonus+" ");
                                        }
                                        break;
                                    default:
                                        thePlayer.blueBonus++;
                                        if(whosTurn == 1)
                                        {
                                            p1BlueBonus.setText(" "+thePlayer.blueBonus+" ");
                                        }
                                        else
                                        {
                                            p2BlueBonus.setText(" "+thePlayer.blueBonus+" ");
                                        }
                                        break;
                                }
                                thePlayer.addToScore(clickedCard.getCardPoints());
                                if(whosTurn == 1)
                                    p1ScoreText.setText("Player 1 Score: " + thePlayer.getScore());
                                else
                                    p2ScoreText.setText("Player 2 Score: " + thePlayer.getScore());
                                removeCard(index / 4, index % 4, index);
                                int redSpend = clickedCard.getRedCost(), whiteSpend = clickedCard.getWhiteCost(), greenSpend = clickedCard.getGreenCost(), blackSpend = clickedCard.getBlackCost(), blueSpend = clickedCard.getBlueCost();
                                if(redSpend - thePlayer.getRedBonus() > 0)
                                {
                                    redSpend -= thePlayer.getRedBonus();
                                    while (thePlayer.getRedCount() > 0 && redSpend > 0)
                                    {

                                        thePlayer.ReturnRed();
                                        if(whosTurn == 1)
                                            p1RedGems.setText(" "+thePlayer.getRedCount()+" ");
                                        else
                                            p2RedGems.setText(" "+thePlayer.getRedCount()+" ");
                                        redGems.setText("    "+table.getRedCount());
                                        redSpend -= 1;
                                    }
                                } else{ redSpend = 0; }
                                if(whiteSpend - thePlayer.getWhiteBonus() > 0)
                                {
                                    whiteSpend -= thePlayer.getWhiteBonus();
                                    while (thePlayer.getWhiteCount() > 0 && whiteSpend > 0)
                                    {

                                        thePlayer.ReturnWhite();
                                        if(whosTurn == 1)
                                            p1WhiteGems.setText(" "+thePlayer.getWhiteCount()+" ");
                                        else
                                            p2WhiteGems.setText(" "+thePlayer.getWhiteCount()+" ");
                                        whiteGems.setText("    "+table.getWhiteCount());
                                        whiteSpend -= 1;
                                    }
                                } else { whiteSpend = 0; }
                                if(greenSpend - thePlayer.getGreenBonus() > 0)
                                {
                                    greenSpend -= thePlayer.getGreenBonus();
                                    while (thePlayer.getGreenCount() > 0 && greenSpend > 0)
                                    {

                                        thePlayer.ReturnGreen();
                                        if(whosTurn == 1)
                                            p1GreenGems.setText(" "+thePlayer.getGreenCount()+" ");
                                        else
                                            p2GreenGems.setText(" "+thePlayer.getGreenCount()+" ");
                                        greenGems.setText("    "+table.getGreenCount());
                                        greenSpend -= 1;
                                    }
                                } else { greenSpend = 0; }
                                if(blackSpend - thePlayer.getBlackBonus() > 0)
                                {
                                    blackSpend -= thePlayer.getBlackBonus();
                                    while (thePlayer.getBlackCount() > 0 && blackSpend > 0)
                                    {

                                        thePlayer.ReturnBlack();
                                        if(whosTurn == 1)
                                            p1BlackGems.setText(" "+thePlayer.getBlackCount()+" ");
                                        else
                                            p2BlackGems.setText(" "+thePlayer.getBlackCount()+" ");
                                        blackGems.setText("    "+table.getBlackCount());
                                        blackSpend -= 1;
                                    }
                                } else { blackSpend = 0; }
                                if(blueSpend - thePlayer.getBlueBonus() > 0)
                                {
                                    blueSpend -= thePlayer.getBlueBonus();
                                    while (thePlayer.getBlueCount() > 0 && blueSpend > 0)
                                    {

                                        thePlayer.ReturnBlue();
                                        if(whosTurn == 1)
                                            p1BlueGems.setText(" "+thePlayer.getBlueCount()+" ");
                                        else
                                            p2BlueGems.setText(" "+thePlayer.getBlueCount()+" ");
                                        blueGems.setText("    "+table.getBlueCount());
                                        blueSpend -= 1;
                                    }
                                } else { blueSpend = 0; }
                                for(int i = 0; i < redSpend+greenSpend+whiteSpend+blackSpend+blueSpend; i++){
                                    thePlayer.ReturnGold();
                                    if(whosTurn == 1)
                                        p1GoldGems.setText(" "+thePlayer.getGoldCount()+" ");
                                    else
                                        p2GoldGems.setText(" "+thePlayer.getGoldCount()+" ");
                                    goldGems.setText("    "+table.getGoldCount());
                                }
                                changeTurn();
                            }
                            else
                            {
                                // if they can't buy it yell at them and tell them they can't
                                JOptionPane.showMessageDialog(gameFrame,"You can't afford that mine, bro.");
                            }

                            break;
                        case(1): // if reserve
                            // check to see if they already have 3 cards reserved
                            if(thePlayer.getReservedCount() > 2) //  if they do yell at them and tell them they can't
                            {
                               JOptionPane.showMessageDialog(gameFrame,"You already have 3 cards reserved, consider buying one of them to fee up some space!");
                            }
                            else
                            {
                                thePlayer.reserveCard(clickedCard);
                                removeCard(index / 4, index % 4, index);
                                if(table.getGoldCount() > 0){
                                    thePlayer.TakeGold();
                                    goldGems.setText("    "+table.getGoldCount());
                                    if(whosTurn == 1)
                                        p1GoldGems.setText(" "+thePlayer.getGoldCount()+" ");
                                    else
                                        p2GoldGems.setText(" "+thePlayer.getGoldCount()+" ");
                                    changeTurn();
                                }
                            }
                            //  if they can reserve, add the card to their reserved, remove from table and frame, draw a new card into its place on the table
                            // and place the new card where it was on the frame, then give the player one(1) gold
                            break;
                        default:
                            // check if they have any reserved mines
                            if(thePlayer.getReservedCount() > 0) {
                                boolean forBreak = false;
                                for (int i = 0; i < thePlayer.getReservedCount(); i++) {
                                    MineCard myRes = thePlayer.getReservedCards()[i];
                                    String resDescription = "Do you want to purchase the ";
                                    resDescription += Integer.toString(myRes.getCardPoints()) + " point card, with a ";
                                    switch (myRes.getBonusGem()) {
                                        case RED:
                                            resDescription += "red bonus for\n";
                                            break;
                                        case WHITE:
                                            resDescription += "white bonus for\n";
                                            break;
                                        case GREEN:
                                            resDescription += "green bonus for\n";
                                            break;
                                        case BLACK:
                                            resDescription += "black bonus for\n";
                                            break;
                                        default:
                                            resDescription += "blue bonus for\n";
                                            break;
                                    }
                                    if(myRes.getRedCost() > 0)
                                    {
                                        resDescription += myRes.getRedCost() + " red gems";
                                    }
                                    if(myRes.getWhiteCost() > 0)
                                    {
                                        resDescription += myRes.getWhiteCost() + " white gems";
                                    }
                                    if(myRes.getGreenCost() > 0)
                                    {
                                        resDescription += myRes.getGreenCost() + " green gems";
                                    }
                                    if(myRes.getBlackCost() > 0)
                                    {
                                        resDescription += myRes.getBlackCost() + " black gems";
                                    }
                                    if(myRes.getBlueCost() > 0)
                                    {
                                        resDescription += myRes.getBlueCost() + " blue gems";
                                    }
                                    // 0 is yes 1 is no
                                    int yesOrNo = JOptionPane.showConfirmDialog(gameFrame, resDescription, "Reserved Purchase Option", JOptionPane.YES_NO_OPTION);
                                    if(yesOrNo == 0)
                                    {
                                        /////////////////
                                        // check if they can buy it
                                        redWant = myRes.getRedCost() - thePlayer.getRedBonus() - thePlayer.getRedCount();
                                        whiteWant = myRes.getWhiteCost() - thePlayer.getWhiteBonus() - thePlayer.getWhiteCount();
                                        greenWant = myRes.getGreenCost() - thePlayer.getGreenBonus() - thePlayer.getGreenCount();
                                        blackWant = myRes.getBlackCost() - thePlayer.getBlackBonus() - thePlayer.getBlackCount();
                                        blueWant = myRes.getBlueCost() - thePlayer.getBlueBonus() - thePlayer.getBlueCount();
                                        if(redWant<0)
                                            redWant = 0;
                                        if(whiteWant<0)
                                            whiteWant = 0;
                                        if(greenWant<0)
                                            greenWant = 0;
                                        if(blackWant<0)
                                            blackWant = 0;
                                        if(blueWant<0)
                                            blueWant = 0;
                                        if(redWant+whiteWant+greenWant+blackWant+blueWant <= thePlayer.getGoldCount())
                                        {
                                            switch(myRes.getBonusGem())
                                            {
                                                case RED:
                                                    thePlayer.redBonus++;
                                                    if(whosTurn == 1)
                                                    {
                                                        p1RedBonus.setText(" "+thePlayer.redBonus+" ");
                                                    }
                                                    else
                                                    {
                                                        p2RedBonus.setText(" "+thePlayer.redBonus+" ");
                                                    }
                                                    break;
                                                case WHITE:
                                                    thePlayer.whiteBonus++;
                                                    if(whosTurn == 1)
                                                    {
                                                        p1WhiteBonus.setText(" "+thePlayer.whiteBonus+" ");
                                                    }
                                                    else
                                                    {
                                                        p2WhiteBonus.setText(" "+thePlayer.whiteBonus+" ");
                                                    }
                                                    break;
                                                case GREEN:
                                                    thePlayer.greenBonus++;
                                                    if(whosTurn == 1)
                                                    {
                                                        p1GreenBonus.setText(" "+thePlayer.greenBonus+" ");
                                                    }
                                                    else
                                                    {
                                                        p2GreenBonus.setText(" "+thePlayer.greenBonus+" ");
                                                    }
                                                    break;
                                                case BLACK:
                                                    thePlayer.blackBonus++;
                                                    if(whosTurn == 1)
                                                    {
                                                        p1BlackBonus.setText(" "+thePlayer.blackBonus+" ");
                                                    }
                                                    else
                                                    {
                                                        p2BlackBonus.setText(" "+thePlayer.blackBonus+" ");
                                                    }
                                                    break;
                                                default:
                                                    thePlayer.blueBonus++;
                                                    if(whosTurn == 1)
                                                    {
                                                        p1BlueBonus.setText(" "+thePlayer.blueBonus+" ");
                                                    }
                                                    else
                                                    {
                                                        p2BlueBonus.setText(" "+thePlayer.blueBonus+" ");
                                                    }
                                                    break;
                                            }
                                            thePlayer.addToScore(myRes.getCardPoints());
                                            if(whosTurn == 1)
                                                p1ScoreText.setText("Player 1 Score: " + thePlayer.getScore());
                                            else
                                                p2ScoreText.setText("Player 2 Score: " + thePlayer.getScore());
                                            removeCard(index / 4, index % 4, index);
                                            int redSpend = myRes.getRedCost(), whiteSpend = myRes.getWhiteCost(), greenSpend = myRes.getGreenCost(), blackSpend = myRes.getBlackCost(), blueSpend = myRes.getBlueCost();
                                            if(redSpend - thePlayer.getRedBonus() > 0)
                                            {
                                                redSpend -= thePlayer.getRedBonus();
                                                while (thePlayer.getRedCount() > 0 && redSpend > 0)
                                                {

                                                    thePlayer.ReturnRed();
                                                    if(whosTurn == 1)
                                                        p1RedGems.setText(" "+thePlayer.getRedCount()+" ");
                                                    else
                                                        p2RedGems.setText(" "+thePlayer.getRedCount()+" ");
                                                    redGems.setText("    "+table.getRedCount());
                                                    redSpend -= 1;
                                                }
                                            } else{ redSpend = 0; }
                                            if(whiteSpend - thePlayer.getWhiteBonus() > 0)
                                            {
                                                whiteSpend -= thePlayer.getWhiteBonus();
                                                while (thePlayer.getWhiteCount() > 0 && whiteSpend > 0)
                                                {

                                                    thePlayer.ReturnWhite();
                                                    if(whosTurn == 1)
                                                        p1WhiteGems.setText(" "+thePlayer.getWhiteCount()+" ");
                                                    else
                                                        p2WhiteGems.setText(" "+thePlayer.getWhiteCount()+" ");
                                                    whiteGems.setText("    "+table.getWhiteCount());
                                                    whiteSpend -= 1;
                                                }
                                            } else { whiteSpend = 0; }
                                            if(greenSpend - thePlayer.getGreenBonus() > 0)
                                            {
                                                greenSpend -= thePlayer.getGreenBonus();
                                                while (thePlayer.getGreenCount() > 0 && greenSpend > 0)
                                                {

                                                    thePlayer.ReturnGreen();
                                                    if(whosTurn == 1)
                                                        p1GreenGems.setText(" "+thePlayer.getGreenCount()+" ");
                                                    else
                                                        p2GreenGems.setText(" "+thePlayer.getGreenCount()+" ");
                                                    greenGems.setText("    "+table.getGreenCount());
                                                    greenSpend -= 1;
                                                }
                                            } else { greenSpend = 0; }
                                            if(blackSpend - thePlayer.getBlackBonus() > 0)
                                            {
                                                blackSpend -= thePlayer.getBlackBonus();
                                                while (thePlayer.getBlackCount() > 0 && blackSpend > 0)
                                                {

                                                    thePlayer.ReturnBlack();
                                                    if(whosTurn == 1)
                                                        p1BlackGems.setText(" "+thePlayer.getBlackCount()+" ");
                                                    else
                                                        p2BlackGems.setText(" "+thePlayer.getBlackCount()+" ");
                                                    blackGems.setText("    "+table.getBlackCount());
                                                    blackSpend -= 1;
                                                }
                                            } else { blackSpend = 0; }
                                            if(blueSpend - thePlayer.getBlueBonus() > 0)
                                            {
                                                blueSpend -= thePlayer.getBlueBonus();
                                                while (thePlayer.getBlueCount() > 0 && blueSpend > 0)
                                                {

                                                    thePlayer.ReturnBlue();
                                                    if(whosTurn == 1)
                                                        p1BlueGems.setText(" "+thePlayer.getBlueCount()+" ");
                                                    else
                                                        p2BlueGems.setText(" "+thePlayer.getBlueCount()+" ");
                                                    blueGems.setText("    "+table.getBlueCount());
                                                    blueSpend -= 1;
                                                }
                                            } else { blueSpend = 0; }
                                            for(i = 0; i < redSpend+greenSpend+whiteSpend+blackSpend+blueSpend; i++){
                                                thePlayer.ReturnGold();
                                                if(whosTurn == 1)
                                                    p1GoldGems.setText(" "+thePlayer.getGoldCount()+" ");
                                                else
                                                    p2GoldGems.setText(" "+thePlayer.getGoldCount()+" ");
                                                goldGems.setText("    "+table.getGoldCount());
                                            }
                                            changeTurn();
                                        }
                                        else
                                        {
                                            // if they can't buy it yell at them and tell them they can't
                                            JOptionPane.showMessageDialog(gameFrame,"You can't afford that mine, bro.");
                                        }
                                        /////////////////
                                    }
                                }
                            }
                            else {
                                // if not yell at them
                                JOptionPane.showMessageDialog(gameFrame,"You don't have any reserved mines. Nice try.");
                            }
                    }
                }
            }

            // not using
            public void mousePressed(MouseEvent me){}
            public void mouseReleased(MouseEvent me){}
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
        }

        private void removeCard( int level, int column, int minePanelsIndex)
        {
            table.PurchaseMine(level, column);
            if(table.getMine(level,column) == null)
            {
                mineCards[minePanelsIndex].removeAll();
                mineCards[minePanelsIndex].add(new JLabel());
            }
            else
            {
                minePointsArray[minePanelsIndex].setText("Points: " + table.getMine(level,column).getCardPoints());
                mineBonusArray[minePanelsIndex].setText("Something went wrong!");
                MineCard.Color bonusColor = table.getMine(level,column).getBonusGem();

                switch (bonusColor)
                {
                    case RED:
                        mineBonusArray[minePanelsIndex].setText("Bonus Gem: RED");
                        mineBonusArray[minePanelsIndex].setBackground(Color.red);
                        mineBonusArray[minePanelsIndex].setOpaque(true);
                        break;
                    case BLUE:
                        mineBonusArray[minePanelsIndex].setText("Bonus Gem: BLUE");
                        mineBonusArray[minePanelsIndex].setBackground(Color.cyan);
                        mineBonusArray[minePanelsIndex].setOpaque(true);
                        break;
                    case BLACK:
                        mineBonusArray[minePanelsIndex].setText("Bonus Gem: BLACK");
                        mineBonusArray[minePanelsIndex].setBackground(Color.black);
                        mineBonusArray[minePanelsIndex].setForeground(Color.white);
                        mineBonusArray[minePanelsIndex].setOpaque(true);
                        break;
                    case GREEN:
                        mineBonusArray[minePanelsIndex].setText("Bonus Gem: GREEN");
                        mineBonusArray[minePanelsIndex].setBackground(Color.green);
                        mineBonusArray[minePanelsIndex].setOpaque(true);
                        break;
                    case WHITE:
                        mineBonusArray[minePanelsIndex].setText("Bonus Gem: WHITE");
                        break;
                    default:
                        System.out.println("what....");
                }

                mineCostArray[minePanelsIndex].setText("Cost:");
                //if(table.getMine(level,column).getRedCost() > 0)
                //{
                    String temp = "RED- " + table.getMine(level,column).getRedCost();
                    redCostArray[minePanelsIndex].setText(temp);
                //}
                //if(table.getMine(level,column).getWhiteCost() > 0)
                //{
                    temp = "WHITE- " + table.getMine(level,column).getWhiteCost();
                    whiteCostArray[minePanelsIndex].setText(temp);
                //}
                //if(table.getMine(level,column).getGreenCost() > 0)
                //{
                    temp = "GREEN- " + Integer.toString(table.getMine(level,column).getGreenCost());
                    greenCostArray[minePanelsIndex].setText(temp);
                //}
                //if(table.getMine(level,column).getBlackCost() > 0)
                //{
                    temp = "BLACK- " + Integer.toString(table.getMine(level,column).getBlackCost());
                    blackCostArray[minePanelsIndex].setText(temp);
                //}
                //if(table.getMine(level,column).getBlueCost() > 0)
                //{
                    temp = "BLUE- " + Integer.toString(table.getMine(level,column).getBlueCost());
                    blueCostArray[minePanelsIndex].setText(temp);
                //}
            }
        }

        boolean transactionStarted = false;
        boolean redClicked = false;
        boolean whiteClicked = false;
        boolean greenClicked = false;
        boolean blackClicked = false;
        boolean blueClicked = false;
        private void setClicksFalse()
        {
            transactionStarted = false;
            redClicked = false;
            whiteClicked = false;
            greenClicked = false;
            blackClicked = false;
            blueClicked = false;
        }
        private class GemListener implements MouseListener
        {
            public void mouseClicked( MouseEvent event)
            {
                System.out.println("The button is " + event.getComponent().getName());
                if(event.getComponent().getName() == "red")
                {
                    if(table.getRedCount() == 0) // there are no reds left
                    {
                        JOptionPane.showMessageDialog(gameFrame, "There are no red gems remaining.");
                    }
                    else if(transactionStarted) {
                        if (redClicked) {
                            if (whiteClicked || greenClicked || blackClicked || blueClicked) { // they're trying to get a duplicate when choosing 3 colors
                                JOptionPane.showMessageDialog(gameFrame, "Please choose a different color.");
                            } else // they chose to take only two reds
                            {
                                if (table.getRedCount() < 3)
                                {
                                    JOptionPane.showMessageDialog(gameFrame,"You may only take two of one color if there are 4 or more remaining of that color. Sorry.");
                                } else {
                                    if (whosTurn == 1) {
                                        player1.TakeRed();
                                        p1RedGems.setText(" " + player1.getRedCount() + " ");
                                    } else {
                                        player2.TakeRed();
                                        p2RedGems.setText(" " + player2.getRedCount() + " ");
                                    }
                                    redGems.setText("    " + table.getRedCount());
                                    setClicksFalse();
                                    changeTurn();
                                }
                            }
                        } else if (whiteClicked || greenClicked || blackClicked || blueClicked) // they're choosing red as a valid third or second choice
                        {
                            int gemCounter = 0;
                            if (whiteClicked)
                                gemCounter++;
                            if (greenClicked)
                                gemCounter++;
                            if (blackClicked)
                                gemCounter++;
                            if (blueClicked)
                                gemCounter++;
                            if (gemCounter == 1) // they're choosing red as a second gem
                            {
                                if (whosTurn == 1) {
                                    player1.TakeRed();
                                    p1RedGems.setText(" " + player1.getRedCount() + " ");
                                } else {
                                    player2.TakeRed();
                                    p2RedGems.setText(" " + player2.getRedCount() + " ");
                                }
                                redGems.setText("    " + table.getRedCount());
                                redClicked = true;
                            } else // red is their third and final gen
                            {
                                if (whosTurn == 1) {
                                    player1.TakeRed();
                                    p1RedGems.setText(" " + player1.getRedCount() + " ");
                                } else {
                                    player2.TakeRed();
                                    p2RedGems.setText(" " + player2.getRedCount() + " ");
                                }
                                redGems.setText("    " + table.getRedCount());
                                setClicksFalse();
                                changeTurn();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "You can either get two gems of the same color(if there are 4 left), or three gems of different colors.\nIn order to choose the first option, just click the same color again.\nIn order to choose the second, click two more colors.\nClick gold if there aren't enough gems available, or to finish taking gems early.");
                        transactionStarted = true;
                        redClicked = true;
                        if(whosTurn == 1) {
                            player1.TakeRed();
                            p1RedGems.setText(" "+player1.getRedCount()+" ");
                        }
                        else {
                            player2.TakeRed();
                            p2RedGems.setText(" "+player2.getRedCount()+" ");
                        }
                        redGems.setText("    " + table.getRedCount());
                    }
                }
                else if(event.getComponent().getName() == "white")
                {
                    if(table.getWhiteCount() == 0)
                    {
                        JOptionPane.showMessageDialog(gameFrame, "There are no white gems remaining.");
                    }
                    else if(transactionStarted) {

                        if (whiteClicked) {
                            if (redClicked || greenClicked || blackClicked || blueClicked) { // they're trying to get a duplicate when choosing 3 colors
                                JOptionPane.showMessageDialog(gameFrame, "Please choose a different color.");
                            } else // they chose to take only two whites
                            {
                                if (table.getWhiteCount() < 3)
                                {
                                    JOptionPane.showMessageDialog(gameFrame,"You may only take two of one color if there are 4 or more remaining of that color. Sorry.");
                                } else {
                                    if (whosTurn == 1) {
                                        player1.TakeWhite();
                                        p1WhiteGems.setText(" " + player1.getWhiteCount() + " ");
                                    } else {
                                        player2.TakeWhite();
                                        p2WhiteGems.setText(" " + player2.getWhiteCount() + " ");
                                    }
                                    whiteGems.setText("    " + table.getWhiteCount());
                                    setClicksFalse();
                                    changeTurn();
                                }
                            }
                        } else if (redClicked || greenClicked || blackClicked || blueClicked) {
                            int gemCounter = 0;
                            if (redClicked)
                                gemCounter++;
                            if (greenClicked)
                                gemCounter++;
                            if (blackClicked)
                                gemCounter++;
                            if (blueClicked)
                                gemCounter++;
                            if (gemCounter == 1) // they're choosing white as a second gem
                            {
                                if (whosTurn == 1) {
                                    player1.TakeWhite();
                                    p1WhiteGems.setText(" " + player1.getWhiteCount() + " ");
                                } else {
                                    player2.TakeWhite();
                                    p2WhiteGems.setText(" " + player2.getWhiteCount() + " ");
                                }
                                whiteGems.setText("    " + table.getWhiteCount());
                                whiteClicked = true;
                            } else // white is their third and final gen
                            {
                                if (whosTurn == 1) {
                                    player1.TakeWhite();
                                    p1WhiteGems.setText(" " + player1.getWhiteCount() + " ");
                                } else {
                                    player2.TakeWhite();
                                    p2WhiteGems.setText(" " + player2.getWhiteCount() + " ");
                                }
                                whiteGems.setText("    " + table.getWhiteCount());
                                setClicksFalse();
                                changeTurn();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "You can either get two gems of the same color(if there are 4 left), or three gems of different colors.\nIn order to choose the first option, just click the same color again.\nIn order to choose the second, click two more colors.\nClick gold if there aren't enough gems available, or to finish taking gems early.");
                        transactionStarted = true;
                        whiteClicked = true;
                        if(whosTurn == 1) {
                            player1.TakeWhite();
                            p1WhiteGems.setText(" "+player1.getWhiteCount()+" ");
                        }
                        else {
                            player2.TakeWhite();
                            p2WhiteGems.setText(" "+player2.getWhiteCount()+" ");
                        }
                        whiteGems.setText("    " + table.getWhiteCount());
                    }
                }
                else if(event.getComponent().getName() == "green")
                {
                    if(table.getGreenCount() == 0)
                    {
                        JOptionPane.showMessageDialog(gameFrame, "There are no green gems remaining.");
                    }
                    else if(transactionStarted) {

                        if (greenClicked) {
                            if (redClicked || whiteClicked || blackClicked || blueClicked) { // they're trying to get a duplicate when choosing 3 colors
                                JOptionPane.showMessageDialog(gameFrame, "Please choose a different color.");
                            } else // they chose to take only two greens
                            {
                                if (table.getGreenCount() < 3) {
                                    JOptionPane.showMessageDialog(gameFrame, "You may only take two of one color if there are 4 or more remaining of that color. Sorry.");
                                } else {
                                    if (whosTurn == 1) {
                                        player1.TakeGreen();
                                        p1GreenGems.setText(" " + player1.getGreenCount() + " ");
                                    } else {
                                        player2.TakeGreen();
                                        p2GreenGems.setText(" " + player2.getGreenCount() + " ");
                                    }
                                    greenGems.setText("    " + table.getGreenCount());
                                    setClicksFalse();
                                    changeTurn();
                                }
                            }
                        } else if (redClicked || whiteClicked || blackClicked || blueClicked) {
                            int gemCounter = 0;
                            if (redClicked)
                                gemCounter++;
                            if (whiteClicked)
                                gemCounter++;
                            if (blackClicked)
                                gemCounter++;
                            if (blueClicked)
                                gemCounter++;
                            if (gemCounter == 1) // they're choosing green as a second gem
                            {
                                if (whosTurn == 1) {
                                    player1.TakeGreen();
                                    p1GreenGems.setText(" " + player1.getGreenCount() + " ");
                                } else {
                                    player2.TakeGreen();
                                    p2GreenGems.setText(" " + player2.getGreenCount() + " ");
                                }
                                greenGems.setText("    " + table.getGreenCount());
                                greenClicked = true;
                            } else // green is their third and final gen
                            {
                                if (whosTurn == 1) {
                                    player1.TakeGreen();
                                    p1GreenGems.setText(" " + player1.getGreenCount() + " ");
                                } else {
                                    player2.TakeGreen();
                                    p2GreenGems.setText(" " + player2.getGreenCount() + " ");
                                }
                                greenGems.setText("    " + table.getGreenCount());
                                setClicksFalse();
                                changeTurn();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "You can either get two gems of the same color(if there are 4 left), or three gems of different colors.\nIn order to choose the first option, just click the same color again.\nIn order to choose the second, click two more colors.\nClick gold if there aren't enough gems available, or to finish taking gems early.");
                        transactionStarted = true;
                        greenClicked = true;
                        if(whosTurn == 1) {
                            player1.TakeGreen();
                            p1GreenGems.setText(" "+player1.getGreenCount()+" ");
                        }
                        else {
                            player2.TakeGreen();
                            p2GreenGems.setText(" "+player2.getGreenCount()+" ");
                        }
                        greenGems.setText("    " + table.getGreenCount());
                    }
                }
                else if(event.getComponent().getName() == "black")
                {
                    if(table.getBlackCount() == 0)
                    {
                        JOptionPane.showMessageDialog(gameFrame, "There are no black gems remaining.");
                    }
                    else if(transactionStarted) {

                        if (blackClicked) {
                            if (redClicked || whiteClicked || greenClicked || blueClicked) { // they're trying to get a duplicate when choosing 3 colors
                                JOptionPane.showMessageDialog(gameFrame, "Please choose a different color.");
                            } else // they chose to take only two blacks
                            {
                                if (table.getBlackCount() < 3) {
                                    JOptionPane.showMessageDialog(gameFrame, "You may only take two of one color if there are 4 or more remaining of that color. Sorry.");
                                } else {
                                    if (whosTurn == 1) {
                                        player1.TakeBlack();
                                        p1BlackGems.setText(" " + player1.getBlackCount() + " ");
                                    } else {
                                        player2.TakeBlack();
                                        p2BlackGems.setText(" " + player2.getBlackCount() + " ");
                                    }
                                    blackGems.setText("    " + table.getBlackCount());
                                    setClicksFalse();
                                    changeTurn();
                                }
                            }
                        } else if (redClicked || whiteClicked || greenClicked || blueClicked) {
                            int gemCounter = 0;
                            if (redClicked)
                                gemCounter++;
                            if (whiteClicked)
                                gemCounter++;
                            if (greenClicked)
                                gemCounter++;
                            if (blueClicked)
                                gemCounter++;
                            if (gemCounter == 1) // they're choosing black as a second gem
                            {
                                if (whosTurn == 1) {
                                    player1.TakeBlack();
                                    p1BlackGems.setText(" " + player1.getBlackCount() + " ");
                                } else {
                                    player2.TakeBlack();
                                    p2BlackGems.setText(" " + player2.getBlackCount() + " ");
                                }
                                blackGems.setText("    " + table.getBlackCount());
                                blackClicked = true;
                            } else // black is their third and final gen
                            {
                                if (whosTurn == 1) {
                                    player1.TakeBlack();
                                    p1BlackGems.setText(" " + player1.getBlackCount() + " ");
                                } else {
                                    player2.TakeBlack();
                                    p2BlackGems.setText(" " + player2.getBlackCount() + " ");
                                }
                                blackGems.setText("    " + table.getBlackCount());
                                setClicksFalse();
                                changeTurn();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "You can either get two gems of the same color(if there are 4 left), or three gems of different colors.\nIn order to choose the first option, just click the same color again.\nIn order to choose the second, click two more colors.\nClick gold if there aren't enough gems available, or to finish taking gems early.");
                        transactionStarted = true;
                        blackClicked = true;
                        if(whosTurn == 1) {
                            player1.TakeBlack();
                            p1BlackGems.setText(" "+player1.getBlackCount()+" ");
                        }
                        else {
                            player2.TakeBlack();
                            p2BlackGems.setText(" "+player2.getBlackCount()+" ");
                        }
                        blackGems.setText("    " + table.getBlackCount());
                    }
                }
                else if(event.getComponent().getName() == "blue")
                {
                    if(table.getBlueCount() == 0)
                    {
                        JOptionPane.showMessageDialog(gameFrame, "There are no blue gems remaining.");
                    }
                    else if(transactionStarted) {

                        if (blueClicked) {
                            if (redClicked || whiteClicked || greenClicked || blackClicked) { // they're trying to get a duplicate when choosing 3 colors
                                JOptionPane.showMessageDialog(gameFrame, "Please choose a different color.");
                            } else // they chose to take only two blues
                            {
                                if (whosTurn == 1) {
                                    player1.TakeBlue();
                                    p1BlueGems.setText(" " + player1.getBlueCount() + " ");
                                } else {
                                    player2.TakeBlue();
                                    p2BlueGems.setText(" " + player2.getBlueCount() + " ");
                                }
                                blueGems.setText("    " + table.getBlueCount());
                                setClicksFalse();
                                changeTurn();
                            }
                        } else if (redClicked || whiteClicked || greenClicked || blackClicked) {
                            int gemCounter = 0;
                            if (redClicked)
                                gemCounter++;
                            if (whiteClicked)
                                gemCounter++;
                            if (greenClicked)
                                gemCounter++;
                            if (blackClicked)
                                gemCounter++;
                            if (gemCounter == 1) // they're choosing blue as a second gem
                            {
                                if (table.getBlueCount() < 3)
                                {
                                    JOptionPane.showMessageDialog(gameFrame,"You may only take two of one color if there are 4 or more remaining of that color. Sorry.");
                                } else {
                                    if (whosTurn == 1) {
                                        player1.TakeBlue();
                                        p1BlueGems.setText(" " + player1.getBlueCount() + " ");
                                    } else {
                                        player2.TakeBlue();
                                        p2BlueGems.setText(" " + player2.getBlueCount() + " ");
                                    }
                                    blueGems.setText("    " + table.getBlueCount());
                                    blueClicked = true;
                                }
                            } else // blue is their third and final gen
                            {
                                if (whosTurn == 1) {
                                    player1.TakeBlue();
                                    p1BlueGems.setText(" " + player1.getBlueCount() + " ");
                                } else {
                                    player2.TakeBlue();
                                    p2BlueGems.setText(" " + player2.getBlueCount() + " ");
                                }
                                blueGems.setText("    " + table.getBlueCount());
                                setClicksFalse();
                                changeTurn();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(gameFrame, "You can either get two gems of the same color(if there are 4 left), or three gems of different colors.\nIn order to choose the first option, just click the same color again.\nIn order to choose the second, click two more colors.\nClick gold if there aren't enough gems available, or to finish taking gems early.");
                        transactionStarted = true;
                        blueClicked = true;
                        if(whosTurn == 1) {
                            player1.TakeBlue();
                            p1BlueGems.setText(" "+player1.getBlueCount()+" ");
                        }
                        else {
                            player2.TakeBlue();
                            p2BlueGems.setText(" "+player2.getBlueCount()+" ");
                        }
                        blueGems.setText("    " + table.getBlueCount());
                    }
                }
                else if(event.getComponent().getName() == "gold")
                {
                    if(transactionStarted)
                    {
                        setClicksFalse();
                        changeTurn();
                    }
                }
            }

            // not using
            public void mousePressed(MouseEvent me){}
            public void mouseReleased(MouseEvent me){}
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
        }

    }



    private boolean checkWin()
    {
        if(player1.getScore() >= 15 || player2.getScore() >= 15)
            return true;
        return false;
    }

    private void checkGems()
    {
        if(whosTurn == 1)
        {
            if(player1.getRedCount() + player1.getWhiteCount() + player1.getGreenCount() + player1.getBlackCount() + player1.getBlueCount() + player1.getGoldCount() > 10)
            {
                Object[] options = {"Red","White","Green","Black","Blue","Gold"};
                int response = JOptionPane.showOptionDialog( gameFrame, "You have too many gems, please select a gem to return to the pool","Player " + whosTurn + " has more than 10 gems", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                System.out.print(response);
                switch(response)
                {
                    case 0:
                        if(player1.getRedCount() > 0){
                        player1.ReturnRed();
                        p1RedGems.setText(" "+player1.getRedCount()+" ");
                        redGems.setText("    "+table.getRedCount());
                        }
                        break;
                    case 1:
                        if(player1.getWhiteCount() > 0){
                        player1.ReturnWhite();
                        p1WhiteGems.setText(" "+player1.getWhiteCount()+" ");
                        whiteGems.setText("    "+table.getWhiteCount());
                        }
                        break;
                    case 2:
                        if(player1.getGreenCount() > 0){
                        player1.ReturnGreen();
                        p1GreenGems.setText(" "+player1.getGreenCount()+" ");
                        greenGems.setText("    "+table.getGreenCount());
                        }
                        break;
                    case 3:
                        if(player1.getBlackCount() > 0){
                        player1.ReturnBlack();
                        p1BlackGems.setText(" "+player1.getBlackCount()+" ");
                        blackGems.setText("    "+table.getBlackCount());
                        }
                        break;
                    case 4:
                        if(player1.getBlueCount() > 0){
                        player1.ReturnBlue();
                        p1BlueGems.setText(" "+player1.getBlueCount()+" ");
                        blueGems.setText("    "+table.getBlueCount());
                        }
                        break;
                    default:
                        if(player1.getGoldCount() > 0){
                        player1.ReturnGold();
                        p1GoldGems.setText(" "+player1.getGoldCount()+" ");
                        goldGems.setText("    "+table.getGoldCount());
                        }
                        break;
                }
                checkGems();
            }
        }
        else
        {
            if(player2.getRedCount() + player2.getWhiteCount() + player2.getGreenCount() + player2.getBlackCount() + player2.getBlueCount() + player2.getGoldCount() > 10)
            {
                if(player2.getRedCount() + player2.getWhiteCount() + player2.getGreenCount() + player2.getBlackCount() + player2.getBlueCount() + player2.getGoldCount() > 10)
                {
                    Object[] options = {"Red","White","Green","Black","Blue","Gold"};
                    int response = JOptionPane.showOptionDialog( gameFrame, "You have too many gems, please select a gem to return to the pool","Player " + whosTurn + " has more than 10 gems", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    switch(response)
                    {
                        case 0:
                            if(player2.getRedCount() > 0){
                                player2.ReturnRed();
                                p2RedGems.setText(" "+player2.getRedCount()+" ");
                                redGems.setText("    "+table.getRedCount());
                            }
                            break;
                        case 1:
                            if(player2.getWhiteCount() > 0){
                                player2.ReturnWhite();
                                p2WhiteGems.setText(" "+player2.getWhiteCount()+" ");
                                whiteGems.setText("    "+table.getWhiteCount());
                            }
                            break;
                        case 2:
                            if(player2.getGreenCount() > 0){
                                player2.ReturnGreen();
                                p2GreenGems.setText(" "+player2.getGreenCount()+" ");
                                greenGems.setText("    "+table.getGreenCount());
                            }
                            break;
                        case 3:
                            if(player2.getBlackCount() > 0){
                                player2.ReturnBlack();
                                p2BlackGems.setText(" "+player2.getBlackCount()+" ");
                                blackGems.setText("    "+table.getBlackCount());
                            }
                            break;
                        case 4:
                            if(player2.getBlueCount() > 0){
                                player2.ReturnBlue();
                                p2BlueGems.setText(" "+player2.getBlueCount()+" ");
                                blueGems.setText("    "+table.getBlueCount());
                            }
                            break;
                        default:
                            if(player2.getGoldCount() > 0){
                                player2.ReturnGold();
                                p2GoldGems.setText(" "+player2.getGoldCount()+" ");
                                goldGems.setText("    "+table.getGoldCount());
                            }
                            break;
                    }
                    checkGems();
                }
            }
        }
    }

    private void checkNobles()
    {
        Player thePlayer;
        if(whosTurn == 1)
            thePlayer = player1;
        else
            thePlayer = player2;

        for(int i = 0; i < 3; i++)
        {
            NobleCard x = table.nobles[i];
            if(x.isVisited())
                continue;
            int redWant, whiteWant, greenWant, blackWant, blueWant;
            redWant = x.getRedCost() - thePlayer.getRedBonus();
            whiteWant = x.getWhiteCost() - thePlayer.getWhiteBonus();
            greenWant = x.getGreenCost() - thePlayer.getGreenBonus();
            blackWant = x.getBlackCost() - thePlayer.getBlackBonus();
            blueWant = x.getBlueCost() - thePlayer.getBlueBonus();
            if(redWant<0)
                redWant = 0;
            if(whiteWant<0)
                whiteWant = 0;
            if(greenWant<0)
                greenWant = 0;
            if(blackWant<0)
                blackWant = 0;
            if(blueWant<0)
                blueWant = 0;
            if(redWant+whiteWant+greenWant+blackWant+blueWant == 0)
            {
                JOptionPane.showMessageDialog(gameFrame,"You've been visited by a noble! They gave you " + x.getCardPoints() + " points!");
                thePlayer.addToScore(x.getCardPoints());
                if(whosTurn == 1)
                    p1ScoreText.setText("Player 1 Score: " + thePlayer.getScore());
                else
                    p2ScoreText.setText("Player 2 Score: " + thePlayer.getScore());
                x.setVisited();
                nobelCards[i].removeAll();
                nobelCards[i].add(new JLabel());
            }
        }

    }

    private void changeTurn()
    {
        checkGems();
        checkNobles();
        checkWin();
        if(gameOver && whosTurn == 2)
        {
            if(player1.getScore() > player2.getScore())
            {
                JOptionPane.showMessageDialog(gameFrame,"Player 1 Wins!");
                System.exit(0);
            }
            else if(player2.getScore() > player1.getScore())
            {
                JOptionPane.showMessageDialog(gameFrame,"Player 2 Wins!");
                System.exit(0);
            }
            else // check tie breaker
            {
                if(player1.getMineCount() < player2.getMineCount())
                {
                    JOptionPane.showMessageDialog(gameFrame,"Player 1 Wins, but only narrowly");
                    System.exit(0);
                }
                else if(player2.getMineCount() < player1.getMineCount())
                {
                    JOptionPane.showMessageDialog(gameFrame,"Player 2 Wins, but only narrowly");
                    System.exit(0);
                }
                else // there's an actual tie
                {
                    JOptionPane.showMessageDialog(gameFrame,"Wow. There was actually a tie. Game over. You both lose.");
                    System.exit(0);
                }
            }
        }

        if(player1.goldCount < 0)
            player1.goldCount = 0;
        if(player2.goldCount < 0)
            player2.goldCount = 0;

        if(whosTurn == 1) {
            JOptionPane.showMessageDialog(gameFrame, "Player 2's Turn!");
            whosTurn = 2;
        }
        else {
            JOptionPane.showMessageDialog(gameFrame,"Player 1's Turn!");
            whosTurn = 1;
        }
    }
}