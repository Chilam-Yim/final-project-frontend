package FinalProject_frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @PostMapping ("/logout")
    public String showlogout() {
        return "redirect:/login?logout"; // Redirect to login page with a logout parameter
    }
}

