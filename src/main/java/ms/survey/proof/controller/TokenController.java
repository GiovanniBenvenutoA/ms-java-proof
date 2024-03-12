package ms.survey.proof.controller;

import ms.survey.proof.DTO.TokenDTO;
import ms.survey.proof.security.JWTAuthtenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @PostMapping("/get/token")
    public TokenDTO login(
            @RequestParam("userToken") String username) {
        return jwtAuthtenticationConfig.getJWTToken(username);

    }
}
