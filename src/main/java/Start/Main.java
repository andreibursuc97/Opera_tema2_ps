package Start;

import Controller.Controller;
import Model.AdminOperations;
import View.LogareView;

import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        AdminOperations admin= new AdminOperations();
        //admin.insert("Admin","1234");
        //System.out.println(admin.logare("Admin","12345"));
        //System.out.println(1+2+"House");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        LogareView logareView=new LogareView();
        Controller controller=new Controller(logareView);
    }
}
