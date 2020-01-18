package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.Category;
import cdu.lincnaqiang.bookshop.pojo.Product;
import cdu.lincnaqiang.bookshop.pojo.Review;
import cdu.lincnaqiang.bookshop.service.CategoryService;
import cdu.lincnaqiang.bookshop.service.ProductService;
import cdu.lincnaqiang.bookshop.service.ReviewService;
import cdu.lincnaqiang.bookshop.util.AliYunOssUtil;
import cdu.lincnaqiang.bookshop.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    final
    ReviewService reviewService;
    final
    AliYunOssUtil aliYunOssUtil;
    final
    ProductService productService;
    final
    CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService, AliYunOssUtil aliYunOssUtil, ReviewService reviewService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.aliYunOssUtil = aliYunOssUtil;
        this.reviewService = reviewService;
    }

    @GetMapping("products")
    public Page<Product> getList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                  @RequestParam(value = "page-size", defaultValue = "5") int pageSize) throws Exception{
        Page<Product> page = productService.getList(pageNum-1,pageSize);
        return page;
    }

    /**
     * 查询某一分类下的所有商品
     * @param cid 分类id
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("categories/{id}/products")
    public Page<Product> getList(@PathVariable("id") int cid,
                                  @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                  @RequestParam(value = "page-size", defaultValue = "5") int pageSize) throws Exception{
        Page<Product> page = productService.getList(cid,pageNum-1,pageSize);
        return page;
    }


    @PostMapping("products")
    public Object add(Product form, MultipartFile file,@RequestParam("cid") int cid) throws Exception {
        String url = aliYunOssUtil.upload(file);
        form.setImage(url);
        Category c = categoryService.get(cid);
        form.setCategory(c);
        productService.add(form);
        return form;
    }

    @DeleteMapping("products/{id}")
    public String delete(@PathVariable("id") int id)  throws Exception {
        productService.delete(id);
        return null;
    }

    @PutMapping("products/{id}")
    public Object update(Product form,MultipartFile file) throws Exception {
        if(file != null) {
            String url = aliYunOssUtil.upload(file);
            form.setImage(url);
        }
        productService.update(form);
        return form;
    }

    @GetMapping("product/{id}")
    public Object product(@PathVariable("id") int id) {
        Product product = productService.get(id);
        List<Review> reviews = reviewService.list(product);
        productService.setReviewNumber(product);

        Map<String,Object> map= new HashMap<>();
        map.put("product", product);
        map.put("reviews", reviews);

        return Result.success(map);
    }
}
