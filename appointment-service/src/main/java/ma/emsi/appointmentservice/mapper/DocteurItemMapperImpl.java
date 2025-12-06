package ma.emsi.appointmentservice.mapper;

import ma.emsi.appointmentservice.dto.DocteurItemDTO;
import ma.emsi.appointmentservice.entities.DocteurItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DocteurItemMapperImpl implements DocteurItemMapper {
    @Override
    public DocteurItem fromDocteurDTOtoDocteurItem(DocteurItemDTO docteurItemDTO) {
        DocteurItem docteurItem = new DocteurItem();
        BeanUtils.copyProperties(docteurItemDTO, docteurItem);
        return docteurItem;
    }

    @Override
    public DocteurItemDTO fromDocteurItemToDocteurItemDTO(DocteurItem docteurItem) {
        DocteurItemDTO docteurItemDTO = new DocteurItemDTO();
        BeanUtils.copyProperties(docteurItem, docteurItemDTO);
        return docteurItemDTO;
    }
}
