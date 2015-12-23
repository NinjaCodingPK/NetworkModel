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
    private int matrix[][];
    private int transiet_matrix[][];
    
    public Node getNodeByNum(int num) {
        for(Node n : nodes) {
            if(num == n.getNum())
                return n;
        }
        return null;
    }
    
    public Line getBranchByNum(int num) {
        for(Line n : branches) {
            if(num == n.getNumber())
                return n;
        }
        return null;
    }
    
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
    
    public void addConnection(Node n1, Node n2, boolean isSatelite, javax.swing.JPanel panel) {
        boolean isConnected = false;
        
        if(!branches.isEmpty())
            for(Line b : branches) {
                if((((b.getStartNode() == n1) && (b.getEndNode() == n2)) ||
                        ((b.getStartNode() == n2) && (b.getEndNode() == n1)))) {
                    isConnected = true;
                }                
            }
        else {
            Line l = new Line(0, isSatelite);
            l.Connect(n1, n2, panel);
            addBranch(l);
            isConnected = true;
        }
        
        if(!isConnected) {
            Line l = new Line(getBranches().get(getBranches().size() - 1).getNumber() + 1, isSatelite);
            l.Connect(n1, n2, panel);
            addBranch(l);
        }
    }
    
    public void removeBranch(int num, javax.swing.JPanel panel) {
        //if(getBranches().get(num) != null) {
//            getBranches().get(num).getStartNode().removeConnection(getBranches().get(num));
//            getBranches().get(num).getEndNode().removeConnection(getBranches().get(num));
//            getBranches().get(num).destroyLine(panel);
//            this.branches.remove(getBranches().get(num));
            getBranchByNum(num).getStartNode().removeConnection(getBranchByNum(num));
            getBranchByNum(num).getEndNode().removeConnection(getBranchByNum(num));
            getBranchByNum(num).destroyLine(panel);
            this.branches.remove(getBranchByNum(num));
        //}
        
    }
    
    public void removeNode(int num, javax.swing.JPanel panel) {
//        Node temp = this.getNodeByNum(num);
//        for(int i = 0; i < temp.g)
//            removeBranch(l.getNumber(), panel);
//        
//        temp.destroyNode();
//        this.nodes.remove(temp);
        Node tempNode = this.getNodeByNum(num);
        ArrayList<Line> temp = tempNode.getConnetions();
        int s = temp.size();
//        for(Line templ : temp) {
//            //templ.getEndNode().removeConnection(templ);
//            removeBranch(templ.getNumber(), panel);
//        }
        for(int i = 0; i < s; i++) {
             removeBranch(temp.get(0).getNumber(), panel);
        }
        tempNode.destroyNode();
        this.nodes.remove(tempNode);
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
            nodes.get(nodes.size() - 1).setState(sn.isState());
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
                addConnection(nodes.get(sl.getStartNode()), nodes.get(sl.getEndNode()), false, panel);
                
        }
        
        for(Line l : this.branches) {
            l.setWeight(slines.get(l.getNumber()).getWeight(), panel);
            //l.destroyLine(panel);
            //l.drawLine(panel);
        }

    }
    
    public void SendMessage(int start, int end, int package_size, javax.swing.JPanel panel, javax.swing.JTextField field) throws InterruptedException {
//        Sender s = new Sender(nodes.get(0), nodes.get(1), nodes, branches, panel);
        
        //for(int i = 0; i < 2; i++)
            if(nodes.get(start).isState() && nodes.get(start).isState()) {
                Sender s = new Sender(nodes.get(start), nodes.get(end), package_size, panel, field);
                s.Clear();
                field.setText("Transmission started");
                s.SendPackage(nodes.get(start));
                
                //new Thread(new Waiter(nodes.get(start).getMaxWeihgt(), package_size, nodes.get(start), nodes.get(end),
                //                    package_size, panel, field)).start();
                
                
                
            } else
                field.setText("Can't send package. One of the nodes is offline.");
//        new Thread(s).start();
        
//        Algorithm a = new Algorithm(nodes, branches);
//        a.SendPackage(0, 1, panel);
    }
    
    public void GraphtoMatrix() {
        int temp[][] = new int[nodes.size()][nodes.size()];
        int transiet_temp[][] = new int[nodes.size()][nodes.size()];
        
        for(Node n : nodes) {
            for(Line l : n.getConnetions()) {
                if(l.getEndNode() != n) {
                    temp[n.getNum()][l.getEndNode().getNum()] = l.getWeight();
                    transiet_temp[n.getNum()][l.getEndNode().getNum()] = 1;
                }
                else {
                    temp[n.getNum()][l.getStartNode().getNum()] = l.getWeight();
                    transiet_temp[n.getNum()][l.getStartNode().getNum()] = 1;
                }
            }
        }
        
        this.matrix = temp;
        this.transiet_matrix = transiet_temp;
    }
    
    public void ShowMatrix() {
        for(int i = 0; i < nodes.size(); i++) {
            for(int j = 0; j < nodes.size(); j++)
                System.out.print(matrix[i][j] + " ");
            
            System.out.println();
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getTransiet_matrix() {
        return transiet_matrix;
    }
    
    public void MakeRoutingTable() {
        //Dijkstras d = new Dijkstras(this.matrix);
        //ArrayList<Node> temp = new ArrayList<>();
        this.GraphtoMatrix();
        
        for(Node s : nodes) {
            for(Node e : nodes) {
                if(s != e) {
                    for(int i = 0; i < 3; i++)
                    {
                        Dijkstras d = new Dijkstras(this.matrix);
                        d.DijkstrasAlg(s.getNum(), e.getNum());
                        
                        Dijkstras tr = new Dijkstras(this.transiet_matrix);
                        tr.DijkstrasAlg(s.getNum(), e.getNum());
                    
                        ArrayList<Node> temp = new ArrayList<>();
                        for(int j : d.getPath()) {
                            this.matrix[s.getNum()][j] = 0;
                            temp.add(this.getNodeByNum(j));
                        } 
                    
                        getNodeByNum(s.getNum()).addRoute(new Route(s, e, temp, d.getlength(), tr.getlength()));
                        //temp.clear();
                    }
                    this.GraphtoMatrix();
                }
            }
        }
    }
    
}


//public void MakeRoutingTable() {
//        //Dijkstras d = new Dijkstras(this.matrix);
//        //ArrayList<Node> temp = new ArrayList<>();
//        this.GraphtoMatrix();
//        
//        for(Node s : nodes) {
//            for(Node e : nodes) {
//                if(s != e) {
//                    Dijkstras d = new Dijkstras(this.matrix);
//                    d.DijkstrasAlg(s.getNum(), e.getNum());
//                    
//                    ArrayList<Node> temp = new ArrayList<>();
//                    for(int i : d.getPath()) {
//                       temp.add(this.getNodeByNum(i));
//                    } 
//                    
//                    getNodeByNum(s.getNum()).addRoute(new Route(s, e, temp, d.getlength()));
//                    //temp.clear();
//                }
//            }
//        }
//    }