package ms.survey.proof.service;

import ms.survey.proof.DTO.ProductDto;
import ms.survey.proof.DTO.ResponseProduct;
import ms.survey.proof.DTO.StyleMusicalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService  {
    ResponseEntity<ResponseProduct> addProduct(ProductDto productDto);

    ResponseEntity<ResponseProduct> getProduct(Map<String, String> requestBody);
}
