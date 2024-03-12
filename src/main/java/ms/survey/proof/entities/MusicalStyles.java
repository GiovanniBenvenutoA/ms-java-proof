package ms.survey.proof.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "musical_styles")
public class MusicalStyles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_musical", nullable = false)
    private Long MusicId;

    @Column(name = "musical_type", nullable = false)
    private String musicSpecification;




}
