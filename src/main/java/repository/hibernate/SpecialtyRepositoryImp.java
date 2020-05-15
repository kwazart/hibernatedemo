package repository.hibernate;

import connection.HibernateUtil;
import model.Specialty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import repository.SpecialtyRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SpecialtyRepositoryImp implements SpecialtyRepository {
    private final SessionFactory factory;


    public SpecialtyRepositoryImp() {
        factory = HibernateUtil.getFactory();
    }

    @Override
    public Specialty save(Specialty specialty) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(specialty);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error into specialty's creating method()");
        }
        return specialty;
    }

    @Override
    public Specialty read(Long id) {
        Specialty specialty = null;
        try (final Session session = factory.openSession()) {
            specialty =  session.get(Specialty.class, id);
        } catch (Exception e) {
            System.out.println("Error into specialty's reading method()");
        }
        return specialty;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = null;
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Specialty> criteria = builder.createQuery(Specialty.class);
            criteria.from(Specialty.class);
            specialties = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println("Error into specialty's getAll method()");
        }
        return specialties;
    }

    @Override
    public Specialty update(Specialty specialty) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(specialty);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error into specialty's updating method()");
        }
        return specialty;
    }
}
