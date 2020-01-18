package cdu.lincnaqiang.bookshop.web;


import cdu.lincnaqiang.bookshop.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class SearchController {
    final
    ProductService productService;
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("search")
    public Object search( String keyword){
        if(null == keyword)
            keyword = "";
        return productService.search(keyword);
    }
}
