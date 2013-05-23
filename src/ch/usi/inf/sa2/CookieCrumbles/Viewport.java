package ch.usi.inf.sa2.CookieCrumbles;
/**
 *
 * @author A.Mamyshev / R.Seyidov
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.util.Random;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viewport extends JComponent implements ActionListener {

    private AffineTransform transform = new AffineTransform();
    
    
    
    private ArrayList emitterList;
    private Timer timer;
    double x;
    double y;

    public Viewport() {
        
        initComponents();
        timer = new Timer(10, this);
        emitterList = new ArrayList();
        timer.start();
        timer.setInitialDelay(0);
        //repaint();
        x = 0;
        y = 0;
        transform.setToIdentity();
    }

    public void addEmitter(){
        ParticleEmitter emitter = new ParticleEmitter(-200,0, 5);
        emitterList.add(emitter);
    }
        
    public ArrayList getEmitterList(){
        return emitterList;
    }
        
    
    
     private double originRandomizer(){
         Random rand = new Random();
         int min = -900;
         int max = 900;
         double randomNum = rand.nextInt(max - min + 1) + min;
         return randomNum;
     }
     
    @Override
        public void paint(final Graphics gr) {
        
        Graphics2D g = (Graphics2D) gr;
        super.paint(g);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        final AffineTransform at = g.getTransform();
        g.transform(transform);
        
        
        for (int i = 0; i < emitterList.size(); i++){
            ParticleEmitter emitter = (ParticleEmitter) emitterList.get(i);
            g.setPaint(Color.RED);
            g.fillOval((int)emitter.getOriginX(),(int) emitter.getOriginY() , 30, 30);
            emitter.recalculate(g);
            
        }
        
        g.setTransform(transform);
        g.dispose();
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Viewport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public double lengthRandomizer() {
        Random rand = new Random();
        double randomNum = rand.nextInt(100) + 1;
        return randomNum;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public AffineTransform getTransform() {
        return transform;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
