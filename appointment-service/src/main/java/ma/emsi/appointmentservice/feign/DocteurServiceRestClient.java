package ma.emsi.appointmentservice.feign;

import ma.emsi.appointmentservice.exceptions.DocteurNotFoundException;
import ma.emsi.appointmentservice.model.Docteur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service")
public interface DocteurServiceRestClient {
    @GetMapping("/docteurs/id/{id}")
    Docteur getDocteurById(@PathVariable("id") Long id);
}
