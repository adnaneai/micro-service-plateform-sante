package ma.emsi.patientservice.mapper;

import ma.emsi.patientservice.dto.PatientDTO;
import ma.emsi.patientservice.entities.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientMapperImpl implements PatientMapper {
    @Override
    public Patient fromPatientDTOtoPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        return patient;
    }
    @Override
    public PatientDTO fromPatientToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }
}
