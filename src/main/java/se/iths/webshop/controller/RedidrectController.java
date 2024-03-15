package se.iths.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class RedidrectController {

    @GetMapping("/processLoginForm")
    public RedirectView redirectView(RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashattribute", "redidrectWithRedirectView");
        attributes.addAttribute("attribute", "redidrectWithRedirectView");
        return new RedirectView("/webshop");
    }

 /*   @GetMapping("/processLoginForm")
    public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/webshop", model);
    }*/
}

