package rutkirgly.web.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rutkirgly.web.Services.UserRoleService;
import rutkirgly.web.Services.UserService;
import rutkirgly.web.dto.UserDTO;
import rutkirgly.web.dto.UserRoleDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public AdminController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/dashboard")
    public String userList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<UserDTO> users = userService.getAll();
        model.addAttribute("users", users);

        List<UserRoleDTO> userRoles = userRoleService.getAll();
        model.addAttribute("userRoles", userRoles);

        if (authentication != null) {
            String username = authentication.getName();
            logger.info("Current user: {}", username);

            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            logger.info("Roles: {}", roles);
        }
        return "dashboard";
    }

    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public String updateUserRole(@RequestParam("userId") UUID userId, @RequestParam("roleId") UUID roleId) {
        try {
            userService.updateUserRole(userId, roleId);
            logger.info("Роль пользователя успешно изменена. Пользователь ID: {}, Новая роль ID: {}", userId, roleId);
            return "redirect:/user/user-list";
        } catch (AccessDeniedException e) {
            logger.error("Отказано в доступе при попытке изменить роль пользователя. Пользователь ID: {}, Новая роль ID: {}", userId, roleId);
            return "redirect:/access-denied";
        } catch (Exception e) {
            logger.error("Произошла ошибка при изменении роли пользователя. Пользователь ID: {}, Новая роль ID: {}", userId, roleId, e);
            return "redirect:/not-found.html";
        }
    }
}
