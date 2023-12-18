package rutkirgly.web.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rutkirgly.web.Services.OffersService;
import rutkirgly.web.dto.OffersDTO;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
@Slf4j
public class OffersController {

    private final OffersService offersService;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OffersController.class);
    @Autowired
    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/details")
    public String getAllOffers(Model model, Principal principal) {
        log.info("Offers details");
        List<OffersDTO> offers = offersService.getAll();
        offers.sort(Comparator.comparing(OffersDTO::getPrice));
        model.addAttribute("offers", offers);
        return "offerDetails";
    }


    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable UUID id, Principal principal) {
        log.info("Offers deleted");
        offersService.delete(id);
        return "redirect:/offers/all";
    }
    @ModelAttribute("getPriceCategory")
    public String getPriceCategory(@RequestParam(name = "price", defaultValue = "0") double price) {
        if (price < 10000) {
            return "Less than 10000";
        } else if (price >= 10000 && price <= 50000) {
            return "From 10000 to 50000";
        } else {
            return "More than 50000";
        }
    }

}
