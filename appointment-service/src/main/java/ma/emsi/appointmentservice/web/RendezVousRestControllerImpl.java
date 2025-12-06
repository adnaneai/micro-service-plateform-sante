package ma.emsi.appointmentservice.web;

import lombok.AllArgsConstructor;
import ma.emsi.appointmentservice.dto.RendezVousDTO;
import ma.emsi.appointmentservice.exceptions.PatientNotFoundException;
import ma.emsi.appointmentservice.exceptions.RendezVousNotFoundException;
import ma.emsi.appointmentservice.service.RendezVousService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rendezvouss")
@AllArgsConstructor
@CrossOrigin("*")
public class RendezVousRestControllerImpl implements RendezVousRestController {
    private RendezVousService rendezVousService;
    @GetMapping
    @Override
    public ResponseEntity<Page<RendezVousDTO>> findAllRendezVous(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size){
        Page<RendezVousDTO> rendezVousDTOPage = rendezVousService.findAllRendezVous(page, size);
        return ResponseEntity.ok(rendezVousDTOPage);
    }
    @GetMapping("/keyword")
    @Override
    public ResponseEntity<Page<RendezVousDTO>> findAllRendezVousByKeyword(@PathVariable String keyword,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size)
                                                                          throws RendezVousNotFoundException {
        Page<RendezVousDTO> rendezVousDTOPage = rendezVousService.findAllRendezVousByKeyword(keyword, page, size);
        return ResponseEntity.ok(rendezVousDTOPage);
    }

    @PostMapping()
    @Override
    public ResponseEntity<RendezVousDTO> saveRendezVous(@RequestBody RendezVousDTO rendezVousDTO)
                                                        throws PatientNotFoundException {
        RendezVousDTO saved = rendezVousService.saveRendezVous(rendezVousDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable("id") Long id,
                                                          @RequestBody RendezVousDTO rendezVousDTO)
                                                          throws RendezVousNotFoundException {
        RendezVousDTO updated = rendezVousService.updateRendezVous(id, rendezVousDTO);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteRendezVous(@PathVariable("id") Long id)
                                                 throws RendezVousNotFoundException {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}
