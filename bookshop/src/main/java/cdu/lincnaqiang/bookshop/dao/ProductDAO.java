package cdu.lincnaqiang.bookshop.dao;

import cdu.lincnaqiang.bookshop.pojo.Category;
import cdu.lincnaqiang.bookshop.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {
    Page<Product> findByCategory(Category category, Pageable pageable);
    List<Product> findByCategory(Category category);
    List<Product> findTop4ByCategoryOrderById(Category category);
    List<Product> findByNameLike(String keyword);
}
