package ma.emsi.appointmentservice.service;

import ma.emsi.appointmentservice.dto.RendezVousDTO;
import ma.emsi.appointmentservice.exceptions.PatientNotFoundException;
import ma.emsi.appointmentservice.exceptions.RendezVousNotFoundException;
import org.springframework.data.domain.Page;

public interface RendezVousService {
    Page<RendezVousDTO> findAllRendezVous(int page, int size);
    Page<RendezVousDTO> findAllRendezVousByKeyword(String keyword, int page, int size) throws RendezVousNotFoundException;
    RendezVousDTO saveRendezVous(RendezVousDTO rendezVousDTO) throws PatientNotFoundException;
    RendezVousDTO updateRendezVous(Long id,RendezVousDTO rendezVousDTO) throws RendezVousNotFoundException;
    void deleteRendezVous(Long id) throws RendezVousNotFoundException;
}
