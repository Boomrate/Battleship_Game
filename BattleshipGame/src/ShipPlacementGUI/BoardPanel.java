package ShipPlacementGUI;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;

// </editor-fold>
public final class BoardPanel extends MouseInputAdapter implements MouseListener {

    public BoardPanel(ShipPanel ship, BottomPanel bottom) {
        initComp(ship, bottom);
    }

    public void initComp(ShipPanel ship, BottomPanel bottom) {
        // <editor-fold defaultstate="collapsed" desc="Panel">
        //argument panels χρειάζονταν μεταβλητές γιατί δεν τις έβλεπe mouse listener
        ships = ship;
        bottomPanel = bottom;

        //Layout
        jPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel.setMinimumSize(new Dimension(10, 10));
        jPanel.setLayout(new BorderLayout());

        label.setFont(new Font("Dialog", 1, 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("Border");

        boardPanel.setLayout(new GridLayout(10, 10));
        jPanel.add(boardPanel, BorderLayout.CENTER);

        //Initialize
        blackline = BorderFactory.createLineBorder(Color.black);
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                shipsGrid[m][n] = ' ';
                panelHolder[m][n] = new JPanel();
                panelHolder[m][n].addMouseListener(this);
                panelHolder[m][n].setBorder(blackline);
                boardPanel.add(panelHolder[m][n]);
            }
        }
        color();
        pc();
        // </editor-fold>
    }

