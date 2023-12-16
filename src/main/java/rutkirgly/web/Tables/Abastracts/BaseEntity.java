package rutkirgly.web.Tables.Abastracts;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;


@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private BaseEntity(UUID id){this.id = id;}
    public BaseEntity(){}
}
