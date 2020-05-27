package se.ecutb.foodReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import se.ecutb.foodReview.data.FoodItemRepo;
import se.ecutb.foodReview.data.RestaurantRepo;
import se.ecutb.foodReview.data.ReviewerRepo;
import se.ecutb.foodReview.dto.*;
import se.ecutb.foodReview.entity.FoodItem;
import se.ecutb.foodReview.entity.Reviewer;
import se.ecutb.foodReview.service.FoodItemService;
import se.ecutb.foodReview.service.RestaurantService;
import se.ecutb.foodReview.service.ReviewerService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private ReviewerRepo reviewerRepo;
    private ReviewerService reviewerService;
    private RestaurantRepo restaurantRepo;
    private RestaurantService restaurantService;
    private FoodItemRepo foodItemRepo;
    private FoodItemService foodItemService;

    @Autowired
    public UserController(ReviewerRepo reviewerRepo, ReviewerService reviewerService, RestaurantRepo restaurantRepo,
                          RestaurantService restaurantService, FoodItemRepo foodItemRepo, FoodItemService foodItemService) {
        this.reviewerRepo = reviewerRepo;
        this.reviewerService = reviewerService;
        this.restaurantRepo = restaurantRepo;
        this.restaurantService = restaurantService;
        this.foodItemRepo = foodItemRepo;
        this.foodItemService = foodItemService;
    }

    @GetMapping("users/register/form")
    public String getRegisterForm(Model model) {
        model.addAttribute("form", new CreateReviewerForm());
        return "register";
    }

    @GetMapping("users/{id}")
    public String getUserView(@PathVariable(name = "id") int id, Model model) {
        Reviewer reviewer = reviewerRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("user", reviewer);
        return "userPage";
    }

    @GetMapping("users/{id}/update")
    public String getUpdateForm(@PathVariable(name = "id") int id, Model model) {
        UpdateReviewerForm reviewerForm = new UpdateReviewerForm();
        Reviewer reviewer = reviewerRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        reviewerForm.setFirstName(reviewer.getFirstName());
        reviewerForm.setLastName(reviewer.getLastName());
        reviewerForm.setUsername(reviewer.getUsername());
        reviewerForm.setId(reviewer.getId());
        model.addAttribute("form", reviewerForm);
        return "updateReviewer";
    }

    @GetMapping("admin/registerRestaurant/form")
    public String getRestaurantForm(Model model){
        model.addAttribute("form", new CreateRestaurantForm());
        return "registerRestaurant";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }

    @GetMapping("/restaurants")
    public String getRestaurantPage(Model model) {
        model.addAttribute("restaurants", restaurantRepo.findAll());
        model.addAttribute("foodItems", foodItemRepo.findAll());
        return "restaurants";
    }

    @GetMapping("admin/registerFoodItem/form")
    public String getFoodItemForm(Model model){
        model.addAttribute("form", new CreateFoodItemForm());
        model.addAttribute("resName", restaurantRepo.findAll());
        return "registerFoodItem";
    }

    @GetMapping("/users/restaurantReview/form")
    public String getReviewForm(Model model) {
        model.addAttribute("form", new CreateFoodReviewForm());
        model.addAttribute("item", foodItemRepo.findAll());
        return "restaurantReview";
    }

    @PostMapping("/users/restaurantReview/process")
    public String reviewProcess(@Valid @ModelAttribute("form") CreateFoodReviewForm form, BindingResult bindingResult) {
        FoodItem original = foodItemRepo.findByNameIgnoreCase(form.getFoodItemName()).orElseThrow(IllegalArgumentException::new);
        Optional<FoodItem> optional = foodItemRepo.findByNameIgnoreCase(form.getFoodItemName());
        if(!optional.isPresent()) {
            FieldError error = new FieldError("form", "foodName", "No food item with that name");
            bindingResult.addError(error);
        }

        if(bindingResult.hasErrors()) {
            return "restaurantReview";
        }

        original.setReview(form.getReview());
        original.setStars(form.getStars());
        foodItemRepo.save(original);

        return "redirect:/restaurants";
    }

    @PostMapping("users/{id}/update")
    public String processUpdate(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("form") UpdateReviewerForm form,
            BindingResult result)
    {
        Reviewer original = reviewerRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        Optional<Reviewer> optional = reviewerRepo.findByUsernameIgnoreCase(form.getUsername());
        if(optional.isPresent() && !form.getUsername().equalsIgnoreCase(original.getUsername())){
            FieldError error = new FieldError("form", "username", "Username is already in use");
            result.addError(error);
        }

        if(result.hasErrors()) {
            return "updateReviewer";
        }

        original.setUsername(form.getUsername());
        original.setFirstName(form.getFirstName());
        original.setLastName(form.getLastName());

        reviewerRepo.save(original);

        return "redirect:/users/"+original.getId();
    }

    @PostMapping("users/register/process")
    public String formRegisterProcess(@Valid @ModelAttribute("form") CreateReviewerForm form, BindingResult bindingResult){
        if(reviewerRepo.findByUsernameIgnoreCase(form.getUsername()).isPresent()) {
            FieldError error = new FieldError("form", "username", "Username is already in use");
            bindingResult.addError(error);
        }

        if(!form.getPassword().equals(form.getPasswordConfirm())) {
            FieldError error = new FieldError("form", "passwordConfirm", "Your confirmation didn't match password");
            bindingResult.addError(error);
        }

        if(bindingResult.hasErrors()) {
            return "register";
        }

        Reviewer reviewer = reviewerService.registerReviewer(form.getFirstName(), form.getLastName(), form.getUsername(), form.getPassword(), LocalDate.now(), form.isAdmin());

        if(form.isAdmin()) {
            return "redirect:/users/"+reviewer.getId();
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("admin/registerRestaurant/process")
    public String formRestaurantProcess(@Valid @ModelAttribute("form") CreateRestaurantForm form, BindingResult restaurantBinding) {
        if (restaurantRepo.findByNameIgnoreCase(form.getRestaurantName()).isPresent()) {
            FieldError error = new FieldError("form", "restaurantName", "Name is already in use");
            restaurantBinding.addError(error);
        }

        if(restaurantBinding.hasErrors()) {
            return "registerRestaurant";
        }

        restaurantService.registerRestaurant(form.getRestaurantName());
        return "redirect:/admin/registerRestaurant/form";
    }

    @PostMapping("admin/registerFoodItem/process")
    public String formRegisterFoodProcess(@Valid @ModelAttribute("form") CreateFoodItemForm form) {
        foodItemService.register(form.getFoodItemName(), form.getDesc(), "", "", restaurantRepo.findByNameIgnoreCase(form.getRestaurantName()).get().getId());
        return "redirect:/admin/registerFoodItem/form";
    }

}
