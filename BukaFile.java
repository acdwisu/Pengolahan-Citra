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
public class BukaFile extends OlahFile {

    public BukaFile() {
        pilihFile.setDialogTitle("Open an image file");  
        
        try {
            if(javax.swing.JFileChooser.APPROVE_OPTION == pilihFile.showOpenDialog(null)) {
                gambar = pilihFile.getSelectedFile();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            throw t;
        }
    }
    
    public java.io.File getFile() {
        return gambar;
    }
}
