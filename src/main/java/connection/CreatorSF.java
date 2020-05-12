package connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreatorSF {

    private static SessionFactory factory;

    private CreatorSF() {
    }

    public static SessionFactory getFactory() {
        if (factory == null) {
            factory = new Configuration().configure().buildSessionFactory();
        }
        return factory;
    }

}
