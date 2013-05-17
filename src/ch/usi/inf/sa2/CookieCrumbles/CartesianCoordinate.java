/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.CookieCrumbles;

/**
 * Write a description of class CartesianCoordinate here.
 *
 * @author Alexander Mamyshev / R. Seyidov;
 * @version (a version number or a date)
 */
public class CartesianCoordinate {

    private double x;
    private double y;

    public CartesianCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}