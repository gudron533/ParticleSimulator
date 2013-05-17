/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.CookieCrumbles;

import java.awt.Shape;

/**
 *
 * @author R.Seyidov;
 */
public class Particle {

    private double originX;
    private double originY; 
    private CCVector initValues;
     private CCVector positionValueReset;
    private CCVector positionValue;
    private int lifetime;
    Shape particleRect;

    public Particle(double originX, double originY, double x, double y, 
      double posdir, double poslen, double direction, double velocity, int lifetime) {
        this.originX = originX;
        this.originY = originY;
        initValues = new CCVector(new PolarCoordinate(direction, velocity));
        positionValue = new CCVector(new CartesianCoordinate(x, y), new PolarCoordinate(posdir, poslen));
        this.lifetime = lifetime;
        positionValueReset = positionValue;
    }

    public int getLifetime() {
        return lifetime;
    }
    
    public double getEmitterOriginX(){
        return originX;
    }
    
    public void setParticleRect(Shape newRect){
        particleRect = newRect;
        
    }
    
    public Shape getParticleRect(){
        return particleRect;
    }
    
    public void setParticleReset(CCVector reset){
        positionValueReset = reset;
    }
    
    public double getEmitterOriginY(){
        return originY;
    }

    public void setLifetime(int life) {
        lifetime = life;
    }

    public double positionGetX() {
        return positionValue.getX();
    }
    public CCVector getPosition(){
        return positionValue;
    }
    
    public double getVelocity(){
        return initValues.getLength();
    }
    
    
    public void setVelocity(double velocity){
        double azi = initValues.getAzimuth();
        CCVector newInit = new CCVector(new PolarCoordinate(azi, velocity));
        initValues = newInit;
    }
    
    public void setPosition(CCVector vector){
        positionValue = vector;
    }
    
    public CCVector getPositionReset(){
        return positionValueReset;
    }

    public double positionGetY() {
        return positionValue.getY();

    }

    public double velocityGetDirection() {
        return initValues.getAzimuth();
    }

    public void setNewPosition() {

        CCVector pos = initValues.move(positionValue, 1);
        positionValue = pos;

    }
    public void setOriginX(double x){
        originX = x;
    }
    public void setOriginY(double y){
        originY = y;
    }
    
    
}
