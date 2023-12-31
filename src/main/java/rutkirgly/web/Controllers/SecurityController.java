package rutkirgly.web.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rutkirgly.web.Services.UserService;
import rutkirgly.web.dto.UserDTO;

import java.text.MessageFormat;
@Controller
@RequestMapping("/auth")

public class SecurityController {
    private final UserService userService;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDTO initUser() {
        return new UserDTO();
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(@NotNull Model model) {
        log.info("Registration error");
        model.addAttribute(initUser());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userDTO);

            log.info("Registration successful.");
            return MessageFormat.format("{0}{1}{2}", "redirect:", "/auth", "/registration");
        }
        try {
            userService.registerNewUser(userDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка! Пользователь уже сущетсвует");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user",
                    bindingResult);
            log.info("Registration error, this user does exists!");
            return MessageFormat.format("{0}{1}{2}", "redirect:", "/auth", "/registration");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("Logout");
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }


}