/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import java.util.ArrayList;


public class CompNode {
    
    ArrayList<XandY> child,parent;
    Component comp;
    CompNode(Component comp){
       child=new ArrayList<>();
       parent=new ArrayList<>();
       this.comp=comp;
    }
    void addChild(XandY child){
        if(!this.child.contains(child))
        {
      this.child.add(child);
      
        }
    }
    void addParent(XandY parent){
        if(!this.parent.contains(parent)){
      this.parent.add(parent);}
    }
    
    ArrayList<XandY> getChildren(){
    return child;
    }
    ArrayList<XandY> getParents(){
    return parent;
    }
    Component getComponent(){
    return comp;
    }
}
