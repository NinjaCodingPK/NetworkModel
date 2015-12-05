/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Graph.Line;
import Geometrics.SimpleLIne;
import Graph.Node;
import Geometrics.LineBlock;

/**
 *
 * @author wookie
 */
public class GraphicsPanel extends javax.swing.JPanel {

    /**
     * Creates new form GraphicsPanel
     */
    public GraphicsPanel() {
        initComponents();
        this.setBackground(Color.white);
    }
    
//    @Override
//    public void paint(Graphics g) {
//                Graphics2D gr2d = (Graphics2D) g;
//                gr2d.setBackground(Color.GREEN);
//                // Рисуем простые линии
//                gr2d.setPaint(Color.RED);
//                gr2d.drawLine(300, 50, 100, 100);
//                gr2d.setPaint(Color.BLUE);
//                gr2d.drawLine(300, 50, 100, 500);
//    }
    
//    public void ConnectNodes(Node n1, Node n2) {
//        n1.Connect(n2, 0);
//        
//        Graphics g = this.getGraphics();
//        g.setColor(Color.red);
//        g.drawLine(0, 0, 100, 100);
//        //repaint();
  //  }
    
//    private int round(double x) {
//        if(x - int(x) > 0.5) {
//            return (int)x + 1;
//        else
//            return (int)x;
//    }
    
//    private int round(double x) {
//        double a = x;
//        if(a - (int)a > 0.5)
//            return (int)a + 1;
//        else
//            return (int)a;
//    }
//    
//    public void drawLine(int x1, int y1, int x2, int y2) {
//    
//      // (1) Целочисленные значения координат начала и конца отрезка, 
//      // округленные до ближайшего целого
////        int iX1 = roundf(x1);
////        int iY1 = roundf(y1);
////        int iX2 = roundf(x2);
////        int iY2 = roundf(y2);
// 
//      // (2) Длина и высота линии
//      int deltaX = Math.abs(x1 - x2);
//      int deltaY = Math.abs(y1 - y2);
// 
//      // (3) Считаем минимальное количество итераций, необходимое
//      // для отрисовки отрезка. Выбирая максимум из длины и высоты
//      // линии, обеспечиваем связность линии
//      double length = Math.max(deltaX, deltaY);
//    // особый случай, на экране закрашивается ровно один пиксел
////      if (length == 0)
////      {
////            //SetPixel(hdc, iX1, iY1, 0);
////            return;
////      }
// 
//      // (4) Вычисляем приращения на каждом шаге по осям абсцисс и ординат
//      double dX = (x2 - x1) / length;
//      double dY = (y2 - y1) / length;
// 
//      // (5) Начальные значения
//      double x = x1;
//      double y = y1;
// 
//      // Основной цикл
//      length++;
//      while (length > 0)
//      {
//            x += dX;
//            y += dY;
//            //SetPixel(hdc, roundf(x), roundf(y), 0);
//            LineBlock a = new LineBlock(1, round(x), round(y));
//            this.add(a);
//            
//            length--;
//      }
//    }
//


    
//    public void drawLine(int x0, int y0, int x1, int y1) {
//        //SimpleLIne sl = new SimpleLIne(this.getGraphics(), 0, 0);
//        int deltax = Math.abs(x1 - x0);
//        int deltay = Math.abs(y1 - y0);
//        double error = 0;
//        
//        double deltaerr = 0;
//        if(deltax != 0)
//            deltaerr = deltay / deltax;
//        int y = y0;
//        if(deltax == 0) {
//            for(y = y0; y < y1; y++) {
//                LineBlock a = new LineBlock(1, x0, y);
//                this.add(a);
//            }
//        }
//        else
//        for(int x = x0; x < x1; x++) {
//            //plot(x,y);
//            LineBlock a = new LineBlock(1, x, y);
//            this.add(a);
//            
//            error = error + deltaerr;
//            if (error >= 0.5) {
//                y = y + 1;
//                error = error - 1;
//            }
//        }
//        
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        //drawLine();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
