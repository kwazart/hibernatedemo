package repository.hibernate;

import connection.HibernateUtil;
import model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.DeveloperRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class DeveloperRepositoryImp implements DeveloperRepository {
    private final SessionFactory factory;

    public DeveloperRepositoryImp() {
        factory = HibernateUtil.getFactory();
    }

    @Override
    public Developer save(Developer developer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error into developer's creating method()");
        }
        return developer;
    }

    @Override
    public Developer read(Long id) {
        Developer developer = null;
        try (Session session = factory.openSession()) {
            developer = session.get(Developer.class, id);
        } catch (Exception e) {
            System.out.println("Error into developer's reading method()");
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = null;
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Developer> criteria = builder.createQuery(Developer.class);
            criteria.from(Developer.class);
            developers = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println("Error into developer's getAll method()");
        }
        return developers;
    }

    @Override
    public Developer update(Developer developer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error into developer's updating method()");
        }
        return developer;
    }
}
