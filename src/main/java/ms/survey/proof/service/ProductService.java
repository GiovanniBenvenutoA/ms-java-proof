package ms.survey.proof.service;

import ms.survey.proof.DTO.ProductDto;
import ms.survey.proof.DTO.ResponseProduct;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProductService  {
    ResponseEntity<ResponseProduct> addProduct(ProductDto productDto);

    ResponseEntity<ResponseProduct> getProduct(Map<String, String> requestBody);
}
