/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author AMUTHAN 
 */
  enum ConnType{Bool,Numeral}
interface Component  {
    double getLastKnownX();    
    double getLastKnownY();
    void setLastKnownX(double x);
    void setLastKnownY(double y);
    void setInputregions();
    
    double getStandardLength();
    void setStandardLength(double standardLength);
    double getStandardHeight();
    void setStandardHeight(double standardHeight);
      
    void process();
    void onClick();
    ArrayList getData();
    void setData(ArrayList<DataPocket> pocket);
    ArrayList getInputregions();
    GraphicsContext getContext(GraphicsContext c);
    String getName();
    
    ConnType getType();
    boolean isIsSelected();
    void setIsSelected(boolean isSelected);
    
    
    
    
}
