/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.components;

import ch.usi.inf.sa2.app.App;
import ch.usi.inf.sa2.objects.BlackHole;
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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author ruslan.seyidov
 */
public class Viewport  extends JComponent implements ActionListener {
    
    public Scene scene = new Scene();
    
    public double dt = 0.006;
    
    BufferedImage sprite;
     
       
    private static Logger LOG = Logger.getLogger("Viewport");
    private static DecimalFormat DF = (DecimalFormat) DecimalFormat.getInstance();
    private static final long serialVersionUID = 1234234L;

    static {
        DF.setRoundingMode(RoundingMode.HALF_UP);
        DF.setMaximumFractionDigits(3);
    }

    private Point2D mouseOrigin;
        
    
    private AffineTransform transform = new AffineTransform();
    
    public Viewport()  {
        
        //initComponents();
        //setLocationRelativeTo(null);
        try {
            File bg = new File("img/viewportBG.jpg");
            sprite = ImageIO.read(bg);
        } catch (IOException ex) {
            Logger.getLogger(Viewport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D) g;

        super.paintComponent(gr);

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
       
        for (int x = 0; x < getWidth(); x += sprite.getWidth()) {  
            for (int y = 0; y < getHeight(); y += sprite.getHeight()) {  
              gr.drawImage(sprite, x,y, this);  
            }
        }
        
        
        
        gr.scale(scale, scale);
        gr.translate(scene.getBounds().x, scene.getBounds().y);
        scene.paint(gr);
    }

    public AffineTransform getTransform() {
    return transform;
  }
    
    public void setTransform(AffineTransform aff) {
        transform = aff;
    }
    
    public DecimalFormat getDF() {
        return DF;
    }
    
    public Logger getLog() {
        return LOG;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        scene.makeStep(dt);
        repaint();
    }
    
    
    
    
}
