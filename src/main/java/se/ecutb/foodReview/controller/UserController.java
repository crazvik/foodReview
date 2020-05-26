package se.ecutb.foodReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.ecutb.foodReview.data.ReviewerRepo;
import se.ecutb.foodReview.dto.CreateReviewerForm;
import se.ecutb.foodReview.service.ReviewerService;

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

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }

}
