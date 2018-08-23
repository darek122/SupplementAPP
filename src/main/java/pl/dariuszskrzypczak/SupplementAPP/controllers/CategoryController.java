package pl.dariuszskrzypczak.SupplementAPP.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dariuszskrzypczak.SupplementAPP.models.forms.ProductForm;
import pl.dariuszskrzypczak.SupplementAPP.models.services.CategoryService;


import pl.dariuszskrzypczak.SupplementAPP.models.services.ProductService;
import pl.dariuszskrzypczak.SupplementAPP.models.services.SessionService;

@Controller
public class CategoryController {

    final CategoryService categoryService;
    final SessionService sessionService;
    final ProductService productService;


    @Autowired
    public CategoryController(CategoryService categoryService, SessionService sessionService, ProductService productService) {
        this.categoryService = categoryService;
        this.sessionService = sessionService;

        this.productService = productService;
    }

    @GetMapping("/category")
    public String showAllCategry(Model model) {
        if (sessionService.isLogin()) {
            model.addAttribute("categories", categoryService.showAllCategory());
            return "category";
        }
        return "redirect:/login";
    }


    @GetMapping("/category/{id}")
    public String showProducts(@PathVariable("id") int id,
                               Model model) {
        model.addAttribute("productKind", categoryService.showAllProducts(id));
        model.addAttribute("productForm", new ProductForm());
        return "showProduct";
    }

    @PostMapping("/product/{id}")
    public String product(@PathVariable("id") int id,
                          @ModelAttribute("productForm") ProductForm product) {
        if (!sessionService.isLogin()) {
            return "redirect:/login";
        }
        if (sessionService.getAdminEntity().getEmail().equals("admin@admin")) {
            productService.addProduct(product, id);
            return "redirect:/category/" + id;
        }
        return "redirect:/category/" + id;

    }


}