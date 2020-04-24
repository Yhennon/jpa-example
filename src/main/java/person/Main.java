package person;


import java.time.ZoneId;
import java.util.Locale;

import com.github.javafaker.Faker;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {



    private static Faker faker = new Faker (new Locale("en"));

    private static Person randomPerson() {
        Person person = Person.builder()
                .name(faker.name().nameWithMiddle())
                .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .gender(faker.options().option(Person.Gender.class))
                .address(
                        Address.builder()
                        .country(faker.address().country())
                        .state(faker.address().state())
                        .city(faker.address().city())
                        .streetAddress(faker.address().streetAddress())
                        .zip(faker.address().zipCode())
                                .build()
                        )
                .email(faker.internet().emailAddress())
                .profession(faker.company().profession())
                .build();
        return person;
    }

    public static void main(String[] args) {
        EntityManagerFactory entmanfac = Persistence.createEntityManagerFactory("jpa-example"); // Ennek a beállításai a resources csomagban elhelyezett persistence.xml-ben vannak.
        EntityManager em = entmanfac.createEntityManager();

        int howManyPersons = 1000;

        for (int i = 0; i < howManyPersons; i++) {

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
