
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */
public class OlahFile {
    protected javax.swing.JFileChooser pilihFile;
    protected java.io.File gambar;
    protected FileNameExtensionFilter filterFile;
    public static String direktoriDefault;
    
    public OlahFile() {
        pilihFile = new javax.swing.JFileChooser(OlahFile.direktoriDefault);
        filterFile = new FileNameExtensionFilter("Image file", "BMP","JPG"
            ,"PNG","bmp","jpg","png","gif");
        pilihFile.setFileFilter(new FilterFile(".jpg", "JPEG file"));
        pilihFile.setFileFilter(new FilterFile(".png", "PNG file"));
        pilihFile.setFileFilter(new FilterFile(".bmp", "Bitmap file"));
        pilihFile.setFileFilter(new FilterFile(".JPG", "JPEG file"));
        pilihFile.setFileFilter(new FilterFile(".PNG", "PNG file"));
        pilihFile.setFileFilter(new FilterFile(".BMP", "Bitmap file"));
        pilihFile.setFileFilter(new FilterFile(".gif", "GIF file"));
        pilihFile.setAcceptAllFileFilterUsed(false);
        pilihFile.setFileFilter(filterFile);
    }
    
}
