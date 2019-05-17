/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

/**
 *
 * @author AMUTHAN
 */

public class DataPocket {
    int serialNo;
    boolean datum;
    double data;
    ConnType type;

 
  DataPocket(int serialNo,double data){
    this.serialNo=serialNo;
    this.data=data;
    type=ConnType.Numeral;
    }
  
    DataPocket(int serialNo,boolean datum){
    this.serialNo=serialNo;
    this.datum=datum;
    type=ConnType.Bool;
    
    };
    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public boolean isDatum() {
        return datum;
    }

    public void setDatum(boolean datum) {
        this.datum = datum;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
    }
    
    
    
    
}
