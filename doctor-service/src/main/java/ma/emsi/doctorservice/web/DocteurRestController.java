package ma.emsi.doctorservice.web;

import ma.emsi.doctorservice.dto.DocteurDTO;
import ma.emsi.doctorservice.exceptions.DocteurNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface DocteurRestController {
    ResponseEntity<Page<DocteurDTO>> getAllDocteurs(int page, int size);
    ResponseEntity<Page<DocteurDTO>> getDocteursByKeyWord(String keyword,int page, int size) throws DocteurNotFoundException;
    ResponseEntity<DocteurDTO> saveDocteur(DocteurDTO docteurDTO);
    ResponseEntity<DocteurDTO> upateDocteur(Long id,DocteurDTO docteurDTO) throws DocteurNotFoundException;
    ResponseEntity<Void> deleteDocteur(Long id) throws DocteurNotFoundException;
}
