package rutkirgly.web.Controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rutkirgly.web.Services.UserService;

@Controller
@RequestMapping("/user")

public class UserController {

    private UserService userService;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user-list")
    public ModelAndView getAll() {
        log.info("Show all Users");
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("users", userService.getAll());
        log.info("All Users");
        return modelAndView;
    }

}
