package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.Category;
import cdu.lincnaqiang.bookshop.service.CategoryService;
import cdu.lincnaqiang.bookshop.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PreviewController {
    final
    ProductService productService;
    final
    CategoryService categoryService;
    public PreviewController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    /**
     * 前台首页的分页预览。返回所有分类以及每一分类下的5件商品
     * @return
     */
    @GetMapping("preview")
    public Object getPreview() {
        List<Category> cs= categoryService.getList();
        productService.fill(cs);
        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }
}

