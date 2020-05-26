package se.ecutb.foodReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.ecutb.foodReview.data.RestaurantRepo;
import se.ecutb.foodReview.dto.CreateRestaurantForm;
import se.ecutb.foodReview.entity.Restaurant;
import se.ecutb.foodReview.service.RestaurantService;

import javax.validation.Valid;

@Controller
public class AdminController {
    private RestaurantRepo restaurantRepo;
    private RestaurantService restaurantService;

    @Autowired
    public AdminController(RestaurantRepo restaurantRepo, RestaurantService restaurantService) {
        this.restaurantRepo = restaurantRepo;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin-page";
    }

    @GetMapping("admin/registerRestaurant/form")
    public String getForm(Model model){
        model.addAttribute("form", new CreateRestaurantForm());
        return "registerRestaurant";
    }

    @PostMapping("admin/registerRestaurant/process")
    public String formProcess(@Valid @ModelAttribute("form") CreateRestaurantForm form, BindingResult bindingResult) {
        if (restaurantRepo.findByNameIgnoreCase(form.getRestaurantName()).isPresent()) {
            FieldError error = new FieldError("form", "name", "Name is already in use");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            return "registerRestaurant";
        }

        Restaurant restaurant = restaurantService.registerRestaurant(form.getRestaurantName());
        return "redirect:/restaurants";
    }
}
