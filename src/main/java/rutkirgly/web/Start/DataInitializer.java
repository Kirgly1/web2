package rutkirgly.web.Start;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rutkirgly.web.Services.BrandService;
import rutkirgly.web.Services.OffersService;
import rutkirgly.web.Services.UserRoleService;
import rutkirgly.web.Services.UserService;
import rutkirgly.web.Tables.*;
import rutkirgly.web.constants.Category;
import rutkirgly.web.constants.Engine;
import rutkirgly.web.constants.Role;
import rutkirgly.web.constants.Transmission;

import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final Faker faker;
    private final BrandService brandService;
    private final OffersService offerService;
    private final UserRoleService userRoleService;
    private final UserService userService;

    @Autowired
    public DataInitializer(Faker faker, BrandService brandService, OffersService offerService, UserRoleService userRoleService, UserService userService) {
            this.faker = faker;
            this.brandService = brandService;
            this.offerService = offerService;
            this.userRoleService = userRoleService;
            this.userService = userService;
        }

        @Override
        public void run(String... args) throws Exception {

            UserRole userRoleUser = new UserRole();
            userRoleUser.setRole(Role.ADMIN);
            userRoleService.create(userRoleUser);
            UserRole userRoleAdmin = new UserRole();
            userRoleAdmin.setRole(Role.USER);
            userRoleService.create(userRoleAdmin);

            for (int i = 0; i < 100; i++) {
                User user = new User();
                user.setUsername(faker.name().username());
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setImageUrl(faker.internet().url());
                user.setPassword(faker.internet().password());
                Random random = new Random();
                boolean choice = random.nextBoolean();
                if (!(choice)) {
                    user.setRole(userRoleUser);
                } else{
                    user.setRole(userRoleAdmin);
                }
                user.setIsActive(true);
                userService.create(user);
            }

            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                Brand brand = new Brand();
                brand.setName(faker.company().name());
                for (int j = 0; j < 5; j++) {
                    String modelName = faker.rockBand().name();
                    Category category = Category.values()[random.nextInt(Category.values().length)];
                    String imageUrl = faker.internet().avatar();
                    int startYear = 1886 + random.nextInt(135);
                    int endYear = startYear + random.nextInt(2023 - startYear);

                    Model model = new Model();
                    model.setName(modelName);
                    model.setCategory(category);
                    model.setImageUrl(imageUrl);
                    model.setStartYear(startYear);
                    model.setEndYear(endYear);
                    model.setBrand(brand);
                    brand.setModels(List.of(model));

                    brandService.create(brand);

                    Offers offer = new Offers();
                    offer.setDescription(faker.lorem().characters(20, 255));
                    offer.setEngine(Engine.values()[random.nextInt(Engine.values().length)]);
                    offer.setMileage(random.nextInt(300000));
                    offer.setPrice(faker.number().randomDouble(2, 1000, 100000));
                    offer.setTransmission(Transmission.values()[random.nextInt(Transmission.values().length)]);
                    offer.setYear(faker.number().numberBetween(1886, 2022));
                    offer.setModel(model);
                    offer.setImageUrl(imageUrl);
                    offerService.create(offer);
                }
            }
        }
    }


