/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author wookie
 */

import Graph.*;
import java.util.ArrayList;

public class Core implements java.io.Serializable {
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final ArrayList<Line> branches = new ArrayList<>();
    private final Integer weights[] = {1, 2, 3, 4, 5, 6}; 

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Line> getBranches() {
        return branches;
    }
    
    public void addNode(Node n) {
        nodes.add(n);
    }    
    
    public void addBranch(Line b) {
        branches.add(b);
    }
    
    public void addConnection(Node n1, Node n2, javax.swing.JPanel panel) {
        boolean isConnected = false;
        
        if(!branches.isEmpty())
            for(Line b : branches) {
                if((((b.getStartNode() == n1) && (b.getEndNode() == n2)) ||
                        ((b.getStartNode() == n2) && (b.getEndNode() == n1)))) {
                    isConnected = true;
                }                
            }
        else {
            Line l = new Line(0);
            l.Connect(n1, n2, panel);
            addBranch(l);
            isConnected = true;
        }
        
        if(!isConnected) {
            Line l = new Line(getBranches().get(getBranches().size() - 1).getNumber() + 1);
            l.Connect(n1, n2, panel);
            addBranch(l);
        }
    }
    
    public void removeBranch(int num, javax.swing.JPanel panel) {
        //if(getBranches().get(num) != null) {
            getBranches().get(num).getStartNode().removeConnection(getBranches().get(num));
            getBranches().get(num).getEndNode().removeConnection(getBranches().get(num));
            getBranches().get(num).destroyLine(panel);
            this.branches.remove(getBranches().get(num));
        //}
        
    }
    
    public void removeNode(int num, javax.swing.JPanel panel) {
        ArrayList<Line> temp = getNodes().get(num).getConnetions();
//        for(Line templ : temp) {
//            //templ.getEndNode().removeConnection(templ);
//            removeBranch(templ.getNumber(), panel);
//        }
        for(int i = 0; i < temp.size(); i++) {
             removeBranch(temp.get(i).getNumber(), panel);
        }
        getNodes().get(num).destroyNode();
        this.nodes.remove(num);
    }
    
    public void ClearScreen(javax.swing.JPanel panel) {
        for(Line l : branches)
            l.destroyLine(panel);
        for(Node n : nodes)
            n.destroyNode();
        nodes.clear();
        branches.clear();
    }
    
//    public void ClearScreen(javax.swing.JPanel panel) {
//        for(Line l : branches)
//            l.destroyLine(panel);
//        for(Node n : nodes)
//            n.destroyNode();
//        nodes.clear();
//        branches.clear();
//    }
    
    public void ShowAll(javax.swing.JPanel panel) {
        for(Line l : branches)
            l.drawLine(panel);
        for(Node n : nodes)
            panel.add(n);
        
        panel.repaint();
    }
    
    public void load(ArrayList<SNode> snodes, ArrayList<SLine> slines, javax.swing.JPanel panel) {
        for(SNode sn : snodes) {
            this.nodes.add(new Node(sn.getNumber(), sn.getX(), sn.getY(), panel));
        }
       
//        for(SLine sl : slines) {
//            this.branches.add(new Line(sl.getNumber()));
//        }
//        for(SLine sl : slines) {
//            this.branches.add(new Line(sl.getNumber(), 
//            snodes.get(sl.getStartNode()).getX(), snodes.get(sl.getStartNode()).getY(),
//            snodes.get(sl.getEndNode()).getX(), snodes.get(sl.getEndNode()).getY()));
//        }
        
        for(SLine sl : slines) {
                addConnection(nodes.get(sl.getStartNode()), nodes.get(sl.getEndNode()), panel);
                
        }
        
        for(Line l : this.branches) {
            l.setWeight(slines.get(l.getNumber()).getWeight(), panel);
            //l.destroyLine(panel);
            //l.drawLine(panel);
        }

    }
    
    public void SendPackage(int start, int end, javax.swing.JPanel panel) {
//        Sender s = new Sender(nodes.get(0), nodes.get(1), nodes, branches, panel);
        Sender s = new Sender(nodes.get(start), nodes.get(end), panel);
        s.Clear();
        s.SendPackage(nodes.get(start));
//        new Thread(s).start();
        
//        Algorithm a = new Algorithm(nodes, branches);
//        a.SendPackage(0, 1, panel);
    }
    
}


