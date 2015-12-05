/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Geometrics.LineBlock;
import Geometrics.LineInfo;
import Graph.Node;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author wookie
 */
public class Line implements java.io.Serializable {
    private final int number;
    private int weight;
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    private final ArrayList<LineBlock> blocks = new ArrayList<>();
    private LineInfo info;
    private Node startNode, endNode;
    private Color color;
    
    private final int weights[] = {1, 2, 3, 5, 7, 8, 12, 15, 21, 26}; 
    
    public Line(int number, int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.number = number;
        color = Color.red;
        
        this.weight = weights[(int) Math.random()*10];
        //info = new LineInfo(Integer.toString(number), Math.abs(x0 - x1)/3, Math.abs(x0 - x1)/3);
    }
    
    public Line(int number, boolean isSatelite) {
        this.number = number;
        this.weight = weights[(int) (Math.random()*10)];
        if(isSatelite)
            this.weight *= 3;
        //info = new LineInfo(Integer.toString(number));
    }
      
    
    private int round(double x) {
        double a = x;
        if(a - (int)a > 0.5)
            return (int)a + 1;
        else
            return (int)a;
    }
    
    private void MakingLineInfoCord() {
        double x,y;
        if(x1 - x0 >= 0)
            x = x0 + Math.abs(x0 - x1)/2 + 20;
        else
            x = x1 + Math.abs(x0 - x1)/2 + 20;
//        y = Math.abs(y0 - y1)/2;
        //info.setFx(x0+15);
        
        if(y1 - y0 >= 0) {
            //info.setFy(y0+25);
            //x = x0/2 + 20;
            x = x - 60;
            y = y1 - Math.abs(y0 - y1)/2 + 10;
        } 
        else {
//            x = x0/2 - 20 - 20;  
            y = y0 - Math.abs(y0 - y1)/2 + 10;
        }
            //info.setFy(y0-25);
        info = new LineInfo(Integer.toString(number), Integer.toString(weight), (int)x, (int)y);
    }
    
    public void drawLine(javax.swing.JPanel panel) {
      // (1) Целочисленные значения координат начала и конца отрезка, 
      // округленные до ближайшего целого
//        int iX1 = roundf(x1);
//        int iY1 = roundf(y1);
//        int iX2 = roundf(x2);
//        int iY2 = roundf(y2);
 
      // (2) Длина и высота линии
      int deltaX = Math.abs(x0 - x1);
      int deltaY = Math.abs(y0 - y1);
 
      // (3) Считаем минимальное количество итераций, необходимое
      // для отрисовки отрезка. Выбирая максимум из длины и высоты
      // линии, обеспечиваем связность линии
      double length = Math.max(deltaX, deltaY);
    // особый случай, на экране закрашивается ровно один пиксел
//      if (length == 0)
//      {
//            //SetPixel(hdc, iX1, iY1, 0);
//            return;
//      }
 
      // (4) Вычисляем приращения на каждом шаге по осям абсцисс и ординат
      double dX = (x1 - x0) / length;
      double dY = (y1 - y0) / length;
 
      // (5) Начальные значения
      double x = x0;
      double y = y0;
 
      // Основной цикл
      length++;
      while (length > 0)
      {
            x += dX;
            y += dY;
            //SetPixel(hdc, roundf(x), roundf(y), 0);
            LineBlock a = new LineBlock(number, round(x), round(y), color);
            this.blocks.add(a);
            panel.add(a);
            
            length--;
      }
      
      //drawing info
      MakingLineInfoCord();
      panel.add(info);
      
      panel.repaint();
    }  
    
    public void destroyLine(javax.swing.JPanel panel) {
        for(LineBlock b : this.blocks) {
            panel.remove(b);
        }  
        panel.remove(info);
        info = null;
        
        panel.revalidate();
        panel.repaint();
    }
    
    public boolean Connect(Node n1, Node n2, javax.swing.JPanel panel) {
//        if(n1.isConnected(n2) || n2.isConnected(n1))
//            return false;
        
        setX0(n1.getX() + n1.getFsize()/2);
        setY0(n1.getY() + n1.getFsize()/2);
        setX1(n2.getX() + n2.getFsize()/2);
        setY1(n2.getY() + n2.getFsize()/2);
        
        startNode = n1;
        endNode = n2;
        
        n1.addConnection(this);
        n2.addConnection(this);
        
        this.drawLine(panel);
        
        return true;
//        n1.setCord(new Pair<>(n1.getX(), n1.getY()));
//        n2.setCord(new Pair<>(n2.getX(), n2.getY()));   
    }
    
    public synchronized void changeColor(Color color, javax.swing.JPanel panel) {
        this.destroyLine(panel);
        this.color = color;
        this.drawLine(panel);
    }
    
    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }
    
    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getNumber() {
        return number;
    }

    public synchronized int getWeight() {
        return weight;
    }

    public void setWeight(int weight, javax.swing.JPanel panel) {
        this.weight = weight;
        this.destroyLine(panel);
        this.drawLine(panel);
    }
    
    
}    

//    public void drawLine(javax.swing.JPanel panel) {
//        //SimpleLIne sl = new SimpleLIne(this.getGraphics(), 0, 0);
//        int deltax = x1 - x0;
//        int deltay = y1 - y0;
//        double error = 0;
//        
//        double deltaerr = 0.0;
//        if(deltax != 0)
//            deltaerr = (double) Math.abs(deltay) / Math.abs(deltax);
//        int y = y0;
//        if(deltax == 0) {
//            for(y = y0; y < y1; y++) {
//                LineBlock a = new LineBlock(number, x0, y);
//                this.blocks.add(a);
//                panel.add(a);
//            }
//        }
//        else
//        for(int x = x0; x < x1; x++) {
//            //plot(x,y);
//            LineBlock a = new LineBlock(number, x, y);
//            this.blocks.add(a);
//            panel.add(a);
//            
//            error = error + deltaerr;
//            if (error >= 0.5) {
////                if(deltay >= 0)
//                    y = y + 1;
////                else
////                    y = y - 1;
//                error = error - 1;
//            }
//        }
//        
//    }
