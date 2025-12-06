package ma.emsi.doctorservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.doctorservice.dto.DocteurDTO;
import ma.emsi.doctorservice.entities.Docteur;
import ma.emsi.doctorservice.exceptions.DocteurNotFoundException;
import ma.emsi.doctorservice.mapper.DocteurMapper;
import ma.emsi.doctorservice.repository.DocteurRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Transactional
public class DocteurServiceImpl implements DocteurService {
    private DocteurRepository docteurRepository;
    private DocteurMapper docteurMapper;
    @Override
    public Page<DocteurDTO> findAllDocteurs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Docteur> docteurs = docteurRepository.findAll(pageable);
        return docteurs.map(docteur -> docteurMapper.fromDocteurtoDocteurDTO(docteur));
    }

    @Override
    public Page<DocteurDTO> findDocteurByKeyWord(String keyWord, int page, int size) throws DocteurNotFoundException {
        Page<Docteur> docteurPage = docteurRepository.searchDocteurs(keyWord, PageRequest.of(page, size));
        if (docteurPage.isEmpty()){
            throw new DocteurNotFoundException("Docteur not found with keyword: " + keyWord);
        }
        return docteurPage.map(docteur -> docteurMapper.fromDocteurtoDocteurDTO(docteur));
    }
    @Override
    public DocteurDTO findDocteurById(Long id) throws DocteurNotFoundException {
        Docteur docteur = docteurRepository.findById(id)
                .orElseThrow(() -> new DocteurNotFoundException("Docteur not found with id: " + id));
        return docteurMapper.fromDocteurtoDocteurDTO(docteur);
    }
    @Override
    public DocteurDTO saveDocteur(DocteurDTO docteurDTO) {
        Docteur docteur = docteurMapper.fromDocteurDTOtoDocteur(docteurDTO);
        docteurRepository.save(docteur);
        docteur.setDateCreation(new Date());
        return docteurMapper.fromDocteurtoDocteurDTO(docteur);
    }

    @Override
    public DocteurDTO updateDocteur(Long id, DocteurDTO docteurDTO) throws DocteurNotFoundException {
        Docteur docteur = docteurRepository.findById(id)
                .orElseThrow(() -> new DocteurNotFoundException("Docteur not found with id: " + id));
        if (docteurDTO.getNom() != null){
            docteur.setNom(docteurDTO.getNom());
        }
        if (docteurDTO.getPrenom() != null){
            docteur.setPrenom(docteurDTO.getPrenom());
        }
        if (docteurDTO.getEmail() != null){
            docteur.setEmail(docteurDTO.getEmail());
        }
        if (docteurDTO.getSpeciality() != null){
            docteur.setSpeciality(docteurDTO.getSpeciality());
        }
        Docteur updateDocteur = docteurRepository.save(docteur);
        return docteurMapper.fromDocteurtoDocteurDTO(updateDocteur);
    }

    @Override
    public void deleteDocteur(Long id) throws DocteurNotFoundException {
        Docteur docteur = docteurRepository.findById(id)
                .orElseThrow(() -> new DocteurNotFoundException("Docteur not found with id: " + id));
        docteurRepository.delete(docteur);
    }
}
