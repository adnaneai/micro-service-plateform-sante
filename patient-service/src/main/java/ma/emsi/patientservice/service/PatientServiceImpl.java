package ma.emsi.patientservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.patientservice.dto.PatientDTO;
import ma.emsi.patientservice.entities.Patient;
import ma.emsi.patientservice.exceptions.PatientNotFoundException;
import ma.emsi.patientservice.mapper.PatientMapper;
import ma.emsi.patientservice.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @Override
    public Page<PatientDTO> findAllPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patientPage = patientRepository.findAll(pageable);
        return patientPage.map(patient -> patientMapper.fromPatientToPatientDTO(patient));
    }

    @Override
    public Page<PatientDTO> findPatientByKeyWord(String keyword,int page,int size) throws PatientNotFoundException {
        Page<Patient> patientPage = patientRepository.searchPatients(keyword,PageRequest.of(page, size));
        if (patientPage.isEmpty()){
            throw new PatientNotFoundException("Patient not found with keyword " +keyword);
        }
        Page<PatientDTO> patientDTOPage = patientPage.map(patient -> patientMapper.fromPatientToPatientDTO(patient));
        return patientDTOPage;
    }

    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.fromPatientDTOtoPatient(patientDTO);
        patient.setDateCreation(new Date());
        patientRepository.save(patient);
        return patientMapper.fromPatientToPatientDTO(patient);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id));
        if (patientDTO.getNom() != null) {
            patient.setNom(patientDTO.getNom());
        }
        if (patientDTO.getPrenom() != null) {
            patient.setPrenom(patientDTO.getPrenom());
        }
        if (patientDTO.getEmail() != null) {
            patient.setEmail(patientDTO.getEmail());
        }
        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.fromPatientToPatientDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id));
        patientRepository.delete(patient);
    }
}
