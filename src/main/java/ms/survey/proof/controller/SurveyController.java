package ms.survey.proof.controller;

import ms.survey.proof.DTO.*;
import ms.survey.proof.entities.MusicalStyles;
import ms.survey.proof.security.JWTAuthtenticationConfig;
import ms.survey.proof.service.SurveyService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/survey")
public class SurveyController {



    @Autowired
    SurveyService surveyServices;

    @PostMapping("/add/musical")
    public List<StyleMusicalDTO> addMusical(@RequestParam("musicalType") String musicalType) {
        return surveyServices.addMusical(musicalType);
    }


    @GetMapping("/get/musical")
    public List<StyleMusicalDTO> getMusical() {
        return surveyServices.getMusical();
    }

    @PostMapping("/musical/insert")
    public ResponseInsertSurvey insertSurvey(
            @RequestBody SurveyDataDTO survey) {
        return surveyServices.saveSurvey(survey);

    }

    @GetMapping("/get/charts")
    public List<ChartsDTO> getCharts() {
        return surveyServices.getCharts();
    }
}
