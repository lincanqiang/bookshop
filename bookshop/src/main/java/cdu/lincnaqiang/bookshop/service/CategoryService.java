package cdu.lincnaqiang.bookshop.service;

import cdu.lincnaqiang.bookshop.dao.CategoryDAO;
import cdu.lincnaqiang.bookshop.pojo.Category;
import cdu.lincnaqiang.bookshop.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@CacheConfig(cacheNames="categories")
public class CategoryService {

    final CategoryDAO categoryDAO;
    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Cacheable(key="'categories-page-'+#p0+ '-' + #p1")
    public Page<Category> getList(int pageNum,int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        return categoryDAO.findAll(pageable);
    }

    @Cacheable(key="'categories-all'")
    public List<Category> getList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    @CacheEvict(allEntries=true)
    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    @CacheEvict(allEntries=true)
    public void delete(int id) {
        categoryDAO.deleteById(id);
    }

    @Cacheable(key="'categories-one-'+ #p0")
    public Category get(int id) {
        return categoryDAO.getOne(id);
    }

    @CacheEvict(allEntries=true)
    public void update(Category bean) {
        categoryDAO.save(bean);
    }

    public void removeCategoryFromProduct(List<Category> cs) {
        for (Category category : cs) {
            removeCategoryFromProduct(category);
        }
    }

    public void removeCategoryFromProduct(Category category) {
        List<Product> products = category.getProducts();
        if (null != products) {
            for (Product product : products) {
                product.setCategory(null);
            }
        }
    }
}
