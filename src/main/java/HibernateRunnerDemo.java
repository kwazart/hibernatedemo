import connection.CreatorSF;
import model.Developer;
import model.Skill;
import model.Specialty;
import model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateRunnerDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = CreatorSF.getFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Skill skillPhp = new Skill();
        skillPhp.setName("php");
        skillPhp.setStatus(Status.ACTIVE);
        session.save(skillPhp);

        Skill skillCSharp = new Skill();
        skillCSharp.setName("c#");
        skillCSharp.setStatus(Status.ACTIVE);
        session.save(skillCSharp);

        Skill skillJava = new Skill();
        skillJava.setName("java");
        skillJava.setStatus(Status.ACTIVE);
        session.save(skillJava);

        Specialty specialtyWeb = new Specialty();
        specialtyWeb.setName("Web");
        specialtyWeb.setStatus(Status.ACTIVE);
        session.save(specialtyWeb);

        Specialty specialtyNet = new Specialty();
        specialtyNet.setName("Net");
        specialtyNet.setStatus(Status.ACTIVE);
        session.save(specialtyNet);

        Developer developer = new Developer();
        developer.setFirstName("Artem");
        developer.setLastName("Polozov");
        developer.setSpecialty(session.get(Specialty.class, 1));
        List<Skill> listSkills = new ArrayList<>();
        listSkills.add(session.get(Skill.class, 1));
        listSkills.add(session.get(Skill.class, 2));
        developer.setSkills(listSkills);
        developer.setStatus(Status.ACTIVE);
        session.save(developer);



        Skill skill = session.get(Skill.class, 2);
        Specialty specialty = session.get(Specialty.class, 1);
        developer = session.get(Developer.class, 1);
        transaction.commit();

        System.out.println(skill);
        System.out.println(specialty);
        System.out.println(developer);

        session.close();
        sessionFactory.close();
    }
}
