package ms.survey.proof.controller;

import ms.survey.proof.DTO.ProductDto;
import ms.survey.proof.DTO.ResponseProduct;
import ms.survey.proof.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductsController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductOK() {
        // Datos de prueba
        ProductDto productDto = new ProductDto();
        productDto.setProductName("iphone 15");
        productDto.setProductPrice("1.000.000");
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setProduct(null);
        responseProduct.setCode("0");
        responseProduct.setMessage("producto agregado");

        // Simulamos el comportamiento del servicio
        when(productService.addProduct(productDto)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(responseProduct));

        // Ejecutamos el método del controlador
        ResponseEntity<ResponseProduct> responseEntity = productController.addProduct(productDto);

        // Verificamos que se haya llamado al método del servicio con los parámetros correctos
        verify(productService).addProduct(productDto);

        // Verificamos que la respuesta sea la esperada
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseProduct, responseEntity.getBody());
    }

    @Test
    void testAddProductNoOK() {
        // Datos de prueba
        ProductDto productDto = new ProductDto();
        productDto.setProductName("iphone 15");
        productDto.setProductPrice("1.000.000");
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setProduct(null);
        responseProduct.setCode("0");
        responseProduct.setMessage("producto ya existe");

        // Simulamos el comportamiento del servicio
        when(productService.addProduct(productDto)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseProduct));

        // Ejecutamos el método del controlador
        ResponseEntity<ResponseProduct> responseEntity = productController.addProduct(productDto);

        // Verificamos que se haya llamado al método del servicio con los parámetros correctos
        verify(productService).addProduct(productDto);

        // Verificamos que la respuesta sea la esperada
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(responseProduct, responseEntity.getBody());
    }
}
