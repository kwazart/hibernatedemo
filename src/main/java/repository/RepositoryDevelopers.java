package repository;

import connection.CreatorSF;
import model.Developer;
import model.Specialty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class RepositoryDevelopers implements RepositoryCommon<Developer, Integer> {
    private final SessionFactory factory;

    public RepositoryDevelopers() {
        factory = CreatorSF.getFactory();
    }

    @Override
    public Developer create(Developer developer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();
            Query query = session.createQuery("FROM Developer firstName = '"
                    + developer.getFirstName()
                    + "' AND lastName = '"
                    + developer.getLastName() + "';");
            List<Developer> list = query.getResultList();
            developer = list.get(0);
        } catch (Exception e) {
            System.out.println("Error into developer's creating method()");
        }
        return developer;
    }

    @Override
    public Developer read(Integer id) {
        Developer developer = null;
        try (Session session = factory.openSession()) {
            developer =  session.get(Developer.class, id);
        } catch (Exception e) {
            System.out.println("Error into developer's reading method()");
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
            developer = session.get(Developer.class, developer.getId());
        } catch (Exception e) {
            System.out.println("Error into developer's updating method()");
        }
        return developer;
    }
}
