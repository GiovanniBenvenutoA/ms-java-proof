package ms.survey.proof.service.impl;


import ms.survey.proof.DTO.ChartsDTO;
import ms.survey.proof.DTO.ResponseInsertSurvey;
import ms.survey.proof.DTO.StyleMusicalDTO;
import ms.survey.proof.DTO.SurveyDataDTO;
import ms.survey.proof.entities.MusicalStyles;
import ms.survey.proof.entities.Survey;
import ms.survey.proof.repository.MusicalRepository;
import ms.survey.proof.repository.SurveyRepository;
import ms.survey.proof.service.SurveyService;
import ms.survey.proof.util.ChartsDTOConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyServicesImpl implements SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    MusicalRepository musicalRepository;

    @Autowired
    private ChartsDTOConverter chartsDTOConverter;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseInsertSurvey saveSurvey(SurveyDataDTO surveyData) {

        ResponseInsertSurvey responseInsertSurvey = new ResponseInsertSurvey();
        Survey searchEmail = surveyRepository.findByEmail(surveyData.getEmailIdentifier());
        if(searchEmail==null){
            Survey survey = modelMapper.map(surveyData, Survey.class);
            Optional<MusicalStyles> optionalMusicalStyles  = musicalRepository.findById((long)surveyData.getMusicId());
            MusicalStyles musicalStyles = optionalMusicalStyles.orElseGet(() -> new MusicalStyles());
            survey.setMusicalType(musicalStyles);
            surveyRepository.save(survey);
            responseInsertSurvey.setCode(0);
            responseInsertSurvey.setResponse("Se guarda Exitosamente su encuesta");
            return responseInsertSurvey;
        }else{
            responseInsertSurvey.setCode(-1);
            responseInsertSurvey.setResponse("Existe un registro con este email intente con otro");
            return responseInsertSurvey;
        }
    }

    @Override
    public List<StyleMusicalDTO> getMusical() {
        List<MusicalStyles> musicalStylesList = musicalRepository.findAll();
        return musicalStylesList.stream()
                .map(musicalStyle -> modelMapper.map(musicalStyle, StyleMusicalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChartsDTO> getCharts() {
        List<Object[]> rawData = surveyRepository.chartsData();
        return chartsDTOConverter.convertToChartsDTOList(rawData);
    }

    @Override
    public List<StyleMusicalDTO> addMusical(String musicalType) {
        MusicalStyles musicalStyles = new MusicalStyles();
        musicalStyles.setMusicSpecification(musicalType);
        musicalRepository.save(musicalStyles);
        return null;
    }


}
