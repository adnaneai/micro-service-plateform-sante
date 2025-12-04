package ma.emsi.doctorservice.repository;

import ma.emsi.doctorservice.entities.Docteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocteurRepository extends JpaRepository<Docteur,Long> {
    Page<Docteur> findAll(Pageable pageable);

    @Query("""
    SELECT d FROM Docteur d
    WHERE (CAST(d.id AS string) LIKE concat('%', :keyword, '%'))
       OR lower(d.nom) LIKE lower(concat('%', :keyword, '%'))
       OR lower(d.prenom) LIKE lower(concat('%', :keyword, '%'))
       OR lower(d.email) LIKE lower(concat('%', :keyword, '%'))
       OR LOWER(CAST(d.speciality AS string)) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    Page<Docteur> searchDocteurs(@Param("keyword") String keyword, Pageable pageable);

}
