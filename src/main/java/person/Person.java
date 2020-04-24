package person;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Person {

    public static enum  Gender {
        FEMALE, MALE;
    }

    @Id
    @GeneratedValue // Hibernate által generalt elsődleges kulcs,nem kell megadni
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private Gender gender;

    @Embedded // @Embedded is used to embed a type into another entity. | a másik entityhez kelleni fog egy @Embeddable annotáció,jelezvén, hogy majd más entitásokból beágyazásra kerül valami
    private Address address; //Így majd ezt,a builder patternt és a Faker-t használva adhatjuk meg az Adress változóinak értékét

    private String email;

    private String profession;


}