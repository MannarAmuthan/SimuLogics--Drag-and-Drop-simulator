/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author AMUTHAN
 */
public class orgate implements Component {

 double LastKnownX=0,LastKnownY=0; 
 double inonex,inoney,intwox,intwoy,outx,outy; 
 Circle in1,in2,out;
 double height=44,length=66;
 String id;
 ConnType Type=ConnType.Bool;
 
  boolean isSelected=false;
 
  //variables section
 boolean inputone,inputtwo;
 boolean output;
 Image img;
     
    public orgate(String id) {
        this.id=id;
        in1=new Circle();
        in2=new Circle();
        out=new Circle();
        img=new Image("Images/orgatemain.png");
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

    @Override
    public GraphicsContext getContext(GraphicsContext c) {
       if(isSelected){
        c.setFill(Color.BISQUE);
        c.fillRect(this.LastKnownX,this.LastKnownY,length, height);
      }
      c.drawImage(img, this.LastKnownX, this.LastKnownY, length,height);

      setInputregions();
      return c;
    }

    @Override
    public String getName() {
        return id ;
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
    public void setInputregions() {
        inoney=LastKnownY+(height/4);
        intwoy=(LastKnownY+height)-(height/4);
        inonex=LastKnownX;
        intwox=LastKnownX;
        
        outy=LastKnownY+(height/2);
        outx=LastKnownX+length;
        in1.setCenterX(inonex);in2.setCenterX(intwox);
        in1.setCenterY(inoney);in2.setCenterY(intwoy);
        out.setCenterX(outx);out.setCenterY(outy);
        
        
        
    }

    @Override
    public ArrayList getInputregions() {
       ArrayList<XandY>a=new ArrayList<>();
       a.add(new XandY(in1,type.input,id,0,ConnType.Bool));
       a.add(new XandY(in2,type.input,id,1,ConnType.Bool));
       a.add(new XandY(out,type.output,id,2,ConnType.Bool));
       return a;
    }

    @Override
    public void process() {
   output=inputone|inputtwo;
    
    }

    @Override
    public void onClick() {
        
       }

     public ArrayList getData() {
        ArrayList<DataPocket> pocket=new ArrayList<>();
        pocket.add(new DataPocket(0,inputone));
        pocket.add(new DataPocket(1,inputtwo));
        pocket.add(new DataPocket(2,output));
        return pocket;
   }

    public void setData(ArrayList<DataPocket> pocket) {
        for(int i=0;i<pocket.size();i++){
              int no=pocket.get(i).getSerialNo();
              if(no==0){inputone=pocket.get(i).isDatum();}
              if(no==1){inputtwo=pocket.get(i).isDatum();}
              if(no==2){output=pocket.get(i).isDatum();}
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
