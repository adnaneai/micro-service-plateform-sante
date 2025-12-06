package ma.emsi.appointmentservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.appointmentservice.dto.RendezVousDTO;
import ma.emsi.appointmentservice.entities.DocteurItem;
import ma.emsi.appointmentservice.entities.RendezVous;
import ma.emsi.appointmentservice.exceptions.PatientNotFoundException;
import ma.emsi.appointmentservice.exceptions.RendezVousNotFoundException;
import ma.emsi.appointmentservice.feign.PatientServiceRestClient;
import ma.emsi.appointmentservice.mapper.RendezVousMapper;
import ma.emsi.appointmentservice.model.Patient;
import ma.emsi.appointmentservice.repository.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class RendezVousServiceImpl implements RendezVousService {
    private RendezVousRepository rendezVousRepository;
    private RendezVousMapper rendezVousMapper;
    private PatientServiceRestClient patientServiceRestClient;
    @Override
    public Page<RendezVousDTO> findAllRendezVous(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<RendezVous> rendezVousPage = rendezVousRepository.findAll(pageable);
        rendezVousPage.stream().map(rendezVous -> {
            Patient patient =patientServiceRestClient.getPatientById(rendezVous.getPatientId());
            rendezVous.setNom(patient.getNom());
            rendezVous.setPrenom(patient.getPrenom());
            rendezVous.setPatient(patient);
            return rendezVous;
        }).collect(Collectors.toList());
        return rendezVousPage.map(rendezVous -> rendezVousMapper.fromRendezVousToRendezVousDTO(rendezVous));
    }
    @Override
    public Page<RendezVousDTO> findAllRendezVousByKeyword(String keyword, int page, int size) throws RendezVousNotFoundException {
        Pageable pageable = PageRequest.of(page, size);
        Page<RendezVous> rendezVousPage = rendezVousRepository.searchRendezVous(keyword, pageable);
        if (rendezVousPage.isEmpty()) {
            throw new RendezVousNotFoundException("Rendez vous not found with keyword " + keyword);
        }
        rendezVousPage.stream().map(rendezVous -> {
            Patient patient = patientServiceRestClient.getPatientById(rendezVous.getPatientId());
            rendezVous.setNom(patient.getNom());
            rendezVous.setPrenom(patient.getPrenom());
            rendezVous.setPatient(patient);
            return rendezVous;
        }).collect((Collectors.toList()));
        return rendezVousPage.map(rendezVous -> rendezVousMapper.fromRendezVousToRendezVousDTO(rendezVous));
    }
    @Override
    public RendezVousDTO saveRendezVous(RendezVousDTO rendezVousDTO) throws PatientNotFoundException{
        RendezVous rendezVous = rendezVousMapper.fromRendezVousDTOtoRendezVous(rendezVousDTO);
        Patient patient = patientServiceRestClient.getPatientById(rendezVous.getPatientId());
        if (patient != null) {
            rendezVous.setNom(patient.getNom());
            rendezVous.setPrenom(patient.getPrenom());
            rendezVous.setPatient(patient);
            rendezVousRepository.save(rendezVous);
            return rendezVousMapper.fromRendezVousToRendezVousDTO(rendezVous);
        }else
            throw new PatientNotFoundException("Patient not found with id : "+patient.getId());
    }
    @Override
    public RendezVousDTO updateRendezVous(Long id, RendezVousDTO rendezVousDTO) throws RendezVousNotFoundException {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RendezVousNotFoundException("Rendez vous not found with id " + id));
        Patient patient = patientServiceRestClient.getPatientById(rendezVous.getPatientId());
        if (patient != null) {
            rendezVous.setNom(patient.getNom());
            rendezVous.setPrenom(patient.getPrenom());
            rendezVous.setPatient(patient);
        }
        if (rendezVousDTO.getDateRendezVous() != null) {
            rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        }
        if (rendezVousDTO.getDocteurItemDTOS() != null) {
            List<DocteurItem> docteurItems = rendezVousDTO.getDocteurItemDTOS().stream()
                    .map(docteurItemDTO -> {
                        DocteurItem docteurItem = new DocteurItem();
                        docteurItem.setId(docteurItemDTO.getId());
                        docteurItem.setDocteurId(docteurItemDTO.getDocteurId());
                        docteurItem.setNom(docteurItemDTO.getNom());
                        docteurItem.setPrenom(docteurItemDTO.getPrenom());
                        docteurItem.setSpeciality(docteurItemDTO.getSpeciality());
                        return docteurItem;
                    })
                    .toList();
            rendezVous.setDocteurItems(docteurItems);
        }
        RendezVous updatedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.fromRendezVousToRendezVousDTO(updatedRendezVous);
    }
    @Override
    public void deleteRendezVous(Long id) throws RendezVousNotFoundException {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RendezVousNotFoundException("Rendez vous not found with id " + id));
        rendezVousRepository.delete(rendezVous);
    }
}
