package ms.survey.proof.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import ms.survey.proof.DTO.*;
import ms.survey.proof.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/products")
@OpenAPIDefinition(
        tags = {@Tag(
                name = "Encuesta",
                description = "Operaciones relacionadas con las Encuestas"
        )}
)
public class ProductsController {




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
    public ResponseEntity<String> addMusical(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto).getStatusCode());
    }

    @Operation(
            summary = "Obtiene estilos musicales",
            description = "Obtiene una lista de estilos musicales para la encuesta."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/get/product/{}")
    public List<StyleMusicalDTO> getProduct(@RequestBody Map<String, String> requestBody) {
        return productService.getProduct(requestBody);
    }

}
