package ms.survey.proof.service.impl;

import ms.survey.proof.DTO.ProductDto;
import ms.survey.proof.DTO.StyleMusicalDTO;
import ms.survey.proof.repository.ProductRepository;
import ms.survey.proof.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    @Override
    public ResponseEntity<String> addProduct(ProductDto productDto) {

        if(productDto!=null){
            if(productRepository.findByProduct(productDto.getProductName())!=null){
                productRepository.save(productDto);
                return new ResponseEntity<>("Producto viene vacio",HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Producto viene vacio",HttpStatus.BAD_GATEWAY);
            }

        }
        return new ResponseEntity<>("Producto viene vacio",HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<StyleMusicalDTO> getProduct(Map<String, String> requestBody) {
        return List.of();
    }
}
