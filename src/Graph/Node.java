/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.swing.*;
import javax.swing.border.*;

public class Node extends JComponent implements java.io.Serializable {

    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0; 
    private volatile int x = 0; 
    private volatile int y = 0; 
    private final int fx;
    private final int fy;
    private final int fsize;
    private Color color = Color.green;
  
    //private final Graphics g;
    private final javax.swing.JPanel panel;
    
    private final int num;
    private ArrayList<Line> connections = new ArrayList<>(); 
    
    private boolean state = true;
   
    //private Pair<Integer, Integer> cord;
    
    public Node(int num, int fx, int fy, javax.swing.JPanel panel) {
        this.fx = fx;
        this.fy = fy;
        this.x = fx;
        this.y = fy;
        this.fsize = 40;
        
        //this.g = g;
        this.panel = panel;
        
        this.num = num;
      
        setBorder(new LineBorder(Color.white, 1));
        setBounds(fx, fy, fsize, fsize);
        setOpaque(false);       

        addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) { 
                //System.out.println(e.getButton());
                if(e.getButton() == 3) { //right mouse button
                    setState(!state);
                    repaintNode();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
                x = myX;
                y = myY;
                
                for(Line c : connections) {
                    c.destroyLine(panel);
                }
//                panel.revalidate();
//                panel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) { 
                for(Line c : connections) {
                    if(num == c.getStartNode().getNum()) {  
                        c.setX0(getX() + fsize/2);
                        c.setY0(getY() + fsize/2);
                    }
                    else if(num == c.getEndNode().getNum()) {
                        c.setX1(getX() + fsize/2);
                        c.setY1(getY() + fsize/2);
                    }
//                    c.setX0(getX());
//                    c.setY0(getY());
                    c.drawLine(panel);
                }
                
                panel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

        });
    
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
//                for(Line c : connections) {
//                    c.destroyLine(panel);
//                }
                
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;
                
               
                x = myX + deltaX;
                y = myY + deltaY;
                
                setLocation(myX + deltaX, myY + deltaY);
//                for(Line c : connections) {
//                    c.setX0(myX + deltaX);
//                    c.setY0(myY + deltaY);
//                }
//                
//                panel.revalidate();
//                panel.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) { }

        });
        
        panel.add(this);
        panel.repaint();
    }
  
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        //g.fillRect(fx, fy, fsize, fsize);
        //g.fillRect(0, 0, fsize, fsize);
        g.fillOval(0, 0, fsize-2, fsize-2);
        g.setColor(Color.BLACK);
        if(num > 9)
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        else
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        g.drawString(Integer.toString(this.num), 10, fsize-7);
    }
    
    public int getFsize() {
        return fsize;
    }

    public int getNum() {
        return num;
    }

    public Color getColor() {
        return color;
    }
    
    public ArrayList<Line> getConnetions() {
        return connections;
    }
    
    public void setConnetions(ArrayList<Line> conncetions) {
        this.connections = conncetions;
    }

//    public Pair<Integer, Integer> getCord() {
//        return cord;
//    }
//
//    public void setCord(Pair<Integer, Integer> cord) {
//        this.cord = cord;
//    }
    
    public void addConnection(Line l) {
        this.connections.add(l);
    }
    
    public void removeConnection(Line l) {
        connections.remove(l);
    }
    
    public boolean isConnected(Node n) {
        
        for(Line l : connections) {
            //if(l.getEndNode().getNum() == n.getNum())
                if(this == l.getEndNode())
                    return true;
       }
       return false;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void destroyNode() {  
        panel.remove(this);
        panel.revalidate();
        panel.repaint();
    }
    
//    public void removeFullNode() {
//        for(Line c : connections) {
//            c.destroyLine(panel);
//        }
//        connections.clear();
//    }
    
    public void repaintNode() {
//        if(this.state)
//            this.color = Color.green;
//        else
//            this.color = Color.red;
        destroyNode();
        panel.add(this);
        panel.repaint();
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if(this.state)
            this.color = Color.green;
        else
            this.color = Color.red;
        
    }
    
    

}



