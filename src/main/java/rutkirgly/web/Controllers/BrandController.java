package rutkirgly.web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rutkirgly.web.Services.BrandService;
import rutkirgly.web.Services.ModelService;
import rutkirgly.web.Tables.Brand;
import rutkirgly.web.dto.BrandDTO;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public BrandController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping("/{id}")
    public String getBrandById(@PathVariable UUID id, Principal principal, Model model) {
        BrandDTO brand = brandService.getById(id);
        if (brand != null) {
            model.addAttribute("brand", brand);
            return "brand-details";
        } else {
            // Если бренд не найден, вернуться к списку брендов
            return "redirect:/brands/all";
        }
    }

    @GetMapping("/all")
    public String getAllBrands(Model model, Principal principal) {
        List<BrandDTO> brands = brandService.getAll();
        model.addAttribute("brands", brands);
        return "brands-list";
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute Brand brand, Principal principal) {
        brandService.create(brand);
        return "redirect:/brands/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable UUID id, Principal principal) {
        brandService.delete(id);
        return "redirect:/brands/all";
    }
    @GetMapping("/models/{id}")
    public ModelAndView getAllModelsFromBrand(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("brand-details");
        modelAndView.addObject("models", modelService.getAllByBrandId(id));
        return modelAndView;
    }
}
