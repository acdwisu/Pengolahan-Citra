
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */
public class TampilCitra { //extends JPanel
    private javax.swing.ImageIcon citra;
    
    public TampilCitra(java.io.File pathCitra) {
        boolean gif = pathCitra.toString().endsWith(".gif");
//        System.out.println(gif);
        try {
            if(!gif)
                this.citra = new javax.swing.ImageIcon(javax.imageio.ImageIO.read(pathCitra));
            else {                
                this.citra = new javax.swing.ImageIcon(pathCitra.toString()) ;
            }
        } catch (IOException ex) {
            Logger.getLogger(TampilCitra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TampilCitra(java.awt.image.BufferedImage citra) {
        this.citra = new javax.swing.ImageIcon(citra);
    }
    
    public javax.swing.ImageIcon getImage() {
        return citra;
    }
    /*
    private  java.awt.Image citra;
    public tampilCitra(java.io.File pathGambar) {
        citra = new javax.swing.ImageIcon(pathGambar.getPath()).getImage();
    }
    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);
        g.drawImage(citra, 0, 0, this);
    }*/
}
