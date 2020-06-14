package com.cjc.filesystem.dao.impl;

import com.cjc.filesystem.dao.UserDao;
import com.cjc.filesystem.domain.User;
import com.cjc.filesystem.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
//    private User user=null;
    @Override
    public User queryByName(String name) {
        User user=null;
        String sql="select * from c_user where name=?";
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), name);
        return user;
    }

    @Override
    public User queryByNameAndPassword(String name,String password) {
         String sql="select * from c_user where name=? and password=?";
         User user=null;
        try {
           user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), name, password);
        } catch (DataAccessException e) {
            return user;
        }
        return user;
    }

    @Override
    public boolean saveUser(User user) {
//        设置默认权限 0（普通用户）
        try {
            user.setScore(0);
            String sql="insert into c_user(name,password,score) values(?,?,?)";
            template.update(sql, user.getName(),user.getPassword(),user.getScore());
        } catch (DataAccessException e) {
//            e.printStackTrace();
            return false;
        }
         return true;
    }

    @Override
    public void updateUser(User user) {
        String sql="update c_user set name=?,password=?,score=? where id=?";
        template.update(sql,user.getName(),user.getPassword(),user.getScore(),user.getId());
    }

    @Override
    public int totalCount() {
        String sql="select count(id) from c_user ";
        return template.queryForObject(sql,Integer.class);
    }

    @Override//查询普通用户
    public List<User> queryUser(int start, int pageSize) {
        String sql="select * from c_user limit ?,?";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, pageSize);
        return users;
    }

    @Override
    public void changeScore(int id,int score) {
        String sql="";
        if(score==0){
            sql+="update c_user set score=1 where id=?";
        }else {
            sql+="update c_user set score=0 where id=?";
        }
        template.update(sql,id);
    }

    @Override
    public void deleterUser(int id) {
        String sql="delete from c_user where id=?";
        template.update(sql,id);
    }
}
