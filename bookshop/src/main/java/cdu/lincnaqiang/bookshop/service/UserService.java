package cdu.lincnaqiang.bookshop.service;

import cdu.lincnaqiang.bookshop.dao.UserDAO;
import cdu.lincnaqiang.bookshop.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name,password);
    }

    public boolean isExist(String name) {
        User user = getByName(name);
        return null!=user;
    }

    public User getByName(String name) {
        return userDAO.getByName(name);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public Page<User> getList(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        return userDAO.findAll(pageable);
    }
}
