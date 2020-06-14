 package com.cjc.filesystem.service.impl;

import com.cjc.filesystem.dao.UserDao;
import com.cjc.filesystem.dao.impl.UserDaoImpl;
import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.domain.User;
import com.cjc.filesystem.service.UserService;

import java.util.List;

 public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();

    @Override
    public boolean registerUser(User user) {
        return dao.saveUser(user);
    }

    @Override
    public User login(String name, String password) {
        User user = dao.queryByNameAndPassword(name, password);
            return user;

    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public PageBean<User> queryUser(int currentPage,int pageSize) {
        int totalCount = dao.totalCount();
        int totalPage=totalCount % pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
        PageBean<User> userPageBean = new PageBean<User>();
        userPageBean.setTotalCount(totalCount);
        userPageBean.setTotalPage(totalPage);
        userPageBean.setPageSize(pageSize);
        int start=(currentPage-1)*pageSize;
//        int end=start+pageSize;
        List<User> users = dao.queryUser(start, pageSize);

        userPageBean.setList(users);
        return userPageBean;
    }

     @Override
     public void deleterUser(int id) {
         dao.deleterUser(id);
     }

     @Override
     public void changeScore(int id,int score) {
         dao.changeScore(id,score);
     }
 }
