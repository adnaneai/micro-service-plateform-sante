package ma.emsi.doctorservice.service;

import ma.emsi.doctorservice.dto.DocteurDTO;
import ma.emsi.doctorservice.exceptions.DocteurNotFoundException;
import org.springframework.data.domain.Page;

public interface DocteurService {
    Page<DocteurDTO> findAllDocteurs(int page, int size);
    Page<DocteurDTO> findDocteurByKeyWord(String keyWord,int page, int size) throws DocteurNotFoundException;
    DocteurDTO saveDocteur(DocteurDTO docteurDTO);
    DocteurDTO updateDocteur(Long id, DocteurDTO docteurDTO)throws DocteurNotFoundException;
    void deleteDocteur(Long id) throws DocteurNotFoundException;

}
