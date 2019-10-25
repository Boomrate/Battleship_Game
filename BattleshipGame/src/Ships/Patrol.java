package Ships;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Patrol  implements Ship {
    
    @Override
    public int shipSize() {
        return shipSize;
    }
    
    @Override
    public JToggleButton shipButton() {
        patrol.setBackground(new java.awt.Color(255, 255, 255));
        return patrol;
    }
     
    @Override
    public void disableButton(){
        patrol.setEnabled(false);
    }

    private JToggleButton patrol=new JToggleButton(new ImageIcon("C:\\Users\\Κωνσταντινος\\Documents\\NetBeansProjects\\BattleshipGame\\ships\\patrolButton.png"));
    private int shipSize=2;
}
