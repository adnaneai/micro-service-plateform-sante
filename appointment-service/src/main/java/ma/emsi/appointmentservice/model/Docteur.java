package ma.emsi.appointmentservice.model;

import lombok.Getter;
import lombok.Setter;
import ma.emsi.appointmentservice.enums.Speciality;
@Getter @Setter
public class Docteur {
    private Long id;
    private String nom;
    private String prenom;
    private Speciality speciality;
}
