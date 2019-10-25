package Ships;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Submarine  implements Ship {
    
    @Override
    public int shipSize() {
        return shipSize;
    }
    
    @Override
    public JToggleButton shipButton() {
        submarine.setBackground(new java.awt.Color(255, 255, 255));
        return submarine;
    }
     
    @Override
    public void disableButton(){
        submarine.setEnabled(false);
    }

    private JToggleButton submarine=new JToggleButton(new ImageIcon("C:\\Users\\Κωνσταντινος\\Documents\\NetBeansProjects\\BattleshipGame\\ships\\submarineButton.png"));
    private int shipSize=3;
}