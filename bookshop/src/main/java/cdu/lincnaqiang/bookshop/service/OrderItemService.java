package cdu.lincnaqiang.bookshop.service;

import cdu.lincnaqiang.bookshop.dao.OrderItemDAO;
import cdu.lincnaqiang.bookshop.pojo.Order;
import cdu.lincnaqiang.bookshop.pojo.OrderItem;
import cdu.lincnaqiang.bookshop.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    final
    OrderItemDAO orderItemDAO;
    public OrderItemService(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public void update(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    public void add(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    public OrderItem get(int id) {
        return orderItemDAO.getOne(id);
    }

    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    public void fill(Order order) {
        List<OrderItem> orderItems = listByOrder(order);
        float amount = 0;
        int sum = 0;
        for (OrderItem oi :orderItems) {
            amount += oi.getNumber() * oi.getProduct().getPrice();
            sum += oi.getNumber();
        }
        order.setAmount(amount);
        order.setOrderItems(orderItems);
        order.setSum(sum);
    }

    public List<OrderItem> listByOrder(Order order) {
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

    public List<OrderItem> listByUser(User user) {
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }
}
