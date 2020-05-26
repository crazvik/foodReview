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
    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin-page";
    }

}
