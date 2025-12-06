package ma.emsi.appointmentservice.repository;

import ma.emsi.appointmentservice.entities.DocteurItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface DocteurItemRepository extends JpaRepository<DocteurItem, Long> {

    Page<DocteurItem> findAll(Pageable pageable);

    @Query("""
    SELECT DISTINCT d FROM DocteurItem d
    WHERE CAST(d.id AS string) LIKE CONCAT('%', :keyword, '%')
       OR CAST(d.docteurId AS string) LIKE CONCAT('%', :keyword, '%')
       OR LOWER(d.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(d.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR str(d.speciality) LIKE CONCAT('%', :keyword, '%')
""")
    Page<DocteurItem> searchDocteur(@Param("keyword") String keyword, Pageable pageable);

}
