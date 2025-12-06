package ma.emsi.appointmentservice.web;

import lombok.AllArgsConstructor;
import ma.emsi.appointmentservice.dto.DocteurItemDTO;
import ma.emsi.appointmentservice.exceptions.DocteurItemNotFoundException;
import ma.emsi.appointmentservice.exceptions.DocteurNotFoundException;
import ma.emsi.appointmentservice.service.DocteurItemService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docteuritems")
@AllArgsConstructor
@CrossOrigin("*")
public class DocteurItemRestControllerImpl implements DocteurItemRestController {
    private DocteurItemService docteurItemService;
    @GetMapping
    @Override
    public ResponseEntity<Page<DocteurItemDTO>> findAllDocteurItem(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Page<DocteurItemDTO> docteurItemDTOPage = docteurItemService.findAllDocteurItems(page, size);
        return ResponseEntity.ok(docteurItemDTOPage);
    }
    @GetMapping("/{keyword}")
    @Override
    public ResponseEntity<Page<DocteurItemDTO>> findDocteurItemByKeyWord(@PathVariable("keyword") String keyword,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size)
                                                                         throws DocteurItemNotFoundException {
        Page<DocteurItemDTO> docteurItemDTOPage =  docteurItemService.findDocteurItemByKeyword(keyword, page, size);
        return ResponseEntity.ok(docteurItemDTOPage);
    }
    @PostMapping
    @Override
    public ResponseEntity<DocteurItemDTO> saveDocteurItem(@RequestBody DocteurItemDTO docteurItemDTO)
                                                          throws DocteurNotFoundException {
        DocteurItemDTO saved =  docteurItemService.saveDocteurItem(docteurItemDTO);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<DocteurItemDTO> updateDocteurItem(@PathVariable("id") Long id,
                                                            @RequestBody DocteurItemDTO docteurItemDTO)
                                                            throws DocteurItemNotFoundException {
        DocteurItemDTO updeted = docteurItemService.updateDocteurItem(id, docteurItemDTO);
        return ResponseEntity.ok(updeted);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteDocteurItem(@PathVariable("id") Long id)
                                                  throws DocteurItemNotFoundException {
        docteurItemService.deleteDocteurItem(id);
        return ResponseEntity.noContent().build();
    }
}
