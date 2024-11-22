package velykoivanStanislav;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String dbUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String dbDialect = dotenv.get("DB_DIALECT");

        Configuration con = new Configuration();
        con.setProperty("hibernate.connection.url", dbUrl);
        con.setProperty("hibernate.connection.username", dbUser);
        con.setProperty("hibernate.connection.password", dbPassword);
        con.setProperty("hibernate.dialect", dbDialect);
        con.setProperty("hibernate.hbm2ddl.auto", "update");
        con.setProperty("hibernate.show_sql", "true");
        con.setProperty("hibernate.format_sql", "true");
        con.addAnnotatedClass(User.class);

        ServiceRegistry reg =  new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties())
                .build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        try {

            User user = new User();

            Transaction tx = session.beginTransaction();

            user = (User) session.get(User.class,2);


            tx.commit();

            System.out.println(user.getName() +" "+ user.getRole());
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sf.close();
        }
    }
}