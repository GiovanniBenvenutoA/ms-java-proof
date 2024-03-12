package ms.survey.proof.controller;

import ms.survey.proof.DTO.StyleMusicalDTO;
import ms.survey.proof.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class SurveyControllerTest {

    @Mock
    private SurveyService surveyService;

    @InjectMocks
    private SurveyController surveyController;

    @Test
    void testGetMusical() {
        StyleMusicalDTO styleMusicalDTO1 = new StyleMusicalDTO();
        styleMusicalDTO1.setId(1L);
        styleMusicalDTO1.setMusicSpecification("Rock");
        StyleMusicalDTO styleMusicalDTO2 = new StyleMusicalDTO();
        styleMusicalDTO2.setId(2L);
        styleMusicalDTO2.setMusicSpecification("Pop");
        List<StyleMusicalDTO> expectedDTOList = Arrays.asList(styleMusicalDTO1, styleMusicalDTO2);
        when(surveyService.getMusical()).thenReturn(expectedDTOList);
        List<StyleMusicalDTO> actualDTOList = surveyController.getMusical();
        assertEquals(expectedDTOList, actualDTOList);
        verify(surveyService, times(1)).getMusical();
    }

    @Test
    void testAddMusical() {
        String musicalType = "Rock";
        StyleMusicalDTO styleMusicalDTO1 = new StyleMusicalDTO();
        styleMusicalDTO1.setId(1L);
        styleMusicalDTO1.setMusicSpecification("Rock");
        StyleMusicalDTO styleMusicalDTO2 = new StyleMusicalDTO();
        styleMusicalDTO2.setId(2L);
        styleMusicalDTO2.setMusicSpecification("Pop");
        List<StyleMusicalDTO> expectedDTOList = Arrays.asList(styleMusicalDTO1, styleMusicalDTO2);
        when(surveyService.addMusical(anyString())).thenReturn(expectedDTOList);
        List<StyleMusicalDTO> actualDTOList = surveyController.addMusical(musicalType);
        assertEquals(expectedDTOList, actualDTOList);
    }
}
