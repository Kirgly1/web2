package rutkirgly.web.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rutkirgly.web.Services.BrandService;
import rutkirgly.web.Services.ModelService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
@Slf4j
public class ModelController {

    private final BrandService brandService;
    private final ModelService modelService;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ModelController.class);
    @Autowired
    public ModelController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }
    @GetMapping("/{brandId}/models")
    public ModelAndView getAllByBrand(@PathVariable UUID brandId, Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("models-list");
        modelAndView.addObject("models", modelService.getAllByBrandId(brandId));
        modelAndView.addObject("brandId", brandId);
        log.info("Model founded by BrandId");
        return modelAndView;
    }
}
