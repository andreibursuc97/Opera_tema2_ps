package Controller;

import Model.AdminOperations;
import View.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    LogareView logareView;

    public Controller(LogareView logareView)
    {
        this.logareView=logareView;
        logareView.setVisible(true);
        logareView.setLogareAdminButton(new ButonLogareAdmin());
    }

    public class ButonLogareAdmin implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String username=logareView.getUserNameField().getText();
            String parola=logareView.getParolaField().getText();
            AdminOperations adminOperations=new AdminOperations();
            if(adminOperations.logare(username,parola))
                JOptionPane.showMessageDialog(null,"Meniu Admin");
        }
    }
}
