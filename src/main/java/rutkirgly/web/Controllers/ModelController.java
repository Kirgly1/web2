package rutkirgly.web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rutkirgly.web.Services.BrandService;
import rutkirgly.web.Services.ModelService;

import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class ModelController {

    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public ModelController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

//    @GetMapping("/{brandId}/models")
//    public ModelAndView getAllByBrand(@PathVariable UUID brandId, Model model) {
//        ModelAndView modelAndView = new ModelAndView("models-list");
//        modelAndView.addObject("models", modelService.getAllByBrand(brandId));
//        modelAndView.addObject("brandId", brandId);
//        return modelAndView;
//    }
}
