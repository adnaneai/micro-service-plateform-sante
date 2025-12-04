package ma.emsi.doctorservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.emsi.doctorservice.enums.Speciality;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Docteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    private Date dateCreation;
}
