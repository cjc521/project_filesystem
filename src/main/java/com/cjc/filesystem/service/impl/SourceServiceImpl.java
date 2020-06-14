package com.cjc.filesystem.service.impl;

import com.cjc.filesystem.dao.SourceDao;
import com.cjc.filesystem.dao.impl.SourceDaoImpl;
import com.cjc.filesystem.domain.Ffile;
import com.cjc.filesystem.domain.Ftype;
import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.service.SourceService;

import java.util.List;

public class SourceServiceImpl implements SourceService {
    private SourceDao dao=new SourceDaoImpl();
    
    @Override
    public List<Ftype> queryType() {
        return dao.query();
    }

    @Override
    public PageBean<Ffile> querySource(int tid,int currentPage,int pageSize) {
        PageBean<Ffile> pageBean = new PageBean<>();
        int totalCount = dao.querytotalCount(tid);//总记录数
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pageBean.setTotalCount(totalCount);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        //计算limit ？，？
        int start=(currentPage-1) * pageSize;
//        int end=start+pageSize;
        List<Ffile> list = dao.queryList(tid, start, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void sourceUpload(Ffile ffile) {
        dao.saveSource(ffile);

    }

    @Override
    public Ffile downloadSource(int fid) {
        Ffile ffile = dao.querySourceById(fid);
        if(ffile==null||ffile.equals("")){
            return  null;
        }else {
            return ffile;
        }
    }
}
