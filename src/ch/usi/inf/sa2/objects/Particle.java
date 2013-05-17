/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;
import ch.usi.inf.sa2.objects.LoadImageParticle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *    //// virtual timer, Save/Load
 * @author ruslan.seyidov
 */
public class Particle extends SceneItem{
    
    private Vector2D v = new Vector2D(0.0, 0.0);
    
    
    private Vector2D force = new Vector2D();
    
    
    private double mass;
    
    private double lifespan;
    
    private double size;
    
    private Color color;
    
    private double t = 0.0d; //lifetime
    
    public boolean stop;
    

    public Particle(double x, double y) {
        super(x, y);
    }

    /**
     * @return the v
     */
    public Vector2D getV() {
        return v;
    }

    /**
     * @param v the v to set
     */
    public void setV(Vector2D v) {
        this.v = v;
    }

    /**
     * @return the force
     */
    public Vector2D getForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(Vector2D force) {
        this.force = force;
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * @return the lifespan
     */
    public double getLifespan() {
        return lifespan;
    }

    /**
     * @param lifespan the lifespan to set
     */
    public void setLifespan(double lifespan) {
        this.lifespan = lifespan;
    }

    /**
     * @return the size
     */
    public double getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

     
    @Override
    
    public void makeStep(double dt) {
        t += dt;
        Vector2D f = getScene().getForce(this);
        double ax = f.getX() / mass;
        double ay = f.getY() / mass;
        // average speed on step is used to improve precision
        double vx = v.getX() + ax * dt / 2;
        double vy = v.getY() + ay * dt / 2;
        
        setX(getX() + vx * dt);
        setY(getY() + vy * dt);
        v.setX(v.getX() + ax * dt);
        v.setY(v.getY() + ay * dt);
        //check for a black hole
    }

        
    
    
    public boolean checkForDestruction() {
        return (t >= lifespan || getScene().blackHoleisNear(this));
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics2D g) {
        //Rectangle2D rect = new Rectangle2D.Double(getX(), getY(), getSize(), getSize()/4);
        Ellipse2D el = new Ellipse2D.Double(getX(), getY(), getSize(), getSize());
        //LoadImageParticle li = new LoadImageParticle();
        g.setPaint(getColor());
        if(size<0.3){
        g.setPaint(new Color(0, 0, 51));
        }
        if(size<0.25){
        g.setPaint(new Color(173, 173, 173));
        }
        if(size<0.2){
        g.setPaint(new Color(186, 186, 186));
        }
        if(size<0.15){
        g.setPaint(new Color(199, 199, 199));
        }
        if(size<0.1){
        g.setPaint(new Color(212, 212, 212));
        }
        if(size<0.06){
        g.setPaint(new Color(224, 224, 224));
        }
        if(size<0.03){
        g.setPaint(new Color(237, 237, 237));
        }
        
        
        g.fill(el);
    }
    
}