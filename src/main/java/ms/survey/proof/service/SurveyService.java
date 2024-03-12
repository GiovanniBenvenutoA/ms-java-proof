package ms.survey.proof.service;

import ms.survey.proof.DTO.ChartsDTO;
import ms.survey.proof.DTO.ResponseInsertSurvey;
import ms.survey.proof.DTO.StyleMusicalDTO;
import ms.survey.proof.DTO.SurveyDataDTO;

import java.util.List;

public interface SurveyService {

    ResponseInsertSurvey saveSurvey(SurveyDataDTO survey);

    List<StyleMusicalDTO> getMusical();

    List<ChartsDTO> getCharts();

    List<StyleMusicalDTO> addMusical(String musicalType);
}
