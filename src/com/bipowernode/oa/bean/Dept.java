package com.bipowernode.oa.bean;

import java.util.Objects;

public class Dept {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(deptno, dept.deptno) &&
                Objects.equals(dname, dept.dname) &&
                Objects.equals(loc, dept.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, dname, loc);
    }

    private String deptno;
    private String dname;
    private String loc;

    public Dept() {
    }

    public Dept(String deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    /**
     * 获取
     * @return deptno
     */
    public String getDeptno() {
        return deptno;
    }

    /**
     * 设置
     * @param deptno
     */
    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    /**
     * 获取
     * @return dname
     */
    public String getDname() {
        return dname;
    }

    /**
     * 设置
     * @param dname
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     * 获取
     * @return loc
     */
    public String getLoc() {
        return loc;
    }

    /**
     * 设置
     * @param loc
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String toString() {
        return "Dept{deptno = " + deptno + ", dname = " + dname + ", loc = " + loc + "}";
    }
}
