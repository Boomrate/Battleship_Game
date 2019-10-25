package PlayerInfoGUI;


// <editor-fold defaultstate="collapsed" desc="Imports">
import ShipPlacementGUI.ShipPlacementFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
// </editor-fold> 

public class InfoFrame extends JFrame {

    //Using Singleton Design Pattern
    private static InfoFrame instance = new InfoFrame();

    public InfoFrame() {
        initComponents();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Panel Initialize">
        
        setLayout(null);
        setPreferredSize(new Dimension(400,160));
        setTitle("Battleship");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-200, dim.height/2-80);
        
        //On Okbutton click create a new Frame for the ship placement.
        okButton.setText("OK!");
        okButton.setBounds(165,90,65,20);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerNickname = nameTextField.getText();
                new ShipPlacementFrame(playerNickname).setVisible(true);
                dispose();
                
            }
        });

        nameTextField.setText("");
        nameTextField.setBounds(110,40,180,30);
        
        nameReguestLabel.setText("Please insert your Nickname and press OK!...");
        nameReguestLabel.setHorizontalAlignment( SwingConstants.CENTER);
        nameReguestLabel.setBounds(50,0,300,30);
        
        
        add(nameReguestLabel);
        add(nameTextField);
        add(okButton);
        // </editor-fold> 
        
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // </editor-fold> 
    }

    public static InfoFrame getInstance() {
        return instance;
    }

    public void startGame() {
        instance.setVisible(true);
    }

// <editor-fold defaultstate="collapsed" desc="Fields">
    public String playerNickname;
    private JButton okButton = new JButton();
    private JLabel nameReguestLabel = new JLabel();
    private JTextField nameTextField = new JTextField();
// </editor-fold> 
}
