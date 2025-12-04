package ma.emsi.doctorservice.mapper;

import ma.emsi.doctorservice.dto.DocteurDTO;
import ma.emsi.doctorservice.entities.Docteur;

public interface DocteurMapper {
    Docteur fromDocteurDTOtoDocteur(DocteurDTO docteurDTO);
    DocteurDTO  fromDocteurtoDocteurDTO(Docteur docteur);
}
