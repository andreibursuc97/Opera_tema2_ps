package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LogareView extends JFrame {
    private JPanel panel1;
    private JTextField userNameField;
    private JTextField parolaField;
    private JButton logareCasierButton;
    private JButton logareAdminButton;


    public LogareView()
    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public JTextField getParolaField() {
        return parolaField;
    }

    public void setLogareAdminButton(ActionListener e) {
        this.logareAdminButton.addActionListener(e);
    }
}
