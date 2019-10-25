package Ships;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Battle  implements Ship {
    
    @Override
    public int shipSize() {
        return shipSize;
    }
    
    @Override
    public JToggleButton shipButton() {
        battleship.setBackground(new java.awt.Color(255, 255, 255));
        return battleship;
    }
    
    @Override
    public void disableButton(){
        battleship.setEnabled(false);
    }

    private JToggleButton battleship=new JToggleButton(new ImageIcon("C:\\Users\\Κωνσταντινος\\Documents\\NetBeansProjects\\BattleshipGame\\ships\\battleshipButton.png"));
    private int shipSize=4;
}
