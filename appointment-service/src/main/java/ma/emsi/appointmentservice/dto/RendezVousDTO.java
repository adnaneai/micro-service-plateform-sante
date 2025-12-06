package ma.emsi.appointmentservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter @Setter
public class RendezVousDTO {
    private Long id;
    private Date dateRendezVous;
    private Long patientId;
    private String nom;
    private String prenom;
    private List<DocteurItemDTO> docteurItemDTOS;
}
