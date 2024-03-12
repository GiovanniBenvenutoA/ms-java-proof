package ms.survey.proof.service.impl;

import ms.survey.proof.DTO.ChartsDTO;
import ms.survey.proof.DTO.ResponseInsertSurvey;
import ms.survey.proof.DTO.StyleMusicalDTO;
import ms.survey.proof.DTO.SurveyDataDTO;
import ms.survey.proof.entities.MusicalStyles;
import ms.survey.proof.entities.Survey;
import ms.survey.proof.repository.MusicalRepository;
import ms.survey.proof.repository.SurveyRepository;
import ms.survey.proof.util.ChartsDTOConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SurveyServicesImplTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private MusicalRepository musicalRepository;

    @Mock
    private ChartsDTOConverter chartsDTOConverter;

    @InjectMocks
    private SurveyServicesImpl surveyServices;

    @Test
    void testSaveSurvey() {
        // Datos de prueba
        SurveyDataDTO surveyData = new SurveyDataDTO();
        surveyData.setEmailIdentifier("test@example.com");
        surveyData.setMusicId(1);
        Survey existingSurvey = new Survey();
        existingSurvey.setEmail("test@example.com");
        when(surveyRepository.findByEmail(any())).thenReturn(existingSurvey);
        ResponseInsertSurvey response = surveyServices.saveSurvey(surveyData);
        assertEquals(-1, response.getCode());
        assertEquals("Existe un registro con este email intente con otro", response.getResponse());
        verify(surveyRepository, times(1)).findByEmail(("test@example.com"));
        verify(surveyRepository, never()).save(any());
    }

    @Test
    void testGetMusical() {
        MusicalStyles musicalStyle1 = new MusicalStyles();
        musicalStyle1.setMusicId(1L);
        musicalStyle1.setMusicSpecification("Rock");
        MusicalStyles musicalStyle2 = new MusicalStyles();
        musicalStyle2.setMusicId(2L);
        musicalStyle2.setMusicSpecification("Pop");
        List<MusicalStyles> mockMusicalStylesList = Arrays.asList(musicalStyle1, musicalStyle2);
        when(musicalRepository.findAll()).thenReturn(mockMusicalStylesList);
        List<StyleMusicalDTO> result = surveyServices.getMusical();
        verify(musicalRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testGetCharts() {
        Object[] rawData1 = new Object[] { "Genre1", 10L };
        Object[] rawData2 = new Object[] { "Genre2", 20L };

        List<Object[]> mockRawData = Arrays.asList(rawData1, rawData2);
        when(surveyRepository.chartsData()).thenReturn(mockRawData);
        ChartsDTO dto1 = new ChartsDTO();
        dto1.setMusicalDescription("Genre1");
        dto1.setAmount(10);

        ChartsDTO dto2 = new ChartsDTO();
        dto2.setMusicalDescription("Genre2");
        dto2.setAmount(20);

        List<ChartsDTO> expectedDTOList = Arrays.asList(dto1, dto2);
        when(chartsDTOConverter.convertToChartsDTOList(mockRawData)).thenReturn(expectedDTOList);
        List<ChartsDTO> result = surveyServices.getCharts();
        verify(surveyRepository, times(1)).chartsData();
        verify(chartsDTOConverter, times(1)).convertToChartsDTOList(mockRawData);
        assertEquals(expectedDTOList, result);
    }
}
