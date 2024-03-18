package se.iths.webshop.controller;

import jakarta.servlet.http.HttpSession;
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
import se.iths.webshop.repository.model.User;
import se.iths.webshop.security.UserService;

import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>RegistrationController</h2>
 * @date 2024-03-14
 */

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // add an InitBinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showHomePage() {
        return "user/home-page";
    }

    @GetMapping("/registrationForm")
    public String showRegistrationForm (Model model) {
        model.addAttribute("webUser", new WebUser());
        return "user/registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm (@Valid @ModelAttribute("webUser") WebUser webUser,
                               BindingResult theBindingResult,
                               HttpSession session, Model model) {

        String email = webUser.getEmail();

        // form validation
        if (theBindingResult.hasErrors()) {
            return "user/registration-form";
        }

        // check the database if user already exists
        Optional<User> savedUser = userService.findByEmail(email);
        if (savedUser.isPresent()) {
            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "Email already exists!");
            return "user/registration-form";
        }

        // create user account and store in the database
        userService.save(webUser);

        // place user in the web http session for later use
        session.setAttribute("user", webUser);
        return "user/customer-confirmation";



        /*
        if (!theBindingResult.hasErrors() && userService.emailAlreadyExists(user.getEmail())) {
            theBindingResult.rejectValue("email", "error.email", "Email already exists!");
        }

        if (theBindingResult.hasErrors()) {
            return "user/registration-form";
        } else {
            userService.addUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
            return "user/customer-confirmation";
        }

         */
    }


}
