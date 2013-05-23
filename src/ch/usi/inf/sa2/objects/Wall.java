/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author ruslan.seyidov
 */
public class Wall extends SceneItem{
    
     private Point2D.Double pointA = new Point2D.Double();
     
     private Point2D.Double pointB = new Point2D.Double();
     
     private Vector2D n = new Vector2D();
     
     private Vector2D tau = new Vector2D();
     
     private Color color = Color.WHITE;
     
     //private double width = 0.01;
     
     
     public Wall(Point2D.Double a, Point2D.Double b) {
         super(a.getX(), a.getY());
         this.pointA = a;
         this.pointB = b;
         Rectangle2D.Double r = new Rectangle2D.Double(a.x, a.y, b.x - a.x, b.y - a.y);
         
         this.setBounds(r);
         calcVectors();
     }
     
     private void calcVectors() {
         double ab = getPoint().distance(getPointB());
         Point2D a = getPoint();
         Point2D.Double b = getPointB();
         getN().setX((b.getY() - a.getY()) / ab);
         getN().setY((a.getX() - b.getX()) / ab);
         
         getTau().setX((b.getX() - a.getX()) / ab);
         getTau().setY((b.getY() - a.getY()) / ab);
         
     }
     
     public boolean isParticleAffected(Particle p) {
         double ab = getPoint().distance(getPointB());
         Vector2D x = new Vector2D(getPoint(), p.getPoint());
         // projection of the particle location vector to a Wall
         double tauProj = x.dot(getTau()); //(x-xa)*tau
         boolean result = (tauProj >= 0) && (tauProj <= ab); //the particle belongs to this segment 
         
         double normalProj = Math.abs(x.dot(getN())); //should be zero for a collision to happen
         
         result = result && (normalProj <= (p.getSize() / 2));
         return result;
         
     }

     /**
     * @return the pointB
     */
    public Point2D.Double getPointA() {
        return pointA;
    }
    
    /**
     * @return the pointB
     */
    public Point2D.Double getPointB() {
        return pointB;
    }

    /**
     * @param pointB the pointB to set
     */
    public void setPointB(Point2D.Double pointB) {
        this.pointB = pointB;
        calcVectors();
    }
    
     @Override
    public void setPoint(Point2D.Double point) {
         super.setPoint(point);
         calcVectors();
    }

    /**
     * @return the n
     */
    public Vector2D getN() {
        return n;
    }

    /**
     * @return the tau
     */
    public Vector2D getTau() {
        return tau;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
     /**
     * Paints a wall
     * @param g
     */
    @Override
    public void paint(Graphics2D g) {
        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke(0.1f));
        g.setColor(color);
        g.draw(new Line2D.Double(getPoint(), pointB));
        g.setStroke(oldStroke);        
        
    }

    
}
