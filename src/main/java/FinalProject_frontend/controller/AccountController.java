package FinalProject_frontend.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject_frontend.model.form.RegistrationForm;
import FinalProject_frontend.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class AccountController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AccountController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "authentication/register";
    }

    @PostMapping
    public String registerUserAccount(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/";
    }
}
