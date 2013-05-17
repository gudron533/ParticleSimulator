/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.CookieCrumbles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.sound.sampled.Line;

/**
 *
 * @author alexm
 */
public class CCWall {
    private CCVector wallCoord; 
    private Shape wallShape;
    private Shape wallNorm;
    
    
    
    public CCWall(double x, double y, double length, double direction){
        wallCoord = new CCVector(new CartesianCoordinate(x, y), new PolarCoordinate(direction, length));
         
        
    }
    

    
    public void drawWall(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        double initX = wallCoord.getX();
        double initY = wallCoord.getY();
        double dir = wallCoord.getAzimuth();
        g.setPaint(Color.BLACK);
        double length = wallCoord.getLength();
        double endX = (length * Math.cos(dir))+initX;
        double endY = (length * Math.sin(dir))+initY;
        g.setPaint(Color.BLACK);
        Shape wall = new Line2D.Double( initX,  initY,  endX,  endY );
        wallShape = wall;
        //g.drawLine((int)initX, (int) initY,(int) endX,(int) endY);
        g.draw(wall); 
        if (wallNorm != null){
        g.setPaint(Color.BLACK);
        g.draw(wallNorm);

        }

                

        
         
        
    }
    
    public CCVector getWallCoordinates(){
        return wallCoord;
    }
    
    public Shape getWallShape(){
        return wallShape;
    }
    
    
    
    
    public CCVector calculateReflected(CCVector particlePosition, double newOriginX, 
            double newOriginY){
        
//        double nomitator = particlePosition.dotProduct(wallCoord);
//        double denominator = wallCoord.dotProduct(wallCoord);
//        double fraction = nomitator / denominator;
//        double twoByFrac = 2 * fraction;
//        CCVector scaled1 = wallCoord.scale(twoByFrac);
//        CCVector reflected = scaled1.subtract(particlePosition);
//        double azi = particlePosition.getAzimuth();
//        azi = Math.toDegrees(azi);
//        double aziNeg = (-azi);
//       
//       double newAzi =  Math.toRadians(aziNeg) ;
//
//      reflected.setAzi(newAzi);
        
        /////CREATE NORMAL TO THE WALL
  
        double normalDir = wallCoord.getAzimuth() + 3.14/2;
        CCVector normal  = new CCVector (new CartesianCoordinate(newOriginX, newOriginY),
                new PolarCoordinate(normalDir, 10));
        
        double vDotN = particlePosition.dotProduct(normal);
        double twoTimesDot = 2 * vDotN;
        CCVector scaledNormal = normal.scale(twoTimesDot);
        CCVector reflected = particlePosition.subtract(scaledNormal);
        double partDir = particlePosition.getAzimuth();
        double azimuth = 2*wallCoord.getAzimuth() - partDir;
        reflected.setAzi(azimuth);
        double length = normal.getLength();
        double endX = (length * Math.cos(normalDir))+normal.getX();
        double endY = (length * Math.sin(normalDir))+normal.getY();
        wallNorm = new Line2D.Double( normal.getX(),  normal.getY(),  endX,  endY );
        
        
        //System.out.println(""+ reflected.getAzimuth()+","+reflected.getX());
      return reflected;
        
        
        
        
        
        
        
        
        
    }
    
    
        
    
}

