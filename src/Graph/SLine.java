/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author wookie
 */
public class SLine implements java.io.Serializable {
    private final int number;
    private int weight;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private final int StartNode;
    private final int EndNode;
    
    public SLine(int number, int n1, int n2, int weight) {
        this.number = number;
//        this.x1 = x1;
//        this.y1 = y1;
//        this.x2 = x2;
//        this.y2 = y2;
        
        this.weight = weight;    
        StartNode = n1;
        EndNode = n2;
    }
    
    public SLine(int number, int x1, int y1, int x2, int y2, int n1, int n2) {
        this.number = number;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
            
        StartNode = n1;
        EndNode = n2;
    }
    
    public int getNumber() {
        return number;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
      
    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getStartNode() {
        return StartNode;
    }

    public int getEndNode() {
        return EndNode;
    }
    
    
}
