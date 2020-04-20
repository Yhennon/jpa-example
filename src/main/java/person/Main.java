package person;

import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;

import guice.PersistenceModule;
import legoset.model.LegoSet;
import user.model.User;
import user.UserDao;
import usertodo.model.UserTodo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory entmanfac = Persistence.createEntityManagerFactory("jpa-example");

    private static void createPerson() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new LegoSet("60073", "Service Truck", Year.of(2015), 233));
            em.persist(new LegoSet("75211", "Imperial TIE Fighter", Year.of(2018), 519));
            em.persist(new LegoSet("21034", "London", Year.of(2017), 468));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        UserDao userDao = injector.getInstance(UserDao.class);
        for (int i = 0; i < 10; i++) {
            User user = createUser();
            userDao.persist(user);
        }
        userDao.findAll()
                .stream()
                .forEach(System.out::println);
    }

    private static Faker faker = new Faker (new Locale("hu"));
}
