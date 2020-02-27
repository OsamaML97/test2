/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author daami
 */
public class Classes {
    int idC;
    String name;
     int cap;
    String desc;

    public Classes(int idC, String name, int cap, String desc) {
        this.idC = idC;
        this.cap = cap;
        this.name = name;
        this.desc = desc;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Classes{" + "idC=" + idC + ", cap=" + cap + ", name=" + name + ", desc=" + desc + '}';
    }

}