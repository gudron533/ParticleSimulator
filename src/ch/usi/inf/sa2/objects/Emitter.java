/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import sun.awt.RepaintArea;

/**
 *
 * @author ruslan.seyidov
 */
public class Emitter extends SceneItem{
    
    private double creationRate;
    
    private RandomValue v = new RandomValue(0, 300);
    
    private RandomValue azimuth =  new RandomValue(0,360);
    
    private RandomValue mass = new RandomValue(20000, 50000);
    
    private RandomValue size = new RandomValue(10, 35);
    
    private RandomValue lifespan = new RandomValue(0, 10);
    
    private double t = 0.0;
    
    private Color color = Color.RED;
    
    Rectangle2D.Double re;
    public Emitter(double x, double y) {
        super(x,y);
        re = new Rectangle2D.Double(x-10, y-10,20,20);
        this.setBounds(re);
    }
    
    /**
     * @return the creationRate
     */
    public double getCreationRate() {
        return creationRate;
    }

    /**
     * @param creationRate the creationRate to set
     */
    public void setCreationRate(double creationRate) {
        this.creationRate = creationRate;
    }
    
    public double getMinLifespan() {
        return lifespan.getMin();
    }

    
    public double getMaxLifespan() {
        return lifespan.getMax();
    }
    
    public void setMinLifespan(double val) {
        lifespan.setMin(val);
    }

    public void setMaxLifespan(double val) {
        lifespan.setMax(val);
    }
    
    /**
     * @return the minV
     */
    public double getMinV() {
        return v.getMin();
    }
    

    /**
     * @param minV the minV to set
     */
    public void setMinV(double minV) {
        this.v.setMin(minV);
    }

    /**
     * @return the maxV
     */
    public double getMaxV() {
        return v.getMax();
    }

    /**
     * @param maxV the maxV to set
     */
    public void setMaxV(double maxV) {
        this.v.setMax(maxV);
    }

    /**
     * @return the minAzimuth
     */
    public double getMinAzimuth() {
        return azimuth.getMin();
    }

    /**
     * @param minAzimuth the minAzimuth to set
     */
    public void setMinAzimuth(double minAzimuth) {
        this.azimuth.setMin(minAzimuth);
    }

    /**
     * @return the maxAzimuth
     */
    public double getMaxAzimuth() {
        return azimuth.getMax();
    }

    /**
     * @param maxAzimuth the maxAzimuth to set
     */
    public void setMaxAzimuth(double maxAzimuth) {
        this.azimuth.setMax(maxAzimuth);
    }

    /**
     * @return the minMass
     */
    public double getMinMass() {
        return mass.getMin();
    }

    /**
     * @param minMass the minMass to set
     */
    public void setMinMass(double minMass) {
        this.mass.setMin(minMass);
    }

    /**
     * @return the maxMass
     */
    public double getMaxMass() {
        return mass.getMax();
    }

    /**
     * @param maxMass the maxMass to set
     */
    public void setMaxMass(double maxMass) {
        this.mass.setMax(maxMass);
    }

    /**
     * @return the minSize
     */
    public double getMinSize() {
        return size.getMin();
    }

    /**
     * @param minSize the minSize to set
     */
    public void setMinSize(double minSize) {
        this.size.setMin(minSize);
    }

    /**
     * @return the maxSize
     */
    public double getMaxSize() {
        return size.getMax();
    }

    /**
     * @param maxSize the maxSize to set
     */
    public void setMaxSize(double maxSize) {
        this.size.setMax(maxSize);
    }
    
    public Particle generateParticle() {
        Particle res = new Particle(this.getX(), this.getY()-5);
        res.setSize(size.getRandomValue());
        res.setMass(mass.getRandomValue());
        res.setLifespan(lifespan.getRandomValue());
        double part_v = v.getRandomValue();
        double part_azimuth = azimuth.getRandomValue();
        res.getV().setX(part_v * Math.cos(Math.PI * part_azimuth / 180 ));
        res.getV().setY(part_v * Math.sin(Math.PI * part_azimuth / 180 ));
        return res;
    } 
    
    
    @Override
    public void makeStep(double dt) {
        t += dt;
        long count = (long) (t *creationRate);
        if (count > 0) {
            t = 0.0d;
        }
        while (count > 0) {
            this.getScene().addItem(generateParticle());
            count--;
        }
        
        
    }
    
    public void emitterBoundUpdatePosition(double x, double y) {
        re.setRect(x-10, y-10, 20, 20);
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public void paint(Graphics2D g) {
        double r = (getMinSize() + getMaxSize())*3;
        Ellipse2D el = new Ellipse2D.Double(getX() - r/2, getY() - r/2, r, r);
        
        g.setColor(color);
        g.fill(el);

        g.setColor(Color.BLACK);

        g.draw(re);
        
    }
    
}
