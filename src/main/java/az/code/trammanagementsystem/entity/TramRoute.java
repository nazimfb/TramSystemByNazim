package az.code.trammanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TramRoute {
    @Id
    private Long id;

    @ManyToOne
    private Tram tram;

    @ManyToOne
    private Route route;

    private Date dateAddedToRoute;

    private boolean active;
}
