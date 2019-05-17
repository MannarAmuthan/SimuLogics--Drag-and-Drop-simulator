/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import javafx.scene.shape.Circle;

/**
 *
 * @author AMUTHAN
 */
enum type{input,output}

public class XandY {
    Circle c;
    type t;
    String id;
    int serialNo;
    ConnType ct;

    public ConnType getCt() {
        return ct;
    }

    public void setCt(ConnType ct) {
        this.ct = ct;
    }

    public XandY(Circle c, type t, String id, int serialNo,ConnType ct) {
        this.c = c;
        this.t = t;
        this.id = id;
        this.serialNo = serialNo;
        this.ct=ct;
    }

     

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public Circle getC() {
        return c;
    }

    public void setC(Circle c) {
        this.c = c;
    }

    public type getT() {
        return t;
    }

    public void setT(type t) {
        this.t = t;
    }

   
    
    
}
