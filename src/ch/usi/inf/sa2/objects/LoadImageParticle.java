/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.objects;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import javax.imageio.*;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 *
 * @author ruslan.seyidov
 */
public class LoadImageParticle extends JApplet{
static String imageFileName = "img/duke_skateboard.jpg";
    private URL imageSrc;
    private LoadImageParticle jumbledImage;
 
    public LoadImageParticle () {
    }
 
    public LoadImageParticle (URL imageSrc) {
        this.imageSrc = imageSrc;
    }
 
    public void init() {
        try {
            imageSrc = new URL(getCodeBase(), imageFileName);
        } catch (MalformedURLException e) {
        }
    }
      
   
 
    public static void main(String s[]) {
        JFrame f = new JFrame("Jumbled Image");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        URL imageSrc = null;
        try {
             imageSrc = ((new File(imageFileName)).toURI()).toURL();
        } catch (MalformedURLException e) {
        }
        LoadImageParticle jumbler = new LoadImageParticle(imageSrc);
        f.add("Center", jumbler);
        f.pack();
        f.setVisible(true);
    }
    
    
    
    
}

