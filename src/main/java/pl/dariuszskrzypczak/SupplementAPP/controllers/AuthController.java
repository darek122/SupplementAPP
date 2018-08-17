package pl.dariuszskrzypczak.SupplementAPP.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dariuszskrzypczak.SupplementAPP.models.forms.RegisterForm;
import pl.dariuszskrzypczak.SupplementAPP.models.services.AuthService;

@Controller
public class AuthController {

    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/register")

    public String register(Model model){
        model.addAttribute("registerForm",new RegisterForm());
    return "register";
    }

    @PostMapping("/register")

    public String register(@ModelAttribute("registerForm")RegisterForm registerForm,
                           Model model){

        if(!authService.tryToRegister(registerForm)){
            model.addAttribute("infoAboutRegister","Email już jest zajęty");
            return "register";
        }
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){
        return "login";

    }
    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        Model model){
        if(!authService.tryToLogin(email,password)) {
            model.addAttribute("infoAboutLogin","Nieprawidłowe Logowanie");
            return "login";
        }
        return "redirect:/category";
    }

}
