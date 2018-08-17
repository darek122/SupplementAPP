package pl.dariuszskrzypczak.SupplementAPP.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.dariuszskrzypczak.SupplementAPP.models.services.CategoryService;

import pl.dariuszskrzypczak.SupplementAPP.models.services.SessionService;

@Controller
public class CategoryController {

    final CategoryService categoryService;
    final SessionService sessionService;


    @Autowired
    public CategoryController(CategoryService categoryService, SessionService sessionService) {
        this.categoryService = categoryService;
        this.sessionService = sessionService;

    }

    @GetMapping("/category")
    public String showAllCategry(Model model){
        if(sessionService.isLogin()) {
            model.addAttribute("categories", categoryService.showAllCategory());
            return "category";
        }
        return "redirect:/login";
    }

    @GetMapping("/category/{id}")
    public String showProducts(@PathVariable("id")int id,
                               Model model){
        model.addAttribute("productKind",categoryService.showAllProducts(id));
        return "showProduct";
    }



    }


