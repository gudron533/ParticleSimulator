/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * A scene contains all elements which are visible (particles, emitters etc)
 * @author ruslan.seyidov
 */
public class Scene {
    
    public static double G = 6.674e-11;
    
    public static double g = 9.8;
    
    private final List<SceneItem> items = Collections.synchronizedList(new ArrayList<SceneItem>());
    
    private List<ForceSource> forces = new ArrayList<>();
    
    private Rectangle.Double bounds = new Rectangle.Double(0, 0, 0, 0);
    
    private double t = 0.0;
    
    private AffineTransform transform = new AffineTransform();
    
    public void addItem(SceneItem item) {
        synchronized(items) {
            items.add(item);
        }    
        item.setScene(this);
        // correct scene's bounds if necessary
        getBounds().createUnion(item.getBounds());
        if (item instanceof ForceSource) {
            forces.add((ForceSource) item);
        }
    }
    
    public void removeItem(SceneItem item) {
        synchronized(items) {
            items.remove(item);
        }
        if (item instanceof ForceSource) {
            forces.remove((ForceSource) item);
        }
        item.setScene(null);
    }
    
    public Vector2D getForce(Particle p) {
        double fx = 0;
        double fy = g * p.getMass();
        for (ForceSource fs : forces) {
            Vector2D fsr = fs.getForce(p);
            fx += fsr.getX();
            fy += fsr.getY();
        }
        return new Vector2D(fx, fy);
    }
    
    public boolean blackHoleisNear(Particle p) {
        boolean result = false;
        for (ForceSource fs : forces) {
            if (fs instanceof BlackHole) {
                BlackHole bh = (BlackHole) fs;
                result = result || bh.isParticleAffected(p);
                if (result) {
                    break;
                }
            }
        }
        
        return result;
    }
    
    public AffineTransform getTransform() {
    return transform;
  }
    
    /**
     *
     * @param g
     */
    public void paint(Graphics2D g) {
        synchronized(items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).paint(g);
        }
        }
    }
    
    public void makeStep(double dt) {
        t += dt;
        List<SceneItem> tmp = new ArrayList<>(items);
        
        Iterator<SceneItem> si = tmp.iterator();
        while (si.hasNext()) {
            SceneItem it = si.next();
            it.makeStep(dt);
            if (it instanceof Particle) {
                Particle p = (Particle) it;
                // check if the particle is to be destroyed
                if (p.checkForDestruction()) {
                    items.remove(p);
                }
            }
            
        }
    }

    /**
     * @return the bounds
     */
    public Rectangle.Double getBounds() {
        return bounds;
    }

    /**
     * @param bounds the bounds to set
     */
    public void setBounds(Rectangle.Double bounds) {
        this.bounds = bounds;
    }
    
}
