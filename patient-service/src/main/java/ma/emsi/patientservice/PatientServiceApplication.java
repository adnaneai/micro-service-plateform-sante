package ma.emsi.patientservice;

import ma.emsi.patientservice.entities.Patient;
import ma.emsi.patientservice.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(Patient.builder()
                    .nom("AINOUNI")
                    .prenom("Adnane")
                    .dateCreation(new Date())
                    .email("adnane.ainouni@gmail.com").build());
            patientRepository.save(Patient.builder()
                    .nom("FAROUKI")
                    .prenom("Ayman")
                    .dateCreation(new Date())
                    .email("ayman.farouki@gmail.com").build());
            patientRepository.save(Patient.builder()
                    .nom("AIT SAID")
                    .prenom("Fatima")
                    .dateCreation(new Date())
                    .email("fatima.aitsaid@gmail.com").build());
        };
    }
}
