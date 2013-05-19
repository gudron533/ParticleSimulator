/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.components;

import ch.usi.inf.sa2.objects.Scene;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author ruslan.seyidov
 */
public class Viewport  extends JComponent implements ActionListener {
    
    public Scene scene = new Scene();
    
    public double dt = 0.006;
    
    private AffineTransform transform = new AffineTransform();
    
    public Viewport() {
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        Dimension d = getSize();
//        gr.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//    gr.setPaint(Color.WHITE);
//    g.fillRect(0, 0, getWidth(), getHeight());
//    final AffineTransform at = gr.getTransform();
//    gr.transform(transform);
//    gr.setPaint(Color.RED);
//    g.drawLine(0, 0, 100, 0);
//    gr.setPaint(Color.BLUE);
//    g.drawLine(0, 0, 0, 100);
//    gr.setTransform(at);
//        AffineTransform tr = gr.getTransform();
//        tr.setToIdentity();
//        gr.setTransform(tr);
        double scale = Math.min(d.getWidth() / scene.getBounds().width, 
                d.getHeight() / scene.getBounds().height);
        
        Stroke oldStroke = gr.getStroke();
//        gr.setStroke(new BasicStroke(2));
//        gr.draw(new Rectangle2D.Double(0, 0, 
//                scene.getBounds().width * scale,
//                scene.getBounds().height * scale));
       
        gr.setStroke(oldStroke);        
        
        gr.scale(scale, scale);
        gr.translate(scene.getBounds().x, scene.getBounds().y);
        
        scene.paint(gr);
    }

    public AffineTransform getTransform() {
    return transform;
  }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        scene.makeStep(dt);
        repaint();
    }
    
    
    
    
}
