/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author ruslanseyidov
 */
public class Fan extends SceneItem implements ForceSource{

    
    private double power;
    
    private double size;
    private double get;
    
    
    public Fan(double x, double y) {
        super(x, y);
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return power;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        this.power = mass;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return size;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.size = radius;
    }

    @Override
    public Vector2D getForce(Particle p) {
        
        double distance = this.getPoint().distance(p.getPoint());
        Vector2D vector = new Vector2D(p.getPoint(), this.getPoint());
        double G = Scene.G;
        if(p.getPoint().getY() <= this.getPoint().getY()+0.01 && p.getPoint().getX() >= this.getPoint().getX() - 0.5 && p.getPoint().getX() <= this.getPoint().getX() + 0.5){
        double forceFan = power;
        double force = ((G - forceFan) * p.getMass());// - forceFan*p.getMass());
        double force2 = Scene.G * power * p.getMass()/distance/distance;
        double distsq = Math.sqrt(distance);

        vector.setX(vector.getX() / distsq * force);
        vector.setY(vector.getY() / distsq * force);
        return vector;}
        else {
            double force = Scene.G * power * p.getMass()/distance/distance;
            double distsq = Math.sqrt(distance);

            vector.setX(vector.getX() / distsq * force);
            vector.setY(vector.getY() / distsq * force);
            return vector;
        }
    }
    
    public boolean isParticleAffected(Particle p) {
        double s = this.getPoint().distance(p.getPoint());
        return (s < size);
    }  
   
    @Override
    public void paint(Graphics2D g ){
        //Ellipse2D el = new Ellipse2D.Double(getX() - radius, getY() - radius, 2* radius, 2 * radius);
        Rectangle2D rect = new Rectangle2D.Double(getX()-size/4, getY(), size, size/4);
        g.setPaint(new Color(61, 197, 255));
        g.fill(rect);
    }
    

    
}

