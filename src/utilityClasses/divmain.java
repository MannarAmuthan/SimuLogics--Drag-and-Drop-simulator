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
public class divmain implements Component {
   double LastKnownX=0,LastKnownY=0; 
 double inonex,inoney,intwox,intwoy,inthreex,inthreey,infourx,infoury,infivex,infivey,insixx,insixy,insevenx,inseveny; 
 Circle in1,in2,in3,in4,in5,in6,in7;
 double height=44,length=66;
 String id;
 ConnType Type=ConnType.Numeral;
  //variables section
 double inputone,inputtwo,inputthree,inputfour;
 boolean isSelected=false;
 
 Image img,a,b,c,d,e,f,g,emp;

     
    public divmain(String id) {
        this.id=id;
        in1=new Circle();
        in2=new Circle();
        in3=new Circle();
        in4=new Circle();
        in5=new Circle();
        in6=new Circle();
        in7=new Circle();
        img=new Image("Images/divmain.png");
        
        
        
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
        
        
        inthreex=LastKnownX+length;
        inthreey=(LastKnownY+height)-(height/4);
        
        
        infourx=LastKnownX+length;
        infoury=LastKnownY+(height/4);
        
        in1.setCenterX(inonex);in1.setCenterY(inoney);
        in2.setCenterX(intwox);in2.setCenterY(intwoy);
        in3.setCenterX(inthreex);in3.setCenterY(inthreey);
        in4.setCenterX(infourx);in4.setCenterY(infoury);
        
        
        
        
        
    }

    @Override
    public ArrayList getInputregions() {
       ArrayList<XandY>a=new ArrayList<>();
       a.add(new XandY(in1,type.input,id,0,ConnType.Numeral));
       a.add(new XandY(in2,type.input,id,1,ConnType.Numeral));
       a.add(new XandY(in3,type.output,id,2,ConnType.Numeral));
       a.add(new XandY(in4,type.output,id,3,ConnType.Numeral));

      
       return a;
    }

    @Override
    public void process() {
        
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
              if(no==0){inputone=pocket.get(i).getData();}
              if(no==1){inputtwo=pocket.get(i).getData();}
              if(no==2){inputthree=pocket.get(i).getData();}
              if(no==3){inputfour=pocket.get(i).getData();}
              
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

