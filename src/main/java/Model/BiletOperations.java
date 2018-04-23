package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BiletOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public ArrayList<String[]> afiseazaBilete(int idSpectaool)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("BiletEntity.showAll");

        query.setParameter("id", idSpectaool);
        //Query query = entityManager.createQuery(afisareSpectacoleString);
        String[] dateTabel;
        dateTabel= new String[40];
        ArrayList<String[]> elemente=new ArrayList<String[]>();
        //query.setParameter("username", username);
        List<BiletEntity> biletEntities= query.getResultList();

        for(BiletEntity bilet:biletEntities)
        {
            dateTabel=new String[]{Integer.toString(bilet.getId()),Integer.toString(bilet.getRand()),Integer.toString(bilet.getNumar())};
            elemente.add(dateTabel);
        }
        entityManagerFactory.close();

        return elemente;
    }

    public List<BiletEntity> returneazaBilete(int idSpectaool)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("BiletEntity.showAll");

        query.setParameter("id", idSpectaool);
        //Query query = entityManager.createQuery(afisareSpectacoleString);
        ArrayList<String[]> elemente=new ArrayList<String[]>();
        //query.setParameter("username", username);
        List<BiletEntity> biletEntities= query.getResultList();


        entityManagerFactory.close();

        return biletEntities;
    }

    public void export(Exporter exporter,int id)
    {
        exporter.export(id);
    }

    public void adaugaBilet(int idSpectacol,int rand,int numar)
    {
        try {
            entityManagerFactory=Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager=entityManagerFactory.createEntityManager();

            Query query = entityManager.createNamedQuery("BiletEntity.check");

            query.setParameter("rand", rand);
            query.setParameter("numar",numar);
            query.setParameter("idSpectacol",idSpectacol);
            if(!query.getResultList().isEmpty())
            {
                throw new IllegalArgumentException("Exista deja un bilet pe acest rand si numar!");
            }

            SpectacolEntity spectacol=entityManager.find(SpectacolEntity.class,idSpectacol);

            if(spectacol.getNrBileteVandute()==spectacol.getNrTotalBilete())
                throw new IllegalArgumentException("A fost atins numarul total de bilete!");
            BiletEntity bilet=new BiletEntity();
            bilet.setIdSpectacol(idSpectacol);
            bilet.setRand(rand);
            bilet.setNumar(numar);
            spectacol.setNrBileteVandute(spectacol.getNrBileteVandute()+1);

            entityManager.getTransaction().begin();
            entityManager.persist(bilet);
            entityManager.merge(spectacol);
            entityManager.getTransaction().commit();

            JOptionPane.showMessageDialog(null,"Biletul a fost adaugat cu succes!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        finally {
            entityManagerFactory.close();
        }

    }


}
