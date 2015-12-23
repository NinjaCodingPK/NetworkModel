/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Graph.Line;
import Graph.Node;

/**
 *
 * @author wookie
 */
public class Waiter implements Runnable {
    private final int time;
    private final int size;
    private final int package_size;
    private final Node start;
    private final Node end;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField field;
    
    public Waiter(int time, int size, Node start, Node end, int package_size, javax.swing.JPanel panel, javax.swing.JTextField field) {
        this.time = time;
        this.size = size;
        this.start = start;
        this.end = end;
        this.panel = panel;
        this.field = field;
        this.package_size = package_size;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("ya");
            Thread.sleep(time*200 + size*5 + 500);
            
            
            for(Line l : start.getConnetions()) {
                Sender.usedBranches.remove(l);
            }
            Sender s = new Sender(start, end, package_size, panel, field);
            s.SendPackage(start);
            //Thread.sleep(2000);
        } catch (InterruptedException ex) {
             //Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
