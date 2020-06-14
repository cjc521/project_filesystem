package com.cjc.filesystem.domain;

import java.io.Serializable;

public class Ftype implements Serializable {
    private int tid;
    private String ttype;

    public Ftype(int tid, String ttype) {
        this.tid = tid;
        this.ttype = ttype;
    }

    public Ftype() {
    }

    @Override
    public String toString() {
        return "Ftype{" +
                "tid=" + tid +
                ", ttype='" + ttype + '\'' +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }
}
