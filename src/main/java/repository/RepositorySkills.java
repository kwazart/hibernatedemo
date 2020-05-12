package repository;

import connection.CreatorSF;
import model.Skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import java.util.List;

public class RepositorySkills implements RepositoryCommon<Skill, Integer> {

    private final SessionFactory factory;

    public RepositorySkills() {
        this.factory = CreatorSF.getFactory();
    }


    @Override
    public Skill create(Skill skill) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();
            Query query = session.createQuery("FROM Skill name = '" + skill.getName() + "';");
            List<Skill> list = query.getResultList();
            skill = list.get(0);
        } catch (Exception e) {
            System.out.println("Error into skill's creating method()");
        }
        return skill;
    }

    @Override
    public Skill read(Integer id) {
        Skill skill = null;
        try (final Session session = factory.openSession()) {
            skill =  session.get(Skill.class, id);
        } catch (Exception e) {
            System.out.println("Error into skill's reading method()");
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
            skill = session.get(Skill.class, skill.getId());
        } catch (Exception e) {
            System.out.println("Error into skill's updating method()");
        }
        return skill;
    }
}
