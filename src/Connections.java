/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvoresearch;

import javafx.scene.shape.Line;

/**
 *
 * @author AMUTHAN
 */
public class Connections  {
    XandY input,output;

    public Connections(XandY input, XandY output) {
        
        this.input = input;
        this.output = output;
    }
    
    public XandY getInput() {
        return input;
    }

    public void setInput(XandY input) {
        this.input = input;
    }

    public XandY getOutput() {
        return output;
    }

    public void setOutput(XandY output) {
        this.output = output;
    }
   

    
    
}
