package ma.emsi.appointmentservice.feign;

import ma.emsi.appointmentservice.exceptions.PatientNotFoundException;
import ma.emsi.appointmentservice.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
public interface PatientServiceRestClient {
    @GetMapping("/patients/id/{id}")
    Patient getPatientById(@PathVariable("id") Long id);
}
