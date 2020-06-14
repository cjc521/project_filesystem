package com.cjc.filesystem.dao;

import com.cjc.filesystem.domain.Ffile;
import com.cjc.filesystem.domain.Ftype;
import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.domain.User;

import java.util.List;

public interface SourceDao {
    List<Ftype> query();
    int querytotalCount(int tid);
    List<Ffile> queryList(int tid,int start,int end);//查找资源
    void saveSource(Ffile ffile);//增加资源
    Ffile querySourceById(int fid);
}
