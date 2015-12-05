/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Graph.Line;
import Graph.Node;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wookie
 */
public class Sender implements Runnable {
    //private final Object lock = new Object();
    private static boolean flag = false;
    private static  ArrayList<Node> usedNodes = new ArrayList<>();
    private static ArrayList<Line> usedBranches = new ArrayList<>();
    private static long starttime = System.currentTimeMillis();
    //private static ArrayList<Sender> pool = new ArrayList<>();
    //private ArrayList<Node> nodes = new ArrayList<>();
    //private final ArrayList<Line> branches;
    private Node start;
    private Node end;
    private final Node finalNode;
    private Line branch;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField field;
    
    public Sender(Node start, Node end, Node finalNode, Line branch, javax.swing.JPanel panel, 
                    javax.swing.JTextField field) {
       this.start = start;
       this.end = end;
       this.panel = panel;
       //this.branches = branches;
       this.branch = branch;
       //this.nodes = nodes;
       this.finalNode = finalNode;
       this.field = field;
       
    }
    
    public Sender(Node startNode, Node finalNode, javax.swing.JPanel panel, javax.swing.JTextField field) {
       this.panel = panel;
       //this.branches = branches;
       //this.nodes = nodes;
       this.finalNode = finalNode;
       this.start = startNode;
       this.field = field;
    }
    
    public void SendPackage(Node start) {
        if(usedNodes.contains(finalNode)) {
            if(!flag) {
                field.setText("Transmission's time : " 
                   + Long.toString(System.currentTimeMillis() - starttime) + " ms");
                flag = true;
            }
        }
        else
        for(Line l : start.getConnetions()) {

            if(!usedBranches.contains(l)) {
                usedBranches.add(l);
                if((!usedNodes.contains(l.getEndNode()) && (start != l.getEndNode()))) {
                   // usedNodes.add(l.getEndNode());
                    //pool.add(new Sender(l.getEndNode(), l.getStartNode(), panel));
                    new Thread(new Sender(l.getEndNode(), l.getStartNode(), this.finalNode, l, panel, field)).start();
                    //pool.get(pool.size() - 1).run();
                }
                else 
                    if((!usedNodes.contains(l.getStartNode()) && (start != l.getStartNode()))) {
                        //usedNodes.add(l.getStartNode());
                        //pool.add(new Sender(l.getEndNode(), l.getEndNode(), panel));
                        new Thread(new Sender(l.getStartNode(), l.getEndNode(), this.finalNode, l, panel, field)).start();
                        //pool.get(pool.size() - 1).run();
                    } 
            }

        }
        
        
    }
    
    @Override
    public void run() {
        //for(Line l : start.getConnetions()) {
            //if((.getEndNode() == end) || ((l.getStartNode()== end))) {
                branch.changeColor(Color.green, panel);
                try {
                    Thread.sleep(branch.getWeight()*200 + 2000);
                    //Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
                }
                branch.changeColor(Color.black, panel);
            //}

            usedNodes.add(start);    
            SendPackage(start);
            
        //}
    } 
    
    public void Clear() {
        usedNodes.clear();
        usedBranches.clear();
        flag = false;
        //pool.clear();
    }
}


//        for(Line l : nodes.get(start).getConnetions()) {
//            if(!usedNodes.contains(l.getEndNode())) {
//                usedNodes.add(l.getEndNode());
//                //pool.add(new Sender(l.getEndNode(), l.getStartNode(), panel));
//                new Thread(new Sender(l.getEndNode(), l.getStartNode(), nodes, l,  panel)).start();
//                //pool.get(pool.size() - 1).run();
//            }
//            else 
//                if(!usedNodes.contains(l.getStartNode())) {
//                    usedNodes.add(l.getStartNode());
//                    //pool.add(new Sender(l.getEndNode(), l.getEndNode(), panel));
//                    new Thread(new Sender(l.getEndNode(), l.getEndNode(), nodes, l, panel)).start();
//                    //pool.get(pool.size() - 1).run();
//                } 