/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author ruslan.seyidov
 */
public class WhiteHole extends SceneItem implements ForceSource{
    
    private double mass;
    
    private double radius;
    
    
    public WhiteHole(double x, double y) {
        super(x, y);
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
        this.mass = -mass;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Vector2D getForce(Particle p) {
        double distance = this.getPoint().distance(p.getPoint());
        Vector2D vector = new Vector2D(p.getPoint(), this.getPoint());
        double force = Scene.G * mass * p.getMass()/distance/distance;
        double distsq = Math.sqrt(distance);
        vector.setX(vector.getX() / distsq * force);
        vector.setY(vector.getY() / distsq * force);
        return vector;
    }
    
//    public boolean isParticleAffected(Particle p) {
//        double s = this.getPoint().distance(p.getPoint());
//        return (s < radius);
//    }  
    
    @Override
    public void paint(Graphics2D g ){
        Ellipse2D el = new Ellipse2D.Double(getX() - radius, getY() - radius, 2* radius, 2 * radius);
        g.setPaint(new Color(255, 255, 255));
        
        g.fill(el);
    }
    

    
}
