       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;


import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author AMUTHAN
 */
public class CanvoResearch extends Application  {
    CanvasDraw t;
    GraphicsContext gc;
    Canvas m,cim;
    boolean isRec=true;
    ToggleButton move,delete,wire,simulate,stop;
    Component selected;
    boolean released=true;
    Slider slider;
    double thresHold=0;
    ComboBox<String> Components ;
    ListView<String> subComponents,fieldComponents;
    BorderPane bp;
    StackPane forCanvas;
    ArrayList<Component> complist;
    Timer timer;
    Stage clone;
    TimerTask simu;
    Image imgMarker ;
    ImageCursor wireMarker;

    
    
    @Override
    public void start(Stage primaryStage) {
        
        initialiseUI(primaryStage);
        imgMarker=new Image("Images/wiring.png");
        wireMarker=new ImageCursor(imgMarker,imgMarker.getHeight()/2,imgMarker.getWidth()/2);
       
        
        
        
        m.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               
               
               if(t.getMode()==mode.move){
                   m.setCursor(Cursor.CLOSED_HAND);
                    
                   t.start=null;t.end=null;
                   //t.drawWireRound(event.getX(),event.getY());
                       if(released==false){
                        if(selected!=null)
                    t.redraw(selected,event.getX(),event.getY());
                   
               }}
               
               if(t.getMode()==mode.wiring)
               { 
               m.setCursor(wireMarker);
                if(selected!=null){
                        selected.setIsSelected(false);
                        }
               t.drawWires();    
               t.drawWireRound(event.getX(),event.getY());
               
                }
                if(t.getMode()==mode.simulation)
               {
                    if(selected!=null){
                        selected.setIsSelected(false);
                        }
                   m.setCursor(Cursor.DEFAULT);
               
               }
               
 }
         });
        
        m.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(t.getMode()==mode.move){
                    
                    t.start=null;t.end=null;
                    
                    released=!released;
                    Component co=t.getSelected(event.getX(),event.getY());
                    if(co!=null)
                    {   if(selected!=null){
                        selected.setIsSelected(false);
                        }
                    selected=co;
                    selected.setIsSelected(true);
                    t.drawWires();
                    
                    }
                    else {}
                }
                
                
                else if(t.getMode()==mode.wiring){
                    if(selected!=null){
                        selected.setIsSelected(false);
                        }
                        t.wireCon(); 
                     }
                 else if(t.getMode()==mode.delete){
                     Connections c=t.getSelectedWire(event.getX(),event.getY());
                     if(selected!=null){
                        selected.setIsSelected(false);
                        }
                      if(c!=null){
                      t.wires.remove(c);
                      t.drawWires();} 
                      else{System.out.println("null");}
                     
                    }
                 else if(t.getMode()==mode.simulation){
                      if(selected!=null){
                        selected.setIsSelected(false);
                        }
                      Component co=t.getSelected(event.getX(),event.getY());
                    if(co!=null){
                          co.onClick();
                          t.drawWires();
                    }  
                 }
                else{}
                   }
           
            });
        
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
             //selected=null;
             double wid=Double.valueOf(String.valueOf(newValue));
             double hei=Double.valueOf(String.valueOf(newValue))/1.5;
             if(selected!=null){
               selected.setStandardHeight(hei);
               selected.setStandardLength(wid);
             }
             t.cleanAndDraw();
             
             t.drawWires(); 
           }
        });
       
        Scene sc=new Scene(bp);
        primaryStage.setScene(sc);
        clone=primaryStage;
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("SimLogic");
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    
    
    private void initialiseUI(Stage primaryStage) {
        timer=new Timer();
        t=new CanvasDraw();
        complist=new ArrayList<>();
        

        Component a=new andgate("sampleAnd");
        complist.add(a);
        t.setMode(mode.move);
        m=t.redraw(a,t.canvas.getWidth()/2, t.canvas.getHeight()/2);
        
        forCanvas=new StackPane();
        forCanvas.getChildren().add(m);
        forCanvas.setStyle("-fx-background-color:lightgray");
        
        selected=a;
        Components = new ComboBox<>();

        Components.getItems().addAll("boolean", "soonly..");

        Components.getSelectionModel().selectFirst();
        
        subComponents=new ListView();
        subComponents.getItems().clear();
        
        if(Components.getSelectionModel().getSelectedItem()=="boolean"){
        subComponents.getItems().addAll("AND","OR","NAND","NOT","EXOR","EXNOR","LED","PUSH BUTTON","SEVEN SEGMENT");}
        else if(Components.getSelectionModel().getSelectedItem()=="arithmetic"){
            subComponents.getItems().addAll("ADDITION","SUBTRACTION","MULTIPLICATION","DIVITION");
        }
        subComponents.getSelectionModel().selectFirst();
        subComponents.setPrefHeight(100);
        
        fieldComponents=new ListView();
        fieldComponents.getItems().clear();
        fieldComponents.getItems().addAll(""); 
        fieldComponents.getSelectionModel().selectFirst();
        fieldComponents.setPrefHeight(200);
        
         refreshList();
         MenuItem deleteItem=new MenuItem("DELETE");
         MenuItem renameItem=new MenuItem("CHANGE ID");
         MenuItem[] FieldItems = {deleteItem,renameItem};
         

         ContextMenu menu = new ContextMenu(FieldItems);
         fieldComponents.setContextMenu(menu);
         if(fieldComponents.getSelectionModel().getSelectedItem()==null)
             menu.hide();
         
         
         fieldComponents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
               String selectedComp=newValue.toString();
              if(selectedComp!=null){ 
              if(selected!=null){
                 selected.setIsSelected(false);
                 
                 }
               selected=t.items.get(selectedComp);
               selected.setIsSelected(true);
               
               t.drawWires();
              }
            } catch(Exception e){}}
           
        });
         
         
         MenuItem addItem=new  MenuItem("ADD");
         MenuItem[] SubItems = {addItem};
         ContextMenu menu1 = new ContextMenu(SubItems);
         
         
        subComponents.setContextMenu(menu1);
        subComponents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                drawImgHold(gc,newValue.toString());}
                catch(Exception e){}
               
            }
        });

        Components.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue.equals("boolean")){
                 subComponents.getItems().clear();
                 subComponents.getItems().addAll("AND","OR","NAND","NOT","EXOR","EXNOR","LED","PUSH BUTTON","SEVEN SEGMENT"); }
               
                else if(newValue.equals("arithmetic")){
                        subComponents.getItems().clear();
                        subComponents.getItems().addAll("ADDITION","SUBTRACTION","MULTIPLICATION","DIVITION");
                 }  
            }
        });
        slider=new Slider();
        slider.setMin(6);
        slider.setMax(600);
        
        move=new ToggleButton("MOVE");
        wire=new ToggleButton("CONNECT");
        delete=new ToggleButton("DELETE");
        simulate=new ToggleButton("SIMULATE");
        stop=new ToggleButton("STOP");
        
        ToggleGroup group=new ToggleGroup();
        group.getToggles().addAll(move,delete,simulate,stop);
        

        
        addItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                t.setMode(mode.move);
                SettingBox s=new SettingBox();
                s.display(complist,subComponents.getSelectionModel().getSelectedItem());
             }

            
        });
         wire.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              t.setMode(mode.wiring);
             }

            
        });
         
          move.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              t.setMode(mode.move);
             }

            
        });        
        
        
        
        
        delete.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                t.setMode(mode.delete);
                selected=null;
               String name= fieldComponents.getSelectionModel().getSelectedItem();
               Component forDel=null;
               for(int i=0;i<complist.size();i++){
                   try{
                  if(name.equals(complist.get(i).getName())){
                    forDel=complist.get(i);
                 }}
                   catch(Exception e){}
               }
               if(forDel!=null){
                  complist.remove(forDel);
                  t.items.remove(forDel.getName());
                  t.drawWires();}
                   refreshList();
             }
        });
        
        //for menu
        deleteItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               selected=null;
               String name= fieldComponents.getSelectionModel().getSelectedItem();
               Component forDel=null;
               for(int i=0;i<complist.size();i++){
                   try{
                  if(name.equals(complist.get(i).getName())){
                    forDel=complist.get(i);
                 }}
                   catch(Exception e){}
               }
               if(forDel!=null){
                  complist.remove(forDel);
                  t.items.remove(forDel.getName());
                  t.drawWires();}
                  refreshList();
             }
        });
        
      
        
      
        
        
        simulate.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
            t.buildGraph();
            t.setMode(mode.simulation);
            if(simu!=null){
                simu.cancel();}
            simu=new TimerTask() {
            @Override
            public void run() {
                
                 if(clone.isShowing()){
                   t.simulate();
                }
                else{
                 return;
                }
                
          }};
            timer.schedule(simu, 0, 100);
            }
        });
        
        
        stop.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                simu.cancel();
            }
        });
        
        
        
        
        bp=new BorderPane();
        
        bp.setCenter(forCanvas);
        
        
        cim=new Canvas(150,100);
        gc=cim.getGraphicsContext2D();
        gc.drawImage(new Image("Images/andgatemain.png"),5,5,145,95);
        
        VBox v=new VBox(slider,new Label("Component Type"),Components,new Label("Components")
                ,subComponents,new Label("Working components"),fieldComponents,cim);
        v.setSpacing(10);
        bp.setLeft(v);
        
        HBox h=new HBox(move,delete,wire,simulate,stop);
        h.setSpacing(20);
        
        bp.setTop(h);
    }

   public class SettingBox {
    
    void display(ArrayList a,String comp){
    
      
       
       VBox v=new VBox();
       ComponentFactory factory=new ComponentFactory();
       TextField name=new TextField();
       Button b=new Button("ADD");
       Label l=new Label("Enter component id");
       Scene sc=new Scene(new VBox(name,b,l));
       Stage s=new Stage();
       s.setTitle(comp+" Configuration");
       s.setScene(sc);
       s.setHeight(150);
       s.setWidth(350);
       s.show();
       b.setOnAction(new EventHandler() {
           @Override
           public void handle(Event event) {
               System.out.println(comp);
               Component c=factory.getComponent(comp,name.getText().toString(),complist);
               if(c==null){
               l.setText("don't enter a dublicate name");
               name.setText("");
               }
               else{
               a.add(c);
               m=t.redraw(complist.get(complist.size()-1),t.canvas.getWidth()/2, t.canvas.getHeight()/2);
               refreshList();
               s.close();}
               
          }
       });
       
    
    
    
      class alertBox{
           
       }
    
    
    }
    
}

    void refreshList(){
                fieldComponents.getItems().clear();
                Collection list=new ArrayList<String>();
                for(int i=0;i<complist.size();i++){
                list.add(complist.get(i).getName());
                }
               fieldComponents.getItems().addAll(list);
    }
    
    void drawImgHold(GraphicsContext gc,String comp){
        gc.clearRect(0,0,150,100);
        gc.drawImage(new ComponentFactory().getImage(comp),5,5,145,95);
    }
    
  
}
