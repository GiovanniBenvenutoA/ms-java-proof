package ms.survey.proof.service;

import ms.survey.proof.DTO.ProductDto;
import ms.survey.proof.DTO.ResponseProduct;
import ms.survey.proof.entities.Product;
import ms.survey.proof.repository.ProductRepository;
import ms.survey.proof.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    public ProductServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Test Product");

        Product product = new Product();
        product.setProductName(productDto.getProductName());

        when(productRepository.findByProduct(productDto.getProductName())).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ResponseEntity<ResponseProduct> response = productService.addProduct(productDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Producto creado", response.getBody().getMessage());
        assertEquals("0", response.getBody().getCode());

        verify(productRepository, times(1)).findByProduct(productDto.getProductName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testAddProductNoOk() {
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Test Product");

        Product product = new Product();
        product.setProductName(productDto.getProductName());

        when(productRepository.findByProduct(productDto.getProductName())).thenReturn(product);
        ResponseEntity<ResponseProduct> response = productService.addProduct(productDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Producto existe", response.getBody().getMessage());
        assertEquals("1", response.getBody().getCode());

        verify(productRepository, times(1)).findByProduct(productDto.getProductName());

    }

    @Test
    void testGetProduct() {
        String productName = "Test Product";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("productName", productName);

        Product product = new Product();
        product.setProductName(productName);

        when(productRepository.findByProductNameLike(productName)).thenReturn(Collections.singletonList(product));

        ResponseEntity<ResponseProduct> response = productService.getProduct(requestBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Se encontraron registros de productos", response.getBody().getMessage());
        assertEquals("0", response.getBody().getCode());
        assertEquals(1, response.getBody().getProduct().size());

        verify(productRepository, times(1)).findByProductNameLike(productName);
    }

    @Test
    void testGetProductNoOk() {
        String productName = "";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("productName", productName);

        Product product = new Product();
        product.setProductName(productName);

        when(productRepository.findByProductNameLike(productName)).thenReturn(Collections.singletonList(null));

        ResponseEntity<ResponseProduct> response = productService.getProduct(requestBody);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El parámetro 'nombreProducto' es requerido", response.getBody().getMessage());
        assertEquals("-1", response.getBody().getCode());

        verify(productRepository, times(0)).findByProductNameLike(productName);
    }

}
