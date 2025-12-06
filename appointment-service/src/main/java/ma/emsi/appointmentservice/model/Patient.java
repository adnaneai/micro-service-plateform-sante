package ma.emsi.appointmentservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
