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
public class notgate implements Component {
    double LastKnownX=0,LastKnownY=0;
 double inonex,inoney,intwox,intwoy,outx,outy;
 double height=44,length=66;
 Circle in,out; 
 boolean output = false,inputone;
String id;
ConnType Type=ConnType.Bool;
Image img;

boolean isSelected=false;
      //variables section

 
    public notgate (String id) {
        this.id=id;
        in=new Circle();
        out=new Circle();
        img=new Image("Images/notgatemain.png");
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
      c.drawImage(img, this.LastKnownX, this.LastKnownY, length, height);
      

      setInputregions();
      return c;
    }

    @Override
    public String getName() {
        return id;
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
       
        
        outy=LastKnownY+(height/2);
        outx=LastKnownX+length; 
        
        inoney=LastKnownY+(height/2);
        inonex=LastKnownX;
        
        in.setCenterX(inonex);
        in.setCenterY(inoney);
        
        out.setCenterX(outx);out.setCenterY(outy);
        
        
    }

    @Override
    public ArrayList getInputregions() {
       ArrayList<XandY>a=new ArrayList<>();
       a.add(new XandY(in,type.input,id,0,ConnType.Bool));
       a.add(new XandY(out,type.output,id,1,ConnType.Bool));
       return a;
    }

    @Override
    public void process() {
        if(inputone){output=false;}
        else{output=true;}
    }

    @Override
    public void onClick() {
        
        
   }
    
      public ArrayList getData() {
        ArrayList<DataPocket> pocket=new ArrayList<>();
        pocket.add(new DataPocket(0,inputone));
        pocket.add(new DataPocket(1,output));
        return pocket;
   }

    public void setData(ArrayList<DataPocket> pocket) {
        for(int i=0;i<pocket.size();i++){
              int no=pocket.get(i).getSerialNo();
              
              if(no==0){inputone=pocket.get(i).isDatum();}
              if(no==1){output=pocket.get(i).isDatum();}
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
