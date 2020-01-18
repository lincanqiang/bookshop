package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.Category;
import cdu.lincnaqiang.bookshop.service.CategoryService;
import cdu.lincnaqiang.bookshop.util.AliYunOssUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    final AliYunOssUtil aliYunOSSUtil;
    final CategoryService categoryService;
    public CategoryController(CategoryService categoryService, AliYunOssUtil aliYunOSSUtil) {
        this.categoryService = categoryService;
        this.aliYunOSSUtil = aliYunOSSUtil;
    }

    /**
     * 查询分类信息
     * @param pageNum 页数
     * @param pageSize 每页长度
     * @return 分页处理后的category集合
     * @throws Exception
     */
    @GetMapping("categories")
    public Page<Category> getList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                  @RequestParam(value = "page-size", defaultValue = "5") int pageSize) throws Exception{
        Page<Category> page = categoryService.getList(pageNum-1,pageSize);
        return page;
    }

    /**
     * 查询所有分类信息
     * @return 以集合形式返回
     * @throws Exception
     */
    @GetMapping("category")
    public List<Category> getList() throws Exception{
        return categoryService.getList();
    }

    /**
     * 添加分类
     * @param form 前端上传的表单
     * @param file 前端上传的图片
     * @return category对象
     * @throws Exception
     */
    @PostMapping("categories")
    public Object add(Category form,MultipartFile file) throws Exception {
        String url = aliYunOSSUtil.upload(file);
        form.setImage(url);
        categoryService.add(form);
        return form;
    }

    /**
     * 删除分类
     * @param id 分类ID
     * @return null
     * @throws Exception
     */
    @DeleteMapping("categories/{id}")
    public String delete(@PathVariable("id") int id)  throws Exception {
        categoryService.delete(id);
        return null;
    }

    /**
     * 查询
     * @param id
     * @return category对象
     * @throws Exception
     */
    @GetMapping("categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception {
        Category bean=categoryService.get(id);
        return bean;
    }

    /**
     * 更新分类
     * @param form 前端提交表单
     * @param file 前端上传文件
     * @param request 从request取出参数
     * @return category对象
     * @throws Exception
     */
    @PutMapping("categories/{id}")
    public  Object update(Category form,MultipartFile file,HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        form.setName(name);
        if(file!=null) {
            String url = aliYunOSSUtil.upload(file);
            form.setImage(url);
        }
        categoryService.update(form);
        return form;
    }
}


