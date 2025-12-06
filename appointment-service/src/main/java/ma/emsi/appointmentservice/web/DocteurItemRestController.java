package ma.emsi.appointmentservice.web;

import ma.emsi.appointmentservice.dto.DocteurItemDTO;
import ma.emsi.appointmentservice.exceptions.DocteurItemNotFoundException;
import ma.emsi.appointmentservice.exceptions.DocteurNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface DocteurItemRestController {
    ResponseEntity<Page<DocteurItemDTO>> findAllDocteurItem(int  page, int size);
    ResponseEntity<Page<DocteurItemDTO>> findDocteurItemByKeyWord(String keyword,int  page, int size) throws DocteurItemNotFoundException;
    ResponseEntity<DocteurItemDTO> saveDocteurItem(DocteurItemDTO docteurItemDTO) throws DocteurNotFoundException;
    ResponseEntity<DocteurItemDTO> updateDocteurItem(Long id, DocteurItemDTO docteurItemDTO) throws DocteurItemNotFoundException;
    ResponseEntity<Void> deleteDocteurItem(Long id) throws DocteurItemNotFoundException;
}
