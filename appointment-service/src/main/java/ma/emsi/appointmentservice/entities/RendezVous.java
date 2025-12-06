package ma.emsi.appointmentservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.emsi.appointmentservice.model.Patient;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateRendezVous;
    private Long patientId;
    private String nom;
    private String prenom;
    @OneToMany(mappedBy = "rendezVous")
    private List<DocteurItem> docteurItems;
    @Transient
    private Patient patient;
}
