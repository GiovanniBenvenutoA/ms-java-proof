package ms.survey.proof.repository;


import ms.survey.proof.entities.MusicalStyles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalRepository extends JpaRepository<MusicalStyles,Long>
{
}
