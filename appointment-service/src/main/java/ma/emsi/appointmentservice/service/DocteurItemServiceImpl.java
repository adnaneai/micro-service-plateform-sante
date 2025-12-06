package ma.emsi.appointmentservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.appointmentservice.dto.DocteurItemDTO;
import ma.emsi.appointmentservice.entities.DocteurItem;
import ma.emsi.appointmentservice.exceptions.DocteurItemNotFoundException;
import ma.emsi.appointmentservice.exceptions.DocteurNotFoundException;
import ma.emsi.appointmentservice.feign.DocteurServiceRestClient;
import ma.emsi.appointmentservice.mapper.DocteurItemMapper;
import ma.emsi.appointmentservice.model.Docteur;
import ma.emsi.appointmentservice.repository.DocteurItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class DocteurItemServiceImpl implements DocteurItemService {
    private DocteurItemRepository  docteurItemRepository;
    private DocteurItemMapper docteurItemMapper;
    private DocteurServiceRestClient docteurServiceRestClient;
    @Override
    public Page<DocteurItemDTO> findAllDocteurItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DocteurItem> docteurItemPage = docteurItemRepository.findAll(pageable);
        docteurItemPage.stream().map(docteurItem -> {
            Docteur docteur = docteurServiceRestClient.getDocteurById(docteurItem.getDocteurId());
            docteurItem.setNom(docteur.getNom());
            docteurItem.setPrenom(docteur.getPrenom());
            docteurItem.setSpeciality(docteur.getSpeciality());
            docteurItem.setDocteur(docteur);
            return docteur;
        });
        return docteurItemPage.map(docteurItem -> docteurItemMapper.fromDocteurItemToDocteurItemDTO(docteurItem));
    }

    @Override
    public Page<DocteurItemDTO> findDocteurItemByKeyword(String keyword, int page, int size) throws DocteurItemNotFoundException {
        Pageable pageable = PageRequest.of(page, size);
        Page<DocteurItem> docteurItemPage = docteurItemRepository.searchDocteur(keyword, pageable);
        if (docteurItemPage.isEmpty()) {
            throw new DocteurItemNotFoundException("Docteur Item not found with keyword: " + keyword);
        }
        docteurItemPage.stream().map(docteurItem -> {
            Docteur docteur = docteurServiceRestClient.getDocteurById(docteurItem.getDocteurId());
            docteurItem.setNom(docteur.getNom());
            docteurItem.setPrenom(docteur.getPrenom());
            docteurItem.setSpeciality(docteur.getSpeciality());
            docteurItem.setDocteur(docteur);
            return docteur;
        });
        return docteurItemPage.map(docteurItem -> docteurItemMapper.fromDocteurItemToDocteurItemDTO(docteurItem));
    }

    @Override
    public DocteurItemDTO saveDocteurItem(DocteurItemDTO docteurItemDTO) throws DocteurNotFoundException {
        DocteurItem docteurItem = docteurItemMapper.fromDocteurDTOtoDocteurItem(docteurItemDTO);
        Docteur docteur = docteurServiceRestClient.getDocteurById(docteurItem.getDocteurId());
        if (docteur != null) {
            docteurItem.setNom(docteur.getNom());
            docteurItem.setPrenom(docteur.getPrenom());
            docteurItem.setSpeciality(docteur.getSpeciality());
            docteurItem.setDocteur(docteur);
            docteurItemRepository.save(docteurItem);
            return docteurItemMapper.fromDocteurItemToDocteurItemDTO(docteurItem);
        }else
            throw new DocteurNotFoundException("Docteur not found with id: " + docteurItem.getDocteurId());
    }

    @Override
    public DocteurItemDTO updateDocteurItem(Long id, DocteurItemDTO docteurItemDTO) throws DocteurItemNotFoundException {
        DocteurItem docteurItem = docteurItemRepository.findById(id)
                .orElseThrow(()-> new DocteurItemNotFoundException("Docteur Item not found with id: " + id));
        Docteur docteur = docteurServiceRestClient.getDocteurById(docteurItem.getDocteurId());
        if (docteur != null) {
            docteurItem.setNom(docteur.getNom());
            docteurItem.setPrenom(docteur.getPrenom());
            docteurItem.setSpeciality(docteur.getSpeciality());
            docteurItem.setDocteur(docteur);
        }
        DocteurItem updateDocteurItem = docteurItemRepository.save(docteurItem);
        return docteurItemMapper.fromDocteurItemToDocteurItemDTO(updateDocteurItem);
    }

    @Override
    public void deleteDocteurItem(Long id) throws DocteurItemNotFoundException {
        DocteurItem docteurItem = docteurItemRepository.findById(id)
                .orElseThrow(()-> new DocteurItemNotFoundException("Docteur Item not found with id: " + id));
        docteurItemRepository.delete(docteurItem);
    }
}
