/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author AMUTHAN
 */
public class led implements Component {
   double LastKnownX=0,LastKnownY=0; 
 double inonex,inoney,intwox,intwoy,outx,outy; 
 Circle in1;
 double height=44,length=66;
 String id;
 ConnType Type=ConnType.Bool;
  //variables section
 boolean inputone = false;
 Image img;
 
  boolean isSelected=false;

     
    public led(String id) {
        this.id=id;
        in1=new Circle();
        img=new Image("Images/LEDmain.png");
        
    }
    
    @Override
    public double getLastKnownX() {
         return LastKnownX;
    }

    @Override
    public double getLastKnownY() {
         return LastKnownY;
    }

    @Override
    public void setLastKnownX(double x) {
        this.LastKnownX = x;
    }

    @Override
    public void setLastKnownY(double y) {
        this.LastKnownY =y;
    }
     public double getStandardLength() {
        return length;
    }

    public void setStandardLength(double standardLength) {
        this.length = standardLength;
    }

    public double getStandardHeight() {
        return height;
    }

    public void setStandardHeight(double standardHeight) {
        this.height = standardHeight;
    }

    @Override
    public GraphicsContext getContext(GraphicsContext c) {
      if(isSelected){
        c.setFill(Color.BISQUE);
        c.fillRect(this.LastKnownX,this.LastKnownY,length, height);
      }
      c.drawImage(img, this.LastKnownX, this.LastKnownY, length, height);
      
      if(inputone){
      c.setFill(Color.GREEN);
      c.fillRect(LastKnownX+(height/3), LastKnownY+(length/4), length/4, height/4);
      }
      else{
      c.setFill(Color.RED);
      c.fillRect(LastKnownX+(height/3), LastKnownY+(length/4), length/4, height/4);
      }
      

      setInputregions();
      return c;
    }

    @Override
    public String getName() {
        return id ;
    }

    @Override
    public void setInputregions() {
        inoney=LastKnownY+(height/2);
        
        inonex=LastKnownX;
        
        in1.setCenterX(inonex);
        in1.setCenterY(inoney);
        
        
        
        
    }

    @Override
    public ArrayList getInputregions() {
       ArrayList<XandY>a=new ArrayList<>();
       a.add(new XandY(in1,type.input,id,0,ConnType.Bool));
      
       return a;
    }

    @Override
    public void process() {
         //System.out.println(inputone);
   }

    @Override
    public void onClick() {
         
  }
    
     public ArrayList getData() {
        ArrayList<DataPocket> pocket=new ArrayList<>();
        pocket.add(new DataPocket(0,inputone));
        
        return pocket;
   }

    public void setData(ArrayList<DataPocket> pocket) {
        for(int i=0;i<pocket.size();i++){
              int no=pocket.get(i).getSerialNo();
              if(no==0){inputone=pocket.get(i).isDatum();}
              
        }
        process();
   } 
    
 @Override
    public ConnType getType() {
        return Type;
   }
     public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
