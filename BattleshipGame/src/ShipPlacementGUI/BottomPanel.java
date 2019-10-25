package ShipPlacementGUI;

//<editor-fold defaultstate="collapsed" desc="Imports">
import GameGUI.PlayingBoardFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// </editor-fold>

public class BottomPanel {

    public BottomPanel(String playerNickname,JFrame parent) {
        parentFrame=parent;
        initComp(playerNickname);
    }

    //<editor-fold defaultstate="collapsed" desc="Panel Initialize">
    private void initComp(String playerNickname) {
        
        jPanel.setPreferredSize(new Dimension(900, 40));
        jPanel.setRequestFocusEnabled(false);
        jPanel.setLayout(new BorderLayout());

        //Rotate Button
        rotateButton.setText("Rotate");
        rotateButton.setPreferredSize(new Dimension(140, 20));
        rotateButton.setRolloverEnabled(true);
        rotateButton.setToolTipText("Rotate Ship");
        rotateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (rotateValue == 0) {
                    rotateValue = 1;
                } else {
                    rotateValue = 0;
                }
            }
        });
        jPanel.add(rotateButton, BorderLayout.LINE_START);

        //Start Button
        startButton.setText("Start Game!");
        startButton.setPreferredSize(new Dimension(140, 20));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new PlayingBoardFrame(playerNickname,user,pc).setVisible(true);
                parentFrame.dispose();
            }
        });
        startButton.setEnabled(false);
        jPanel.add(startButton, BorderLayout.LINE_END);
    }
// </editor-fold>
    
    public int getRotate() {
        return rotateValue;
    }

    public void setStartEnable() {
        startButton.setEnabled(true);
    }

    public void setStartDisable() {
        startButton.setEnabled(false);
    }

    public void takeBoards(char[][] userGrid, char[][] pcGrid) {
        user = userGrid;
        pc = pcGrid;
    }

//<editor-fold defaultstate="collapsed" desc="Fields">  
    private JFrame parentFrame;
    public JPanel jPanel = new JPanel();
    private JButton rotateButton = new JButton(), startButton = new JButton();
    private int rotateValue = 0;
    private char[][] user = new char[10][10], pc = new char[10][10];
// </editor-fold>
}
