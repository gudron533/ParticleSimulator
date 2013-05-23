/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.usi.inf.sa2.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author PsyXandeR
 */
public class jBackgroundPanel extends JPanel{
    BufferedImage bg;
    
    public jBackgroundPanel(){
        try {
            bg = ImageIO.read(new File("img/menu.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(jBackgroundPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
         super.paintComponent(g);
         g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
