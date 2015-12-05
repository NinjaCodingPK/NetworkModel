/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.ArrayList;

/**
 *
 * @author wookie
 */
public class SGraph implements java.io.Serializable {
    private final ArrayList<SNode> nodes = new ArrayList<>();
    private final ArrayList<SLine> branches = new ArrayList<>();

    public void formNodes(ArrayList<Node> bign) {
        ArrayList<Integer> connections = new ArrayList<>();
        
        for(Node n : bign) {
            for(Line l : n.getConnetions())
                connections.add(l.getNumber());
            nodes.add(new SNode(n.getNum(), n.getX(), n.getY(), n.getColor(), connections));
            connections.clear();
        }
    }
    
    public void formLines(ArrayList<Line> bigl) {
        for(Line l : bigl) {
            branches.add(new SLine(l.getNumber(), l.getStartNode().getNum(), l.getEndNode().getNum(), l.getWeight()));
            //branches.add(new SLine(l.getNumber(), l.getStartNode().getNum(), l.getEndNode().getNum()));
        }
    }
    
    public ArrayList<SNode> getNodes() {
        return nodes;
    }

    public ArrayList<SLine> getBranches() {
        return branches;
    }
    
    public void addSBranch(SLine l) {
        branches.add(l);
    }
    
    public void addSNode(SNode n) {
        nodes.add(n);
    }
}
