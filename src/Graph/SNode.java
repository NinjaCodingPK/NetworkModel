/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

//import Graph.Line;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author wookie
 */
public class SNode implements java.io.Serializable {
    private final int x;
    private final int y;
    private final int number;
    private final ArrayList<Integer> connections; 
    private final Color color;
    
    public SNode(int number, int x, int y, Color color, ArrayList<Integer> c) {
        this.number = number;
        this.color = color;
        this.x = x;
        this.y = y;
        this.connections = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }

    public Color getColor() {
        return color;
    }
   
        
}
