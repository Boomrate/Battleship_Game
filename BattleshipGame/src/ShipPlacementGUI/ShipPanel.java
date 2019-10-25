package ShipPlacementGUI;

// <editor-fold defaultstate="collapsed" desc="Imports">
import Ships.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// </editor-fold> 

public class ShipPanel {

    public ShipPanel() {
        initComp();
    }

    private void initComp() {

        // <editor-fold defaultstate="collapsed" desc="Panel">
        
        jPanel.setPreferredSize(new Dimension(370, 480));
        jPanel.setLayout(new BorderLayout());

        label.setFont(new Font("Dialog", 1, 10));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setText("                                     Select Ship!");
        label.setPreferredSize(new Dimension(40, 70));
        jPanel.add(label, BorderLayout.PAGE_START);

        shipsPanel.setPreferredSize(new Dimension(300, 180));
        shipsPanel.setLayout(new GridLayout(5, 1, 0, 15));

        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="ShipFactory">
        Ship aircraft = shipFactory.getShip("Aircraft");
        aircraftShip = aircraft.shipButton();
        shipsPanel.add(aircraftShip);

        Ship battle = shipFactory.getShip("Battle");
        battleShip = battle.shipButton();
        shipsPanel.add(battleShip);

        Ship submarine = shipFactory.getShip("Submarine");
        submarineShip = submarine.shipButton();
        shipsPanel.add(submarineShip);

        Ship destroyer = shipFactory.getShip("Destroyer");
        destroyerShip = destroyer.shipButton();
        shipsPanel.add(destroyerShip);

        Ship patrol = shipFactory.getShip("Patrol");
        patrolShip = patrol.shipButton();
        shipsPanel.add(patrolShip);

        jPanel.add(shipsPanel, BorderLayout.LINE_START);
        gapPanel.setPreferredSize(new Dimension(378, 70));
        gapPanel.setLayout(new BorderLayout());
        jPanel.add(gapPanel, BorderLayout.PAGE_END);

        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="ActionListeners">
        
        //-----------------------aircraftShip-----------------------------------
        aircraftShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // <editor-fold defaultstate="collapsed" desc="Color/Selection state">
                submarineShip.setSelected(false);
                destroyerShip.setSelected(false);
                patrolShip.setSelected(false);
                battleShip.setSelected(false);

                //change current selected button color and set other to default
                aircraftShip.setBackground(new java.awt.Color(255, 255, 0));
                battleShip.setBackground(new java.awt.Color(255, 255, 255));
                submarineShip.setBackground(new java.awt.Color(255, 255, 255));
                destroyerShip.setBackground(new java.awt.Color(255, 255, 255));
                patrolShip.setBackground(new java.awt.Color(255, 255, 255));

                // </editor-fold>
                currentShipSize = aircraft.shipSize();
                currentShipName = 'A';
            }
        });

        //-------------------------battleship-----------------------------------
        battleShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // <editor-fold defaultstate="collapsed" desc="Color/Selection state">
                aircraftShip.setSelected(false);
                submarineShip.setSelected(false);
                destroyerShip.setSelected(false);
                patrolShip.setSelected(false);

                //change current selected button color and set other to default
                aircraftShip.setBackground(new java.awt.Color(255, 255, 255));
                battleShip.setBackground(new java.awt.Color(255, 255, 0));
                submarineShip.setBackground(new java.awt.Color(255, 255, 255));
                destroyerShip.setBackground(new java.awt.Color(255, 255, 255));
                patrolShip.setBackground(new java.awt.Color(255, 255, 255));

                // </editor-fold>
                currentShipSize = battle.shipSize();
                currentShipName = 'B';
            }
        });

        //-------------------------submarineShip--------------------------------
        submarineShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // <editor-fold defaultstate="collapsed" desc="Color/Selection state">
                aircraftShip.setSelected(false);
                destroyerShip.setSelected(false);
                patrolShip.setSelected(false);
                battleShip.setSelected(false);

                //change current selected button color and set other to default
                aircraftShip.setBackground(new java.awt.Color(255, 255, 255));
                battleShip.setBackground(new java.awt.Color(255, 255, 255));
                submarineShip.setBackground(new java.awt.Color(255, 255, 0));
                destroyerShip.setBackground(new java.awt.Color(255, 255, 255));
                patrolShip.setBackground(new java.awt.Color(255, 255, 255));

                // </editor-fold>
                currentShipSize = submarine.shipSize();
                currentShipName = 'S';
            }
        });

        //-------------------------destroyerShip--------------------------------
        destroyerShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // <editor-fold defaultstate="collapsed" desc="Color/Selection state">
                aircraftShip.setSelected(false);
                submarineShip.setSelected(false);
                patrolShip.setSelected(false);
                battleShip.setSelected(false);

                //change current selected button color and set other to default
                aircraftShip.setBackground(new java.awt.Color(255, 255, 255));
                battleShip.setBackground(new java.awt.Color(255, 255, 255));
                submarineShip.setBackground(new java.awt.Color(255, 255, 255));
                destroyerShip.setBackground(new java.awt.Color(255, 255, 0));
                patrolShip.setBackground(new java.awt.Color(255, 255, 255));

                // </editor-fold>
                currentShipSize = destroyer.shipSize();
                currentShipName = 'D';
            }
        });

        //--------------------------patrolShip----------------------------------
        patrolShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // <editor-fold defaultstate="collapsed" desc="Color/Selection state">
                aircraftShip.setSelected(false);
                submarineShip.setSelected(false);
                destroyerShip.setSelected(false);
                battleShip.setSelected(false);

                //change current selected button color and set other to default
                aircraftShip.setBackground(new java.awt.Color(255, 255, 255));
                battleShip.setBackground(new java.awt.Color(255, 255, 255));
                submarineShip.setBackground(new java.awt.Color(255, 255, 255));
                destroyerShip.setBackground(new java.awt.Color(255, 255, 255));
                patrolShip.setBackground(new java.awt.Color(255, 255, 0));

                // </editor-fold>
                currentShipSize = patrol.shipSize();
                currentShipName = 'P';
            }
        });
        
        // </editor-fold> 
    }

    void setDisable(JToggleButton togB) {
        togB.setEnabled(false);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private JPanel gapPanel = new JPanel(), shipsPanel = new JPanel();
    private JLabel label = new JLabel();
    public JToggleButton aircraftShip, battleShip, submarineShip, destroyerShip, patrolShip;
    public int currentShipSize = 0;
    public char currentShipName = '0';
    public JPanel jPanel = new JPanel();
    private ShipFactory shipFactory = new ShipFactory();
    // </editor-fold> 
}
