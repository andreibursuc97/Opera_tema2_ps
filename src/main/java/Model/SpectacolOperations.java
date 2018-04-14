package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpectacolOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    //private static final String afisareSpectacoleString;
    //private static final String cautareParolaAdminString;
    DateFormat outputFormat;

    /*static
    {
        afisareSpectacoleString="FROM SpectacolEntity";
        cautareParolaAdminString="update admin set logat=false where logat=true";


    }*/

    public SpectacolOperations()
    {
        outputFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void adaugaSpectaool(String gen, String titlu, String regia, String distributie,String data, Integer nrTotalBilete, Integer nrBileteVandute)
    {
        try {
        entityManagerFactory=Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager=entityManagerFactory.createEntityManager();

        SpectacolEntity spectacol=new SpectacolEntity();
        spectacol.setGen(gen);
        spectacol.setTitlu(titlu);
        spectacol.setRegia(regia);
        spectacol.setDistributie(distributie);
        spectacol.setNrTotalBilete(nrTotalBilete);
        spectacol.setNrBileteVandute(nrBileteVandute);


        Date date=outputFormat.parse(data);

        spectacol.setData(new java.sql.Date(date.getYear(),date.getMonth(),date.getDay()));



        entityManager.getTransaction().begin();
        entityManager.persist(spectacol);
        entityManager.getTransaction().commit();

        JOptionPane.showMessageDialog(null,"Spectacolul a fost adaugat cu succes!");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null,"Formatul introdus pentru data este gresit!");
        }
        finally {
            entityManagerFactory.close();
        }

    }

    public void modificaSpectaool(Integer id,String gen, String titlu, String regia, String distributie,String data, Integer nrTotalBilete, Integer nrBileteVandute)
    {
        try {
            entityManagerFactory=Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager=entityManagerFactory.createEntityManager();

            SpectacolEntity spectacol=entityManager.find(SpectacolEntity.class,id);
            spectacol.setGen(gen);
            spectacol.setTitlu(titlu);
            spectacol.setRegia(regia);
            spectacol.setDistributie(distributie);
            spectacol.setNrTotalBilete(nrTotalBilete);
            spectacol.setNrBileteVandute(nrBileteVandute);


            Date date=outputFormat.parse(data);

            spectacol.setData(new java.sql.Date(date.getYear(),date.getMonth(),date.getDay()));



            entityManager.getTransaction().begin();
            entityManager.merge(spectacol);
            entityManager.getTransaction().commit();

            JOptionPane.showMessageDialog(null,"Spectacolul a fost modificat cu succes!");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null,"Formatul introdus pentru data este gresit!");
        }
        finally {
            entityManagerFactory.close();
        }

    }

    public ArrayList<String[]> afiseazaSpectacole()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        //Query query = entityManager.createQuery(afisareSpectacoleString);
        String[] dateTabel;
        dateTabel= new String[40];
        ArrayList<String[]> elemente=new ArrayList<String[]>();
        //query.setParameter("username", username);
        List<SpectacolEntity> spectacolEntities= entityManager.createNamedQuery("SpectacolEntity.afiseazaSpectacole").getResultList();

        for(SpectacolEntity spectacol:spectacolEntities)
        {
            dateTabel=new String[]{Integer.toString(spectacol.getId()),spectacol.getGen(),spectacol.getTitlu(),spectacol.getRegia(),
                    spectacol.getDistributie(),outputFormat.format(spectacol.getData()),Integer.toString(spectacol.getNrTotalBilete()),Integer.toString(spectacol.getNrBileteVandute())};
            elemente.add(dateTabel);
        }

        return elemente;
    }





}
