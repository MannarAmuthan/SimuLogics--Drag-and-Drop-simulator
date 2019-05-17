 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author AMUTHAN
 */
enum mode{move,wiring,simulation,delete,add}

public class CanvasDraw   {
    Canvas canvas; 
    GraphicsContext gc;
    double xc,yc,xr,yr,xl,yl;
    mode t;
    HashMap<String,Component> items;
    XandY start,end,lastlydetected;
    ArrayList<Connections> wires;
    double roundRadius;
     boolean print=true;
     ArrayList<Connections> newWires;

    public CanvasDraw() {
     canvas=new Canvas();
     gc=canvas.getGraphicsContext2D();
     gc.setStroke(Color.BLACK);
      
     canvas.setWidth(1000);
     canvas.setHeight(1000);
     
     gc.setFill(Color.ANTIQUEWHITE);
     gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
     items=new HashMap<>();
     wires=new ArrayList<>();
     newWires=new ArrayList<>();
     
     }
    
    public void setItem(Component c){
      items.put(c.getName(), c);
    }

    public mode getMode() {
        return t;
    }

    public void setMode(mode t) {
        this.t = t;
    }
    
    
     public Canvas redraw(Component c,double x,double y){
     gc.setFill(Color.ANTIQUEWHITE);
     gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());  
      if(!items.containsValue(c)){setItem(c);}   
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight()); 
      c.setLastKnownX(x);
      c.setLastKnownY(y);
      drawAllOther(c);
      gc=c.getContext(gc);
      
      drawCircles();
      drawWires();
      return canvas;
      
     }
     
     //to move circle around all inputs and outputs
     
      public void drawCircles() {
             ArrayList co;
             for(Map.Entry<String,Component> entry:items.entrySet()){
              Component comp=(Component)entry.getValue();
           
              co=comp.getInputregions();
              for(int j=0;j<co.size();j++){
              Circle ci=((XandY)co.get(j)).getC();
              gc.setFill(Color.ORANGERED);
              gc.fillOval(ci.getCenterX()-comp.getStandardHeight()/20,ci.getCenterY()-comp.getStandardHeight()/20, comp.getStandardHeight()/10,comp.getStandardHeight()/10);
              
             
        }
       
     }
     }
      
      
     public void drawWireRound(double x,double y){
       
             ArrayList co;
             for(Map.Entry<String,Component> entry:items.entrySet()){
              Component comp=(Component)entry.getValue();
           
              co=comp.getInputregions();
              for(int j=0;j<co.size();j++){
              XandY point=((XandY)co.get(j)) ; 
              Circle ci=point.getC();
              ci.setRadius(comp.getStandardHeight()/20);
              
              if((Math.sqrt(((x-ci.getCenterX())*(x-ci.getCenterX()))-((y-ci.getCenterY())*(y-ci.getCenterY()))))<=ci.getRadius()*3){
              gc.setFill(Color.TURQUOISE);
              gc.fillOval(ci.getCenterX()-comp.getStandardHeight()/20,ci.getCenterY()-comp.getStandardHeight()/20,comp.getStandardHeight()/10, comp.getStandardHeight()/10);
              lastlydetected=point;
             
              }
              
              else{
              gc.setFill(Color.ORANGERED);
              gc.fillOval(ci.getCenterX()-comp.getStandardHeight()/20,ci.getCenterY()-comp.getStandardHeight()/20,comp.getStandardHeight()/10,comp.getStandardHeight()/10);
              }
              
              if(start!=null){
              gc.setFill(Color.BLUE);
              gc.fillOval(start.getC().getCenterX()-comp.getStandardHeight()/20,start.getC().getCenterY()-comp.getStandardHeight()/20, comp.getStandardHeight()/10, comp.getStandardHeight()/10);
              
              }
              }
      }
     }
     
     void wireCon(){
         cleanAndDraw();
         if(this.getMode()==mode.wiring){
            if(start==null){
              start=lastlydetected;
              
            }
            else{
                end=lastlydetected;
                if(start.getT()==end.getT()){System.out.println("invalid..");start=null;end=null;}
                else if(start.getCt()!=end.getCt()){System.out.println("you are trying to connect two different types..");start=null;end=null;}
                else{
                    
               wires.add(new Connections(start,end));
               //System.out.println(wires.size());
               drawWires();
               //System.out.println(start.getId()+" "+start.getSerialNo()+" "+end.getId()+" "+end.getSerialNo());
               start=null;end=null;
            }}
            
            
        }
     
     }
    

     
     public void drawAllOther(Component c){
         
      for(Map.Entry<String,Component> entry:items.entrySet()){
       Component co=(Component)entry.getValue();
       if(c.equals(co)){}
       else{
       gc=co.getContext(gc);}
       
       }
     }
     
     
     public void cleanAndDraw(){
     gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());    
     for(Map.Entry<String,Component> entry:items.entrySet()){
        Component co=(Component)entry.getValue();
        gc=co.getContext(gc);
        drawCircles();
        } 
        
     
     }
     public Component getSelected(double x,double y){
       for(Map.Entry<String,Component> entry:items.entrySet()){
        
       Component co=(Component)entry.getValue();
        if(new CanvoResearch().selected==co)  
        continue;
//       if((co.getLastKnownX()-10<x&x<co.getLastKnownX()+10)&
//              (co.getLastKnownY()-10<y&y<co.getLastKnownY()+10)){
if((co.getLastKnownX()<x&x<co.getLastKnownX()+co.getStandardLength())&
              (co.getLastKnownY()<y&y<co.getLastKnownY()+co.getStandardHeight())){
           return co;
       }
       }
       return null;
     }
     
     public Connections getSelectedWire(double x,double y){
     for(int i=0;i<wires.size();i++){
              double x1=wires.get(i).getInput().getC().getCenterX();
              double y1=wires.get(i).getInput().getC().getCenterY();
              double x2=wires.get(i).getOutput().getC().getCenterX();
              double y2=wires.get(i).getOutput().getC().getCenterY();
              double m=(y1-y2)/(x1-x2);
              double m1=(y2-y)/(x2-x);
              System.out.println(m+" "+m1);
              double big,small;
              
              if(x2>x1){big=x2;small=x1;}else{big=x1;small=x2;}
               
               
              if(Double.parseDouble(new DecimalFormat("##.#").format(m))==
                      Double.parseDouble(new DecimalFormat("##.#").format(m1))){
                  if(big>=x&x>=small){
                   return wires.get(i);}
              }
     }
        return null;
     }

   void drawWires(){
                
                cleanAndDraw();
                
                gc.setStroke(Color.BLACK);
                for(int i=0;i<wires.size();i++){
                Connections c=wires.get(i);
                double x=c.getInput().getC().getCenterX();
                double y=c.getInput().getC().getCenterY();
                double xx=c.getOutput().getC().getCenterX();
                double yy=c.getOutput().getC().getCenterY();
                gc.setLineWidth(10/20);
                gc.strokeLine(x,y, xx, yy);
                
                
                }
                
   
   }
   
   void buildGraph(){
       Graph graph=new Graph();
         for(int i=0;i<wires.size();i++){
          Connections c=wires.get(i);
          XandY in=c.getInput();
          XandY out=c.getOutput();
          CompNode pa=graph.getNode(items.get(in.getId()));
          CompNode ch=graph.getNode(items.get(out.getId()));
          pa.addChild(out);
          ch.addParent(in);
         
         }
         
          ArrayList<Component> al=graph.listRootsandChild(items);
          for(int i=0;i<al.size();i++){
            for(int j=0;j<wires.size();j++){
              if(wires.get(j).getInput().getId().equals(al.get(i).getName())){
                  if(!newWires.contains(wires.get(j)))
                         newWires.add(wires.get(j));
              }
              
            }
          }
          
         wires=newWires;
   }

   synchronized void simulate() {
      
        if(this.getMode()==mode.simulation){
            //System.out.println("simulation");
              for(int i=0;i<wires.size();i++){
              Connections c=wires.get(i);
                XandY input,output;
                if(c.getInput().getT()==type.input){input=c.getInput();output=c.getOutput();}
                else{input=c.getOutput();output=c.getInput();}
                
                String incompid=input.getId();
                String outcompid=output.getId();
                Component in=items.get(incompid);
                Component out=items.get(outcompid);
                
                ArrayList<DataPocket> inputPocket=out.getData();
                
                DataPocket dp=null;
                if(in.getType()==ConnType.Numeral){
                dp=new DataPocket(input.getSerialNo(),(inputPocket.get(output.getSerialNo())).getData());}
                if(in.getType()==ConnType.Bool){
                    //System.out.println(output.getSerialNo());
                    if(print)
                    System.out.println("from  "+out.getName()+" "+(inputPocket.get(output.getSerialNo())).isDatum()+" to "+input.getSerialNo());
                    dp=new DataPocket(input.getSerialNo(),(inputPocket.get(output.getSerialNo())).isDatum());
               // System.out.println("after data entered "+dp.getSerialNo()+"  "+dp.isDatum());
                }
                ArrayList<DataPocket>data=new ArrayList<>();
                
                
                data.add(dp);
                in.setData(data);
                
              }
              print=!print;
              drawWires();
             
        }
   }
    
     
     }
     

    

