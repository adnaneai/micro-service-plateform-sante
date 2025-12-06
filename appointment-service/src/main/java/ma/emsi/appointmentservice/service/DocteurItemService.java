package ma.emsi.appointmentservice.service;

import ma.emsi.appointmentservice.dto.DocteurItemDTO;
import ma.emsi.appointmentservice.exceptions.DocteurItemNotFoundException;
import ma.emsi.appointmentservice.exceptions.DocteurNotFoundException;
import org.springframework.data.domain.Page;

public interface DocteurItemService {
    Page<DocteurItemDTO> findAllDocteurItems(int page, int size);
    Page<DocteurItemDTO> findDocteurItemByKeyword(String keyword, int page, int size) throws DocteurItemNotFoundException;
    DocteurItemDTO saveDocteurItem(DocteurItemDTO docteurItemDTO) throws DocteurNotFoundException;
    DocteurItemDTO updateDocteurItem(Long id, DocteurItemDTO docteurItemDTO) throws DocteurItemNotFoundException;
    void deleteDocteurItem(Long id) throws DocteurItemNotFoundException;
}
