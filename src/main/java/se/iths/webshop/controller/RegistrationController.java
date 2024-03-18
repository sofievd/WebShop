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
import se.iths.webshop.repository.model.User;
import se.iths.webshop.service.UserService;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>RegistrationController</h2>
 * @date 2024-03-14
 */

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showHomePage() {
        return "user/home-page";
    }

    @GetMapping("/registrationForm")
    public String showRegistrationForm (Model model) {

        model.addAttribute("user", new User());
        return "user/registration-form";
    }

    @PostMapping("/processForm")
    public String processForm (@Valid @ModelAttribute("user") User user,
                              BindingResult theBindingResult) {

        if (!theBindingResult.hasErrors() && userService.emailAlreadyExists(user.getEmail())) {
            theBindingResult.rejectValue("email", "error.email", "Email already exists!");
        }

        if (theBindingResult.hasErrors()) {
            return "user/registration-form";
        } else {
            userService.addUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
            return "user/customer-confirmation";
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
