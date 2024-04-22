package ms.survey.proof.repository;

import ms.survey.proof.entities.Product;
import ms.survey.proof.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select * from survey u where u.email_survey = :email ",nativeQuery = true)
    Survey findByProduct(@Param(value = "email") String email);

}
