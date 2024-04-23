package ms.survey.proof.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import ms.survey.proof.DTO.*;
import ms.survey.proof.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/products")
@OpenAPIDefinition(
        tags = {@Tag(
                name = "Productos",
                description = "Operaciones relacionadas con los Productos"
        )}
)
public class ProductsController {



    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Agrega productos nuevos",
            description = "Inserta nuevos productos."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add/product")
    public ResponseEntity<ResponseProduct> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @Operation(
            summary = "Obtiene productos por medio de un filtro",
            description = "Obtiene productos por medio de un filtro."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/get/product")
    public ResponseEntity<ResponseProduct> getProduct(@RequestBody Map<String, String> requestBody) {
        return productService.getProduct(requestBody);
    }

}