    // <editor-fold defaultstate="collapsed" desc="MouseListener">    
    @Override
    public void mouseEntered(MouseEvent event) {
        JPanel panel = (JPanel) event.getSource();

        //change current ship Size and Alignment
        shipSize = ships.currentShipSize;
        shipName = ships.currentShipName;
        rotateValue = bottomPanel.getRotate();

        if (shipSize > 1) {//ship selected
            if (rotateValue == 0) {//Vertical
                highlightPanels(event.getLocationOnScreen(), 'v');
            } else {//Horizontal
                highlightPanels(event.getLocationOnScreen(), 'h');
            }
        } else if (shipSize == 0) {//if player do not have select Ship
            panel.setBackground(Color.black);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        color();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel panel = (JPanel) e.getSource();
        int size,cX=0,cY=0;
        
        if (shipSize != 0) {
            markPlayerBoard(e);
            color();
        }else{//if player click on a placed Ship player have the ability to move it
            
            
            for (int x = 0; x < 10; x++)
                for (int y = 0; y < 10; y++)
                    if(panel==panelHolder[x][y]){
                        cX=x;
                        cY=y;
                    }
            
            if (shipsGrid[cX][cY] != ' ') {
                ships.currentShipName = shipsGrid[cX][cY];
                size = remove(shipsGrid[cX][cY]);
                ships.currentShipSize = size;
                shipSize = ships.currentShipSize;
                shipName = ships.currentShipName;
                rotateValue = bottomPanel.getRotate();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HighlightPanels() (hover) ">   
    private void highlightPanels(Point cursor, char h) {
        boolean check = false;
        Color col = Color.decode("0xFFFF00");

        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {

                Point panelLocation = panelHolder[m][n].getLocationOnScreen();
                if (h == 'v') { //Vertical
                    west = panelLocation.getX();
                    east = panelLocation.getX() + panelHolder[m][n].getWidth();
                    north = panelLocation.getY() - panelHolder[m][n].getHeight() * (shipSize - 1);
                    south = panelLocation.getY() + panelHolder[m][n].getHeight();
                } else if (h == 'h') {//Horizontal
                    west = panelLocation.getX() - panelHolder[m][n].getWidth() * (shipSize - 1);
                    east = panelLocation.getX() + panelHolder[m][n].getWidth();
                    north = panelLocation.getY();
                    south = panelLocation.getY() + panelHolder[m][n].getHeight();
                }

                //Check for the bounds
                boolean inRow = (cursor.getX() > west && cursor.getX() < east) && (cursor.getY() > north && cursor.getY() < south);
                boolean inBounds = inRow;

                if (inBounds == true && roundCheck == 0) {
                    check = checkPlacement(m, n, 1);
                    if (check == true) {
                        col = Color.decode("0xFFFF00");
                        roundCheck += 1;
                    } else {
                        col = Color.decode("0xFF0000");
                        roundCheck += 1;
                    }
                }

                if (inBounds) {
                    panelHolder[m][n].setBackground(col);
                }
            }
        }
        roundCheck = 0;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mark() (ShipPlacer)"> 
    private void markPlayerBoard(MouseEvent e) {
        //set position
        int currentI = 0, currentJ = 0;
        JPanel panel = (JPanel) e.getSource();

        //look through the board for the current position
        boolean check = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (panelHolder[i][j] == panel) {
                    currentI = i;
                    currentJ = j;
                }
            }
        }

        //If there is empty cell and there is a ship selected
        if (shipsGrid[currentI][currentJ] == ' ' && shipSize != 0) {
            check = checkPlacement(currentI, currentJ, 1);

            if (check == true) {//If ship can be placed then check alignment
                if (rotateValue == 0) {//vertical
                    for (int i = currentI; i < (currentI + shipSize); i++) {
                        for (int j = currentJ; j <= currentJ; j++) {
                            shipsGrid[i][j] = shipName;
                        }
                    }
                } else {//horizontal
                    for (int i = currentJ; i < (currentJ + shipSize); i++) {
                        for (int j = currentI; j <= currentI; j++) {
                            shipsGrid[j][i] = shipName;
                        }
                    }
                }

                switch (shipName) {
                    case 'P':
                        ships.patrolShip.setEnabled(false);
                        ships.currentShipSize = 0;
                        ships.currentShipName = ' ';
                        shipsPlaced++;
                        break;
                    case 'S':
                        ships.submarineShip.setEnabled(false);
                        ships.currentShipSize = 0;
                        ships.currentShipName = ' ';
                        shipsPlaced++;
                        break;
                    case 'B':
                        ships.battleShip.setEnabled(false);
                        ships.currentShipSize = 0;
                        ships.currentShipName = ' ';
                        shipsPlaced++;
                        break;
                    case 'A':
                        ships.aircraftShip.setEnabled(false);
                        ships.currentShipSize = 0;
                        ships.currentShipName = ' ';
                        shipsPlaced++;
                        break;
                    case 'D':
                        ships.destroyerShip.setEnabled(false);
                        ships.currentShipSize = 0;
                        ships.currentShipName = ' ';
                        shipsPlaced++;
                        break;
                }
                if (shipsPlaced == 5) {
                    bottomPanel.setStartEnable();
                    bottomPanel.takeBoards(shipsGrid, PCshipsGrid);
                }
            }
        }
    }

    private boolean markPcBoard(int currentI, int currentJ) {
        //set position
        //look through the board for the current position
        boolean check = false;

        //If there is empty cell and there is a ship selected
        if (PCshipsGrid[currentI][currentJ] == ' ' && shipSize != 0) {
            check = checkPlacement(currentI, currentJ, 2);

            if (check == true) {//If ship can be placed then check alignment
                if (rotateValue == 0) {//vertical
                    for (int i = currentI; i < (currentI + shipSize); i++) {
                        for (int j = currentJ; j <= currentJ; j++) {
                            PCshipsGrid[i][j] = shipName;
                        }
                    }
                } else {//horizontal
                    for (int i = currentJ; i < (currentJ + shipSize); i++) {
                        for (int j = currentI; j <= currentI; j++) {
                            PCshipsGrid[j][i] = shipName;
                        }
                    }
                }

                return true;
            }
        }
        return false;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Color() (BoardPaint)"> 
    private void color() {//visualize ships on board
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                if (shipsGrid[m][n] == ' ') {
                    panelHolder[m][n].setBackground(Color.blue);
                    panelHolder[m][n].repaint();
                    panelHolder[m][n].revalidate();
                } else {
                    panelHolder[m][n].setBackground(Color.green);
                    panelHolder[m][n].repaint();
                    panelHolder[m][n].revalidate();
                }
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CheckPlacement() (if it's posible)"> 
    private boolean checkPlacement(int x, int y, int b) {//makes red some speecific places in the board if ship can't placed there
        int m1 = 0, n1 = 0;

        //if its vertical and the (position)+(lenght of the ship) isn't >=10 or horizontal the same for y-axis
        if ((rotateValue == 0 && (x + (shipSize - 1)) >= 10) || (rotateValue == 1 && (y + (shipSize - 1)) >= 10)) {
            return false;//red alert
        }

        //set whole ship position
        if (rotateValue == 0) {
            n1 = y;
            m1 = x + (shipSize - 1);
        } else {
            m1 = x;
            n1 = y + (shipSize - 1);
        }
        if (b == 1) {
            //And check in every ships position for another obstacle
            for (int m = x; m <= m1; m++) {
                for (int n = y; n <= n1; n++) {
                    if (shipsGrid[m][n] != ' ' || shipSize == 0) {
                        return false;
                    }
                }
            }
        } else {//pcBoard
            //And check in every ships position for another obstacle
            for (int m = x; m <= m1; m++) {
                for (int n = y; n <= n1; n++) {
                    if (PCshipsGrid[m][n] != ' ' || shipSize == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Remove() (remove the ship for replacement)"> 
    private int remove(char sh) {
        int size = 0;
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                if (shipsGrid[m][n] == sh) {
                    shipsGrid[m][n] = ' ';
                    panelHolder[m][n].setBackground(Color.blue);
                    panelHolder[m][n].repaint();
                    panelHolder[m][n].revalidate();
                    size += 1;

                }
            }
        }
        shipsPlaced--;
        if (shipsPlaced != 5) {
            bottomPanel.setStartDisable();
        }
        return size;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="pc() (make pc board)">
    private void pc() {
        boolean check = false;
        char name = ' ';
        int randomNum = 0, randomNum2 = 0, size = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                PCshipsGrid[i][j] = ' ';
            }
        }

        for (int i = 0; i < 5; i++) {
            //size
            if (i == 0 || i == 1) {
                size = i + 2;
            } else {
                size = i + 1;
            }

            //find place
            check = false;
            while (check == false) {

                switch (i) {
                    case 0:
                        name = 'P';
                        break;
                    case 1:
                        name = 'D';
                        break;
                    case 2:
                        name = 'S';
                        break;
                    case 3:
                        name = 'B';
                        break;
                    case 4:
                        name = 'A';
                        break;
                }
                randomNum = rand.nextInt(10) + 0;
                randomNum2 = rand.nextInt(10) + 0;
                rotateValue = rand.nextInt(2) + 0;
                shipSize = size;
                shipName = name;

                if (markPcBoard(randomNum, randomNum2) == true) {
                    check = true;
                    }
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private JPanel[][] panelHolder = new JPanel[10][10];
    private char[][] shipsGrid = new char[10][10];//' ' is empty
    private char[][] PCshipsGrid = new char[10][10];// ' ' is empty
    private int rotateValue = 0, shipSize = 0, roundCheck = 0, shipsPlaced = 0;
    private char shipName;
    
    private JLabel label = new JLabel();
    private JPanel boardPanel = new JPanel();
    public JPanel jPanel = new JPanel();
    private double west, east, north, south;
    private Border blackline;
    private ShipPanel ships;
    private BottomPanel bottomPanel;
    Random rand = new Random();
    // </editor-fold>
}
