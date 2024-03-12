package ms.survey.proof.repository;


import ms.survey.proof.DTO.ChartsDTO;
import ms.survey.proof.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {

    @Query(value = "select * from survey u where u.email_survey = :email ",nativeQuery = true)
    Survey findByEmail(@Param(value = "email") String email);

    @Query(value = "SELECT M.MUSICAL_TYPE, COUNT(S.ID_SURVEY) AS CANTIDAD_DE_REGISTROS\n" +
            "FROM SURVEY S\n" +
            "JOIN MUSICAL_STYLES M ON S.MUSICAL_TYPE_ID = M.ID_MUSICAL\n" +
            "GROUP BY M.MUSICAL_TYPE",nativeQuery = true)
    List<Object[]> chartsData();
}
