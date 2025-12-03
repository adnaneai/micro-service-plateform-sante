package ma.emsi.patientservice.mapper;

import ma.emsi.patientservice.dto.PatientDTO;
import ma.emsi.patientservice.entities.Patient;

public interface PatientMapper {
    Patient fromPatientDTOtoPatient(PatientDTO patientDTO);
    PatientDTO fromPatientToPatientDTO(Patient patient);
}
