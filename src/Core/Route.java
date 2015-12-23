/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Graph.Node;
import java.util.ArrayList;

/**
 *
 * @author wookie
 */
public class Route {
    private final Node start;
    private final Node end;
    private final ArrayList<Node> route;
    private final int weight;
    private final int transiet;
    
    public Route(Node start, Node end, ArrayList<Node> route, int weight, int transiet) {
        this.start = start;
        this.end = end;
        this.route = route;
        this.weight = weight;
        this.transiet = transiet;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public ArrayList<Node> getRoute() {
        return route;
    }

    public int getWeight() {
        return weight;
    }

    public int getTransiet() {
        return transiet;
    }
    
    
}
