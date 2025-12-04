package ma.emsi.patientservice.service;

import ma.emsi.patientservice.dto.PatientDTO;
import ma.emsi.patientservice.exceptions.PatientNotFoundException;
import org.springframework.data.domain.Page;

public interface PatientService {
    Page<PatientDTO> findAllPatients(int page, int size);
    Page<PatientDTO> findPatientByKeyWord(String keyword, int page, int size)throws PatientNotFoundException;
    PatientDTO savePatient(PatientDTO patientDTO);
    PatientDTO updatePatient(Long id,PatientDTO patientDTO) throws PatientNotFoundException;
    void deletePatient(Long id) throws PatientNotFoundException;
}
