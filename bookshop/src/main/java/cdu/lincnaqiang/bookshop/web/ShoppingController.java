package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.Order;
import cdu.lincnaqiang.bookshop.pojo.OrderItem;
import cdu.lincnaqiang.bookshop.pojo.Product;
import cdu.lincnaqiang.bookshop.pojo.User;
import cdu.lincnaqiang.bookshop.service.OrderItemService;
import cdu.lincnaqiang.bookshop.service.OrderService;
import cdu.lincnaqiang.bookshop.service.ProductService;
import cdu.lincnaqiang.bookshop.util.Result;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ShoppingController {
    final
    ProductService productService;
    OrderItemService orderItemService;
    OrderService orderService;
    public ShoppingController(ProductService productService,OrderItemService orderItemService,OrderService orderService) {
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @GetMapping("buy")
    public Object buy(int pid, int num,HttpSession session) {
        return buyOrAddToCart(pid,num,session);
    }

    @GetMapping("addCart")
    public Object addCart(int pid, int num, HttpSession session) {
        buyOrAddToCart(pid,num,session);
        return Result.success();
    }

    private int buyOrAddToCart(int pid, int num, HttpSession session) {
        Product product = productService.get(pid);
        int oiid = 0;
        User user =(User)session.getAttribute("user");
        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUser(user);
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId()==product.getId()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found = true;
                oiid = oi.getId();
                break;
            }
        }
        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUser(user);
            oi.setProduct(product);
            oi.setNumber(num);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return oiid;
    }

    @GetMapping("confirm")
    public Object buy(String[] oiid,HttpSession session){
        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;

        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.get(id);
            total +=oi.getProduct().getPrice()*oi.getNumber();
            orderItems.add(oi);
        }

        session.setAttribute("ois", orderItems);

        Map<String,Object> map = new HashMap<>();
        map.put("orderItems", orderItems);
        map.put("total", total);
        return Result.success(map);
    }

    @PostMapping("createOrder")
    public Object createOrder(@RequestBody Order order, HttpSession session){
        User user =(User)  session.getAttribute("user");
        if(null==user)
            return Result.fail("未登录");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(0,10000);
        order.setOrderNumber(orderCode);
        order.setCreateTime(new Date());
        order.setUser(user);
        order.setStatus(OrderService.waitPay);
        List<OrderItem> ois= (List<OrderItem>)  session.getAttribute("ois");

        float total =orderService.add(order,ois);

        Map<String,Object> map = new HashMap<>();
        map.put("oid", order.getId());
        map.put("total", total);

        return Result.success(map);
    }

    @GetMapping("payed")
    public Object payed(int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayTime(new Date());
        orderService.update(order);
        return Result.success();
    }
}
