package Controller;

import Model.AdminOperations;
import Model.CasierOperations;
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
    CasierOperations casierOperations;

    public Controller(LogareView logareView, AdminView adminView)
    {
        this.logareView=logareView;
        this.adminView=adminView;
        logareView.setVisible(true);
        logareView.setLogareAdminButton(new ButonLogareAdmin());
        logareView.setLogareCasierButton(new ButonLogareCasier());
        adminView.setListenerLogOutButon(new ButonDelogareAdmin());
        adminView.setListenerAdaugaSpectacolButon(new AdaugaSpectacol());
        adminView.setListenerModificaSpectacolButon(new ModificaSpectacol());
        adminView.setListenerAdaugaCasierButon(new AdaugaCasier());
        adminView.setListenerModificaDateCasierButon(new ModificaCasier());
        spectacolOperations=new SpectacolOperations();
        casierOperations=new CasierOperations();
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

    public class ButonLogareCasier implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String username=logareView.getUserNameField().getText();
            String parola=logareView.getParolaField().getText();
            //AdminOperations adminOperations=new AdminOperations();
            casierOperations.logareCasier(username,parola);
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

    public class AdaugaCasier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

                String nume=adminView.getNumeField().getText();
                String username=adminView.getUsernameField().getText();
                String parola=adminView.getParolaField().getText();

                casierOperations.adaugaCasier(nume,username,parola);
                adminView.modelUpdateCasier();
                adminView.getTabelSpectacole().setModel(adminView.getModelSpectacol());


        }
    }

    public class ModificaCasier implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Integer id=Integer.parseInt(adminView.getIdCasierField().getText());
            String nume=adminView.getNumeField().getText();
            String username=adminView.getUsernameField().getText();
            String parola=adminView.getParolaField().getText();

            casierOperations.modificaCasier(id,nume,username,parola);
            adminView.modelUpdateCasier();
            adminView.getTabelSpectacole().setModel(adminView.getModelSpectacol());


        }
    }


}
