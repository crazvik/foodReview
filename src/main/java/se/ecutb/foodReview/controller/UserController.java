package se.ecutb.foodReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.ecutb.foodReview.data.ReviewerRepo;
import se.ecutb.foodReview.dto.CreateReviewerForm;
import se.ecutb.foodReview.entity.Reviewer;
import se.ecutb.foodReview.service.ReviewerService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UserController {
    private ReviewerRepo reviewerRepo;
    private ReviewerService reviewerService;

    @Autowired
    public UserController(ReviewerRepo reviewerRepo, ReviewerService reviewerService) {
        this.reviewerRepo = reviewerRepo;
        this.reviewerService = reviewerService;
    }

    @GetMapping("users/register/form")
    public String getForm(Model model) {
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

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }

}
