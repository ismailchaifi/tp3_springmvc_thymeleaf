package ma.enset.hospital.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class Patient {

    @Id
    private String id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Collection<RendezVous> rendezVous;

}
