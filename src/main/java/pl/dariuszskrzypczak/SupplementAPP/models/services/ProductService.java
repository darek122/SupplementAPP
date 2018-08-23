package pl.dariuszskrzypczak.SupplementAPP.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dariuszskrzypczak.SupplementAPP.models.CategoryEntity;
import pl.dariuszskrzypczak.SupplementAPP.models.ProductEntity;
import pl.dariuszskrzypczak.SupplementAPP.models.forms.ProductForm;
import pl.dariuszskrzypczak.SupplementAPP.models.repositories.ProductRepository;


@Service
public class ProductService {

        final SessionService sessionService;
        final ProductRepository productRepository;

    @Autowired
    public ProductService(SessionService sessionService, ProductRepository productRepository) {
        this.sessionService = sessionService;
        this.productRepository = productRepository;
    }


    public void addProduct(ProductForm productForm, int id){
        ProductEntity productEntity= createProductEntity(productForm, id);
        productRepository.save(productEntity);
    }

    private ProductEntity createProductEntity(ProductForm productForm, int id){
        ProductEntity productEntity=new ProductEntity();
        CategoryEntity categoryEntity= new CategoryEntity();
        categoryEntity.setId(id);

        productEntity.setName(productForm.getName());
        productEntity.setCategory(categoryEntity);
        productEntity.setAdmin(sessionService.getAdminEntity());
      return productEntity;

    }

    }


