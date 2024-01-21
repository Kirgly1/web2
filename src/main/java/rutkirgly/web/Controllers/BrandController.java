package rutkirgly.web.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rutkirgly.web.Services.BrandService;
import rutkirgly.web.Services.ModelService;
import rutkirgly.web.Services.OffersService;
import rutkirgly.web.Tables.Brand;
import rutkirgly.web.dto.BrandDTO;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
@Slf4j
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;
    private final OffersService offersService;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BrandController.class);


    @Autowired
    public BrandController(BrandService brandService, ModelService modelService, OffersService offersService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offersService = offersService;
    }

    @GetMapping("/{id}")
    public String getBrandById(@PathVariable UUID id, Principal principal, Model model) {
        log.info("Brand founded by id");
        BrandDTO brand = brandService.getById(id);
        if (brand != null) {
            model.addAttribute("brand", brand);
            return "brand-details";
        } else {
            return "redirect:/brands/all";
        }
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        log.info("Show all Brands");
        List<BrandDTO> brands = brandService.getAll();
        model.addAttribute("brands", brands);
        log.info("All Brands: ");
        return "brands-list";
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute Brand brand, Principal principal) {
        log.info("Brand created");
        brandService.create(brand);
        return "redirect:/brands/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable UUID id, Principal principal) {
        log.info("User deletes Brand");
        brandService.delete(id);
        return "redirect:/brands/all";
    }
    @GetMapping("/models/{id}")
    public ModelAndView getAllModelsFromBrand(@PathVariable UUID id, Principal principal) {
        log.info("Get models on this brand");
        ModelAndView modelAndView = new ModelAndView("brand-details");
        modelAndView.addObject("models", modelService.getAllByBrandId(id));
        return modelAndView;
    }

}
