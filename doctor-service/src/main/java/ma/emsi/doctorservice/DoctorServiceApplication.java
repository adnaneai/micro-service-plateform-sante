package ma.emsi.doctorservice;

import ma.emsi.doctorservice.entities.Docteur;
import ma.emsi.doctorservice.enums.Speciality;
import ma.emsi.doctorservice.repository.DocteurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DoctorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(DocteurRepository docteurRepository) {
        return args -> {
            docteurRepository.save(Docteur.builder()
                    .nom("AINOUNI")
                    .prenom("Adnane")
                    .speciality(Speciality.DENTISTRY)
                    .dateCreation(new Date())
                    .email("adnane.ainouni@gmail.com")
                    .build());
            docteurRepository.save(Docteur.builder()
                    .nom("FAROUKI")
                    .prenom("Ayman")
                    .speciality(Speciality.CARDIOLOGY)
                    .dateCreation(new Date())
                    .email("ayman.farouki@gmail.com")
                    .build());
            docteurRepository.save(Docteur.builder()
                    .nom("AIT SAID")
                    .prenom("Fatima")
                    .speciality(Speciality.HEMATOLOGY)
                    .dateCreation(new Date())
                    .email("fatima.ait.said@gmail.com")
                    .build());
        };
    }
}
