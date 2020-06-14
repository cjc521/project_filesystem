package com.cjc.filesystem.dao.impl;

import com.cjc.filesystem.dao.SourceDao;
import com.cjc.filesystem.dao.UserDao;
import com.cjc.filesystem.domain.Ffile;
import com.cjc.filesystem.domain.Ftype;
import com.cjc.filesystem.domain.User;
import com.cjc.filesystem.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SourceDaoImpl implements SourceDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Ftype> query() {
        String sql = "select * from c_type";
        List<Ftype> ftypes = template.query(sql, new BeanPropertyRowMapper<Ftype>(Ftype.class));
        return ftypes;
    }

    @Override
    public int querytotalCount(int tid) {
        if(tid!=0){
            String sql = "select count(fid) from c_file where tid=?";
            return template.queryForObject(sql, Integer.class, tid);
        }else{
            String sql = "select count(fid) from c_file";
            return template.queryForObject(sql, Integer.class);
        }

    }

    @Override
    public List<Ffile> queryList(int tid, int start, int pageSize) {
        if(tid!=0){
            String sql = "select * from c_file where tid=? order by fid desc limit ?,?";
            List<Ffile> list = template.query(sql, new BeanPropertyRowMapper<Ffile>(Ffile.class), tid, start, pageSize);
            return list;
        }else{
            String sql = "select * from c_file order by fid desc limit ?,?";
            List<Ffile> list = template.query(sql, new BeanPropertyRowMapper<Ffile>(Ffile.class), start,pageSize);
            return list;
        }
    }

    @Override
    public void saveSource(Ffile ffile) {
        try {
            String sql = "insert into c_file(fname,fdescribe,uploadTime,tid,savename) values(?,?,?,?,?)";
            template.update(sql, ffile.getFname(), ffile.getFdescribe(), ffile.getUploadTime(), ffile.getTid(),ffile.getSavename());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ffile querySourceById(int fid) {
        String sql="select * from c_file where fid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Ffile>(Ffile.class),fid);
    }
}
