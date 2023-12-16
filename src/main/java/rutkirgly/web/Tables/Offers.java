package rutkirgly.web.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import rutkirgly.web.Tables.Abastracts.BaseEx;
import rutkirgly.web.constants.Engine;
import rutkirgly.web.constants.Transmission;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Offers")
public class Offers extends BaseEx {
    @NotBlank(message = "Description can't be empty")
    private String description;

    @NotNull
    private Engine engine;

    @Pattern(regexp = "^(http|https)://.*\\.(jpg|png|gif|bmp)$", message = "Image URL must be a valid image URL")
    private String imageUrl;

    @NotNull(message = "Mileage can't be empty")
    private Integer mileage;

    @NotNull(message = "price can't be empty")
    private double price;

    @NotNull(message = "Engine can't be empty")
    private Transmission transmission;

    @Size(min = 1800, max = 2050)
    @NotNull(message = "Year can't be less than 1800's and can't be more than 2050")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "Model_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}
