package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CasierOperations {


    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public ArrayList<String[]> afiseazaListaCasieri()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        //Query query = entityManager.createQuery(afisareSpectacoleString);
        String[] dateTabel;
        dateTabel= new String[40];
        ArrayList<String[]> elemente=new ArrayList<String[]>();
        //query.setParameter("username", username);
        List<CasierEntity> casierEntities= entityManager.createNamedQuery("CasierEntity.showAll").getResultList();

        for(CasierEntity casier:casierEntities)
        {
            dateTabel=new String[]{Integer.toString(casier.getId()),casier.getNume(),casier.getUsername()};
            elemente.add(dateTabel);
        }

        return elemente;
    }

    public void adaugaCasier(String nume,String username, String parola)
    {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager = entityManagerFactory.createEntityManager();

            CasierEntity casier=cautaCasierUsername(username);
            if(casier!=null)
                throw new IllegalArgumentException("Username-ul exista deja!");
            CasierEntity casierEntity = new CasierEntity();
            casierEntity.setNume(nume);
            casierEntity.setUsername(username);
            Encrypt encrypt = new Encrypt();
            byte[] codare = encrypt.code(parola);
            casierEntity.setParola(codare);

            entityManager.getTransaction().begin();
            entityManager.persist(casierEntity);
            entityManager.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"Casierul a fost adaugat cu succes!");
        }catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        finally {
            entityManagerFactory.close();
        }

    }

    public void modificaCasier(Integer id,String nume,String username,String parola)
    {

            entityManagerFactory=Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager=entityManagerFactory.createEntityManager();

            CasierEntity casier=entityManager.find(CasierEntity.class,id);

            casier.setNume(nume);
            casier.setUsername(username);

            if(!parola.equals(""))
            {
                Encrypt encrypt=new Encrypt();
                casier.setParola(encrypt.code(parola));
            }


            entityManager.getTransaction().begin();
            entityManager.merge(casier);
            entityManager.getTransaction().commit();

            JOptionPane.showMessageDialog(null,"Datele casierului au fost modificate cu succes!");


    }

    public CasierEntity cautaCasierUsername(String username)
    {

            Encrypt code = new Encrypt();

            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("CasierEntity.findByUsername");

            query.setParameter("username", username);
            List<CasierEntity> casierEntityList=query.getResultList();
            CasierEntity casier=null;
            if(casierEntityList.isEmpty())
            {   //entityManagerFactory.close();
                throw new IllegalArgumentException("Nu exista acest username!");
            }
            casier=(CasierEntity) query.getSingleResult();
            //admin=entityManager.find(AdminEntity);

            entityManagerFactory.close();

        return casier;

    }

    public boolean logareCasier(String username,String parola)
    {

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager = entityManagerFactory.createEntityManager();

            CasierEntity casier = this.cautaCasierUsername(username);
            Encrypt encrypt=new Encrypt();
            if(casier==null)
                throw new IllegalArgumentException("Nu exista acest username!");
            byte[] codParola=encrypt.code(parola);
            if(!encrypt.codeToString(codParola).equals(encrypt.codeToString(casier.getParola())))
            {
                throw new IllegalArgumentException("Ai introdus o parola gresita!!");
            }

            return true;

        }catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return false;
        //JOptionPane.showMessageDialog(null,"Datele casierului au fost modificate cu succes!");


    }

}
