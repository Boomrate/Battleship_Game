package Ships;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Aircraft implements Ship {
    
    @Override
    public int shipSize() {
        return shipSize;
    }
    
    @Override
    public JToggleButton shipButton(){
        aircraft.setBackground(new java.awt.Color(255, 255, 255));
        return aircraft;
    }
    
    @Override
    public void disableButton(){
        aircraft.setEnabled(false);
    }
    
    private JToggleButton aircraft=new JToggleButton(new ImageIcon("C:\\Users\\Κωνσταντινος\\Documents\\NetBeansProjects\\BattleshipGame\\ships\\aircraftButton.png"));
    private int shipSize=5;
}
