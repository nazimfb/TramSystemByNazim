package az.code.trammanagementsystem.entity;

import az.code.trammanagementsystem.validation.ValidManufactureYear;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tram {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    private String manufacturer;
    private String model;

    @ValidManufactureYear
    private Integer manufactureYear;

    private Double latitude;
    private Double longitude;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route currentRoute;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Tram tram = (Tram) o;
        return getId() != null && Objects.equals(getId(), tram.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

//    public int getLastWaypointIndex() {
//        return 0;
//    }
//
//    public void setLastWaypointIndex(int nextWaypointIndex) {
//
//    }
}
