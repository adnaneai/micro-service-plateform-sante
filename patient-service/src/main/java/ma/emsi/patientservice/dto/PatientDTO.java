package ma.emsi.patientservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
