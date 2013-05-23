/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
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
    private Color color = Color.BLUE;
    private static Rectangle2D shape;
    int[] Polygonx = new int[4];
    int[] Polygony = new int[4];
    Rectangle2D.Double r;
    
    public Fan(double x, double y) {
        super(x, y);
        r = new Rectangle2D.Double(x-25, y-5,50,25);
        this.setBounds(r);
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
        if (this.getPolygon().contains(p.getPoint())) {
        double forceFan = power;
        //double force1 = ((Scene.G - forceFan) * p.getMass()/distance);// - forceFan*p.getMass());
        //double force = Scene.G;
        //System.out.println("bl");
        double force2 = Scene.G - forceFan * p.getMass()/distance/distance;
        double distsq = Math.sqrt(distance);
        vector.setX(vector.getX() / distsq * force2);
        vector.setY(vector.getY() / distsq * force2);
        return vector;
        } else {
            double force = Scene.G * p.getMass()/distance/distance;
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
    
    public void setPolygonPoints(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Polygonx[0] = x1;
        Polygony[0] = y1;
        Polygonx[1] = x2;
        Polygony[1] = y2;
        Polygonx[2] = x3;
        Polygony[2] = y3;
        Polygonx[3] = x4;
        Polygony[3] = y4;
    }
    
    public Polygon getPolygon() {
        Polygon pl = new Polygon(Polygonx, Polygony, 4);
        return pl;
    }
    
    public Rectangle2D getShape() {
        return shape;
    }
    
    public void setColor(Color color) {
        this.color = color;
    } 
    
    @Override
    public void paint(Graphics2D g) {
        Polygon pl = new Polygon(Polygonx, Polygony, 4);
        Rectangle2D rect = new Rectangle2D.Double(getX()-size/2, getY(), size, size/3);
        shape = rect;
        g.setColor(color);
        g.draw(pl);
        g.fill(rect);
        g.draw(r);
    }
}

