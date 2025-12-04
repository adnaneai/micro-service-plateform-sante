package ma.emsi.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;
import ma.emsi.doctorservice.enums.Speciality;

@Getter @Setter
public class DocteurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Speciality speciality;
}
