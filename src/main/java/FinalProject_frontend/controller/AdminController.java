package FinalProject_frontend.controller;

import java.util.Arrays;

import FinalProject_frontend.model.dto.DistributionCenterDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;


@Controller
@Slf4j
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminController {

    private RestTemplate restTemplate;

    public AdminController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String admin() {
        return "admin";
    }

    @ModelAttribute
    public void getCenters(Model model) {
        var centers = Arrays.asList(restTemplate.getForObject("http://localhost:8081/api/center", DistributionCenterDto[].class));
        model.addAttribute("centers", centers);
    }
}
