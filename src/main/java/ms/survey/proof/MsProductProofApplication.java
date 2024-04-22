package ms.survey.proof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class MsProductProofApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductProofApplication.class, args);
    }

}
