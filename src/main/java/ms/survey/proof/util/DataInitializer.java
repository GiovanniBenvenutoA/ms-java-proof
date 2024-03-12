package ms.survey.proof.util;

import ms.survey.proof.entities.MusicalStyles;
import ms.survey.proof.repository.MusicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final MusicalRepository musicalStylesRepository;

    @Autowired
    public DataInitializer(MusicalRepository musicalStylesRepository) {
        this.musicalStylesRepository = musicalStylesRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // Aquí puedes agregar la lógica para inicializar la base de datos con datos predefinidos
        // Por ejemplo:

        MusicalStyles style1 = new MusicalStyles();
        style1.setMusicSpecification("Rock");
        musicalStylesRepository.save(style1);

        MusicalStyles style2 = new MusicalStyles();
        style2.setMusicSpecification("Pop");
        musicalStylesRepository.save(style2);

        MusicalStyles style3 = new MusicalStyles();
        style3.setMusicSpecification("jazz");
        musicalStylesRepository.save(style3);

        MusicalStyles style4 = new MusicalStyles();
        style4.setMusicSpecification("Clasica");
        musicalStylesRepository.save(style4);

        MusicalStyles style5 = new MusicalStyles();
        style5.setMusicSpecification("Romantica");
        musicalStylesRepository.save(style5);

        // Agrega más datos según tus necesidades
    }
}
