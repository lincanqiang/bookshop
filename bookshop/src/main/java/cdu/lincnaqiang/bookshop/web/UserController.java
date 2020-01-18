package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.User;
import cdu.lincnaqiang.bookshop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    final
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public Page<User> getList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                 @RequestParam(value = "page-size", defaultValue = "5") int pageSize) throws Exception{
        Page<User> page = userService.getList(pageNum-1,pageSize);
        return page;
    }
}
