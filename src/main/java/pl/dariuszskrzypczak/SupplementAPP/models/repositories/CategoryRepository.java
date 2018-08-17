package pl.dariuszskrzypczak.SupplementAPP.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dariuszskrzypczak.SupplementAPP.models.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
    List<CategoryEntity> findAllByOrderByIdDesc();

}
