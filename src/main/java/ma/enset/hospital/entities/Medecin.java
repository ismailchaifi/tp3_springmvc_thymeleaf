package ma.enset.hospital.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class Medecin {

    @Id
    private String id;
    private String nom;
    private String email;
    private String specialite;
    @OneToMany(mappedBy = "medecin", fetch = FetchType.EAGER)
    private Collection<RendezVous> rendezVous;
}
