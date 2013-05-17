/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;

/**
 *
 * @author ruslan.seyidov
 */
public class RandomValue {
    
    private double min;
    
    private double max;
    
    public RandomValue(double min, double max) {
        this.max = max;
        this.min = min;
    }
    
    public double getRandomValue() {
        return getMin() + (getMax() - getMin()) * Math.random();
    }

    /**
     * @return the min
     */
    public double getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(double max) {
        this.max = max;
    }
    
    
}
