package ms.survey.proof.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import ms.survey.proof.DTO.*;
import ms.survey.proof.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/survey")
@OpenAPIDefinition(
        tags = {@Tag(
                name = "Encuesta",
                description = "Operaciones relacionadas con las Encuestas"
        )}
)
public class SurveyController {



    @Autowired
    private SurveyService surveyServices;

    @Operation(
            summary = "Agrega estilos musicales",
            description = "Inserta nuevos estilos musicales."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add/musical")
    public List<StyleMusicalDTO> addMusical(@RequestParam("musicalType") String musicalType) {
        return surveyServices.addMusical(musicalType);
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
    @GetMapping("/get/musical")
    public List<StyleMusicalDTO> getMusical() {
        return surveyServices.getMusical();
    }


    @Operation(
            summary = "Inserta encuestas",
            description = "Inserta las encuestas enviadas por los usuarios."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/musical/insert")
    public ResponseInsertSurvey insertSurvey(
            @RequestBody SurveyDataDTO survey) {
        return surveyServices.saveSurvey(survey);

    }

    @Operation(
            summary = "Obtiene resultados encuesta",
            description = "Obtiene una lista con los resultados de la encuesta."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = {@Content(
                    mediaType = "application/json"
            )}
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/get/charts")
    public List<ChartsDTO> getCharts() {
        return surveyServices.getCharts();
    }
}
