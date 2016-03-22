
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */

public class Grayscale extends Proses {
    
    public Grayscale() {
    }

    @Override
    public void proses(BufferedImage imgAsli) {
        citra = new BufferedImage(imgAsli.getWidth(), imgAsli.getHeight()
            , BufferedImage.TYPE_INT_RGB);
        
        for(short i=0; i<imgAsli.getHeight(); i++) {
            for(short j=0; j<imgAsli.getWidth(); j++) {
                java.awt.Color warnaAsli = new java.awt.Color(imgAsli.getRGB(j, i));
                int gray = (warnaAsli.getRed() + warnaAsli.getGreen() + warnaAsli.getBlue())/3;
                int warnaGray = (gray << 16) + (gray << 8) + gray;
                citra.setRGB(j, i, warnaGray);
            }
        }
        Citra.manipulated = true;
    }
    @Override
    public void bersihkan() {
        super.bersihkan();
    }
}
