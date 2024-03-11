package ma.enset.hospital.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class Patient {

    @Id
    private String id;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Collection<RendezVous> rendezVous;

}
