package in.ashokit.repo;

import in.ashokit.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {

    public ProductCategoryEntity findByCategoryName(String categoryName);
}
