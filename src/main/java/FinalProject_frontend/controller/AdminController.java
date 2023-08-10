package FinalProject_frontend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import FinalProject_frontend.model.Item;
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

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Item.Brand.class);
        model.addAttribute("brands", brands);
    }

    @PostMapping("/search")
    public String searchCenters(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String brand,
                                Model model) {
        var centers = Arrays.asList(restTemplate.getForObject("http://localhost:8081/api/center", DistributionCenterDto[].class));
        List<DistributionCenterDto> filteredCenters = new ArrayList<>(centers);

        if (name != null && brand != null) {
            String brandUpperCase = brand.toUpperCase();
            filteredCenters.removeIf(center -> center.getItems().stream()
                    .noneMatch(item -> item.getName().equalsIgnoreCase(name) && item.getBrandFrom() == Item.Brand.valueOf(brandUpperCase)));
        }

        model.addAttribute("centers", filteredCenters);
        model.addAttribute("brands", EnumSet.allOf(Item.Brand.class));
        model.addAttribute("searchName", name);
        model.addAttribute("searchBrand", brand);

        return "admin";
    }




}
