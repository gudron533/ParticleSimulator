/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
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
    
    private List<Emitter> emitters = new ArrayList<>();
    
    private Rectangle.Double bounds = new Rectangle.Double(0, 0, 0, 0);
    
    private List<BlackHole> blackHoles = new ArrayList<>();
    
    private List<WhiteHole> whitekHoles = new ArrayList<>();
    
    private List<Fan> fans = new ArrayList<>();
    
    private List<Wall> walls = new ArrayList<>();
    
    private double t = 0.0;
    
    private AffineTransform transform = new AffineTransform();
    
    private Wall selectedWall;
    
    private Emitter selectedEmitter;
    
    private BlackHole selectedBH;
    
    private WhiteHole selectedWH;
    
    private Fan selectedFan;
    
    public void addItem(SceneItem item) {
        synchronized(items) {
            if (!items.contains(item)) {
                items.add(item);
            }
        } 
        if (item.getScene() != this) {
            item.setScene(this);
        }
        // correct scene's bounds if necessary
        getBounds().createUnion(item.getBounds());
        if (item instanceof ForceSource && !forces.contains((ForceSource) item)) {
            forces.add((ForceSource) item);
        }
        
        if (item instanceof Emitter && !emitters.contains((Emitter) item)) {
            emitters.add((Emitter) item);
        }
        
        if (item instanceof Wall && !walls.contains((Wall) item)) {
            walls.add((Wall) item);
        }
        
        if (item instanceof Fan && !fans.contains((Fan) item)) {
            fans.add((Fan) item);
        }
        
        if (item instanceof WhiteHole && !whitekHoles.contains((WhiteHole) item)) {
            whitekHoles.add((WhiteHole) item);
        }
        
        if (item instanceof BlackHole && !blackHoles.contains((BlackHole) item)) {
            blackHoles.add((BlackHole) item);
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
   
    
    //// EDITED THIS SPLIT INTO SEVERAL METHODS HOPE YOU LIKEY LIKEY
    public boolean checkForPointInWallList(Point2D p) {
        for (Wall w : walls) {
            if (w.getBounds().contains(p)) {
                w.setColor(Color.WHITE.darker().darker());
                selectedWall = w;
                return true;
            } else {
                w.setColor(Color.WHITE);
            }
            
        }
        return false;
    }
     
    public boolean checkForBlackHole(Point2D p){
        for (BlackHole b : blackHoles){
            if (b.getBounds().contains(p)) {
                b.setColor(Color.GRAY.darker().darker());
                selectedBH = b;
                return true;
            } else {
                b.setColor(Color.BLACK);
            }            
        }
        return false;
    }
        
    public boolean checkForWhiteHole(Point2D p){ 
        for (WhiteHole wh : whitekHoles) {
            if (wh.getBounds().contains(p)) {
                wh.setColor(Color.WHITE.darker());
                selectedWH = wh;
                return true;
            } else {
                wh.setColor(Color.WHITE);
            }
        }
        return false;
}
    
    public boolean checkForFan(Point2D p){
        for (Fan fa : fans) {
            if(fa.getBounds().contains(p)) {
                fa.setColor(Color.BLUE.darker());
                selectedFan = fa;
                return true;
            } else {
                fa.setColor(Color.BLUE);
            }
        }
        return false;
    }
    
    public boolean checkForEmitter(Point2D p){
        for (Emitter e : emitters) {
            if (e.getBounds().contains(p)) {
                e.setColor(Color.WHITE);
                selectedEmitter = e;
                return true;
            } else {
                e.setColor(Color.RED);
            }
        }
        return false;
    }
    

    public Wall getSelectedWall() {
        return selectedWall;
    }
    
    public BlackHole getBH(){
    return selectedBH;
    }
    
    public WhiteHole getWH(){
        return selectedWH;
    }
    
    public Fan getFan(){
        return selectedFan;
    }
    
    public Emitter getEmitter(){
        return selectedEmitter;
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
    
    public void checkWall(Particle p) {
        boolean result = false;
        for ( Wall w : walls ) {
            result = result || w.isParticleAffected(p);
            if (result) {
                p.reflectfromTheWall(w);
                break;
            }
       }
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
            if (fs instanceof TargetHole) {
                TargetHole th = (TargetHole) fs;
                result = result || th.isParticleAffected(p);
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
    
    public void setTransform(AffineTransform t) {

        transform = t;
    }

    public void paint(Graphics2D g) {
        
        
            
        
        synchronized(items) {
            
        for (int i = 0; i < items.size(); i++) {
            AffineTransform at = g.getTransform();
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.transform(transform);

            items.get(i).paint(g);
            
            g.setTransform(at);
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
                if (p.checkForDestruction()) {
                    items.remove(p);
                }
                checkWall(p);
            }
            
        }
    }


    public Rectangle.Double getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle.Double bounds) {
        this.bounds = bounds;
    }
  
}
