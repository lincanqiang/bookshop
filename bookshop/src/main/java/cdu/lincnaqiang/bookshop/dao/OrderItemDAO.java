package cdu.lincnaqiang.bookshop.dao;

import cdu.lincnaqiang.bookshop.pojo.Order;
import cdu.lincnaqiang.bookshop.pojo.OrderItem;
import cdu.lincnaqiang.bookshop.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}
