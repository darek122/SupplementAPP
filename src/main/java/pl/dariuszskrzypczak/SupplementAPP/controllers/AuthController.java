package pl.dariuszskrzypczak.SupplementAPP.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dariuszskrzypczak.SupplementAPP.models.forms.RegisterForm;
import pl.dariuszskrzypczak.SupplementAPP.models.services.AuthService;
import pl.dariuszskrzypczak.SupplementAPP.models.services.SessionService;

@Controller
public class AuthController {

    final AuthService authService;
    final SessionService sessionService;

    @Autowired
    public AuthController(AuthService authService, SessionService sessionService) {
        this.authService = authService;
        this.sessionService = sessionService;
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
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";

    }
    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        Model model) {

        if (authService.tryToLogin(email, password)) {
            return "redirect:/category";
        }
        model.addAttribute("infoAboutLogin", "Nieprawidłowe Logowanie");
        return "index";
    }

    @GetMapping("/logout")
    public String logout(){
        sessionService.setLogin(false);
        sessionService.setUserEntity(null);

        return "redirect:/login";
    }

}
