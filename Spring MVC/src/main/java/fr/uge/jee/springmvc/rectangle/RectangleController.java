package fr.uge.jee.springmvc.rectangle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RectangleController {


    @GetMapping("/rectangle")
    public String rectangleForm() {
        return "rectangle-form";
    }


    @PostMapping("/rectangle")
    public String processForm(@ModelAttribute("rectangle-model") Rectangle rectangle,
                              BindingResult result,
                              Model model){
        model.addAttribute("area", rectangle.area());
        if (result.hasErrors()){
            return "error";
        }
        if (rectangle.getWidth()<0 || rectangle.getHeight()<0){
            return "error";
        }
        return "rectangle-result";
    }
}
