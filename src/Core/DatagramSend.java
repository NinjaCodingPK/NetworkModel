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
public class DatagramSend implements Runnable{
    private final static ArrayList<Node> nodes = new ArrayList<>();
    private final ArrayList<Line> branches;
    public static ArrayList<Line> usedBranches = new ArrayList<>();
    private javax.swing.JPanel panel;
    private javax.swing.JTextField field; 
    private Node n;
    private Node end;
    
    public DatagramSend(ArrayList<Node> nodes, ArrayList<Line> branches,
            javax.swing.JPanel panel, javax.swing.JTextField field) {
        
        for(Node n : nodes) {
            this.nodes.add(n);
        }
        this.branches = branches;
        this.panel = panel;
        this.field = field;
    }
    
    public void ModelSend(Node start, Node end, int message_size, int package_size) {
        int package_count = message_size/package_size;
        int serve_package_size = 128 + 32;
        int mid_weight = 0;
        if((double)message_size/package_size - (int)(message_size/package_size) > 0)
            package_count += 1;
        
        int temp = 0;
        for(Node n : nodes) {
            for(Route r : n.getRoutings()) {
                mid_weight += r.getWeight();
                temp++;
            }
        }
        
        mid_weight = mid_weight/temp;
        
        package_size += 200;
        
        long sending_time;
        int weight = start.getMinRoute(end).getWeight();
        if(package_count == 1) {
            sending_time = weight + package_size;
        } else
        if(start.getConnetions().size() > package_count) {
            sending_time = package_count*(mid_weight + package_size + serve_package_size);
        }
        else {
            sending_time = package_count*(mid_weight + package_size + serve_package_size) + 500;
        }
        
        field.setText("Sending time = " + sending_time + ". Count of serve packages is " + 
                            package_count + ".");
    }
    
    public void SteppingSend(Node start, Node end, int message_size, int package_size) {
        package_size = 128;
        int package_count = message_size/package_size;
        int serve_package_size = 512;
        if((double)message_size/package_size - (int)(message_size/package_size) > 0)
            package_count += 1;
        
        for(int i = 0; i < package_count; i++) {
            nodes.get(start.getNum()).pushPool(end);
        }
        
        SendStep(start, end);
    }
        
    public void SendStep(Node n, Node end) {
        this.n = n;
        this.end = end;
        
        while(n.getPool().size() > 0)
            new Thread(this).start();
    }
    
    public Line chooseBranch(Node start, Node end) {
        for(Line l : start.getConnetions()) {
           if(((l.getStartNode() == start) && (l.getEndNode() == end)) ||
                   (((l.getStartNode() == end)) && (l.getEndNode() == start))) {
                        return l;            
           }
       }
        return null;
    }
    
    @Override
    public void run() {
       n.setState(false);
       Line branch = null;
       
       int i = 0;
       Node next;
       do {
           if(i >= n.getRoutings().size())
               try {
                   wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DatagramSend.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           next = n.getRoutings().get(i).getRoute().get(1);
           branch = chooseBranch(n, next);
           i++;
       }
       while(usedBranches.contains(branch));
       usedBranches.add(branch);
       
       if(branch != null)
            branch.changeColor(Color.green, panel);
       
       try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LogicSend.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
     
        n.popPool();
        next.pushPool(n);
        //notifyall();
        
        if(branch != null)
            branch.changeColor(Color.black, panel);
        
        usedBranches.remove(branch);
        if(next == end) {
            
        } else
            SendStep(next, end);
    }
        
}
