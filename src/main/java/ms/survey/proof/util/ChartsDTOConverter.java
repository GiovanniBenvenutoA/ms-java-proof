package ms.survey.proof.util;

import ms.survey.proof.DTO.ChartsDTO;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class ChartsDTOConverter {

    public List<ChartsDTO> convertToChartsDTOList(List<Object[]> resultList) {
        List<ChartsDTO> chartsDTOList = new ArrayList<>();

        for (Object[] result : resultList) {
            String musicalType = Objects.toString(result[0], null);
            BigInteger amountBigInteger = (BigInteger) result[1];
            int amount = amountBigInteger.intValue();
            ChartsDTO chartsDTO = new ChartsDTO(musicalType, amount);
            chartsDTOList.add(chartsDTO);
        }
        return chartsDTOList;
    }
}
