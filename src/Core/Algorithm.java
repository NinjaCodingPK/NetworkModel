/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Graph.Line;
import Graph.Node;
import java.util.ArrayList;
/**
 *
 * @author wookie
 */
public class Algorithm {
    private static ArrayList<Integer> usedNodes = new ArrayList<>();
    private static ArrayList<Sender> pool = new ArrayList<>();
    private final ArrayList<Node> nodes;
    private final ArrayList<Line> branches;
    
    public Algorithm(ArrayList<Node> nodes, ArrayList<Line> branches) {
       this.branches = branches;
       this.nodes = nodes;
    }
    
    public void SendPackage(int start, int end, javax.swing.JPanel panel) {
        
//        for(Line l : nodes.get(start).getConnetions()) {
//            if(!usedNodes.contains(l.getEndNode().getNum())) {
//                usedNodes.add(l.getEndNode().getNum());
//                //pool.add(new Sender(l.getEndNode(), l.getStartNode(), panel));
//                new Thread(new Sender(l.getEndNode(), l.getStartNode(), panel)).start();
//                //pool.get(pool.size() - 1).run();
//            }
//            else 
//                if(!usedNodes.contains(l.getStartNode().getNum())) {
//                    usedNodes.add(l.getStartNode().getNum());
//                    //pool.add(new Sender(l.getEndNode(), l.getEndNode(), panel));
//                    new Thread(new Sender(l.getEndNode(), l.getEndNode(), panel)).start();
//                    //pool.get(pool.size() - 1).run();
//                } 
//        }
        
    }
    

}
