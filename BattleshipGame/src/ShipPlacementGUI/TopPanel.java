package ShipPlacementGUI;

import java.awt.*;
import  javax.swing.*;


public class TopPanel{
    
    public TopPanel(String playerNickname){
        initComp(playerNickname);
    }
    
    private void initComp(String playerNickname){
        istructionsLabel.setFont(new Font("Dialog", 3, 12));
        istructionsLabel.setHorizontalAlignment( SwingConstants.CENTER);
        istructionsLabel.setText("Hello "+playerNickname +"! Select your ships and place them in your Board! When you are ready press \"Start Game!\" . . .");
        istructionsLabel.setPreferredSize(new Dimension(900, 50));
        
        jPanel.add(istructionsLabel, BorderLayout.CENTER);
    }
    
    private  JLabel istructionsLabel= new  JLabel();
    public  JPanel jPanel=new JPanel();
}