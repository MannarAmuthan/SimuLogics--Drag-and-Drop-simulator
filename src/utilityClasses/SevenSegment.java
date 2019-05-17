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
public class SevenSegment implements Component {
   double LastKnownX=0,LastKnownY=0; 
 double inonex,inoney,intwox,intwoy,inthreex,inthreey,infourx,infoury,infivex,infivey,insixx,insixy,insevenx,inseveny; 
 Circle in1,in2,in3,in4,in5,in6,in7;
 double height=44,length=66;
 String id;
 ConnType Type=ConnType.Bool;
  //variables section
 boolean inputone = false,inputtwo=false,inputthree=false,
         inputfour = false,inputfive=false,inputsix=false,inputseven=false;
 boolean isSelected=false;
 
 Image imgmain,a,b,c,d,e,f,g,emp;

     
    public SevenSegment(String id) {
        this.id=id;
        in1=new Circle();
        in2=new Circle();
        in3=new Circle();
        in4=new Circle();
        in5=new Circle();
        in6=new Circle();
        in7=new Circle();
        imgmain=new Image("Images/SevenSegment/SEVENmain.png");
        a=new Image("Images/SevenSegment/SEVENamain.png");
        b=new Image("Images/SevenSegment/SEVENbmain.png");
        c=new Image("Images/SevenSegment/SEVENcmain.png");
        d=new Image("Images/SevenSegment/SEVENdmain.png");
        e=new Image("Images/SevenSegment/SEVENemain.png");
        f=new Image("Images/SevenSegment/SEVENfmain.png");
        g=new Image("Images/SevenSegment/SEVENgmain.png");
        
        
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
      c.drawImage(imgmain, this.LastKnownX, this.LastKnownY, length, height);
      if(inputone){c.drawImage(a, this.LastKnownX, this.LastKnownY, length, height);}
      if(inputtwo){c.drawImage(b, this.LastKnownX, this.LastKnownY, length, height);}
      if(inputthree){c.drawImage(this.c, this.LastKnownX, this.LastKnownY, length, height);}
      if(inputfour){c.drawImage(d, this.LastKnownX, this.LastKnownY, length, height);}
      if(inputfive){c.drawImage(e, this.LastKnownX, this.LastKnownY, length, height);}
      if(inputsix){c.drawImage(f, this.LastKnownX, this.LastKnownY, length, height);}
      if(inputseven){c.drawImage(g, this.LastKnownX, this.LastKnownY, length, height);}
     
      
      
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
        intwoy=LastKnownY+(height/2);
        intwox=LastKnownX;
        in2.setCenterX(intwox);
        in2.setCenterY(intwoy);
        
        inoney=LastKnownY+(height/4);
        inthreey=(LastKnownY+height)-(height/4);
        inonex=LastKnownX;
        inthreex=LastKnownX;
        
        infourx=LastKnownX+(length/2);
        infoury=LastKnownY+height;
        
        infivex=LastKnownX+length;
        infivey=(LastKnownY+height)-(height/4);
        
        insixx=LastKnownX+length;
        insixy=LastKnownY+(height/2);
        
        insevenx=LastKnownX+length;
        inseveny=LastKnownY+(height/4);
        
        in1.setCenterX(inonex);in1.setCenterY(inoney);
        in3.setCenterX(inthreex);in3.setCenterY(inthreey);
        in4.setCenterX(infourx);in4.setCenterY(infoury);
        in5.setCenterX(infivex);in5.setCenterY(infivey);
        in6.setCenterX(insixx);in6.setCenterY(insixy);
        in7.setCenterX(insevenx);in7.setCenterY(inseveny);
        
        
        
        
        
    }

    @Override
    public ArrayList getInputregions() {
       ArrayList<XandY>a=new ArrayList<>();
       a.add(new XandY(in1,type.input,id,0,ConnType.Bool));
       a.add(new XandY(in2,type.input,id,1,ConnType.Bool));
       a.add(new XandY(in3,type.input,id,2,ConnType.Bool));
       a.add(new XandY(in4,type.input,id,3,ConnType.Bool));
       a.add(new XandY(in5,type.input,id,4,ConnType.Bool));
       a.add(new XandY(in6,type.input,id,5,ConnType.Bool));
       a.add(new XandY(in7,type.input,id,6,ConnType.Bool));

      
       return a;
    }

    @Override
    public void process() {
         System.out.println(inputone);
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
              if(no==1){inputtwo=pocket.get(i).isDatum();}
              if(no==2){inputthree=pocket.get(i).isDatum();}
              if(no==3){inputfour=pocket.get(i).isDatum();}
              if(no==4){inputfive=pocket.get(i).isDatum();}
              if(no==5){inputsix=pocket.get(i).isDatum();}
              if(no==6){inputseven=pocket.get(i).isDatum();}
              
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

