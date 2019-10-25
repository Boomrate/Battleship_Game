package GameGUI;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
//</editor-fold>

public class PlayingBoardFrame extends JFrame implements MouseListener {

    public PlayingBoardFrame(String playerNickname, char[][] userGrid, char[][] pcGrid) {
        user = userGrid;
        pc = pcGrid;
        playersNickname = playerNickname;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Initialize frame">
    private void initComponents() {
        //Instances
        playerPanel = new javax.swing.JPanel();
        pcPanel = new javax.swing.JPanel();
        playerInfo = new javax.swing.JLabel();
        pcInfo = new javax.swing.JLabel();
        userBoardPanel = new javax.swing.JPanel();
        pcBoardPanel = new javax.swing.JPanel();

        //Frame construction
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1020, 550));
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-510, dim.height/2-275);

        //Labels construction
        playerInfo.setPreferredSize(new java.awt.Dimension(500, 50));
        playerInfo.setFont(new Font("Dialog", 3, 12));
        playerInfo.setHorizontalAlignment(SwingConstants.CENTER);
        playerInfo.setText(playersNickname + "'s Board");

        pcInfo.setPreferredSize(new java.awt.Dimension(500, 50));
        pcInfo.setFont(new Font("Dialog", 3, 12));
        pcInfo.setHorizontalAlignment(SwingConstants.CENTER);
        pcInfo.setText("Computer Board");

        //GridPanels construction
        userBoardPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        userBoardPanel.setLayout(new GridLayout(10, 10));

        pcBoardPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        pcBoardPanel.setLayout(new GridLayout(10, 10));

        //Initialize the two playing boards
        blackline = BorderFactory.createLineBorder(Color.black);
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                playerBoard[m][n] = new JPanel();
                playerBoard[m][n].setBorder(blackline);
                userBoardPanel.add(playerBoard[m][n]);

                pcBoard[m][n] = new JPanel();
                pcBoard[m][n].addMouseListener(this);
                pcBoard[m][n].setBorder(blackline);
                pcBoardPanel.add(pcBoard[m][n]);
            }
        }

        //Panels construction
        playerPanel.setPreferredSize(new java.awt.Dimension(500, 750));
        playerPanel.setLayout(new java.awt.BorderLayout());
        playerPanel.add(playerInfo, java.awt.BorderLayout.PAGE_START);
        playerPanel.add(userBoardPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(playerPanel, java.awt.BorderLayout.LINE_START);

        pcPanel.setPreferredSize(new java.awt.Dimension(500, 750));
        pcPanel.setLayout(new java.awt.BorderLayout());
        pcPanel.add(pcInfo, java.awt.BorderLayout.PAGE_START);
        pcPanel.add(pcBoardPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(pcPanel, java.awt.BorderLayout.LINE_END);

        pcColor();
        playerColor();
        pack();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Color">
    private void pcColor() { //Coloring the PC board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pc[i][j] == 'H') {
                    pcBoard[i][j].setBackground(Color.RED);
                } else if (pc[i][j] == 'E') {
                    pcBoard[i][j].setBackground(Color.WHITE);
                } else {
                    pcBoard[i][j].setBackground(Color.BLUE);
                }
            }
        }
    }

    private void playerColor() { //Coloring the Player Board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (user[i][j] == ' ') {
                    playerBoard[i][j].setBackground(Color.BLUE);
                } else if (user[i][j] == 'H') {
                    playerBoard[i][j].setBackground(Color.RED);
                } else if (user[i][j] == 'E') {
                    playerBoard[i][j].setBackground(Color.WHITE);
                } else {
                    playerBoard[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    private void pcRevealColor() { //Reveal the Pc Board ship location
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pc[i][j] == ' ') {
                    pcBoard[i][j].setBackground(Color.BLUE);
                } else if (pc[i][j] == 'H') {
                    pcBoard[i][j].setBackground(Color.RED);
                } else if (pc[i][j] == 'E') {
                    pcBoard[i][j].setBackground(Color.WHITE);
                } else {
                    pcBoard[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MouseListener">
    @Override
    public void mouseClicked(MouseEvent e) {
        if (playerShips != 0 && pcShips != 0) {
            playerHit(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (playerShips != 0 && pcShips != 0) {
            JPanel panel = (JPanel) e.getSource();
            panel.setBackground(Color.BLACK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (playerShips != 0 && pcShips != 0) {
            pcColor();
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Hit">
    private void playerHit(MouseEvent e) {
        JPanel panel = (JPanel) e.getSource();
        int turn=0;
        
        //look through the board for the current position and 'hits'
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (panel == pcBoard[i][j]) {
                    if (pc[i][j] == ' ') {
                        pc[i][j] = 'E';
                        turn=1;
                    } else if (pc[i][j] == 'E' || pc[i][j] == 'H') {
                        turn=0;
                    } else {
                        pc[i][j] = 'H';
                        pcShips--;
                        turn=1;
                    }
                }
            }
        }
        pcColor();

        //Check in case of winner
        if (pcShips == 0) {
            endGame(1);
        } else if(turn==1){
            int ships=pcHit();
            if(ships==0)
                endGame(2);
        }
    }

    private int pcHit() {
        
        //Select Next Hit Position
        if (currentHit == 0) {
            x = rand.nextInt(10) + 0;
            y = rand.nextInt(10) + 0;
            startX=x;
            startY=y;
            currentHit=1;
        } else if (currentHit == 1) {
            if (x > 1) {
                x--;
            } else if (currentHit < 4) {
                currentHit++;
                x=startX;
                y=startY;
            } else {
                currentHit = 0;
            }
        } else if (currentHit == 2) {
            if (y < 9) {
                y++;
            } else if (currentHit < 4) {
                currentHit++;
                x=startX;
                y=startY;
            } else {
                currentHit = 0;
            }
        } else if (currentHit == 3) {
            if (x < 9) {
                x++;
            } else if (currentHit < 4) {
                currentHit++;
                x=startX;
                y=startY;
            } else {
                currentHit = 0;
            }
        } else if (currentHit == 4) {
            if (y > 1) {
                y--;
            } else if (currentHit < 4) {
                currentHit++;
                x=startX;
                y=startY;
            } else {
                currentHit = 0;
            }
        }

        //After Position selected
        if (user[x][y] == ' ') {
            user[x][y] = 'E';
            if (currentHit < 4) {
                currentHit++;
                x=startX;
                y=startY;
            } else {
                currentHit = 0;
            }

        } else if (user[x][y] == 'E' || user[x][y] == 'H') {
            pcHit();
        } else {
            user[x][y] = 'H';
            startX=x;
            startY=y;
            playerShips--;
        }

        playerColor();
        
        return playerShips;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="End Game">
    private void endGame(int winner) {
        if (winner == 1) {
            JOptionPane.showMessageDialog(null, "Player " + playersNickname + " Wins");
        } else {
            JOptionPane.showMessageDialog(null, "PC Wins");
            pcRevealColor();
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private JPanel playerPanel, pcPanel, userBoardPanel, pcBoardPanel;
    private JPanel[][] playerBoard = new JPanel[10][10], pcBoard = new JPanel[10][10];
    private Border blackline;
    private JLabel playerInfo, pcInfo;
    private char[][] user = new char[10][10], pc = new char[10][10];
    private Random rand = new Random();
    private int playerShips = 17, pcShips = 17, currentHit = 0, x, y,startX,startY;
    private String playersNickname;
    //</editor-fold>

}
