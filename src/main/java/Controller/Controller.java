package Controller;

import Model.*;
import View.AdminView;
import View.CasierView;
import View.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    LogareView logareView;
    AdminView adminView;
    CasierView casierView;
    SpectacolOperations spectacolOperations;
    CasierOperations casierOperations;
    BiletOperations biletOperations;

    public Controller(LogareView logareView, AdminView adminView,CasierView casierView)
    {
        this.logareView=logareView;
        this.adminView=adminView;
        this.casierView=casierView;
        logareView.setVisible(true);
        logareView.setLogareAdminButton(new ButonLogareAdmin());
        logareView.setLogareCasierButton(new ButonLogareCasier());
        adminView.setListenerLogOutButon(new ButonDelogareAdmin());
        adminView.setListenerAdaugaSpectacolButon(new AdaugaSpectacol());
        adminView.setListenerModificaSpectacolButon(new ModificaSpectacol());
        adminView.setListenerAdaugaCasierButon(new AdaugaCasier());
        adminView.setListenerModificaDateCasierButon(new ModificaCasier());
        casierView.setDelogareButton(new ButonDelogareCasier());
        casierView.setAdaugaBiletButton(new AdaugaBilet());
        spectacolOperations=new SpectacolOperations();
        casierOperations=new CasierOperations();
        biletOperations=new BiletOperations();
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
            if(casierOperations.logareCasier(username,parola))
                casierView.setVisible(true);

        }
    }

    public class ButonDelogareAdmin implements ActionListener{
        public void actionPerformed(ActionEvent e){
            adminView.setVisible(false);
            logareView.setVisible(true);
        }
    }

    public class ButonDelogareCasier implements ActionListener{
        public void actionPerformed(ActionEvent e){
            casierView.setVisible(false);
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
                casierView.modelUpdate();
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

                spectacolOperations.modificaSpectaool(id,gen, titlu, regia, distributia, Data, numarTotalBilete);
                adminView.modelUpdate();
                casierView.modelUpdate();
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

    public class AdaugaBilet implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Integer idSpectacol=Integer.parseInt(casierView.getIdSpectacolField().getText());
            Integer rand=Integer.parseInt(casierView.getRandField().getText());
            Integer numar=Integer.parseInt(casierView.getNumarField().getText());

            biletOperations.adaugaBilet(idSpectacol,rand,numar);

            casierView.modelUpdateBilete(idSpectacol);
            casierView.modelUpdate();
            adminView.modelUpdate();
            //casierView.getTabelBilete().setModel(casierOperations.);


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


        }
    }


}
