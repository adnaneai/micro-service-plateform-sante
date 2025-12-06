package ma.emsi.appointmentservice.repository;

import ma.emsi.appointmentservice.entities.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    Page<RendezVous> findAll(Pageable pageable);

    @Query("""
        SELECT r FROM RendezVous r
        WHERE CAST(r.id AS string) LIKE CONCAT('%', :keyword, '%')
        OR CAST(r.patientId AS string) LIKE CONCAT('%', :keyword, '%')
        OR LOWER(r.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(r.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    Page<RendezVous> searchRendezVous(@Param("keyword") String keyword, Pageable pageable);
}
