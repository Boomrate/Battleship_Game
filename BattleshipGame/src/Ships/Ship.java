package Ships;

import javax.swing.JToggleButton;

public interface Ship {
    public int shipSize();
    
    public JToggleButton shipButton();
    
    public void disableButton();
}

