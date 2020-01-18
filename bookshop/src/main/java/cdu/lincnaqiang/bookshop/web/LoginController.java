package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.User;
import cdu.lincnaqiang.bookshop.service.CategoryService;
import cdu.lincnaqiang.bookshop.service.ProductService;
import cdu.lincnaqiang.bookshop.service.UserService;
import cdu.lincnaqiang.bookshop.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {
    final
    UserService userService;
    final
    CategoryService categoryService;
    final
    ProductService productService;

    public LoginController(CategoryService categoryService, ProductService productService, UserService userService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("register")
    public Object register(@RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();
        user.setName(name);
        boolean exist = userService.isExist(name);
        if (exist) {
            String message = "用户名已经被使用,不能使用";
            return Result.fail(message);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.add(user);
        return Result.success();
    }

    @PostMapping("login")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String name = userParam.getName();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, userParam.getPassword());
        try {
            subject.login(token);
            User user = userService.getByName(name);
//          subject.getSession().setAttribute("user", user);
            session.setAttribute("user", user);
            return Result.success();
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return Result.fail(message);
        }
    }
}
