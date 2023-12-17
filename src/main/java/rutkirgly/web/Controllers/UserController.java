package rutkirgly.web.Controllers;

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
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }
}
