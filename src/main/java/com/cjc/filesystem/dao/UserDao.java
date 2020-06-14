package com.cjc.filesystem.dao;

import com.cjc.filesystem.domain.User;

import java.util.List;

public interface UserDao {
    User queryByName(String name);
    User queryByNameAndPassword(String name,String password);
    boolean saveUser(User user);
    void updateUser(User user);
    int totalCount();
    List<User> queryUser(int start,int end);
    void changeScore(int id ,int score);
    void deleterUser(int id);
}
