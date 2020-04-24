package person;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable //beágyazható lesz egy @Embedded osztályba,ami a Person osztály
public class Address {

    @Column(nullable = false)
    public String country;

    @Column(nullable = false)
    public String state;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String streetAddress;

    @Column(nullable = false)
    public String zip;

}
