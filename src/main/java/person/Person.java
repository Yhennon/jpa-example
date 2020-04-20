package person;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import

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
    @GeneratedValue // Hibernate által generalt elsődleges kulcs
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private Gender gender;

    @Embedded // @Embedded is used to embed a type into another entity. | a másik entityhez kelleni fog egy @Embeddable annotáció,jelezvén, hogy majd más entitásokból beágyazásra kerül valami
    private Address address;

    private String email;

    private String profession;


    private createPerson() {
        Person person = new Person.builder()
                .name()
                .dob()
                .gender()
                .addres()
                .email()
                .profession()
                .build();
        return person;
    }




}