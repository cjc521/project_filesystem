package com.cjc.filesystem.service;

import com.cjc.filesystem.domain.Ffile;
import com.cjc.filesystem.domain.Ftype;
import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.domain.User;

import java.util.List;

public interface SourceService {
    List<Ftype> queryType();
    PageBean<Ffile> querySource(int tid,int currentPage,int pageSize);
    void sourceUpload(Ffile ffile);
    Ffile downloadSource(int fid);
}
