package ma.emsi.appointmentservice.dto;

import lombok.Getter;
import lombok.Setter;
import ma.emsi.appointmentservice.enums.Speciality;

@Getter @Setter
public class DocteurItemDTO {
    private Long id;
    private Long docteurId;
    private String nom;
    private String prenom;
    private Speciality speciality;
    private RendezVousDTO rendezVousDTO;
}
