package ms.survey.proof.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ms.survey.proof.DTO.TokenDTO;
import ms.survey.proof.security.JWTAuthtenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@OpenAPIDefinition(
        tags = {@Tag(
                name = "Token",
                description = "Operaciones relacionadas con las Token"
        )}
)
public class TokenController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Operation(
            summary = "Obtiene Token",
            description = "Entrega token para conectarse a los demas servicios."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @PostMapping("/get/token")
    public TokenDTO login(
            @RequestParam("userToken") String username) {
        return jwtAuthtenticationConfig.getJWTToken(username);

    }
}
