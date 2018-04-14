package Controller;

import Model.AdminOperations;
import Model.SpectacolOperations;
import View.AdminView;
import View.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    LogareView logareView;
    AdminView adminView;
    SpectacolOperations spectacolOperations;

    public Controller(LogareView logareView, AdminView adminView)
    {
        this.logareView=logareView;
        this.adminView=adminView;
        logareView.setVisible(true);
        logareView.setLogareAdminButton(new ButonLogareAdmin());
        adminView.setListenerLogOutButon(new ButonDelogareAdmin());
        adminView.setListenerAdaugaSpectacolButon(new AdaugaSpectacol());
        adminView.setListenerModificaSpectacolButon(new ModificaSpectacol());
        spectacolOperations=new SpectacolOperations();
    }

    public class ButonLogareAdmin implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String username=logareView.getUserNameField().getText();
            String parola=logareView.getParolaField().getText();
            AdminOperations adminOperations=new AdminOperations();
            if(adminOperations.logare(username,parola))
            {
                adminView.setVisible(true);
                logareView.setVisible(false);
            }
        }
    }

    public class ButonDelogareAdmin implements ActionListener{
        public void actionPerformed(ActionEvent e){
            adminView.setVisible(false);
            logareView.setVisible(true);
        }
    }

    public class AdaugaSpectacol implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String gen = adminView.getGenField().getText();
                String titlu = adminView.getTitluField().getText();
                String regia = adminView.getRegiaField().getText();
                String distributia = adminView.getDistributiaField().getText();
                String Data = adminView.getDataField().getText();
                Integer numarTotalBilete = Integer.parseInt(adminView.getBileteField().getText());

                if(numarTotalBilete<=0)
                    throw new IllegalArgumentException();

                spectacolOperations.adaugaSpectaool(gen, titlu, regia, distributia, Data, numarTotalBilete, 0);
                adminView.modelUpdate();
                adminView.getTabelSpectacole().setModel(adminView.getModelSpectacol());
            }catch (NumberFormatException e2)
            {
                JOptionPane.showMessageDialog(null,"Format gresit introdus pentru numarul de bilete!");
            }
            catch (IllegalArgumentException e1)
            {
                JOptionPane.showMessageDialog(null,"Numarul total de bilete nu poate fi mai mic sau egal cu 0!");
            }
        }
    }

    public class ModificaSpectacol implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer id = Integer.parseInt(adminView.getIdSpectacolField().getText());
                String gen = adminView.getGenField().getText();
                String titlu = adminView.getTitluField().getText();
                String regia = adminView.getRegiaField().getText();
                String distributia = adminView.getDistributiaField().getText();
                String Data = adminView.getDataField().getText();
                Integer numarTotalBilete = Integer.parseInt(adminView.getBileteField().getText());

                if(numarTotalBilete<=0)
                    throw new IllegalArgumentException();

                spectacolOperations.modificaSpectaool(id,gen, titlu, regia, distributia, Data, numarTotalBilete, 0);
                adminView.modelUpdate();
                adminView.getTabelSpectacole().setModel(adminView.getModelSpectacol());
            }catch (NumberFormatException e2)
            {
                JOptionPane.showMessageDialog(null,"Format gresit introdus pentru numarul de bilete!");
            }
            catch (IllegalArgumentException e1)
            {
                JOptionPane.showMessageDialog(null,"Numarul total de bilete nu poate fi mai mic sau egal cu 0!");
            }
        }
    }




}
