package com.cjc.filesystem.service;

import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.domain.User;

public interface UserService {
    boolean registerUser(User user);
    User login(String name ,String password);
    void updateUser(User user);
    PageBean<User> queryUser(int currentPage,int pageSize);
    void deleterUser(int id);
    void changeScore(int id,int score);
}
