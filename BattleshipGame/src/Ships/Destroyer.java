package Ships;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Destroyer  implements Ship {
    
    @Override
    public int shipSize() {
        return shipSize;
    }
    
    @Override
    public JToggleButton shipButton() {
        destroyer.setBackground(new java.awt.Color(255, 255, 255));
        return destroyer;
    }
    
    @Override
    public void disableButton(){
        destroyer.setEnabled(false);
    }

    private JToggleButton destroyer=new JToggleButton(new ImageIcon("C:\\Users\\Κωνσταντινος\\Documents\\NetBeansProjects\\BattleshipGame\\ships\\destroyerButton.png"));
    private int shipSize=3;
}