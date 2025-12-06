package ma.emsi.appointmentservice.mapper;

import ma.emsi.appointmentservice.dto.DocteurItemDTO;
import ma.emsi.appointmentservice.entities.DocteurItem;

public interface DocteurItemMapper {
    DocteurItem fromDocteurDTOtoDocteurItem(DocteurItemDTO docteurItemDTO);
    DocteurItemDTO fromDocteurItemToDocteurItemDTO(DocteurItem docteurItem);
}
