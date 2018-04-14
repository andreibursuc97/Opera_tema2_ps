package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;

public class AdminOperations {

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    /*private static final String CautareUsernameAdminString;
    private static final String cautareParolaAdminString;

    static
    {
        CautareUsernameAdminString="FROM AdminEntity A where A.username = :username";
        cautareParolaAdminString="update admin set logat=false where logat=true";
    }*/

    public AdminOperations()
    {

    }


    public void insert(String username,String parola)
    {
        entityManagerFactory=Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager=entityManagerFactory.createEntityManager();

        Encrypt code= new Encrypt();

        AdminEntity admin= new AdminEntity();
        admin.setUsername(username);
        admin.setParola(code.code(parola));


        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        //HibernateUtil.shutdown();
    }

    public boolean logare(String username, String parola)
    {
        try {
            Encrypt code = new Encrypt();

            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("Admin.findById");

            query.setParameter("username", username);
            AdminEntity admin=(AdminEntity) query.getSingleResult();
            //admin=entityManager.find(AdminEntity);
            if (admin == null)
                throw new IllegalArgumentException("Ai introdus un username gresit!");

            if(!code.codeToString(admin.getParola()).equals(code.codeToString(code.code(parola))))
                throw new IllegalArgumentException("Ai introdus o parola gresita!");
            return true;

        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        finally {
            entityManagerFactory.close();
        }
      //  Query query = entityManager.createQuery("SELECT id FROM AdminEntity id where username= :?);
        return false;


    }
}