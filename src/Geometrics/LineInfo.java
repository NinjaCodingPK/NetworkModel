/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometrics;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author wookie
 */
public class LineInfo extends JComponent {
    private int fx, fy;
    private final String number;
    private final String weight;
    private final int size;
    
    public LineInfo(String number, String weight, int fx, int fy) {
//        this.number = number;
        this.fx = fx;
        this.fy = fy;
        this.size = 22;
        this.number = number;
        this.weight = weight;
        
        setBorder(new LineBorder(Color.red, 1));
        setBounds(fx, fy, size, size);
        setOpaque(false);  
    }
    
    public LineInfo(String number, String weight) {
        this.number = number;
        this.weight = weight;
        this.size = 22;
        
        setBorder(new LineBorder(Color.red, 1));
        setBounds(fx, fy, size, size);
        setOpaque(false);  
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
        g.drawString(this.number, 5, size-10);
        g.drawString(this.weight, 5, size-1);
    }

    public void setFx(int fx) {
        this.fx = fx;
    }

    public void setFy(int fy) {
        this.fy = fy;
    }
    
    
}
