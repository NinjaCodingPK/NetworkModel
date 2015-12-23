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
public class LogicSend implements Runnable {
    private final ArrayList<Node> nodes;
    private final ArrayList<Line> branches;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField field;
    private Node start, end;
    private static long delay;
    
    public LogicSend(ArrayList<Node> nodes, ArrayList<Line> branches,
            javax.swing.JPanel panel, javax.swing.JTextField field) {
        this.nodes = nodes;
        this.branches = branches;
        this.panel = panel;
        this.field = field;
    }
    
    public void Send(Node start, Node end, long delay) {
        this.start = start;
        this.end = end;
        this.delay = delay;
        
        new Thread(this).start();
    }
    
//    public void ShowMinRoute(Node start, Node end) {
//        this.start = start;
//        this.end = end;
//        
//        ArrayList<Node> route = start.getMinRoute(end).getRoute();
//        ArrayList<Integer> lines = new ArrayList<>();
//        //Iterator r = route.iterator();
//        for(int i = 0; i < route.size()-1; i++) {
//            for(Line l : route.get(i).getConnetions()) {
//                if(((route.get(i) == l.getStartNode()) && (route.get(i+1) == l.getEndNode())) ||
//                   ((route.get(i+1) == l.getStartNode()) && (route.get(i) == l.getEndNode())))
//                        //l.changeColor(Color.green, panel);
//                    lines.add(l.getNumber());
//            }
//        }
//
//        for(int l : lines) {
//            this.branches.get(l).changeColor(Color.green, panel);
//        }
//        
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(LogicS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        for(int l : lines) {
//            this.branches.get(l).changeColor(Color.black, panel);
//        }
//    }
    
    public void ModelSend(Node start, Node end, int message_size, int package_size) {
        this.Send(start, end, 2000);
        
        int package_count = message_size/package_size;
        int weight = start.getMinRoute(end).getWeight();
        int serve_package_size = 128;
        if((double)message_size/package_size - (int)(message_size/package_size) > 0)
            package_count += 1;
      
            
        package_size += 100; // служебная часть сообщения.
        
        System.out.println(package_count);
        
        long set_connection_time = 2*(weight + serve_package_size);
        long lose_connection_time = 2*(weight + serve_package_size);
        long sending_packages = package_count*(weight + package_size) + serve_package_size + weight;
       
        int serve_package_count = 4 + package_count;
        long sending_time = set_connection_time + lose_connection_time + sending_packages;
       
        field.setText("Sending time = " + sending_time + ". Count of serve packages is " + 
                            serve_package_count + ".");
    }
    
    public void StepSend(Node start, Node end, int message_size, int package_size) {
        
        int package_count = message_size/package_size;
        int weight = start.getMinRoute(end).getWeight();
        int serve_package_size = 256;
        if((double)message_size/package_size - (int)(message_size/package_size) > 0)
            package_count += 1;
       
        System.out.println(package_count);
       
        long set_connection_time = 2*(weight + serve_package_size);
        long lose_connection_time = 2*(weight + serve_package_size);
        long sending_packages = package_count*(weight + package_size + serve_package_size);
       
        int serve_package_count = 4 + package_count;
        long sending_time = set_connection_time + lose_connection_time + sending_packages;
       
        field.setText("Sending time = " + sending_time + ". Count of serve packages is " + 
                            serve_package_count + ".");
    }
    
    
    @Override 
    public void run() {
        ArrayList<Node> route = start.getMinRoute(end).getRoute();
        ArrayList<Integer> lines = new ArrayList<>();
        //Iterator r = route.iterator();
        for(int i = 0; i < route.size()-1; i++) {
            for(Line l : route.get(i).getConnetions()) {
                if(((route.get(i) == l.getStartNode()) && (route.get(i+1) == l.getEndNode())) ||
                   ((route.get(i+1) == l.getStartNode()) && (route.get(i) == l.getEndNode())))
                        //l.changeColor(Color.green, panel);
                    lines.add(l.getNumber());
            }
        }

        for(int l : lines) {
            this.branches.get(l).changeColor(Color.green, panel);
        }
        
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(LogicSend.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int l : lines) {
            this.branches.get(l).changeColor(Color.black, panel);
        }
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