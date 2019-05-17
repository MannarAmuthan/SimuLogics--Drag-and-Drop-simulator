/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author AMUTHAN
 */
public class ComponentFactory {
    String and="AND",or="OR",push="PUSH BUTTON",led="LED",not="NOT",exor="EXOR",exnor="EXNOR",nand="NAND",seven="SEVEN SEGMENT";
    String addition="ADDITION",subtraction="SUBTRACTION",mul="MULTIPLICATION",div="DIVITION";
    
    
    Component getComponent(String co,String name,ArrayList<Component> complist){
      
       if(!isDuplicate(complist,name)){ 
      if(co.equals(and)){
      return new andgate(name);
      }
      else if(co.equals(or)){
      return new orgate(name);
      }
       else if(co.equals(push)){
      return new pushbutton(name);
      }
       else if(co.equals(led)){
      return new led(name);
      }
       else if(co.equals(not)){
      return new notgate(name);
      }
       else if(co.equals(exor)){
      return new exorgate(name);
      }
       else if(co.equals(exnor)){
      return new exnorgate(name);
      }
      else if(co.equals(nand)){
      return new nandgate(name);
      }
       else if(co.equals(seven)){ 
       return new SevenSegment(name);
       }
       else if(co.equals(addition)){ 
       return new addmain(name);
       }
       else if(co.equals(subtraction)){ 
       return new submain(name);
       }
      else if(co.equals(mul)){ 
       return new mulmain(name);
       }
        else if(co.equals(div)){ 
       return new divmain(name);
       }
       }
       
       return null;
        
        
    }
    
    
    
    boolean isDuplicate(ArrayList<Component>complist,String name){
        for(int i=0;i<complist.size();i++){
        String compname=complist.get(i).getName();
        if(compname.equals(name)){
              return true;
        }
        }
        return false;
    
    }
    
    Image getImage(String comp){
        if(comp.equals(and)){
       return new Image("Images/andgatemain.png");
      }
      else if(comp.equals(or)){
       return new Image("Images/orgatemain.png");
      }
       else if(comp.equals(push)){
       return new Image("Images/PUSHBUTTONmain.png");
      }
       else if(comp.equals(led)){
       return new Image("Images/LEDmain.png");
      }
       else if(comp.equals(not)){
      return  new Image("Images/notgatemain.png");
      }
       else if(comp.equals(exor)){
       return new Image("Images/exorgatemain.png");
      }
       else if(comp.equals(exnor)){
       return new Image("Images/exnorgatemain.png");
      }
      else if(comp.equals(nand)){
      return new Image("Images/nandgatemain.png");
      }
       else if(comp.equals(seven)){ 
       return new Image("Images/SevenSegment/SEVENmain.png");
       }
        else if(comp.equals(addition)){ 
       return new Image("Images/addmain.png");
       }
         else if(comp.equals(subtraction)){ 
       return new Image("Images/submain.png");
       }
         else if(comp.equals(mul)){ 
       return new Image("Images/mulmain.png");
       }
         else if(comp.equals(div)){ 
       return new Image("Images/divmain.png");
       }
       return null;
    }
       
    }

