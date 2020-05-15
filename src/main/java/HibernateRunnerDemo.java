import connection.HibernateUtil;
import model.Developer;
import model.Skill;
import model.Specialty;
import model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import viewer.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class HibernateRunnerDemo {
    public static void main(String[] args) {
        MainMenu.viewMainMenu();
    }
}
