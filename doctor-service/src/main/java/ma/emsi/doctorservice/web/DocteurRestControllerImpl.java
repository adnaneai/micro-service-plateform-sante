package ma.emsi.doctorservice.web;

import lombok.AllArgsConstructor;
import ma.emsi.doctorservice.dto.DocteurDTO;
import ma.emsi.doctorservice.exceptions.DocteurNotFoundException;
import ma.emsi.doctorservice.service.DocteurService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docteurs")
@AllArgsConstructor
@CrossOrigin("*")
public class DocteurRestControllerImpl implements DocteurRestController {
    private DocteurService docteurService;
    @GetMapping
    @Override
    public ResponseEntity<Page<DocteurDTO>> getAllDocteurs(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<DocteurDTO> docteurDTOPage = docteurService.findAllDocteurs(page, size);
        return ResponseEntity.ok(docteurDTOPage);
    }
    @GetMapping("/{keyword}")
    @Override
    public ResponseEntity<Page<DocteurDTO>> getDocteursByKeyWord(@PathVariable("keyword") String keyword,
                                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size) throws DocteurNotFoundException {
        Page<DocteurDTO> docteurDTOPage = docteurService.findDocteurByKeyWord(keyword, page, size);
        return ResponseEntity.ok(docteurDTOPage);
    }
    @GetMapping("/id/{id}")
    @Override
    public ResponseEntity<DocteurDTO> getDocteurById(@PathVariable("id") Long id) throws DocteurNotFoundException {
        DocteurDTO docteurDTO = docteurService.findDocteurById(id);
        return ResponseEntity.ok(docteurDTO);
    }
    @PostMapping
    @Override
    public ResponseEntity<DocteurDTO> saveDocteur(@RequestBody DocteurDTO docteurDTO) {
        DocteurDTO savedDocteurDTO = docteurService.saveDocteur(docteurDTO);
        return ResponseEntity.ok(savedDocteurDTO);
    }
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<DocteurDTO> upateDocteur(@PathVariable("id") Long id,
                                                   @RequestBody DocteurDTO docteurDTO) throws DocteurNotFoundException {
        DocteurDTO updateDocteurDTO = docteurService.updateDocteur(id, docteurDTO);
        return ResponseEntity.ok(updateDocteurDTO);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteDocteur(@PathVariable("id") Long id) throws DocteurNotFoundException {
        docteurService.deleteDocteur(id);
        return ResponseEntity.noContent().build();
    }
}
