package rutkirgly.web.dto;

import lombok.Data;
import rutkirgly.web.Tables.Offers;

import java.util.Date;
import java.util.UUID;

@Data
public class OffersDTO {
    private UUID id;
    private String description;
    private String engine;
    private String imageUrl;
    private Integer mileage;
    private Double price;
    private String transmission;
    private Integer year;
    private Date created;
    private Date modified;
    private ModelDTO model;
    private UserDTO seller;
}
