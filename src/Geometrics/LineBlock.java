/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometrics;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
/**

/**
 *
 * @author wookie
 */
public class LineBlock extends JComponent {
    private final int number;
    private final int size = 3;
    private final int fx, fy;
    private Color color;
    
    public LineBlock(int number, int fx, int fy) {
        this.number = number;
        this.fx = fx;
        this.fy = fy;
        color = Color.red;
        
        setBorder(new LineBorder(color, 1));
        setBounds(fx, fy, size, size);
        setOpaque(false);  
    }
    
    public LineBlock(int number, int fx, int fy, Color color) {
        this.number = number;
        this.fx = fx;
        this.fy = fy;
        this.color = color;
        
        setBorder(new LineBorder(color, 1));
        setBounds(fx, fy, size, size);
        setOpaque(false);  
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        //g.fillRect(fx, fy, fsize, fsize);
        g.fillRect(fx, fy, size, size);
    }
    
    public void changeColor(Color color) {
        this.color = color;
    }
}
