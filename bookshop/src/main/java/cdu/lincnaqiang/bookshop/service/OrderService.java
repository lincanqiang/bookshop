package cdu.lincnaqiang.bookshop.service;

import cdu.lincnaqiang.bookshop.dao.OrderDAO;

import cdu.lincnaqiang.bookshop.pojo.Order;
import cdu.lincnaqiang.bookshop.pojo.OrderItem;
import cdu.lincnaqiang.bookshop.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class OrderService {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    final
    OrderItemService orderItemService;
    final
    OrderDAO orderDAO;
    public OrderService(OrderDAO orderDAO, OrderItemService orderItemService) {
        this.orderDAO = orderDAO;
        this.orderItemService = orderItemService;
    }

    public Page<Order> getList(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        return orderDAO.findAll(pageable);
    }

    public void removeOrderFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeOrderFromOrderItem(order);
        }
    }

    private void removeOrderFromOrderItem(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    public void add(Order order) {
        orderDAO.save(order);
    }

    public Order get(int oid) {
        return orderDAO.getOne(oid);
    }

    public void update(Order bean) {
        orderDAO.save(bean);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackForClassName ="Exception")
    public float add(Order order, List<OrderItem> ois) {
        float total = 0;
        add(order);

        if(false)
            throw new RuntimeException();

        for (OrderItem oi: ois) {
            oi.setOrder(order);
            orderItemService.update(oi);
            total+=oi.getProduct().getPrice()*oi.getNumber();
        }
        return total;
    }


    public List<Order> listByUserWithoutDelete(User user) {
        List<Order> orders = listByUserAndNotDeleted(user);
        orderItemService.fill(orders);
        return orders;
    }

    public List<Order> listByUserAndNotDeleted(User user) {
        return orderDAO.findByUserAndStatusNotOrderByIdDesc(user, OrderService.delete);
    }

}

