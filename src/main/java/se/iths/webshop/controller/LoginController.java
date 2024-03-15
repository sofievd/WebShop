package se.iths.webshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.webshop.controller.model.WebUser;
import se.iths.webshop.service.UserService;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>LoginController</h2>
 * @date 2024-03-15
 */

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/showLoginForm")
    public String showLoginForm(Model model) {

        model.addAttribute("webUser", new WebUser());
        return "login-form";
    }

    @PostMapping("/processLoginForm")
    public String processLoginForm (@Valid @ModelAttribute("webUser") WebUser webUser,
                                    BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "login-form";
        }

        boolean validLogin = userService.validateLogin(webUser.getEmail(), webUser.getPassword());

        if (!userService.emailAlreadyExists(webUser.getEmail())) {
            theBindingResult.rejectValue("email", "error.email", "User NOT found- Register Now!");
        } else if (!validLogin) {
            theBindingResult.rejectValue("email", "error.email", "Invalid Email/Password: Try again!");
        }

        if (!theBindingResult.hasErrors()) {
            return "tempwebshoppage";
        } else {
            return "login-form";
        }
    }

    // add an InitBinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
