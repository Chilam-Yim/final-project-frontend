package FinalProject_frontend.controller;

import FinalProject_frontend.model.dto.DistributionCenterDto;
import FinalProject_frontend.repository.WareHouseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject_frontend.service.ItemRequestService;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Controller
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:8081")
public class HomeController {

    private WareHouseRepository warehouseRepository;
    private ItemRequestService itemRequestService;

    private RestTemplate restTemplate;

    public HomeController(WareHouseRepository warehouseRepository, ItemRequestService itemRequestService, RestTemplate restTemplate) {
        this.warehouseRepository = warehouseRepository;
        this.itemRequestService = itemRequestService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @ModelAttribute
    public void warehouse(Model model) {
        var warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);
    }

    @ModelAttribute
    public void closestCenter(Model model) {
        var centerList = Arrays.asList(restTemplate.getForObject("http://localhost:8081/api/center", DistributionCenterDto[].class));
        var warehouse = warehouseRepository.findById(1L);
        var closestCenter = itemRequestService.findClosestDistributionCenter(centerList, warehouse.get().getLatitude(), warehouse.get().getLongitude());
        model.addAttribute("closestCenter", closestCenter);
    }
}
