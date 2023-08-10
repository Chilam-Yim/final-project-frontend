package FinalProject_frontend.controller;

import java.util.EnumSet;
import java.util.Optional;

import FinalProject_frontend.model.Item.Brand;
import FinalProject_frontend.model.UserDetail;
import FinalProject_frontend.model.dto.ItemsSearch;
import FinalProject_frontend.repository.ItemRepository;
import FinalProject_frontend.repository.ItemRepositoryPaginated;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/items")
public class ItemController {

    // define the items show in one page
    private static final int PAGE_SIZE = 3;

    private ItemRepository itemRepository;

    private ItemRepositoryPaginated itemRepositoryPaginated;

    public ItemController(ItemRepository itemRepository, ItemRepositoryPaginated itemRepositoryPaginated) {
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated;
    }

    @GetMapping
    public String showItems(Model model) {
        return "items";
    }

    @ModelAttribute
    public void items(Model model) {
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("items", itemPage);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
    }

    @ModelAttribute
    public void itemsSearch(Model model) {
        model.addAttribute("itemsSearch", new ItemsSearch());
    }

    @PostMapping("/delete/{itemId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteItem(@AuthenticationPrincipal UserDetail user, @PathVariable Long itemId) {
        log.info("User {} is deleting item {}", user.getAuthorities(), itemId);
        // Delete the item from the repository
        itemRepository.deleteById(itemId);
        // Redirect to the items list page (or any other desired page)
        return "redirect:/items";
    }

    @PostMapping
    public String searchItems(@ModelAttribute ItemsSearch itemsSearch, Model model) {
        model.addAttribute("items", itemRepository.findByCreateYearAndBrandFromEquals(
                itemsSearch.getCreateYear(),
                itemsSearch.getBrandFrom()));
        return "items";
    }

    @GetMapping("/switchPage")
    public String switchPage(@RequestParam("page") Optional<Integer> page, Model model) {
        var pageToGoTo = page.orElse(0);
        var totalPages = (int)model.getAttribute("totalPages");
        if (pageToGoTo < 0 || pageToGoTo >= totalPages) {
            return "redirect:/items";
        }
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(page.orElse(0), PAGE_SIZE));
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        return "items";
    }
}
