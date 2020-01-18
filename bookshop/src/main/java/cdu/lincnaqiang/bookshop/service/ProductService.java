package cdu.lincnaqiang.bookshop.service;

import cdu.lincnaqiang.bookshop.dao.ProductDAO;
import cdu.lincnaqiang.bookshop.pojo.Category;
import cdu.lincnaqiang.bookshop.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    final ProductDAO productDAO;
    final CategoryService categoryService;
    final ReviewService reviewService;

    public ProductService(ProductDAO productDAO, CategoryService categoryService,ReviewService reviewService) {
        this.productDAO = productDAO;
        this.categoryService = categoryService;
        this.reviewService = reviewService;
    }

    public Page<Product> getList(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        return productDAO.findAll(pageable);
    }

    public Page<Product> getList(int cid,int pageNum, int pageSize) {
        Category category = categoryService.get(cid);
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        return productDAO.findByCategory(category,pageable);
    }

    public List<Product> getList(int cid){
        Category category = categoryService.get(cid);
        return productDAO.findByCategory(category);
    }

    public void add(Product bean) {
        productDAO.save(bean);
    }

    public void delete(int id) {
        productDAO.deleteById(id);
    }

    public Product get(int id) {
        return productDAO.getOne(id);
    }

    public void update(Product bean) {
        productDAO.save(bean);
    }

    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }
    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        category.setProducts(products);
    }
    public List<Product> listByCategory(Category category){
        return productDAO.findTop4ByCategoryOrderById(category);
    }

    public void setReviewNumber(Product product) {
        int reviewCount = reviewService.getCount(product);
        product.setReviewCount(reviewCount);

    }


    public List<Product> search(String keyword) {
        List<Product> products =productDAO.findByNameLike("%"+keyword+"%");
        return products;
    }


}
