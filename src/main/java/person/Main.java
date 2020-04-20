package person;

import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;
import java.util.Date;
import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;

import guice.PersistenceModule;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {



    private static Faker faker = new Faker (new Locale("en"));

    private static Person randomPerson() {
        Person person = new Person.builder()
                .name(faker.name().username())
                .dob(faker.date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .gender(faker.options().option(Person.Gender))
                .addres(faker.address())
                .email(faker.internet().emailAddress())
                .profession(faker.company().profession())
                .build();
        return person;
    }

    public static void main(String[] args) {
        //Injector injector = Guice.createInjector(new PersistenceModule("test"));
        EntityManagerFactory entmanfac = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entmanfac.createEntityManager();

        for (int i = 0; i < 10; i++) {

            Person person = randomPerson();
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            System.out.println(person);
        }

        em.close();
        entmanfac.close();
    }


}
