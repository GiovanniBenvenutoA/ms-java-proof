package ms.survey.proof.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {

    private String message;
    private String code;
    private List<ProductDto> product;
}
