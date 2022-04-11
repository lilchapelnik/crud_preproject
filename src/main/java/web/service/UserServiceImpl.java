package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;




    @Override
    public List<User> allUser() {
        return userDAO.allUsers();
    }


    @Override
    @Transactional(readOnly = false)
    public void add(User user) {
        userDAO.add(user);

    }

    @Transactional
    @Override
    public void deleteById(int id) {
        userDAO.delete(userDAO.getById(id));
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }
}
