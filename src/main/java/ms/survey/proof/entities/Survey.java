package ms.survey.proof.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_survey", nullable = false)
    private Long id;

    @Column(name = "email_survey", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "musical_type_id", nullable = false)
    private MusicalStyles musicalType;


}
