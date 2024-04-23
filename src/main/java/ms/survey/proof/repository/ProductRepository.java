package ms.survey.proof.repository;

import ms.survey.proof.entities.Product;
import ms.survey.proof.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select * from products u where u.product_name = :product_name ",nativeQuery = true)
    Product findByProduct(@Param(value = "product_name") String productName);

    @Query(value = "SELECT * FROM products u WHERE u.product_name LIKE %:product_name%", nativeQuery = true)
    List<Product> findByProductNameLike(@Param("product_name") String productName);

}
