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
public class pushbutton implements Component {
    double LastKnownX=0,LastKnownY=0;
 double inonex,inoney,intwox,intwoy,outx,outy;
 double height=44,length=66;
 Circle out; 
 boolean output = false;
String id;
ConnType Type=ConnType.Bool;
Image img; 
 boolean isSelected=false;
     
      //variables section

 
    public pushbutton (String id) {
        this.id=id;
        img=new Image("Images/PUSHBUTTONmain.png");
        out=new Circle();
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
       if(output){
      c.setFill(Color.GREEN);
      c.fillRect(LastKnownX+(height/2), LastKnownY+(length/3), length/4, height/4);
      }
      else{
      c.setFill(Color.RED);
      c.fillRect(LastKnownX+(height/2), LastKnownY+(length/3), length/4, height/4);
      }

      setInputregions();
      return c;
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public void setInputregions() {
       
        
        outy=LastKnownY+(height/2);
        outx=LastKnownX+length; 
        
        
        out.setCenterX(outx);out.setCenterY(outy);
        
        
    }

    @Override
    public ArrayList getInputregions() {
       ArrayList<XandY>a=new ArrayList<>();
      
       a.add(new XandY(out,type.output,id,0,ConnType.Bool));
       return a;
    }

    @Override
    public void process() {
  // System.out.println(output);
    
    }

    @Override
    public void onClick() {
        output=!output;
   }
    
      public ArrayList getData() {
        ArrayList<DataPocket> pocket=new ArrayList<>();
        
        pocket.add(new DataPocket(0,output));
        return pocket;
   }

    public void setData(ArrayList<DataPocket> pocket) {
        for(int i=0;i<pocket.size();i++){
              int no=pocket.get(i).getSerialNo();
              
              if(no==0){output=pocket.get(i).isDatum();}
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
