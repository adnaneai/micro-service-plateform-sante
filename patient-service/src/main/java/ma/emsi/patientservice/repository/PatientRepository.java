package ma.emsi.patientservice.repository;

import ma.emsi.patientservice.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findAll(Pageable pageable);

    @Query("""
    SELECT p FROM Patient p
    WHERE (CAST(p.id AS string) LIKE concat('%', :keyword, '%'))
       OR lower(p.nom) LIKE lower(concat('%', :keyword, '%'))
       OR lower(p.prenom) LIKE lower(concat('%', :keyword, '%'))
       OR lower(p.email) LIKE lower(concat('%', :keyword, '%'))
""")
    Page<Patient> searchPatients(@Param("keyword") String keyword, Pageable pageable);

}
