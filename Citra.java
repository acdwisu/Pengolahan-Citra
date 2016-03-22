import java.awt.Color;
import java.awt.image.BufferedImage;
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
public class Citra {
    private BufferedImage citra=null;
    public static boolean manipulated;
    
    public Citra(java.io.File file) {
        try {
            citra=javax.imageio.ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(Citra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Citra(BufferedImage citra) {
        this.citra = citra;
    }
    
    public void bersihkan() {
        this.citra = null;
    }
    
    public void setCitra(BufferedImage citra) {
        this.citra = citra;
    }
    
    public void setCitra(String citra) {
        try {
            this.citra = javax.imageio.ImageIO.read(new java.io.File(citra));
        } catch (IOException ex) {
            Logger.getLogger(Citra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getCitra() {
        return citra;
    }
}
