package repository.hibernate;

import connection.HibernateUtil;
import model.Skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.GenericRepository;
import repository.SkillRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SkillRepositoryImp implements SkillRepository {

    private final SessionFactory factory;

    public SkillRepositoryImp() {
        this.factory = HibernateUtil.getFactory();
    }


    @Override
    public Skill save(Skill skill) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error into skill's creating method()");
        }
        return skill;
    }

    @Override
    public Skill read(Long id) {
        Skill skill = null;
        try (final Session session = factory.openSession()) {
            skill = session.get(Skill.class, id);
        } catch (Exception e) {
            System.out.println("Error into skill's reading method()");
        }
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = null;
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Skill> criteria = builder.createQuery(Skill.class);
            criteria.from(Skill.class);
            skills = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println("Error into skill's getAll method()");
        }
        return skills;
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error into skill's updating method()");
        }
        return skill;
    }
}
