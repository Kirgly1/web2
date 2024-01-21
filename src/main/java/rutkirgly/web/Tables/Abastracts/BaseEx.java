package rutkirgly.web.Tables.Abastracts;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEx extends BaseEntity {

    private LocalDateTime created;
    private LocalDateTime modified;


    @PrePersist
    private void setCreated(){
        this.created = LocalDateTime.now();
    }
    @PreUpdate
    private void setModified(){
        this.modified = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }
}
