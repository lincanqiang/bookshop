package cdu.lincnaqiang.bookshop.web;

import cdu.lincnaqiang.bookshop.pojo.Order;
import cdu.lincnaqiang.bookshop.service.OrderItemService;
import cdu.lincnaqiang.bookshop.service.OrderService;
import cdu.lincnaqiang.bookshop.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class OrderController {
    final
    OrderService orderService;
    OrderItemService orderItemService;
    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("orders")
    public Page<Order> getList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                  @RequestParam(value = "page-size", defaultValue = "5") int pageSize) throws Exception{
        Page<Order> page = orderService.getList(pageNum-1,pageSize);
        orderItemService.fill(page.getContent());
        orderService.removeOrderFromOrderItem(page.getContent());
        return page;
    }

    @PutMapping("orders/{oid}")
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        Order o = orderService.get(oid);
        o.setDeliverTime(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }
}
