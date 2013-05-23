package ch.usi.inf.sa2.CookieCrumbles;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CookieCrumblesMain extends JFrame {
    

    private static final Logger LOG = Logger.getLogger("Viewport");
    private static final DecimalFormat DF = (DecimalFormat) DecimalFormat.getInstance();
    private AffineTransform originalTransform;
    private Point2D initPos;
    private int  posX;
    private int posY;
    

    public CookieCrumblesMain() {
        System.setProperty("sun.java2d.opengl","True");
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        translation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        zoom = new javax.swing.JTextField();
        viewport = new ch.usi.inf.sa2.CookieCrumbles.Viewport();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Translation:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        translation.setEditable(false);
        translation.setText("0, 0");
        translation.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 186;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 6, 0);
        getContentPane().add(translation, gridBagConstraints);

        jLabel2.setText("Zoom:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        zoom.setEditable(false);
        zoom.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 186;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 6, 0);
        getContentPane().add(zoom, gridBagConstraints);

        viewport.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                viewportMouseWheelMoved(evt);
            }
        });
        viewport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewportMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewportMouseClicked(evt);
            }
        });
        viewport.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                viewportMouseDragged(evt);
            }
        });
        viewport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 700;
        gridBagConstraints.ipady = 437;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        getContentPane().add(viewport, gridBagConstraints);

        jButton1.setText("Add Emitter");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        getContentPane().add(jButton1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewportMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMouseDragged
        try {
            Point2D currentPos = originalTransform.inverseTransform(evt.getPoint(), null);
            double x1 = currentPos.getX();
            double y1 = currentPos.getY();
            double x2 = initPos.getX();
            double y2 = initPos.getY();
            double transform_x = x1 - x2;
            double transform_y = y1 - y2;
            initPos = currentPos;
            viewport.getTransform().translate(transform_x, transform_y);
            viewport.repaint();
            int print_x = (int) viewport.getTransform().getTranslateX();
            int print_y = (int) viewport.getTransform().getTranslateY();
            translation.setText("" + print_x + "," + print_y);
            
            ArrayList emitterList = viewport.getEmitterList();
            for ( int i = 0; i < emitterList.size(); i++){
                ParticleEmitter emitter = (ParticleEmitter) emitterList.get(i);
                Rectangle boundingBox = emitter.getBoundingBox();
                
                if (boundingBox.contains(x2 , y2 )){
                    System.out.println("Mouse over emitter");
                    double originalX = emitter.getOriginX();
                    double originalY = emitter.getOriginY();
                    double hix = evt.getX();
                    double hiy = evt.getY();
                    repaint();
                    double updatedX = originalX + hix - posX;
                    double updatedY = originalY + hiy - posY;
                    
                   
                    Rectangle updatedPos = new Rectangle((int) updatedX, (int) updatedY, 35, 35);
                    emitter.setBoundingBox(updatedPos);
                    
                    
                    emitter.setOriginX( updatedX);
                    emitter.setOriginY( updatedY);
                    posX = (int) updatedX ;
                    posY = (int) updatedY;
                    repaint();
                    
                    
                }}
        } catch (NoninvertibleTransformException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewportMouseDragged

    private void viewportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMousePressed
        try {
            initPos = viewport.getTransform().inverseTransform(evt.getPoint(), null);
            originalTransform = (AffineTransform) viewport.getTransform().clone();
            posX = evt.getX();
            posY = evt.getY();
            
            
                
                
                
            }
         catch (NoninvertibleTransformException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(Level.SEVERE, null, ex);
        
         }
    }//GEN-LAST:event_viewportMousePressed

    private void viewportMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_viewportMouseWheelMoved
        try {
            double scalefactor = Math.pow(2, -evt.getPreciseWheelRotation() / 10);
            Point2D mouse = viewport.getTransform().inverseTransform(evt.getPoint(), null);
            double x1 = mouse.getX();
            double y1 = mouse.getY();
            viewport.getTransform().translate(x1, y1);
            viewport.getTransform().scale(scalefactor, scalefactor);
            viewport.getTransform().translate(-x1, -y1);
            viewport.repaint();
            double print_scale = viewport.getTransform().getScaleY();
            zoom.setText("" + DF.format(print_scale));
            

        } catch (NoninvertibleTransformException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_viewportMouseWheelMoved

    private void viewportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMouseClicked
        DoubleManipulator da = new DoubleManipulator();
        da.setDefaultCloseOperation(da.DISPOSE_ON_CLOSE);
        if (SwingUtilities.isRightMouseButton(evt)) {
            int x = evt.getX();
            int y = evt.getY();
            System.out.println(x);
            System.out.println(y);

            da.setVisible(true);
            da.setLocation(x, y);
        } else {
            da.setVisible(false);
        }

    }//GEN-LAST:event_viewportMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        viewport.addEmitter();
    }//GEN-LAST:event_jButton1MouseClicked

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CookieCrumblesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CookieCrumblesMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField translation;
    private ch.usi.inf.sa2.CookieCrumbles.Viewport viewport;
    private javax.swing.JTextField zoom;
    // End of variables declaration//GEN-END:variables
}
