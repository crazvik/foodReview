package se.ecutb.foodReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.ecutb.foodReview.data.RestaurantRepo;
import se.ecutb.foodReview.data.ReviewerRepo;
import se.ecutb.foodReview.dto.CreateRestaurantForm;
import se.ecutb.foodReview.dto.CreateReviewerForm;
import se.ecutb.foodReview.entity.Restaurant;
import se.ecutb.foodReview.entity.Reviewer;
import se.ecutb.foodReview.service.RestaurantService;
import se.ecutb.foodReview.service.ReviewerService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {
    private ReviewerRepo reviewerRepo;
    private ReviewerService reviewerService;
    private RestaurantRepo restaurantRepo;
    private RestaurantService restaurantService;

    @Autowired
    public UserController(ReviewerRepo reviewerRepo, ReviewerService reviewerService, RestaurantRepo restaurantRepo, RestaurantService restaurantService) {
        this.reviewerRepo = reviewerRepo;
        this.reviewerService = reviewerService;
        this.restaurantRepo = restaurantRepo;
        this.restaurantService = restaurantService;
    }

    @GetMapping("users/register/form")
    public String getRegisterForm(Model model) {
        model.addAttribute("form", new CreateReviewerForm());
        return "register";
    }

    @PostMapping("users/register/process")
    public String formProcess(@Valid @ModelAttribute("form") CreateReviewerForm form, BindingResult bindingResult){

        if(reviewerRepo.findByUsernameIgnoreCase(form.getUsername()).isPresent()) {
            FieldError error = new FieldError("form", "username", "Username is already in use");
            bindingResult.addError(error);
        }

        if(!form.getPassword().equals(form.getPasswordConfirm())){
            FieldError error = new FieldError("form", "passwordConfirm", "Your confirmation didn't match password");
            bindingResult.addError(error);
        }

        if(bindingResult.hasErrors()){
            return "register";
        }

        Reviewer reviewer = reviewerService.registerReviewer(form.getFirstName(), form.getLastName(), form.getUsername(), form.getPassword(), LocalDate.now(), form.isAdmin());

        if(form.isAdmin()) {
            return "redirect:/users/"+reviewer.getId();
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("admin/registerRestaurant/form")
    public String getRestaurantForm(Model model){
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

    @RequestMapping("/restaurants")
    public String restaurantList(Model model) {
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepo.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }

    @GetMapping("/restaurants")
    public String getRestaurantPage() {
        return "restaurants";
    }
}
