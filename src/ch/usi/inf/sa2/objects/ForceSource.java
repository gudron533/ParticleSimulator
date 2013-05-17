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
public interface ForceSource {
    Vector2D getForce(Particle x);
    
}
