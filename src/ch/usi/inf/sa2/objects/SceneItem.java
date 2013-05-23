/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author ruslan.seyidov
 */
public class SceneItem {
    private Point2D.Double point;
    
    private Point2D.Double point2;
    
    private Scene scene;
    
    private Rectangle.Double bounds = new Rectangle.Double(0, 0, 0, 0);

    
    public SceneItem(double x, double y) {
        this.point = new Point2D.Double(x, y);
        this.bounds.setRect(x, y, 0.0, 0.0);
    }
    
    public SceneItem(double x1, double y1, double x2, double y2){
        this.point = new Point2D.Double(x1, y1);
        this.point2 = new Point2D.Double(x2, y2);
    }
    
    public SceneItem() {
        
    }
    
    /**
     * implements a step by time
     */
    public  void makeStep(double dt) {
    }

    /**
     * @return the x
     */
    public double getX() {
        return point.getX();
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        if (point == null) {
            point = new Point2D.Double();
        }    
        this.point.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return point.getY();
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        if (point == null) {
            point = new Point2D.Double();
        }    
        this.point.y = y;
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
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
    
    public Point2D getPoint() {
        return point;
    }
    
    public void setPoint(Point2D.Double p) {
        this.point = p;
    }
    
    public void paint(Graphics2D g) {
        
    }
    
    
    
}
