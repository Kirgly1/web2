package rutkirgly.web.Tables;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import rutkirgly.web.Tables.Abastracts.BaseEntity;
import rutkirgly.web.Tables.Abastracts.BaseEx;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "brand")
public class Brand extends BaseEx {
    @NotBlank(message = "Brand name can't be empty")
    @Size(min = 2, max = 56, message = "Brand name be 2-56")
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Model> models;
}
