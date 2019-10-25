package ShipPlacementGUI;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
// </editor-fold>

public class ShipPlacementFrame extends JFrame {

    public ShipPlacementFrame(String playerNickname) {
        
        TopPanel top = new TopPanel(playerNickname);
        BottomPanel bottom = new BottomPanel(playerNickname,this);
        ShipPanel ships = new ShipPanel();
        BoardPanel board = new BoardPanel(ships,bottom);
        
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BattleShip-ShipPlacement");
        setPreferredSize(new Dimension(900, 640));
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-450, dim.height/2-320);
        
        getContentPane().add(top.jPanel,BorderLayout.PAGE_START);
        getContentPane().add(bottom.jPanel, BorderLayout.PAGE_END);
        getContentPane().add(ships.jPanel, BorderLayout.LINE_START);
        getContentPane().add(board.jPanel, BorderLayout.LINE_END);
    
        pack();
    }
    
    
}
