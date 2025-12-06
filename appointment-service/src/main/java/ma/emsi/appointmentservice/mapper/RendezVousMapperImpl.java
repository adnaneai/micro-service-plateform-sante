package ma.emsi.appointmentservice.mapper;

import ma.emsi.appointmentservice.dto.RendezVousDTO;
import ma.emsi.appointmentservice.entities.RendezVous;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RendezVousMapperImpl implements RendezVousMapper {
    @Override
    public RendezVous fromRendezVousDTOtoRendezVous(RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = new RendezVous();
        BeanUtils.copyProperties(rendezVousDTO,rendezVous);
        return rendezVous;
    }

    @Override
    public RendezVousDTO fromRendezVousToRendezVousDTO(RendezVous rendezVous) {
        RendezVousDTO rendezVousDTO = new RendezVousDTO();
        BeanUtils.copyProperties(rendezVous, rendezVousDTO);
        return rendezVousDTO;
    }
}
