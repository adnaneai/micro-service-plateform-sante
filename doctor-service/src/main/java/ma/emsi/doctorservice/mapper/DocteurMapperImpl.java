package ma.emsi.doctorservice.mapper;

import ma.emsi.doctorservice.dto.DocteurDTO;
import ma.emsi.doctorservice.entities.Docteur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DocteurMapperImpl implements DocteurMapper {
    @Override
    public Docteur fromDocteurDTOtoDocteur(DocteurDTO docteurDTO) {
        Docteur docteur = new Docteur();
        BeanUtils.copyProperties(docteurDTO, docteur);
        return docteur;
    }

    @Override
    public DocteurDTO fromDocteurtoDocteurDTO(Docteur docteur) {
        DocteurDTO docteurDTO = new DocteurDTO();
        BeanUtils.copyProperties(docteur, docteurDTO);
        return docteurDTO;
    }
}
