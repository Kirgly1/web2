package rutkirgly.web.Tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import rutkirgly.web.Tables.Abastracts.BaseEx;
import rutkirgly.web.constants.Category;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Model")
public class Model extends BaseEx {
    @NotBlank(message = "Model can't be empty")
    @Size(max = 128, message = "Model can't  be more than 128 symbols")
    private String name;

    @NotNull(message = "Category can't be null")
    private Category category;

    @Pattern(regexp = "^(http|https)://.*\\.(jpg|png|gif|bmp)$", message = "Image URL must be a valid image URL")
    private String imageUrl;

    @Min(value = 1800, message = "start Year can't be earlier than 1800's")
    private Integer startYear;

    @Max(value = 2050, message = "end Year can't be older than 2050's")
    private Integer endYear;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Brand_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Brand brand;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<Offers> offers;
}
