package FinalProject_frontend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import FinalProject_frontend.model.Item;
import FinalProject_frontend.model.dto.DistributionCenterDto;

import FinalProject_frontend.repository.WareHouseRepository;
import FinalProject_frontend.service.ItemRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminController {

    private RestTemplate restTemplate;

    private WareHouseRepository warehouseRepository;
    private ItemRequestService itemRequestService;

    public AdminController(RestTemplate restTemplate,
                           WareHouseRepository warehouseRepository,
                           ItemRequestService itemRequestService) {
        this.restTemplate = restTemplate;
        this.warehouseRepository = warehouseRepository;
        this.itemRequestService = itemRequestService;
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

        var warehouse = warehouseRepository.findById(1L);
        var closestCenterWithItem = itemRequestService.findClosestDistributionCenter(filteredCenters, warehouse.get().getLatitude(), warehouse.get().getLongitude());
        Long itemId = closestCenterWithItem.getItems().stream().filter(item -> item.getName().equalsIgnoreCase(name)).findFirst().get().getId();
        Item selectItem = closestCenterWithItem.getItems().stream().filter(item -> item.getName().equalsIgnoreCase(name)).findFirst().get();

        model.addAttribute("itemId", itemId);
        model.addAttribute("selectItem", selectItem);
        model.addAttribute("centers", filteredCenters);
        model.addAttribute("brands", EnumSet.allOf(Item.Brand.class));
        model.addAttribute("searchName", name);
        model.addAttribute("searchBrand", brand);
        model.addAttribute("closestCenterWithItem", closestCenterWithItem);
        return "admin";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam Long itemId, Item selectItem, @RequestParam int quantity) {
        itemRequestService.updateItem(itemId, selectItem, quantity);
        return "redirect:/admin";
    }
}
