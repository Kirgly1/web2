package rutkirgly.web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rutkirgly.web.Services.OffersService;
import rutkirgly.web.dto.OffersDTO;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OffersService offersService;

    @Autowired
    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/details")
    public String getAllOffers(Model model) {
        List<OffersDTO> offers = offersService.getAll();
        // Отсортируйте список перед передачей в модель
        offers.sort(Comparator.comparing(OffersDTO::getPrice));
        model.addAttribute("offers", offers);
        return "offerDetails";
    }


    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable UUID id) {
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
