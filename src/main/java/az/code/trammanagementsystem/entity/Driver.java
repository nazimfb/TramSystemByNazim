package az.code.trammanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "driver")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @OneToOne(mappedBy = "driver")
    private Tram currentTram;

}
