/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometrics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Graph.Node;
/**
 *
 * @author wookie
 */
public class SimpleLIne {
    public SimpleLIne(Graphics g, int x1, int y1) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.RED);
        g2.drawLine(x1, y1, 200, 200);
    }
}
