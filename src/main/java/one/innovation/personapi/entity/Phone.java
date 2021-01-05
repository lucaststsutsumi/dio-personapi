package one.innovation.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.innovation.personapi.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data // insert getters and setters
@Builder // apply the "builder" design patter to the class
@AllArgsConstructor // insert constructor with all the class properties
@NoArgsConstructor// insert constructor with none of the class properties
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
