package pl.dariuszskrzypczak.SupplementAPP.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dariuszskrzypczak.SupplementAPP.models.CategoryEntity;
import pl.dariuszskrzypczak.SupplementAPP.models.forms.CategoryForm;
import pl.dariuszskrzypczak.SupplementAPP.models.repositories.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<CategoryEntity> showAllCategory(){
        return categoryRepository.findAllByOrderByIdDesc();

    }
    public CategoryEntity showAllProducts(int id){
        return categoryRepository.findById(id).get();

    }



}
