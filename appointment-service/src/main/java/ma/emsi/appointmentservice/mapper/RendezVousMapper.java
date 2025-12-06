package ma.emsi.appointmentservice.mapper;

import ma.emsi.appointmentservice.dto.RendezVousDTO;
import ma.emsi.appointmentservice.entities.RendezVous;
import org.springframework.stereotype.Service;

public interface RendezVousMapper {
    RendezVous fromRendezVousDTOtoRendezVous(RendezVousDTO rendezVousDTO);
    RendezVousDTO fromRendezVousToRendezVousDTO(RendezVous rendezVous);
}
