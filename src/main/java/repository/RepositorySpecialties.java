package repository;

import connection.CreatorSF;
import model.Skill;
import model.Specialty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class RepositorySpecialties implements RepositoryCommon<Specialty, Integer> {
    private final SessionFactory factory;


    public RepositorySpecialties() {
        factory = CreatorSF.getFactory();
    }

    @Override
    public Specialty create(Specialty specialty) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(specialty);
            session.getTransaction().commit();
            Query query = session.createQuery("FROM Specialty name = '" + specialty.getName() + "';");
            List<Specialty> list = query.getResultList();
            specialty = list.get(0);
        } catch (Exception e) {
            System.out.println("Error into specialty's creating method()");
        }
        return specialty;
    }

    @Override
    public Specialty read(Integer id) {
        Specialty specialty = null;
        try (final Session session = factory.openSession()) {
            specialty =  session.get(Specialty.class, id);
        } catch (Exception e) {
            System.out.println("Error into specialty's reading method()");
        }
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(specialty);
            session.getTransaction().commit();
            specialty = session.get(Specialty.class, specialty.getId());
        } catch (Exception e) {
            System.out.println("Error into specialty's updating method()");
        }
        return specialty;
    }
}
