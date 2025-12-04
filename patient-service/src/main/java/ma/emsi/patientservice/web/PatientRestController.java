package ma.emsi.patientservice.web;

import ma.emsi.patientservice.dto.PatientDTO;
import ma.emsi.patientservice.exceptions.PatientNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface PatientRestController {
    ResponseEntity<Page<PatientDTO>> findAllPatients(int page, int size);
    ResponseEntity<Page<PatientDTO>> findPatientByKeyWord(String keyword, int page, int size) throws PatientNotFoundException;
    ResponseEntity<PatientDTO> savePatient(PatientDTO patientDTO);
    ResponseEntity<PatientDTO> updatePatient(Long id, PatientDTO patientDTO) throws PatientNotFoundException;
    ResponseEntity<Void> deletePatient(Long id) throws PatientNotFoundException;


}
