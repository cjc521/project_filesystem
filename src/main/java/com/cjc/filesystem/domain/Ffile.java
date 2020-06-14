package com.cjc.filesystem.domain;

import java.io.Serializable;
import java.util.Date;

public class Ffile implements Serializable {
    private int fid;
    private String fname;
    private String fdescribe;
    private String uploadTime;
    private int tid;
    private String savename;
    public Ffile() {
    }

    public Ffile(int fid, String fname, String fdescribe, String uploadTime, int tid, String savename) {
        this.fid = fid;
        this.fname = fname;
        this.fdescribe = fdescribe;
        this.uploadTime = uploadTime;
        this.tid = tid;
        this.savename = savename;
    }

    @Override
    public String toString() {
        return "Ffile{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", fdescribe='" + fdescribe + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", tid=" + tid +
                ", savename='" + savename + '\'' +
                '}';
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFdescribe() {
        return fdescribe;
    }

    public void setFdescribe(String fdescribe) {
        this.fdescribe = fdescribe;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
