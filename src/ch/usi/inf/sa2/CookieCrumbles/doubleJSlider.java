package ch.usi.inf.sa2.CookieCrumbles;

// this is a comment 
import javax.swing.*;

/**
 *
 * @author Alex M
 *
 *
 */
public final class doubleJSlider extends JSlider {

    static final double FLOAT_MINIMUM = 0.0;
    static final double FLOAT_MAXIMUM = 100.0;
    static final double FLOAT_MIDDLE = 50.0;
    static final int PRECISION_MULTIPLIER = 100;

    public doubleJSlider() {
        super();
        setDoubleMinimum(FLOAT_MINIMUM);
        setDoubleMaximum(FLOAT_MAXIMUM);
        setDoubleValue(FLOAT_MIDDLE);
    }

    /**
     * Constructor
     */
    public doubleJSlider(double min, double max, double val) {
        super();
        setDoubleMinimum(min);
        setDoubleMaximum(max);
        setDoubleValue(val);
    }

    /**
     * returns Maximum in float precision
     */
    public double getDoubleMaximum() {
        return (getMaximum() / FLOAT_MAXIMUM);
    }

    /**
     * returns Minimum in float precision
     */
    public double getDoubleMinimum() {
        return (getMinimum() / FLOAT_MAXIMUM);
    }

    /**
     * returns Value in float precision
     */
    public double getDoubleValue() {
        return (getValue() / FLOAT_MAXIMUM);
    }

    /**
     * sets Maximum in float precision
     */
    public void setDoubleMaximum(double max) {
        setMaximum((int) (max * PRECISION_MULTIPLIER));
    }

    /**
     * sets Minimum in float precision
     */
    public void setDoubleMinimum(double min) {
        setMinimum((int) (min * PRECISION_MULTIPLIER));
    }

    /**
     * sets Value in float precision
     */
    public void setDoubleValue(double val) {
        setValue((int) (val * PRECISION_MULTIPLIER));
        setToolTipText("" + val);
    }
}
