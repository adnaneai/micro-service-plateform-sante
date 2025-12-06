package ma.emsi.patientservice.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientservice.dto.PatientDTO;
import ma.emsi.patientservice.exceptions.PatientNotFoundException;
import ma.emsi.patientservice.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
@AllArgsConstructor
public class PatientRestControllerImpl implements PatientRestController {
    private PatientService patientService;
    @GetMapping
    @Override
    public ResponseEntity<Page<PatientDTO>> findAllPatients(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<PatientDTO> patientDTOPage = patientService.findAllPatients(page, size);
        return ResponseEntity.ok(patientDTOPage);
    }

    @GetMapping("/{keyword}")
    @Override
    public ResponseEntity<Page<PatientDTO>> findPatientByKeyWord(@PathVariable("keyword") String keyword,
                                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size) throws PatientNotFoundException {
        Page<PatientDTO> patientDTOPage = patientService.findPatientByKeyWord(keyword, page, size);
        return ResponseEntity.ok(patientDTOPage);
    }
    @GetMapping("/id/{id}")
    @Override
    public ResponseEntity<PatientDTO> findPatientById(@PathVariable("id") Long id) throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.findPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }
    @PostMapping
    @Override
    public ResponseEntity<PatientDTO> savePatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO saved = patientService.savePatient(patientDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable("id") Long id,
                                                    @RequestBody PatientDTO patientDTO) throws PatientNotFoundException {
        PatientDTO updated = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) throws PatientNotFoundException {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
