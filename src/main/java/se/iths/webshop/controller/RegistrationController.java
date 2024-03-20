package se.iths.webshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.webshop.dto.UserDto;
import se.iths.webshop.entity.User;
import se.iths.webshop.service.UserService;

import java.util.List;
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

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showLandingPage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration-form";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult theBindingResult) {

        Optional<User> savedUser = userService.findUserByEmail(userDto.getEmail());

        if(!theBindingResult.hasErrors() && savedUser.isPresent()) {
            theBindingResult.rejectValue("email", "error.email", " email already exists!");
        }

        if(theBindingResult.hasErrors()){
            return "registration-form";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // add an InitBinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/usersList")
    public String usersList(Model model) {
        List<UserDto> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "users-list";
    }

    @GetMapping("/customers")
    public String showHomePage() {
        return "index";
    }

    // add mapping for /admin

    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }
}
