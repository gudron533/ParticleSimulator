/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.geom.Point2D;

/**
 *
 * @author ruslan.seyidov
 */
public class Vector2D {
    
    private double[] vector = new double[2];
    
    public Vector2D() {
        
    }
    
    public Vector2D(double x, double y) {
        this.vector[0] = x;
        this.vector[1] = y;
    }
    
    public Vector2D(Point2D a, Point2D b) {
        this.vector[0] = b.getX() - a.getX();
        this.vector[1] = b.getY() - a.getY();
    }
    
    

    /**
     * @return the vector
     */
    public double[] getVector() {
        return vector;
    }

    /**
     * @param vector the vector to set
     */
    public void setVector(double[] vector) {
        this.vector = vector;
    }
    
    public void setX(double x) {
        vector[0] = x;
    }
    
    public double getX() {
        return vector[0];
    }

    public void setY(double y) {
        vector[1] = y;
    }
    
    public double getY() {
        return vector[1];
    }
    
}
