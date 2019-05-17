/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import java.util.ArrayList;
import java.util.HashMap;


public class Graph {
   
   ArrayList<CompNode> node;
   ArrayList<XandY>  ordered;
   HashMap<String,Component> items;
   HashMap<String,Component> toBesend;
   ArrayList<Component> orderedItems;
   
   boolean isOrdered=false;
   Graph(){
     node=new ArrayList<>();
     ordered=new ArrayList<>();
     items=new HashMap<>();
     toBesend=new HashMap<>();
     orderedItems=new ArrayList<>();
   }
   
   CompNode getNode(Component co){
      
           for(int i=0;i<node.size();i++){
               if(co.equals(node.get(i).getComponent())){
                   //System.out.println("equal.."+co.getName());
               return node.get(i);
               }
           }
       
      
           CompNode node=new CompNode(co);
           this.node.add(node);
          // System.out.println("not equal.."+co.getName());
           return node;
           
   }
   
   void addChildren(CompNode co,XandY child){
      co.addChild(child);
   }
   void addParent(CompNode co,XandY child){
      co.addParent(child);
   }
   
   ArrayList<Component> listRootsandChild(HashMap<String,Component> items){
      this.items=items;
      
       for(int i=0;i<node.size();i++){
         if(node.get(i).getParents().size()==0){
         System.out.println(node.get(i).getComponent().getName()+"...parent");
      
           for(int j=0;j<node.get(i).getChildren().size();j++)
           {
             // ordered.add(node.get(i).getChildren().get(j));
              orderedItems.add(node.get(i).getComponent());
           }
        }
       }
       System.out.println(orderedItems.size()+"..size");
       int size=orderedItems.size();
       for(int a=0;a<size;a++){
         
         ArrayList<XandY> child=getNode(items.get(orderedItems.get(a).getName())).getChildren();
        
         for(int b=0;b<child.size();b++){
          Component c=items.get(child.get(b).getId());
            if(!orderedItems.contains(c)){
            orderedItems.add(c);
            size=size+1;
               }
        
        }
      
      }
           for(int j=0;j<orderedItems.size();j++)
           {
               CompNode c=getNode(orderedItems.get(j));
               toBesend.put(orderedItems.get(j).getName(),orderedItems.get(j));
               ArrayList<XandY> child=c.getChildren();
             for(int k=0;k<child.size();k++){
                         ordered.add(child.get(k));
             } }
           
     for(int m=0;m<orderedItems.size();m++){
         System.out.println(orderedItems.get(m).getName());
     }
     
     return orderedItems;
   
   }
     }
   
   
   
   

