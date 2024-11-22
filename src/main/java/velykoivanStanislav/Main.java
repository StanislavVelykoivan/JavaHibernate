package velykoivanStanislav;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


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
        con.setProperty("hbm2ddl.auto", "update");
        con.addAnnotatedClass(User.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        try {

            User user = new User();
            user.setId(2);
            user.setName("Oleg");
            user.setRole("admin");

            Transaction tx = session.beginTransaction();

            session.save(user);

            tx.commit();

            System.out.println("User saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sf.close();
        }
    }
}