package ma.emsi.appointmentservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ma.emsi.appointmentservice.enums.Speciality;
import ma.emsi.appointmentservice.model.Docteur;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DocteurItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long docteurId;
    private String nom;
    private String prenom;
    private Speciality speciality;
    @ManyToOne
    @JoinColumn(name = "rendez_vous_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RendezVous rendezVous;
    @Transient
    private Docteur docteur;
    @Transient
    public String getSpecialityName() {
        return speciality != null ? speciality.name() : "";
    }
}
