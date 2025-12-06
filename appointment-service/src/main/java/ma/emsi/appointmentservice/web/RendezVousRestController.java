package ma.emsi.appointmentservice.web;

import ma.emsi.appointmentservice.dto.RendezVousDTO;
import ma.emsi.appointmentservice.exceptions.PatientNotFoundException;
import ma.emsi.appointmentservice.exceptions.RendezVousNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface RendezVousRestController {
    ResponseEntity<Page<RendezVousDTO>> findAllRendezVous(int page, int size);
    ResponseEntity<Page<RendezVousDTO>> findAllRendezVousByKeyword(String keyword, int page, int size) throws RendezVousNotFoundException;
    ResponseEntity<RendezVousDTO> saveRendezVous(RendezVousDTO rendezVousDTO) throws PatientNotFoundException;
    ResponseEntity<RendezVousDTO> updateRendezVous(Long id,RendezVousDTO rendezVousDTO) throws RendezVousNotFoundException;
    ResponseEntity<Void> deleteRendezVous(Long id) throws RendezVousNotFoundException;
}
